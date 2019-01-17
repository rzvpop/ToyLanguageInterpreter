package View.sample;

import Controller.InterpreterController;
import Model.Expression.*;
import Model.ProgramState;
import Model.Statement.*;
import Repository.Repo;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    @FXML
    private ListView<String> prg_list;
    @FXML
    private Button select_btn;

    private List<IStatement> stms;
    private RunController run_ctrl;

    private void createStatements()
    {
        IStatement stm1 = new CompoundStm(new AssignmentStm("a", new ConstExp(7)),
                new CompoundStm(new AssignmentStm("b", new ConstExp(3)),
                        new CompoundStm(new AssignmentStm("c", new ArithExp(new VarExp("a"), new VarExp("b"), '+')),
                                new PrintStm(new VarExp("c")))));

        IStatement stm5_2 = new CompoundStm(
                new OpenRFile("var_f", "test.in"), new CompoundStm(
                new ReadFile(new VarExp("var_f"), "var_c"), new CompoundStm(
                new PrintStm(new VarExp("var_c")),
                new IfStm(new VarExp("var_c"), new CompoundStm(
                        new ReadFile(new VarExp("var_f"), "var_c"),
                        new PrintStm(new VarExp("var_c"))
                ),
                        new PrintStm(new ConstExp(0))))));

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

        stms = new ArrayList<IStatement>();
        stms.add(stm1);
        stms.add(stm5_2);
        stms.add(stm9);
    }

    /*private List<String> getStringRepresentations()
    {
        return stms.stream().map(IStatement::toString).collect(Collectors.toList());
    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        createStatements();
        prg_list.getItems().setAll(stms.get(0).toString(), stms.get(1).toString(), stms.get(2).toString());

        select_btn.setOnAction(event -> select_btnEvent());
    }

    void setRunController(RunController _run_ctrl)
    {
        run_ctrl = _run_ctrl;
    }

    private void select_btnEvent()
    {
        int prg_index = prg_list.getSelectionModel().getSelectedIndex();

        if(prg_index < 0)
            return;

        IStatement stm = stms.get(prg_index);
        Repo repo = new Repo(new StringBuilder("log_file" + prg_index + ".txt"));
        ProgramState prg = new ProgramState(stm);
        prg.setId(1);
        repo.add(prg);
        InterpreterController ctrl = new InterpreterController(repo);

        run_ctrl.setInterpreterController(ctrl);
    }
}

