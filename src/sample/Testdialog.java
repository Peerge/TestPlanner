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

    @FXML
    private DatePicker firstTestDatePicker;

    @FXML
    private DatePicker secondTestDatePicker;

    @FXML
    private DatePicker thirdTestDatePicker;

    @FXML
    private DatePicker releaseDatePicker;



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

    public void updateTest(Test test) {
        test.setTestNumber(testNumberField.getText());
        test.setProductThickness(Integer.parseInt(productThicknessField.getText().trim()));
        test.setProductionNumber(productionNumberField.getText());
        test.setProductionDate(productionDatePicker.getValue());
        test.setFirstTestDate(firstTestDatePicker.getValue());
        test.setSecondTestDate(secondTestDatePicker.getValue());
        test.setThirdTestDate(thirdTestDatePicker.getValue());
        test.setReleaseDate(releaseDatePicker.getValue());
    }

    public void editTest(Test test) {
        testNumberField.setText(test.getTestNumber());
        productThicknessField.setText(String.valueOf(test.getProductThickness()));
        productionNumberField.setText(test.getProductionNumber());
        productionDatePicker.setValue(test.getProductionDate());
        firstTestDatePicker.setValue(test.getFirstTestDate());
        secondTestDatePicker.setValue(test.getSecondTestDate());
        thirdTestDatePicker.setValue(test.getThirdTestDate());
        releaseDatePicker.setValue(test.getReleaseDate());
    }
}
