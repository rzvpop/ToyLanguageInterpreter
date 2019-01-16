package Model.Statement;

import Exceptions.*;
import Model.ADT.MyDictionary;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.Heap;

import java.io.IOException;

public class WH implements IStatement
{
    private String var_name;
    private IExpression expr;

    public WH(String var_name, IExpression expr)
    {
        this.var_name = var_name;
        this.expr = expr;
    }

    @Override
    public ProgramState execute(ProgramState ps) throws UndeclaredEx, ExprEx, AlreadyOpenedFileEx, IOException, InexVarEx, MemoryEx
    {
        MyDictionary<String, Integer> sym_table = ps.getSymTable();
        Heap heap = ps.getHeap();

        if(sym_table.find(var_name))
        {
            int addr = sym_table.getValue(var_name);
            if(heap.find(addr))
            {
                heap.writeAddr(addr, expr.evaluate(sym_table, heap));
            }
            else
            {
                throw new MemoryEx("'" + var_name +"' points to an invalid address.");
            }
        }
        else
        {
            throw new UndeclaredEx("No resource with this name: " + var_name + ".");
        }

        return null;
    }

    @Override
    public String toString()
    {
        return "WH(" + var_name + ", " + expr.toString() + ")";
    }
}
