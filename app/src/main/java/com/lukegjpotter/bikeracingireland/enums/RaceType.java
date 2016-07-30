package com.lukegjpotter.bikeracingireland.enums;

public enum RaceType {

    // Race Types
    APLUS("A+", "APlus"),
    A1("A1"),
    A2("A2"),
    A3("A3"),
    A4("A4"),
    VETS("Vets"),
    WOMAN("Woman"),
    JUNIOR("Junior"),
    YOUTH("Youth"),
    PARACYCLING("Paracycling");


    // Instance Fields, Methods
    private String uiText, dbText;

    RaceType(String type) {
        this.uiText = type;
        this.dbText = "is" + type;
    }

    RaceType(String uiText, String dbText) {
        this.uiText = uiText;
        this.dbText = dbText;
    }

    public String getUiText() {
        return uiText;
    }

    public String getDbText() {
        return dbText;
    }

    @Override
    public String toString() {
        return getDbText();
    }
}
