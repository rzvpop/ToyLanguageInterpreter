package Repository;

import Model.ADT.MyDictionary;
import Model.ADT.MyList;
import Model.ADT.MyStack;
import Model.ProgramState;
import Model.Statement.IStatement;
import Model.Utils.Heap;

import java.io.*;

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

        try {
            FileWriter fl = new FileWriter(new File(filename.toString()), true);
            BufferedWriter bf = new BufferedWriter(fl);
            pw = new PrintWriter(bf);

            String str = "";
            ProgramState ps = prgStates.get(current);

            MyStack<IStatement> exe_stack = ps.getExeStack();
            MyDictionary<String, Integer> sym_table = ps.getSymTable();
            MyList<Integer> out = ps.getOutput();
            Heap heap = ps.getHeap();

            str += "ExeStack:\n";
            str += exe_stack.toString() + "\n";

            str += "Symbol table:\n";
            str += sym_table.toString() + "\n";

            str += "Output:\n";
            str += out.toString() + "\n";

            str += "Heap:\n";
            str += heap.toString() + "\n";

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
}
