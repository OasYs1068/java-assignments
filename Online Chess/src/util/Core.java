package util;

import ui.*;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class Core {
    private static Core coreInstance = new Core();
    private static boolean inGame;

    private static GameModel gameModel;
    private static  Preferences preferences;

    private static LaunchFrame launchFrame;
    private static PreferencesFrame preferencesFrame;

    private Core(){}

    public static Core getInstance(){
        return coreInstance;
    }

    public static void launch(){
        inGame = false;
        preferences = new Preferences();
        launchFrame = new LaunchFrame();
    }

    public static GameModel startGame(){
        inGame = true;
        gameModel = new GameModel();
        return gameModel;
    }

    public static String getLocalIPAddress(){
        try{
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "localhost";
        }
    }

    public static Preferences getPreferences() {
        return preferences;
    }

    public static LaunchFrame getLaunchFrame() {
        return launchFrame;
    }

    public static PreferencesFrame getPreferencesFrame() {
        return preferencesFrame;
    }

    public static boolean isInGame() {
        return inGame;
    }

}
