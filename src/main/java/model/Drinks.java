package model;

public enum Drinks {
    TEA('T', "tea"),
    COFFEE('C', "coffee"),
    CHOCOLATE('H', "chocolate");

    private char code;
    private String name;

    Drinks(char code, String name) {
        this.code = code;
        this.name = name;
    }

    public char getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
