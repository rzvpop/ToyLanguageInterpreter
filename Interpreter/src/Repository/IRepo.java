package Repository;

import Model.ProgramState;

import java.io.IOException;

public interface IRepo
{
    ProgramState getCurrPrg();
    void add(ProgramState p);
    void logPrgStateExec() throws IOException;
}