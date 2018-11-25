package Model.Statement;

import Exceptions.*;
import Model.ADT.MyDictionary;
import Model.ADT.MyStack;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.Heap;

import java.io.IOException;

public class While implements IStatement
{
    private IExpression expr;
    private IStatement stm;

    public While(IExpression expr, IStatement stm)
    {
        this.expr = expr;
        this.stm = stm;
    }

    @Override
    public ProgramState execute(ProgramState ps) throws UndeclaredEx, ExprEx, AlreadyOpenedFileEx, IOException, InexVarEx, MemoryEx
    {
        MyDictionary<String, Integer> sym_table = ps.getSymTable();
        Heap heap = ps.getHeap();
        MyStack<IStatement> exe_stack = ps.getExeStack();

        if(expr.evaluate(sym_table, heap) != 0)
        {
            exe_stack.push(this);
            exe_stack.push(stm);
        }

        return null;
    }

    @Override
    public String toString()
    {
        return "while(" + expr.toString() + ") " + stm.toString();
    }
}
