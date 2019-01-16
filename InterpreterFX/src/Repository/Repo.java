package Repository;

import Model.ADT.MyList;
import Model.ProgramState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repo implements IRepo
{
    private List<ProgramState> prgStates;
    //private int current;
    private StringBuilder filename;

    public Repo(StringBuilder fn)
    {
        filename = fn;
        DeleteFileIfExists();

        prgStates = new ArrayList<>();
        //current = 0;
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

    /*@Override
    public ProgramState getCurrPrg() {
        return this.prgStates.get(current);
    }*/

    @Override
    public void add(ProgramState p)
    {
        prgStates.add(p);
    }

    @Override
    public void logPrgStateExec(ProgramState ps)
    {
        PrintWriter pw = null;

        try
        {
            FileWriter fl = new FileWriter(new File(filename.toString()), true);
            BufferedWriter bf = new BufferedWriter(fl);
            pw = new PrintWriter(bf);

            String str = ps.toString();

            System.out.println(str);
            pw.println(str);

            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally
        {
            if(pw != null)
                pw.close();
        }
    }

    @Override
    public List<ProgramState> GetList()
    {
        return prgStates;
    }

    @Override
    public void SetList(List<ProgramState> prg_list)
    {
        prgStates = prg_list;
    }
}
