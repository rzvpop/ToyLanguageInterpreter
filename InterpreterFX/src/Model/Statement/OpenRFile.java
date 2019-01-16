package Model.Statement;

import Exceptions.AlreadyOpenedFileEx;
import Model.ADT.MyDictionary;
import Model.ProgramState;
import Model.Utils.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFile implements IStatement
{
    private String var_file_id;
    private String filename;
    private static Integer unq_key = 0;

    public OpenRFile(String var_file_id, String filename)
    {
        this.var_file_id = var_file_id;
        this.filename = filename;
    }

    public ProgramState execute(ProgramState ps) throws AlreadyOpenedFileEx, FileNotFoundException
    {
        MyDictionary<Integer, Pair<String, BufferedReader>> ft = ps.getFileTable();
        MyDictionary<String, Integer> sym_table = ps.getSymTable();

        for(int i = 0; i < ft.size(); ++i)
        {
            if(ft.getValue(i).GetFirst().equals(filename))
            {
                throw new AlreadyOpenedFileEx("File already in use.");
            }
        }

        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        Pair<String, BufferedReader> file = new Pair<>(filename, br);

        ft.put(unq_key, file);
        sym_table.put(var_file_id, unq_key);
        ++unq_key;

        return null;
    }

    public String toString()
    {
        return ("Open(" + var_file_id.toString() + ", " + filename + ")");
    }

}
