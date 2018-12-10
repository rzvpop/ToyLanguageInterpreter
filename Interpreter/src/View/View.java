package View;

import Controller.InterpreterController;
import Model.ADT.MyDictionary;
import Model.ADT.MyList;
import Model.ADT.MyStack;
import Model.Expression.*;
import Model.ProgramState;
import Model.Statement.*;
import Model.Utils.Heap;
import Model.Utils.Pair;
import Repository.Repo;
import View.Menu.ExitCommand;
import View.Menu.RunExemple;
import View.Menu.TextMenu;

import java.io.BufferedReader;
import java.util.Scanner;

public class View
{
    public static void main(String[] args)
    {
        /*StringBuilder filename;
        System.out.println("Give filename of LogFile:");
        Scanner read = new Scanner(System.in);
        filename = new StringBuilder();
        filename.append(read.next());*/

        MyStack<IStatement> exe_stack1 = new MyStack<>(5);
        MyDictionary<String, Integer> sym_table1 = new MyDictionary<>();
        MyList<Integer> out1 = new MyList<>();
        MyDictionary<Integer, Pair<String, BufferedReader>> file_table1 = new MyDictionary<>();

        IStatement stm1 = new CompoundStm(new AssignmentStm("a", new ConstExp(7)),
                new CompoundStm(new AssignmentStm("b", new ConstExp(3)),
                new CompoundStm(new AssignmentStm("c", new ArithExp(new VarExp("a"), new VarExp("b"), '+')),
                new PrintStm(new VarExp("c")))));

        Repo repo1 = new Repo(new StringBuilder("log_file1.txt"));
        Heap heap1 = new Heap();
        ProgramState prg1 = new ProgramState(stm1, sym_table1, exe_stack1, out1, file_table1, heap1);
        repo1.add(prg1);
        InterpreterController ctrl1 = new InterpreterController(repo1);

        /*IStatement stm2 = new CompoundStm(new AssignmentStm("x", new ArithExp(new ConstExp(2),
                        new ArithExp(new ConstExp(3), new ConstExp(4), '*'), '+')), new PrintStm(new VarExp("x")));

        Repo repo2 = new Repo(new StringBuilder("log_file2.txt"));
        Heap heap2 = new Heap();
        ProgramState prg2 = new ProgramState(stm2, sym_table, exe_stack, out, file_table, heap2);
        repo2.add(prg2);
        InterpreterController ctrl2 = new InterpreterController(repo2);

        IStatement stm3 = new CompoundStm(new AssignmentStm("n", new ConstExp(1)),
                        new CompoundStm(new AssignmentStm("x", new ConstExp(5)),
                        new CompoundStm(new AssignmentStm("y", new ConstExp(7)),
                        new IfStm(new VarExp("n"), new PrintStm(new VarExp("x")),
                        new PrintStm(new VarExp("y"))))));

        Repo repo3 = new Repo(new StringBuilder("log_file3.txt"));
        ProgramState prg3 = new ProgramState(stm3, sym_table, exe_stack, out, file_table);
        repo3.add(prg3);
        InterpreterController ctrl3 = new InterpreterController(repo3);

        IStatement stm4 = new CompoundStm(new AssignmentStm("a", new ArithExp(new ArithExp(new ConstExp(3), new ConstExp(0), '/'),
                new ConstExp(2), '+')), new PrintStm(new VarExp("a")));

        Repo repo4 = new Repo(new StringBuilder("log_file4.txt"));
        ProgramState prg4 = new ProgramState(stm4, sym_table, exe_stack, out, file_table);
        repo4.add(prg4);
        InterpreterController ctrl4 = new InterpreterController(repo4);

        IStatement stm5 = new CompoundStm(
                new OpenRFile("var_f", "test.in"), new CompoundStm(
                    new ReadFile(new VarExp("var_f"), "var_c"), new CompoundStm(
                        new PrintStm(new VarExp("var_c")), new CompoundStm(
                            new IfStm(new VarExp("var_c"), new CompoundStm(
                                        new ReadFile(new VarExp("var_f"), "var_c"),
                                        new PrintStm(new VarExp("var_c"))
                                    ),
                                    new PrintStm(new ConstExp(0))
                            ),
                            new CloseRFile(new VarExp("var_f"))
                        )
                    )
                )
            );

        Repo repo5 = new Repo(new StringBuilder("log_file5.txt"));
        ProgramState prg5 = new ProgramState(stm5, sym_table, exe_stack, out, file_table);
        repo5.add(prg5);
        InterpreterController ctrl5 = new InterpreterController(repo5);

        */
        IStatement stm5_2 = new CompoundStm(
                new OpenRFile("var_f", "test.in"), new CompoundStm(
                new ReadFile(new VarExp("var_f"), "var_c"), new CompoundStm(
                new PrintStm(new VarExp("var_c")),
                new IfStm(new VarExp("var_c"), new CompoundStm(
                        new ReadFile(new VarExp("var_f"), "var_c"),
                        new PrintStm(new VarExp("var_c"))
                ),
                        new PrintStm(new ConstExp(0))
                )

        )
        )
        );

        MyStack<IStatement> exe_stack5 = new MyStack<>(5);
        MyDictionary<String, Integer> sym_table5 = new MyDictionary<>();
        MyList<Integer> out5 = new MyList<>();
        MyDictionary<Integer, Pair<String, BufferedReader>> file_table5 = new MyDictionary<>();
        Repo repo5_2 = new Repo(new StringBuilder("log_file5.txt"));
        Heap heap5_2 = new Heap();
        ProgramState prg5_2 = new ProgramState(stm5_2, sym_table5, exe_stack5, out5, file_table5, heap5_2);
        repo5_2.add(prg5_2);
        InterpreterController ctrl5_2 = new InterpreterController(repo5_2);
        /*

        IStatement stm6 = new CompoundStm(
                new OpenRFile("var_f", "test.in"), new CompoundStm(
                    new ReadFile(new ArithExp(new VarExp("var_f"), new ConstExp(2), '+'), "var_c"), new CompoundStm(
                        new PrintStm(new VarExp("var_c")), new CompoundStm(
                            new IfStm(new VarExp("var_c"), new CompoundStm(
                                        new ReadFile(new VarExp("var_f"), "var_c"),
                                        new PrintStm(new VarExp("var_c"))
                                    ),
                                    new PrintStm(new ConstExp(0))
                            ),
                            new CloseRFile(new VarExp("car_f"))
                        )
                    )
                )
            );

        Repo repo6 = new Repo(new StringBuilder("log_file6.txt"));
        ProgramState prg6 = new ProgramState(stm6, sym_table, exe_stack, out, file_table);
        repo6.add(prg6);
        InterpreterController ctrl6 = new InterpreterController(repo6);

        IStatement stm7 = new CompoundStm(new New("v", new ConstExp(20)),
                            new CompoundStm(new New("a", new ArithExp(
                            new ConstExp(3), new ArithExp(new ConstExp(12), new ConstExp(4), '/'), '+')),
                            new CompoundStm(new WH("a", new ConstExp(10)), new CompoundStm(
                            new PrintStm(new VarExp("a")), new CompoundStm(new PrintStm(new VarExp("a")),
                            new CompoundStm(new PrintStm(new RH("a")), new AssignmentStm("a", new ConstExp(0))))))));

        Repo repo7 = new Repo(new StringBuilder("log_file7.txt"));
        Heap heap7 = new Heap();
        ProgramState prg7 = new ProgramState(stm7, sym_table, exe_stack, out, file_table, heap7);
        repo7.add(prg7);
        InterpreterController ctrl7 = new InterpreterController(repo7);*/

        IStatement stm8 = new CompoundStm(
                            new AssignmentStm("a", new ConstExp(5)),
                            new While(new Bool(new VarExp("a"), new ConstExp(0), ">"),
                                    new CompoundStm(new PrintStm(new VarExp("a")), new AssignmentStm(
                                            "a", new ArithExp(new VarExp("a"), new ConstExp(1), '-'))))
                        );

        MyStack<IStatement> exe_stack8 = new MyStack<>(5);
        MyDictionary<String, Integer> sym_table8 = new MyDictionary<>();
        MyList<Integer> out8 = new MyList<>();
        MyDictionary<Integer, Pair<String, BufferedReader>> file_table8 = new MyDictionary<>();
        Repo repo8 = new Repo(new StringBuilder("log_file8.txt"));
        Heap heap8 = new Heap();
        ProgramState prg8 = new ProgramState(stm8, sym_table8, exe_stack8, out8, file_table8, heap8);
        repo8.add(prg8);
        InterpreterController ctrl8 = new InterpreterController(repo8);

        IStatement stm9 = new CompoundStm(new AssignmentStm("v", new ConstExp(10)),
                            new CompoundStm(new New("a", new ConstExp(22)),
                            new CompoundStm(new Fork(
                            new CompoundStm(new WH("a", new ConstExp(30)),
                            new CompoundStm(new AssignmentStm("v", new ConstExp(32)),
                            new CompoundStm(new PrintStm(new VarExp("v")),
                                                            new PrintStm(new RH("a")))))
                                                ),
                            new CompoundStm(new PrintStm(new VarExp("v")),
                                                            new PrintStm(new RH("a"))))));

        MyStack<IStatement> exe_stack9 = new MyStack<>(5);
        MyDictionary<String, Integer> sym_table9 = new MyDictionary<>();
        MyList<Integer> out9 = new MyList<>();
        MyDictionary<Integer, Pair<String, BufferedReader>> file_table9 = new MyDictionary<>();
        Repo repo9 = new Repo(new StringBuilder("log_file9.txt"));
        Heap heap9 = new Heap();
        ProgramState prg9 = new ProgramState(stm9, sym_table9, exe_stack9, out9, file_table9, heap9);
        prg9.setId(1);
        repo9.add(prg9);
        InterpreterController ctrl9 = new InterpreterController(repo9);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExemple("1", stm1.toString(), ctrl1));
        /*menu.addCommand(new RunExemple("2", stm2.toString(), ctrl2));
        menu.addCommand(new RunExemple("3", stm3.toString(), ctrl3));
        menu.addCommand(new RunExemple("4", stm4.toString(), ctrl4));*/
        menu.addCommand(new RunExemple("5", stm5_2.toString(), ctrl5_2));
        /*menu.addCommand(new RunExemple("6", stm6.toString(), ctrl6));
        menu.addCommand(new RunExemple("7", stm7.toString(), ctrl7));*/
        menu.addCommand(new RunExemple("8", stm8.toString(), ctrl8));
        menu.addCommand(new RunExemple("9", stm9.toString(), ctrl9));

        menu.show();
    }
}