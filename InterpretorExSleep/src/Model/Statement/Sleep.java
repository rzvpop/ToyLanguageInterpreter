package Model.Statement;

import Exceptions.*;
import Model.ADT.MyStack;
import Model.ProgramState;

import java.io.IOException;

public class Sleep implements IStatement
{
    Integer number;

    public Sleep(Integer _number)
    {
        number = _number;
    }

    @Override
    public ProgramState execute(ProgramState ps) throws UndeclaredEx, ExprEx, AlreadyOpenedFileEx, InexVarEx, MemoryEx, IOException
    {
        MyStack<IStatement> exe_stack = ps.getExeStack();

        if(number > 0)
            exe_stack.push(new Sleep(number - 1));

        return null;
    }

    @Override
    public String toString()
    {
        return "Sleep(" + number.toString() + ")";
    }
}
