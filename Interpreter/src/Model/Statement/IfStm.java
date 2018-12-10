package Model.Statement;

import Exceptions.*;
import Model.Expression.IExpression;
import Model.ProgramState;

import java.io.IOException;

public class IfStm implements IStatement
{
    private IExpression expr;
    private IStatement then_s;
    private IStatement else_s;

    public IfStm(IExpression expr, IStatement then_s, IStatement else_s)
    {
        this.expr = expr;
        this.then_s = then_s;
        this.else_s = else_s;
    }

    public String toString()
    {
        return ("If(" + expr.toString() + ")Then(" + then_s.toString() + ")Else(" + else_s.toString() + ")");
    }

    public ProgramState execute(ProgramState ps) throws UndeclaredEx, ExprEx, IOException, AlreadyOpenedFileEx, InexVarEx, MemoryEx
    {
        int r = expr.evaluate(ps.getSymTable(), ps.getHeap());

        if(r != 0)
            then_s.execute(ps);
        else
            else_s.execute(ps);

        return null;
    }
}
