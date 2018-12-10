package Repository;

import Model.ProgramState;

import java.io.IOException;
import java.util.List;

public interface IRepo
{
    //ProgramState getCurrPrg();
    void add(ProgramState p);
    void logPrgStateExec(ProgramState ps) throws IOException;
    List<ProgramState> GetList();
    void SetList(List<ProgramState> prg_list);
}