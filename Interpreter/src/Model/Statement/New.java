package Model.Statement;

import Exceptions.*;
import Model.ADT.MyDictionary;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.Heap;

import java.io.IOException;

public class New implements IStatement
{
    private String var_name;
    private IExpression expression;

    public New(String _var_name, IExpression _expression)
    {
        this.var_name = _var_name;
        this.expression = _expression;
    }

    @Override
    public ProgramState execute(ProgramState ps) throws UndeclaredEx, ExprEx, AlreadyOpenedFileEx, IOException, InexVarEx, MemoryEx
    {
        MyDictionary<String, Integer> sym_table = ps.getSymTable();
        Heap heap = ps.getHeap();

        int mem_loc = heap.allocate(expression.evaluate(sym_table, heap));
        sym_table.put(var_name, mem_loc);

        return null;
    }

    @Override
    public String toString()
    {
        return "New(" + var_name + ", " + expression.toString() + ")";
    }
}
