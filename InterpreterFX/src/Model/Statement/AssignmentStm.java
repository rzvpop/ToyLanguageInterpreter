package Model.Statement;

import Exceptions.ExprEx;
import Exceptions.InexVarEx;
import Exceptions.MemoryEx;
import Exceptions.UndeclaredEx;
import Model.ADT.MyDictionary;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.Heap;

public class AssignmentStm implements IStatement
{
    private String varName;
    private IExpression expr;

    public AssignmentStm(String varName, IExpression expr)
    {
        this.varName = varName;
        this.expr = expr;
    }

    public String toString()
    {
        return varName + "=" + expr.toString();
    }

    public ProgramState execute(ProgramState ps) throws ExprEx, InexVarEx, UndeclaredEx, MemoryEx
    {
        MyDictionary<String, Integer> st = ps.getSymTable();
        Heap heap = ps.getHeap();

        st.put(this.varName, this.expr.evaluate(st, heap));
        return null;
    }
}
