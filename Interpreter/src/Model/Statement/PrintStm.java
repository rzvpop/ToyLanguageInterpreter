package Model.Statement;

import Exceptions.ExprEx;
import Exceptions.InexVarEx;
import Exceptions.MemoryEx;
import Exceptions.UndeclaredEx;
import Model.ADT.MyIList;
import Model.Expression.IExpression;
import Model.ProgramState;

public class PrintStm implements IStatement
{
    private IExpression expr;

    public PrintStm(IExpression expr)
    {
        this.expr = expr;
    }

    public String toString()
    {
        return "print("+ expr.toString() +")";
    }

    public ProgramState execute(ProgramState ps) throws ExprEx, InexVarEx, UndeclaredEx, MemoryEx
    {
        MyIList<Integer> output = ps.getOutput();
        output.add(expr.evaluate(ps.getSymTable(), ps.getHeap()));
        return null;
    }
}
