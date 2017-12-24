package sample;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.datamodel.Test;
import sample.datamodel.TestData;

import java.time.LocalDate;

public class Testdialog {

    @FXML
    private TextField testNumberField;

    @FXML
    private TextField productThicknessField;

    @FXML
    private TextField productionNumberField;

    @FXML
    private DatePicker productionDatePicker;

    public Test processResults() {
        String testNumber = testNumberField.getText().trim();
        int productThickness = Integer.parseInt(productThicknessField.getText().trim());
        String productionNumber = productionNumberField.getText().trim();
        LocalDate productionDate = productionDatePicker.getValue();

        Test newItem = new Test(testNumber, productThickness, productionNumber,productionDate);
        TestData.getInstance().addTest(newItem);
//        System.out.println(newItem);
        return newItem;
    }
}
