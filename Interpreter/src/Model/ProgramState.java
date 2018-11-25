package Model;

import Model.ADT.MyDictionary;
import Model.ADT.MyList;
import Model.ADT.MyStack;
import Model.Statement.IStatement;
import Model.Utils.Heap;
import Model.Utils.Pair;

import java.io.BufferedReader;

public class ProgramState
{
    private IStatement program;
    private MyDictionary<String, Integer> symTable;
    private MyStack<IStatement> exeStack;
    private MyList<Integer> output;
    private MyDictionary<Integer, Pair<String, BufferedReader>> FileTable;
    private Heap heap;

    public ProgramState(IStatement _program, MyDictionary<String, Integer> _symTable, MyStack<IStatement> _exeStack,
                        MyList<Integer> _output, MyDictionary<Integer, Pair<String, BufferedReader>> _FileTable, Heap _heap)
    {
        this.program = _program;
        this.symTable = _symTable;
        this.exeStack = _exeStack;
        this.output = _output;
        this.FileTable = _FileTable;
        this.heap = _heap;

        //this.exeStack.push(program);
    }

    public void putPrgOnStack()
    {
        this.exeStack.push(program);
    }

    public MyDictionary<String, Integer> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyDictionary<String, Integer> symTable) {
        this.symTable = symTable;
    }

    public MyStack<IStatement> getExeStack() {
        return exeStack;
    }

    public void setExeStack (MyStack<IStatement> exeStack) {
        this.exeStack = exeStack;
    }

    public MyList<Integer> getOutput() {
        return output;
    }

    public void setOutput(MyList<Integer> output) {
        this.output = output;
    }

    public Heap getHeap()
    {
        return heap;
    }

    public void setHeap(Heap _heap){ this.heap = _heap;};

    public void setProgram(IStatement prg) {
        this.program = prg;
    }

    public MyDictionary<Integer, Pair<String, BufferedReader>> getFileTable()
    {
        return FileTable;
    }
    public void setFileTable(MyDictionary<Integer, Pair<String, BufferedReader>> ft)
    {
        FileTable = ft;
    }

    public IStatement getCurrPrg() {
        return this.program;
    }

    @Override
    public String toString()
    {
        String str = "";

        str += "ExeStack:\n";
        str += exeStack.toString() + "\n";

        str += "Symbol table:\n";
        str += symTable.toString() + "\n";

        str += "Output:\n";
        str += output.toString() + "\n";

        str += "Heap:\n";
        str += heap.toString() + "\n";

        str += "File table:\n";
        str += FileTable.toString();

        return str;
    }
}
