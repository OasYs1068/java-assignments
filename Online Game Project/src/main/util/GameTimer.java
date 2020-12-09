package main.util;


public class GameTimer {

    private static GameTimer ourInstance = new GameTimer();


    public static GameTimer getInstance() {
        return ourInstance;
    }

    private GameTimer() {
    }
}
