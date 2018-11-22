package Model.Statement;

import Model.ADT.MyStack;
import Model.ProgramState;

public class CompoundStm implements IStatement {
    private IStatement st1, st2;

    public CompoundStm(IStatement st1, IStatement st2)
    {
        this.st1 = st1;
        this.st2 = st2;
    }

    public String toString()
    {
        return ("(" + st1.toString() + ", " + st2.toString() + ")");
    }

    public ProgramState execute(ProgramState ps)
    {
        MyStack<IStatement> exeStack = ps.getExeStack();
        exeStack.push(this.st2);
        exeStack.push(this.st1);

        return ps;
    }
}
