package com.example.funko;

public class ContentProvider {
    int _ID;
    String POP_NAME;
    int POP_NUMBER;
    String POP_TYPE;
    String FANDOM;
    boolean On;
    String Ultimate;
    double price;

    public ContentProvider(int _ID, String POP_NAME, int POP_NUMBER, String POP_TYPE, String FANDOM, boolean on, String ultimate, double price) {
        this._ID = _ID;
        this.POP_NAME = POP_NAME;
        this.POP_NUMBER = POP_NUMBER;
        this.POP_TYPE = POP_TYPE;
        this.FANDOM = FANDOM;
        On = on;
        Ultimate = ultimate;
        this.price = price;
    }

    public int get_ID() {
        return _ID;
    }

    public String getPOP_NAME() {
        return POP_NAME;
    }

    public int getPOP_NUMBER() {
        return POP_NUMBER;
    }

    public String getPOP_TYPE() {
        return POP_TYPE;
    }

    public String getFANDOM() {
        return FANDOM;
    }

    public boolean isOn() {
        return On;
    }

    public String getUltimate() {
        return Ultimate;
    }

    public double getPrice() {
        return price;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public void setPOP_NAME(String POP_NAME) {
        this.POP_NAME = POP_NAME;
    }

    public void setPOP_NUMBER(int POP_NUMBER) {
        this.POP_NUMBER = POP_NUMBER;
    }

    public void setPOP_TYPE(String POP_TYPE) {
        this.POP_TYPE = POP_TYPE;
    }

    public void setFANDOM(String FANDOM) {
        this.FANDOM = FANDOM;
    }

    public void setOn(boolean on) {
        On = on;
    }

    public void setUltimate(String ultimate) {
        Ultimate = ultimate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ContentProvider{" +
                "_ID=" + _ID +
                ", POP_NAME='" + POP_NAME + '\'' +
                ", POP_NUMBER=" + POP_NUMBER +
                ", POP_TYPE='" + POP_TYPE + '\'' +
                ", FANDOM='" + FANDOM + '\'' +
                ", On=" + On +
                ", Ultimate='" + Ultimate + '\'' +
                ", price=" + price +
                '}';
    }
}
