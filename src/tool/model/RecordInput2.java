/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool.model;

import javafx.beans.property.SimpleStringProperty;


public class RecordInput2 {

    private final SimpleStringProperty fieldKey;
    private final SimpleStringProperty fieldValue;

    public RecordInput2(String fKey, String fValue) {
        this.fieldKey = new SimpleStringProperty(fKey);
        this.fieldValue = new SimpleStringProperty(fValue);
    }

    public String getFieldKey() {
        return fieldKey.get();
    }

    public String getFieldValue() {
        return fieldValue.get();
    }

    public void setFieldKey(String fKey) {
        fieldKey.set(fKey);
    }

    public void setFieldValue(String fValue) {
        fieldValue.set(fValue);
    }
}
