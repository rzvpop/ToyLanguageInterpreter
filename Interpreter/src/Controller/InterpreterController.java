package Controller;

import Exceptions.*;
import Model.ADT.MyDictionary;
import Model.ADT.MyList;
import Model.ADT.MyStack;
import Model.ProgramState;
import Model.Statement.IStatement;
import Model.Utils.Pair;
import Repository.Repo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class InterpreterController
{
    private Repo repo;

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

    public ProgramState oneStep(ProgramState ps) throws UndeclaredEx, StackEx, ExprEx, IOException, AlreadyOpenedFileEx, InexVarEx, MemoryEx
    {
        MyStack<IStatement> stk = ps.getExeStack();

        if(stk.isEmpty())
        {
            throw new StackEx("Empty stack.");
        }

        IStatement stm = stk.pop();

        return stm.execute(ps);
    }

    public void allStep() throws UndeclaredEx, StackEx, ExprEx, IOException, AlreadyOpenedFileEx, InexVarEx, MemoryEx
    {
        try
        {
            ProgramState ps = repo.getCurrPrg();
            ps.putPrgOnStack();

            while(!ps.getExeStack().isEmpty())
            {
                oneStep(ps);

                Collection<Integer> sym_table_values = ps.getSymTable().getValues();
                Map<Integer, Integer> heap_map = ps.getHeap().getMap();
                ps.getHeap().setMap((HashMap<Integer, Integer>)
                        conservativeGarbageCollector(sym_table_values, heap_map));
                ps.getHeap().writeAddr(0, 0);
                //System.out.println(GetPrgStateStr());
                repo.logPrgStateExec();

            }
        }
        catch (Exception e)
        {
            MyStack<IStatement> exe_stack = repo.getCurrPrg().getExeStack();
            MyDictionary<String, Integer> sym_table = repo.getCurrPrg().getSymTable();
            MyList<Integer> out = repo.getCurrPrg().getOutput();
            MyDictionary<Integer, Pair<String, BufferedReader>> file_table = repo.getCurrPrg().getFileTable();

            exe_stack.clear();
            sym_table.clear();
            out.clear();
            file_table.clear();

            throw e;
        }
    }

    public Repo getRepo()
    {
        return repo;
    }

    public String GetPrgStateStr()
    {
        String str = "";
        ProgramState ps = repo.getCurrPrg();

        MyStack<IStatement> exe_stack = ps.getExeStack();
        MyDictionary<String, Integer> sym_table = ps.getSymTable();
        MyList<Integer> out = ps.getOutput();

        str += "ExeStack:\n";
        str += exe_stack.toString() + "\n";

        str += "Symbol table:\n";
        str += sym_table.toString() + "\n";

        str += "Output:\n";
        str += out.toString() + "\n";

        return str;
    }
}
