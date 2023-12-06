package org.matbcfs;

public class NumberFound {
    private int value;
    private int index;

    public NumberFound(){
        this.index = 0;
        this.value = 0;
    }

    public void printData(){
        System.out.println(this.getValue() +"@"+ this.getIndex());
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
