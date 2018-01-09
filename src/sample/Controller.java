package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import sample.datamodel.Test;
import sample.datamodel.TestData;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Optional;

public class Controller {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private ListView<Test> testView;

    @FXML
    private Label testNumberText;

    @FXML
    private Label productThicknessText;

    @FXML
    private Label productionNumberText;

    @FXML
    private Label productionDateText;

    @FXML
    private Label firstTestDateText;

    @FXML
    private Label secondTestDateText;

    @FXML
    private Label thirdTestDateText;

    @FXML
    private Label releaseDateText;

    @FXML
    private Label thirdTestDateTextComment;


    public void initialize() {

        testView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Test>() {
            @Override
            public void changed(ObservableValue<? extends Test> observable, Test oldValue, Test newValue) {
                if(newValue != null) {
                    Test item = testView.getSelectionModel().getSelectedItem();
                    testNumberText.setText(item.getTestNumber());
                    productThicknessText.setText(String.valueOf(item.getProductThickness()));
                    productionNumberText.setText(item.getProductionNumber());
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    productionDateText.setText(df.format(item.getProductionDate()));
                    firstTestDateText.setText(df.format(item.getFirstTestDate()));
                    secondTestDateText.setText(df.format(item.getSecondTestDate()));
                    thirdTestDateText.setText(df.format(item.getThirdTestDate()));
                    if((item.getThirdTestDate()) == (item.getSecondTestDate())){
                        thirdTestDateTextComment.setText("Dla płyt o grubości poniżej 80 mm, testy wykonuje się tylko 2 razy!!!");
                    }

                    releaseDateText.setText(df.format(item.getReleaseDate()));

                }
            }
        });

        SortedList<Test> sortedList = new SortedList<Test>(TestData.getInstance().getTestData(),
                new Comparator<Test>() {
                    @Override
                    public int compare(Test o1, Test o2) {
                        return o1.getProductionDate().compareTo(o2.getProductionDate());
                    }
                });

        testView.setItems(sortedList);
        testView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        testView.getSelectionModel().selectFirst();

        updateListView();
    }

    @FXML
    public void showAddTestDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add New Test ");
        dialog.setHeaderText("Use this dialog to create a new test");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("testdialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch(IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            TestDialog controller = fxmlLoader.getController();

            Test newItem = controller.processResults();
            if(newItem == null) {
                showAddTestDialog();
            }

//            System.out.println(newItem);
            testView.getSelectionModel().select(newItem);
        }
    }

    @FXML
    public void showEditTestDialog() {
        Test selectedTest = testView.getSelectionModel().getSelectedItem();
        if(selectedTest == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No test selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select test you want to edit");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Edit Test");
        dialog.setHeaderText("Use this dialog to edit selected test");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("editdialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        TestDialog testdialog = fxmlLoader.getController();
        testdialog.editTest(selectedTest);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            testdialog.updateTest(selectedTest);

            updateListView();
        }

    }

    public void deleteTest(Test item) {
        Test selectedTest = testView.getSelectionModel().getSelectedItem();
        if(selectedTest == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No test selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select test you want to delete");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete test");
        alert.setHeaderText("Delete test: " + item.getTestNumber());
        alert.setContentText("Are you sure? Press OK to confirm or cancel to Back out.");
        Optional<ButtonType> result = alert.showAndWait();


        if(result.isPresent() && (result.get() == ButtonType.OK)) {
            TestData.getInstance().deleteTest(item);
        }
    }

    @FXML
    public void handleDeleteTest(){
        Test selectedItem = testView.getSelectionModel().getSelectedItem();
        deleteTest(selectedItem);
    }

    public void handleKeyPressed(KeyEvent keyEvent) {
        Test selectedTest = testView.getSelectionModel().getSelectedItem();
        if(selectedTest != null) {
            if(keyEvent.getCode().equals(KeyCode.DELETE)) {
                deleteTest(selectedTest);
            }
        }
    }

    @FXML
    public void handleExit() {
        Platform.exit();
    }

    public void updateListView() {
        testView.setCellFactory(new Callback<ListView<Test>, ListCell<Test>>() {
            @Override
            public ListCell<Test> call(ListView<Test> param) {
                ListCell<Test> cell = new ListCell<Test>() {
                    @Override
                    protected void updateItem(Test item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty) {
                            setText(null);
                        } else {
                            setText(item.getTestNumber());
                            if(item.getReleaseDate().isBefore(LocalDate.now())) {
                                setTextFill(Color.RED);
                            }else if(item.getThirdTestDate().isBefore(LocalDate.now())){
                                if(item.getThirdTestDate() != null) {
                                    setTextFill(Color.GREEN);
                                }
                            }
                        }
                    }
                };
                return cell;
            }
        });
    }
}


