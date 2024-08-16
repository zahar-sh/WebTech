package com.example.classes.BasketBall.App;

enum Command {
    HELP("help", "print command list"),
    ADD("add", "creating a new ball and adding it to the basket"),
    REMOVE("remove", "removing ball by index in basket"),
    CLEAR("clear", "delete all balls from basket"),
    PRINT("print", "print all balls from basket"),
    TOTAL_WEIGHT("total-weight", "calculate summary ball weight in basket"),
    BLUE_BALL_COUNT("blue-ball-count", "counting blue ball"),
    EXIT("stop", "finish the process");

    private final String key;
    private final String description;

    Command(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public String infoString() {
        return getKey() + "\t" + getDescription();
    }
}
