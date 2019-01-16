package Controller;

import Exceptions.*;
import Model.Expression.ConstExp;
import Model.Statement.CloseRFile;
import Model.ProgramState;
import Model.Utils.Pair;
import Repository.Repo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class InterpreterController
{
    private Repo repo;
    private ExecutorService executor;

    public InterpreterController(Repo repo)
    {
        this.repo = repo;
    }

    private Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues,
                                                      Map<Integer,Integer> heap)
    {
        return heap.entrySet().stream()
                .filter(e->symTableValues.contains(e.getKey()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private void CloseOpenedFiles(ProgramState ps) throws MemoryEx, IOException, InexVarEx, ExprEx, UndeclaredEx, AlreadyOpenedFileEx
    {
        List<Map.Entry<Integer, Pair<String, BufferedReader>>> files = ps.getFileTable().All().entrySet().stream().
                collect(Collectors.toList());

        for(Map.Entry<Integer, Pair<String, BufferedReader>> k : files)
        {
            new CloseRFile(new ConstExp(k.getKey())).execute(ps);
        }
    }

    List<ProgramState> removeCompletedPrg(List<ProgramState> oldList)
    {
        return oldList.stream().filter(ProgramState::isNotCompleted).
                collect(Collectors.toList());
    }

    public void oneStepForAll(List<ProgramState> prgs) throws InterruptedException
    {
        prgs.forEach(prg -> repo.logPrgStateExec(prg));

        List<Callable<ProgramState>> callList = prgs.stream().
                map((ProgramState p) -> (Callable<ProgramState>) (p::oneStep)).
                collect(Collectors.toList());

        List<ProgramState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException("Executor error.");
                    }
                }).filter(Objects::nonNull).collect(Collectors.toList());

        prgs.addAll(newPrgList);

        prgs.forEach(prg -> repo.logPrgStateExec(prg));
        repo.SetList(prgs);
    }

    public void allStep() throws UndeclaredEx, StackEx, ExprEx, IOException, AlreadyOpenedFileEx, InexVarEx, MemoryEx, InterruptedException
    {
        executor = Executors.newFixedThreadPool(2);

        List<ProgramState> prgs = repo.GetList();
        if(repo.GetList() != null)
            prgs = removeCompletedPrg(repo.GetList());
        ProgramState FirstProgramState = prgs.get(0);
        Map<Integer, Integer> heap_map = FirstProgramState.getHeap().getMap();

        while(prgs.size() > 0)
        {
            //fac un symtable cu toate
            prgs.forEach(prg -> FirstProgramState.getHeap().setMap((HashMap<Integer, Integer>)conservativeGarbageCollector(prg.getSymTable().getValues(),
                    heap_map)));
            oneStepForAll(prgs);
            prgs = removeCompletedPrg(repo.GetList());
        }
        executor.shutdownNow();

        repo.SetList(prgs);

        List<ProgramState> tmpList = repo.GetList();
        if(tmpList != null)
            CloseOpenedFiles(FirstProgramState);
    }

    public Repo getRepo()
    {
        return repo;
    }
}
