package Model.Statement;

import Exceptions.*;
import Model.ProgramState;

import java.io.IOException;

public interface IStatement {
    ProgramState execute(ProgramState ps) throws UndeclaredEx, ExprEx, AlreadyOpenedFileEx, InexVarEx, MemoryEx, IOException;
    String toString();
    }
