/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author 
 */
public class RecordComparator {
    private final SimpleStringProperty fieldCsv;
    private final SimpleBooleanProperty fieldMatch;

    public RecordComparator(String fieldCsv, boolean fieldMatch) {
        this.fieldCsv = new SimpleStringProperty(fieldCsv);
        this.fieldMatch = new SimpleBooleanProperty(fieldMatch);
    }

    public void setFieldCsv(String fCsv) {
        fieldCsv.set(fCsv);
    }

    public void setFieldMatch(boolean fMatch) {
        fieldMatch.set(fMatch);
    }
    
    public String getFieldCsv() {
        return fieldCsv.get();
    }

    public boolean getFieldMatch() {
        return fieldMatch.get();
    }
    
}
