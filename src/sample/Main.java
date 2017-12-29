package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.datamodel.TestData;

import java.io.IOException;

//TODO 1.Poprawić w dodawaniu testu - zamiast wpisywania grubości płyty ustawić listę do wyboru
//TODO 2.Dodać komentarz gdy nie ma 3 testu dla cienkich grubości

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Thermano Test Planner");
        primaryStage.setScene(new Scene(root, 1000, 750));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        try {
            TestData.getInstance().loadTest();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void stop() throws Exception {
        try {
            TestData.getInstance().storeTest();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
