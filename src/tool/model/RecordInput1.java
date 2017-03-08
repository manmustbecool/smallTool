/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class RecordInput1 {

    private final SimpleStringProperty fieldKey;
    private final SimpleIntegerProperty fieldValue;

    public RecordInput1(String fKey, Integer fValue) {
        this.fieldKey = new SimpleStringProperty(fKey);
        this.fieldValue = new SimpleIntegerProperty(fValue);
    }

    public String getFieldKey() {
        return fieldKey.get();
    }

    public int getFieldValue() {
        return fieldValue.get();
    }

    public void setFieldKey(String fMonth) {
        fieldKey.set(fMonth);
    }

    public void setFieldValue(Integer fValue) {
        fieldValue.set(fValue);
    }
}
