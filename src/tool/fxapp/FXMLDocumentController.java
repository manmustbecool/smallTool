/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool.fxapp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import tool.model.Fun;
import tool.model.RecordOutput1;
import tool.model.RecordInput1;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import javafx.util.StringConverter;
import tool.model.MoreFun;
import tool.model.RecordInput2;

/**
 *
 * @author emiewag
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label result1Label;
    @FXML
    private Label result2Label;

    @FXML
    private TableView<RecordInput1> tableViewInput1;
    @FXML
    private TableView<RecordOutput1> tableViewResult1;
    @FXML
    private TableView<RecordInput2> tableViewInput2;
    @FXML
    private TableView<RecordInput2> tableViewResult2;

    @FXML
    private TableColumn<RecordInput1, String> keyColumn;
    @FXML
    private TableColumn<RecordInput1, Integer> valueColumn;

    @FXML
    private TableColumn<RecordOutput1, String> result1keyColumn;

    @FXML
    private TableColumn<RecordInput2, String> input2ValueColumn;

    @FXML
    private TextField txt_size;
    @FXML
    private TextField minFinalValue;
    @FXML
    private TextField maxFinalValue;
    @FXML
    private TextField txt_filterFieldResult2;
    @FXML
    private TextField txt_maxDuplidateKey;
    @FXML
    private CheckBox checkBox_duplicate;

    @FXML
    private Button calculate2;

    @FXML
    private Button button_exportToCSV;

    private ObservableList<RecordInput2> tableViewResult2Data;

    /**
     * add button
     */
    @FXML
    protected void addKvRecordButton(ActionEvent event) {

        ObservableList<RecordInput1> data = tableViewInput1.getItems();

        char c = Character.toChars(data.size() + 65)[0];
        String k = Character.toString(c);
        Integer v = data.size();
        data.add(new RecordInput1(k, v));

        int ix = data.size() - 1;
        System.out.println("ix :" + ix);
        tableViewInput1.requestFocus();
        tableViewInput1.getSelectionModel().select(ix);
        tableViewInput1.getFocusModel().focus(ix);

    }

    /**
     * add button
     */
    @FXML
    protected void addKvRecordButton2(ActionEvent event) {

        ObservableList<RecordInput2> data = tableViewInput2.getItems();

        char c = Character.toChars(data.size() + 65)[0];
        String k = Character.toString(c);
        String s = Character.getNumericValue(c) + "," + (Character.getNumericValue(c) + 100);
        data.add(new RecordInput2(k, s));

        int ix = data.size() - 1;
        System.out.println("ix :" + ix);
        tableViewInput2.requestFocus();
        tableViewInput2.getSelectionModel().select(ix);
        tableViewInput2.getFocusModel().focus(ix);
    }

    /**
     * delete button
     */
    @FXML
    public void deleteKvRecordButton(ActionEvent e) {
        // Get selected row and delete
        int ix = tableViewInput1.getSelectionModel().getSelectedIndex();
        ObservableList<RecordInput1> data = tableViewInput1.getItems();
        data.remove(ix);

        // Select a row
        if (ix != 0) {
            ix = ix - 1;
        }
        System.out.println("ix :" + ix);
        tableViewInput1.requestFocus();
        tableViewInput1.getSelectionModel().select(ix);
        tableViewInput1.getFocusModel().focus(ix);
    }

    /**
     * delete button
     */
    @FXML
    public void deleteKvRecordButton2(ActionEvent e) {
        // Get selected row and delete
        int ix = tableViewInput2.getSelectionModel().getSelectedIndex();
        ObservableList<RecordInput2> data = tableViewInput2.getItems();
        data.remove(ix);

        // Select a row
        if (ix != 0) {
            ix = ix - 1;
        }
        System.out.println("ix :" + ix);
        tableViewInput2.requestFocus();
        tableViewInput2.getSelectionModel().select(ix);
        tableViewInput2.getFocusModel().focus(ix);
    }

    @FXML
    public void onEditStartKey(CellEditEvent<RecordInput1, String> t) {
        System.out.println("onEditStartKey");
    }

    @FXML
    public void caculate1Button(ActionEvent e) {

        tableViewResult1.getItems().clear();

        result1Label.setText("0");

        HashMap<Integer, String> hm = new HashMap<>();
        tableViewInput1.getItems().stream().forEach((item) -> {
            hm.put(item.getFieldValue(), item.getFieldKey());
        });

        System.out.println(hm.toString());
        Set values = hm.keySet();
        Integer[] data = (Integer[]) values.toArray(new Integer[values.size()]);

        int sumMin = Integer.parseInt(minFinalValue.getText());
        int sumMax = Integer.parseInt(maxFinalValue.getText());
        int size = Integer.parseInt(txt_size.getText());
        int maxDuplidateKey = Integer.parseInt(txt_maxDuplidateKey.getText());

        List<RecordOutput1> displayResult = new LinkedList<>();
        
        int index = 0;
        int total = 0;
        
        for (int sum = sumMin; sum <= sumMax; sum++) {
            Fun fun = new Fun(data, sum, size, maxDuplidateKey);
            fun.startCalculate(sum);

            LinkedList<LinkedList<Integer>> outputList = fun.getOutputList();
            total = total + outputList.size();
                    
            if (index <= 2500) {
                index = index + 1;
                for (LinkedList<Integer> outputValues : outputList) {
                    LinkedList<String> reK = new LinkedList();
                    for (Integer v : outputValues) {
                        reK.add(hm.get(v));
                    }
                    displayResult.add(new RecordOutput1(index + "", reK.toString(), outputValues.toString() + "=" + sum));
                }
            }
        }
        result1Label.setText(total + " ");

        ObservableList<RecordOutput1> names = FXCollections.observableArrayList(displayResult);
        tableViewResult1.setItems(names);
    }

    @FXML
    public void caculate2Button(ActionEvent e) {

        ObservableList<RecordInput2> input2 = tableViewInput2.getItems();
        HashMap<String, ArrayList<Integer>> input2Hm = new HashMap<>();
        for (RecordInput2 in2 : input2) {
            String s = in2.getFieldValue();
            String[] ss = s.split(",");
            ArrayList al = new ArrayList();
            for (String t : ss) {
                al.add(Integer.parseInt(t.trim()));
            }
            input2Hm.put(in2.getFieldKey(), al);
        }

        ObservableList<RecordInput2> data2 = tableViewResult2.getItems();
        data2.clear();
        result2Label.setText("0");

        ObservableList<RecordOutput1> ouput1s = tableViewResult1.getSelectionModel().getSelectedItems();

        boolean duplicate = checkBox_duplicate.isSelected();
        System.out.println("checkBox_duplicate : " + duplicate);
        int index = 0;
        for (RecordOutput1 output1 : ouput1s) {
            String k = output1.getFKey();
            k = (String) k.subSequence(1, k.length() - 1);
            System.out.println("selected : " + k);
            String[] ks = k.split(",");

            ArrayList<ArrayList<Integer>> inputList = new ArrayList<>();

            for (String t : ks) {
                t = t.trim();
                System.out.println("Assign value for : " + t);
                inputList.add(input2Hm.get(t));
            }

            MoreFun moreFun = new MoreFun(inputList);
            moreFun.startCalculate(duplicate);
            ArrayList<String> res = moreFun.getResList();
            for (String s : res) {
                index = index + 1;
                data2.add(new RecordInput2(index + "", s));
            }
        }

        result2Label.setText(index + " ");

        // temp variable
        tableViewResult2Data = data2;

    }

    @FXML
    public void button_exportToCSV(ActionEvent e) throws IOException {
        System.out.println("button_exportToCSV");
        // ask the user where to save the excel to
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(File.listRoots()[0]);
        fileChooser.setTitle("Export result to CSV file");
        String fileFormat = "csv";
        fileChooser.setSelectedExtensionFilter(new ExtensionFilter(fileFormat + " files", "*." + fileFormat));
        fileChooser.setInitialFileName("smallTool_result." + fileFormat);
        File file = fileChooser.showSaveDialog(tableViewResult2.getScene().getWindow());
        if (file != null) {
            Writer writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(file));
                for (RecordInput2 row : tableViewResult2Data) {
                    String text = row.getFieldValue();
                    // remove [ ]
                    text = text.substring(1, text.length() - 1) + "\n";
                    writer.write(text);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                writer.flush();
                writer.close();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tableViewResult1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // add edit action for key column
        keyColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        keyColumn.setOnEditCommit((TableColumn.CellEditEvent<RecordInput1, String> t) -> {
            System.out.println("onEditCommit_key");
            ((RecordInput1) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFieldKey(t.getNewValue());
        });

        // add edit action for value column
        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
            @Override
            public String toString(Integer i) {
                return i.toString();
            }

            @Override
            public Integer fromString(String s) {
                return Integer.parseInt(s);
            }
        }
        ));
        valueColumn.setOnEditCommit((TableColumn.CellEditEvent<RecordInput1, Integer> t) -> {
            System.out.println("onEditCommit_value valueColumn");
            System.out.println(t.getNewValue());
            Integer v = t.getNewValue();
            ((RecordInput1) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFieldValue(v);
        });

        // add edit action for value column
        input2ValueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        input2ValueColumn.setOnEditCommit((TableColumn.CellEditEvent<RecordInput2, String> t) -> {
            System.out.println("onEditCommit_value input2ValueColumn");
            System.out.println(t.getNewValue());
            ((RecordInput2) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFieldValue(t.getNewValue());
        });

        txt_filterFieldResult2.textProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable observable) {
                if (txt_filterFieldResult2.textProperty().get().isEmpty()) {
                    tableViewResult2.setItems(tableViewResult2Data);
                    return;
                }

                String filter = txt_filterFieldResult2.textProperty().get().toLowerCase();
                String[] filterArray = filter.replaceAll("\\s", "").split(",");
                LinkedList<Integer> filterList = new LinkedList();
                for (String s : filterArray) {
                    filterList.add(Integer.parseInt(s));
                };
                System.out.println(filterList.toString());

                tableViewResult2.setItems(tableViewResult2Data);
                ObservableList<RecordInput2> data2 = tableViewResult2.getItems(); 

                ObservableList<RecordInput2> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<RecordInput2, ?>> cols = tableViewResult2.getColumns();
                
                for (int i = 0; i < data2.size(); i++) {
                    // only search second column
                    for (int j = 1; j < cols.size(); j++) {
                        TableColumn col = cols.get(j);
                        String cellValue = col.getCellData(data2.get(i)).toString();
                        cellValue = cellValue.substring(1, cellValue.length() - 1);
                        String[] cellValueArray = cellValue.replaceAll("\\s", "").split(",");

                        LinkedList<Integer> cellValueList = new LinkedList();
                        for (String s : cellValueArray) {
                            cellValueList.add(Integer.parseInt(s));
                        };
                        
                        System.out.println(cellValueList.toString());
                        // only work for integer array
                        if (cellValueList.containsAll(filterList)) {
                            tableItems.add(data2.get(i));
                            break;
                        }

                    }
                }
                tableViewResult2.setItems(tableItems);

            }
        });

//        // click table allOutputList 1 to fill table inputList 2
//        tableViewResult1.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends RecordOutput1> obs, RecordOutput1 oldSelection, RecordOutput1 newSelection) -> {
//            if (newSelection != null) {
//                RecordOutput1 output1 = (RecordOutput1) tableViewResult1.getSelectionModel().getSelectedItem();
//
//                String k = output1.getFKey();
//                k = (String) k.subSequence(1, k.length() - 1);
//                System.out.println("selected : " + k);
//                String[] ks = k.split(",");
//
//                ObservableList<RecordInput2> data = tableViewInput2.getItems();
//                data.clear();
//                for (String s : ks) {
//                    s = s.trim();
//                    data.add(new RecordInput2(s, Character.getNumericValue(s.charAt(0)) + "," + (Character.getNumericValue(s.charAt(0)) + 1)));
//                }
//                System.out.println(data.size());
//
//                ObservableList<RecordInput2> data2 = tableViewResult2.getItems();
//                data2.clear();
//
//            }
//        });
    }

}