package View.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

import javafx.scene.control.ListView;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader run_loader = new FXMLLoader();
        run_loader.setLocation(getClass().getResource("run_gui.fxml"));
        Parent run_window = run_loader.load();

        primaryStage.setTitle("Interpreter");
        Scene run_scene = new Scene(run_window, 600, 500);
        primaryStage.setScene(run_scene);
        primaryStage.show();

        FXMLLoader select_loader = new FXMLLoader();
        select_loader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = select_loader.load();

        Controller select_ctrl = select_loader.getController();
        select_ctrl.setRunController(run_loader.getController());

        Stage secStage = new Stage();
        secStage.setTitle("Interpreter");
        Scene select_scene = new Scene(root, 600, 500);
        secStage.setScene(select_scene);
        secStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}