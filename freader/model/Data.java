
package freader.model;

/**
 * Valeur d'un champ.
 * @author chris-scientist
 */
public class Data {
    
    //private Field field;
    
    private String value;
    
    public Data(String value) {
        this.value = value;
    }

//    public Field getField() {
//        return field;
//    }
//
//    public void setField(Field field) {
//        this.field = field;
//    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
