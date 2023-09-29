package util.helpers;

public class Wrapper {

    private Integer intValue;
    private String strValue;

    public Wrapper(Integer value) {
        this.intValue = value;
    }

    public Wrapper(String value) {
        this.strValue = value;
    }

    public boolean isInteger() {
        return intValue != null;
    }

    public int getInt() {
        return intValue;
    }

    public String getStrValue() {
        return strValue;
    }

    public boolean isEqual(String s) {
        return (!this.isInteger() && this.getStrValue().toLowerCase().equals(s.toLowerCase()));
    }

}