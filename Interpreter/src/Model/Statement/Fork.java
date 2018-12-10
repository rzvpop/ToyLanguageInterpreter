package Model.Statement;

import Exceptions.*;
import Model.ADT.MyDictionary;
import Model.ADT.MyList;
import Model.ADT.MyStack;
import Model.ProgramState;
import Model.Utils.Heap;
import Model.Utils.Pair;

import java.io.BufferedReader;

public class Fork implements IStatement
{
    private IStatement stm;

    public Fork(IStatement stm)
    {
        this.stm = stm;
    }

    @Override
    public ProgramState execute(ProgramState ps) throws UndeclaredEx, ExprEx, AlreadyOpenedFileEx, InexVarEx, MemoryEx
    {
        MyStack<IStatement> exe_stack = new MyStack<>(5);
        MyDictionary<String, Integer> sym_table = new MyDictionary<>(ps.getSymTable());
        MyList<Integer> out = ps.getOutput();
        MyDictionary<Integer, Pair<String, BufferedReader>> file_table = ps.getFileTable();
        Heap heap = ps.getHeap();
        ProgramState new_thr = new ProgramState(stm, sym_table, exe_stack, out, file_table, heap);
        new_thr.setId(ps.getId() * 10);

        return new_thr;
    }

    @Override
    public String toString()
    {
        return "fork(" + stm.toString()  + ")";
    }
}
