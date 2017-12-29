package sample.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class TestData {
    private static TestData instance = new TestData();
    private static String filename = "Tests.txt";

    private ObservableList<Test> testData;
    private DateTimeFormatter formatter;

    public static TestData getInstance() {
        return instance;
    }

    public TestData() {
        testData = FXCollections.observableArrayList();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public ObservableList<Test> getTestData() {
        return testData;
    }

    public void addTest(Test item) {
        testData.add(item);
    }

    public void loadTest() throws IOException {

        testData = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try {
            while ((input = br.readLine()) != null) {
                String[] itemPieces = input.split("\t");

                String testNumberString = itemPieces[0];
                String productThicknessString = itemPieces[1];
                String productionNumberString = itemPieces[2];
                String productionDateString = itemPieces[3];
                String firstTestDateString = itemPieces[4];
                String secondTestDateString = itemPieces[5];
                String thirdTestDateString = itemPieces[6];
                String releaseDateString = itemPieces[7];

                int productThickness = Integer.parseInt(productThicknessString);

                LocalDate productionDate = LocalDate.parse(productionDateString, formatter);
                LocalDate firstTestDate = LocalDate.parse(firstTestDateString, formatter);
                LocalDate secondTestDate = LocalDate.parse(secondTestDateString, formatter);
                LocalDate thirdTestDate = LocalDate.parse(thirdTestDateString, formatter);
                LocalDate releaseDate = LocalDate.parse(releaseDateString, formatter);
                Test testItem = new Test(testNumberString, productThickness, productionNumberString, productionDate, firstTestDate, secondTestDate, thirdTestDate, releaseDate);
                testData.add(testItem);
            }

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        }finally {
            if(br != null) {
                br.close();
            }
        }
    }

    public void storeTest() throws IOException {

        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator<Test> iter = testData.iterator();
            while(iter.hasNext()) {
                Test item = iter.next();
                bw.write(String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",
                        item.getTestNumber(),
                        item.getProductThickness(),
                        item.getProductionNumber(),
                        item.getProductionDate(),
                        item.getFirstTestDate(),
                        item.getSecondTestDate(),
                        item.getThirdTestDate(),
                        item.getReleaseDate()));
                bw.newLine();
            }

        } finally {
            if(bw != null) {
                bw.close();
            }
        }
    }

    public void deleteTest(Test item) {
        testData.remove(item);
    }

}
