/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool.model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author emiewag
 */
public class RecordOutput1 {

    private final SimpleStringProperty fIndex;
    private final SimpleStringProperty fKey;
    private final SimpleStringProperty fValue;

    public RecordOutput1(String index, String key, String value) {
        this.fIndex = new SimpleStringProperty(index);
        this.fKey = new SimpleStringProperty(key);
        this.fValue = new SimpleStringProperty(value);
    }

    public String getFIndex() {
        return fIndex.get();
    }

    public String getFKey() {
        return fKey.get();
    }
    
    public String getFValue() {
        return fValue.get();
    }
    
      public void setFIndex(String index) {
        fIndex.set(index);
    }
      
    public void setFKey(String key) {
        fKey.set(key);
    }

    public void setFValue(String value) {
        fValue.set(value);
    }
}
