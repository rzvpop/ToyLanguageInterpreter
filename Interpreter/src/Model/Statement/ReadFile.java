package Model.Statement;

import Exceptions.*;
import Model.ADT.MyDictionary;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.Heap;
import Model.Utils.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadFile implements IStatement
{
    private IExpression exp_var_file;
    private String var_name;

    public ReadFile(IExpression _exp_var_file, String _var_name)
    {
        this.exp_var_file = _exp_var_file;
        this.var_name = _var_name;
    }

    @Override
    public ProgramState execute(ProgramState ps) throws UndeclaredEx, ExprEx, AlreadyOpenedFileEx, IOException, InexVarEx, MemoryEx
    {
        MyDictionary<Integer, Pair<String, BufferedReader>>  file_table = ps.getFileTable();
        MyDictionary<String, Integer> sym_table = ps.getSymTable();
        Heap heap = ps.getHeap();

        int id = exp_var_file.evaluate(sym_table, heap);
        if(!file_table.find(id))
        {
            throw new FileNotFoundException("File not opened.");
        }

        BufferedReader br = file_table.getValue(id).GetSecond();

        String s = br.readLine();
        if(s == null)
        {
            sym_table.put(var_name, 0);
        }
        else
        {
            sym_table.put(var_name, Integer.parseInt(s));
        }

        return null;
    }

    @Override
    public String toString()
    {
        return "ReadFile(" + exp_var_file.toString() + ", " + var_name + ")";
    }
}
