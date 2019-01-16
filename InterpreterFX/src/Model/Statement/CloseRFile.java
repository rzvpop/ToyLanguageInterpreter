package Model.Statement;

import Exceptions.*;
import Model.ADT.MyDictionary;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.Heap;
import Model.Utils.Pair;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStatement
{
    private IExpression exp_var_file;

    public CloseRFile(IExpression _exp_var_file)
    {
        this.exp_var_file = _exp_var_file;
    }

    @Override
    public ProgramState execute(ProgramState ps) throws UndeclaredEx, ExprEx, AlreadyOpenedFileEx, InexVarEx, MemoryEx, IOException
    {
        MyDictionary<Integer, Pair<String, BufferedReader>> file_table = ps.getFileTable();
        MyDictionary<String, Integer> sym_table = ps.getSymTable();
        Heap heap = ps.getHeap();

        int id = exp_var_file.evaluate(sym_table, heap);
        if(!file_table.find(id))
        {
            throw new FileNotFoundEx("File not opened.");
        }


        Pair<String, BufferedReader> file = file_table.getValue(id);
        BufferedReader br = file.GetSecond();
        if(br != null)
            br.close();

        file_table.remove(id);

        return null;
    }

    @Override
    public String toString()
    {
        return "CloseRFile(" + exp_var_file.toString() + ")";
    }
}
