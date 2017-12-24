package sample.datamodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Test {

    private String testNumber;
    private int productThickness;
    private String productionNumber;
    private LocalDate productionDate;
    private LocalDate firstTestDate;
    private LocalDate secondTestDate;
    private LocalDate thirdTestDate;
    private LocalDate releaseDate;



    public Test() {

    }

    public Test(String testNumber, int productThickness, String productionNumber, LocalDate productionDate) {
        this.productThickness = productThickness;
        this.testNumber = testNumber;
        this.productionNumber = productionNumber;
        this.productionDate = productionDate;

        if(productThickness <=70) {
            this.firstTestDate = productionDate.plusDays(3);
            this.secondTestDate = productionDate.plusDays(5);
            this.thirdTestDate = secondTestDate;
            this.releaseDate = productionDate.plusDays(6);
        } else if(productThickness >70 && productThickness <= 80) {
            this.firstTestDate = productionDate.plusDays(3);
            this.secondTestDate = productionDate.plusDays(10);
            this.thirdTestDate = productionDate.plusDays(14);
            this.releaseDate = productionDate.plusDays(15);
        } else if(productThickness >80 && productThickness < 140 ) {
            this.firstTestDate = productionDate.plusDays(3);
            this.secondTestDate = productionDate.plusDays(10);
            this.thirdTestDate = productionDate.plusDays(15);
            this.releaseDate = productionDate.plusDays(19);
        } else if (productThickness == 140) {
            this.firstTestDate = productionDate.plusDays(3);
            this.secondTestDate = productionDate.plusDays(10);
            this.thirdTestDate = productionDate.plusDays(15);
            this.releaseDate = productionDate.plusDays(28);
        } else{
            this.firstTestDate = null;
            this.secondTestDate = null;
            this.thirdTestDate = null;
            this.releaseDate = null;
        }

    }

    public Test(String testNumber, int productThickness, String productionNumber, LocalDate productionDate,
                LocalDate firstTestDate, LocalDate secondTestDate, LocalDate thirdTestDate, LocalDate releaseDate) {
        this.productThickness = productThickness;
        this.testNumber = testNumber;
        this.productionNumber = productionNumber;
        this.productionDate = productionDate;
        this.firstTestDate = firstTestDate;
        this.secondTestDate = secondTestDate;
        this.thirdTestDate = thirdTestDate;
        this.releaseDate = releaseDate;
    }


    public String getTestNumber() {
        return testNumber;
    }

    public void setTestNumber(String testNumber) {
        this.testNumber = testNumber;
    }

    public int getProductThickness() {
        return productThickness;
    }

    public void setProductThickness(int productThickness) {
        this.productThickness = productThickness;
    }

    public String getProductionNumber() {
        return productionNumber;
    }

    public void setProductionNumber(String productionNumber) {
        this.productionNumber = productionNumber;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public LocalDate getFirstTestDate() {
        return firstTestDate;
    }

    public void setFirstTestDate(LocalDate firstTestDate) {
        this.firstTestDate = firstTestDate;
    }

    public LocalDate getSecondTestDate() {
        return secondTestDate;
    }

    public void setSecondTestDate(LocalDate secondTestDate) {
        this.secondTestDate = secondTestDate;
    }

    public LocalDate getThirdTestDate() {
        return thirdTestDate;
    }

    public void setThirdTestDate(LocalDate thirdTestDate) {
        this.thirdTestDate = thirdTestDate;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Test{" +
                "productThickness=" + productThickness +
                ", testNumber=" + testNumber +
                ", productionNumber=" + productionNumber +
                ", productionDate=" + productionDate +
                ", firstTestDate=" + firstTestDate +
                ", secondTestDate=" + secondTestDate +
                ", thirdTestDate=" + thirdTestDate +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
