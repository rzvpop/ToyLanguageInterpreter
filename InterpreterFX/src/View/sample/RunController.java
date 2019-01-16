package View.sample;

import Controller.InterpreterController;
import Model.ProgramState;
import Model.Statement.IStatement;
import Model.Utils.Pair;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class RunController implements Initializable

{
    private  InterpreterController interpreter_ctrl;

    @FXML
    private TextField nr_prg_states;
    @FXML
    private TableView<Map.Entry<Integer, Integer>> heap_table_view;
    @FXML
    private ListView out_list_view;
    @FXML
    private TableView<Map.Entry<Integer, String>> file_table_view;
    @FXML
    private ListView<Integer> program_states_id_list_view;
    @FXML
    private TableView<Map.Entry<String, Integer>> sym_table_view;
    @FXML
    private ListView exe_stack_list_view;
    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, Integer> heap_table_address;
    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, Integer> heap_table_value;
    @FXML
    private TableColumn<Map.Entry<Integer, String>, Integer> file_table_identifier;
    @FXML
    private TableColumn<Map.Entry<Integer, String>, String> file_table_file_name;
    @FXML
    private TableColumn<Map.Entry<String, Integer>, String> sym_table_var_name;
    @FXML
    private TableColumn<Map.Entry<String, Integer>, Integer> sym_table_var_val;
    @FXML
    private Button next_btn;

    private ProgramState getCurrPrgState()
    {
        List<ProgramState> prg_states = interpreter_ctrl.getRepo().GetList();
        int currSelected = program_states_id_list_view.getSelectionModel().getSelectedIndex();
        if(currSelected == -1)
            return null;

        return prg_states.get(currSelected);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        heap_table_address.setCellValueFactory(p ->
        {
            SimpleIntegerProperty sip = new SimpleIntegerProperty(p.getValue().getKey());
            System.out.println(sip.asObject().toString());
            return sip.asObject();
        });
        heap_table_value.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getValue()).asObject());

        file_table_identifier.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        file_table_file_name.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue()));

        sym_table_var_name.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey()));
        sym_table_var_val.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getValue()).asObject());

        program_states_id_list_view.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> changePrgState(getCurrPrgState()));
        next_btn.setOnMouseClicked(mouseEvent -> execOneStep());
    }

    private void changePrgState(ProgramState curr_prg_state)
    {
        if(curr_prg_state != null)
        {
            populateExeStackView();
            populateFileView();
            populateHeapView();
            populateSymTableView();
            populateOutView();
        }
    }

    private void execOneStep()
    {
        if(interpreter_ctrl == null)
        {
            Alert null_ctrl = new Alert(Alert.AlertType.ERROR, "No controller set!", ButtonType.OK);
            null_ctrl.showAndWait();
            return;
        }

        if(Objects.requireNonNull(getCurrPrgState()).getExeStack().isEmpty())
        {
            Alert empty_exe_stack = new Alert(Alert.AlertType.ERROR, "Empty execution stack!", ButtonType.OK);
            empty_exe_stack.showAndWait();
            return;
        }

        List<ProgramState> prg_states = interpreter_ctrl.getRepo().GetList();
        try {
            interpreter_ctrl.oneStepForAll(prg_states);
        } catch (InterruptedException e) {
            Alert inter_exception = new Alert(Alert.AlertType.ERROR, e.toString(), ButtonType.OK);
            inter_exception.showAndWait();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }

        changePrgState(getCurrPrgState());
        populatePrgStateIndetView();
    }

    private List<Integer> getPrgStateIds(List<ProgramState> prg_states)
    {
        return prg_states.stream().map(ProgramState::getId).collect(Collectors.toList());
    }

    private void populatePrgStateIndetView()
    {
        List<ProgramState> prg_states = interpreter_ctrl.getRepo().GetList();
        program_states_id_list_view.setItems(FXCollections.observableList(getPrgStateIds(prg_states)));

        nr_prg_states.setText(Integer.toString(prg_states.size()));
    }

    private void populateHeapView()
    {
        Set<Map.Entry<Integer, Integer>> heap_map = Objects.requireNonNull(getCurrPrgState()).getHeap().getMap().entrySet();
        ObservableList<Map.Entry<Integer, Integer>> list_heap_map = FXCollections.observableList(new ArrayList<>(heap_map));

        heap_table_view.setItems(list_heap_map);
        heap_table_view.refresh();
    }

    private void populateFileView()
    {
        Set<Map.Entry<Integer, Pair<String, BufferedReader>>> file_map = Objects.requireNonNull(getCurrPrgState()).getFileTable().All().entrySet();
        Map<Integer, String> red_file_map = new HashMap<>();

        for(Map.Entry<Integer, Pair<String, BufferedReader>> file : file_map)
        {
            red_file_map.put(file.getKey(), file.getValue().GetFirst());
        }

        Set<Map.Entry<Integer, String>> list_file_map = red_file_map.entrySet();

        file_table_view.setItems(FXCollections.observableList(new ArrayList<>(list_file_map)));
        file_table_view.refresh();
    }

    private void populateSymTableView()
    {
        Set<Map.Entry<String, Integer>> sym_table_map = Objects.requireNonNull(getCurrPrgState()).getSymTable().All().entrySet();
        ObservableList<Map.Entry<String, Integer>> list_sym_table_map = FXCollections.observableList(new ArrayList<>(sym_table_map));

        sym_table_view.setItems(list_sym_table_map);
        sym_table_view.refresh();
    }

    private void populateOutView()
    {
        ObservableList<Integer> list_out = FXCollections.observableList(Objects.requireNonNull(getCurrPrgState()).getOutput().GetArrayList());

        out_list_view.setItems(list_out);
        out_list_view.refresh();
    }

    private void populateExeStackView()
    {
        List<IStatement> exe_stm_list = Objects.requireNonNull(getCurrPrgState()).getExeStack().getArrayList();
        List<String> list_exe_stack = new ArrayList<>();
        for(IStatement stm : exe_stm_list)
        {
            list_exe_stack.add(stm.toString());
        }

        exe_stack_list_view.setItems(FXCollections.observableList(list_exe_stack));
        exe_stack_list_view.refresh();
    }

    void setInterpreterController(InterpreterController _interpreter_ctrl)
    {
        interpreter_ctrl = _interpreter_ctrl;
        populatePrgStateIndetView();
    }
}
