package Repository;

import Model.ADT.MyDictionary;
import Model.ADT.MyList;
import Model.ADT.MyStack;
import Model.ProgramState;
import Model.Statement.IStatement;
import Model.Utils.Heap;

import java.io.*;
import java.util.List;

public class Repo implements IRepo
{
    private MyList<ProgramState> prgStates;
    private int current;
    private StringBuilder filename;

    public Repo(StringBuilder fn)
    {
        filename = fn;
        DeleteFileIfExists();

        prgStates = new MyList<ProgramState>();
        current = 0;
    }

    private void DeleteFileIfExists()
    {
        File f = new File(filename.toString());
        if(f.exists() && !f.isDirectory())
        {
            if(!f.delete())
            {
                System.out.println("Couldn't delete the old file!");
            }
        }
    }

    @Override
    public ProgramState getCurrPrg() {
        return this.prgStates.get(current);
    }

    @Override
    public void add(ProgramState p)
    {
        prgStates.add(p);
    }

    @Override
    public void logPrgStateExec() throws IOException
    {
        PrintWriter pw = null;

        try
        {
            FileWriter fl = new FileWriter(new File(filename.toString()), true);
            BufferedWriter bf = new BufferedWriter(fl);
            pw = new PrintWriter(bf);

            String str = prgStates.get(current).toString();

            System.out.println(str);
            pw.println(str);

            pw.close();
        }
        finally
        {
            if(pw != null)
                pw.close();
        }
    }

    @Override
    public List<ProgramState> GetList()
    {
        return prgStates.GetArrayList();
    }

    @Override
    public void SetList(List<ProgramState> prg_list)
    {
        prgStates.SetList(prg_list);
    }
}
