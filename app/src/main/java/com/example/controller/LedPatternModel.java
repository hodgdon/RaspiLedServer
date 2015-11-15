package com.example.controller;

@SuppressWarnings("CanBeFinal")
class LedPatternModel {
    @SuppressWarnings("unused")
    private LedPatternModel(boolean red, boolean green, boolean blue, boolean yellow) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.yellow = yellow;
    }

    @SuppressWarnings("unused")
    public LedPatternModel() {
        this(false, false, false, false);
    }

    public boolean red;
    public boolean green;
    public boolean blue;
    public boolean yellow;
}
