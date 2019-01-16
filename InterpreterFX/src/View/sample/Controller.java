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

        stms = new ArrayList<IStatement>();
        stms.add(stm1);
    }

    /*private List<String> getStringRepresentations()
    {
        return stms.stream().map(IStatement::toString).collect(Collectors.toList());
    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        createStatements();
        prg_list.getItems().setAll(stms.get(0).toString());

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
        repo.add(prg);
        InterpreterController ctrl = new InterpreterController(repo);

        run_ctrl.setInterpreterController(ctrl);
    }
}

