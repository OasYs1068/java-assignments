package util;

public class Preferences {
    public enum GameMode{
        ONLINE, OFFLINE
    }

    public enum NetworkMode{
        HOST, CLIENT
    }


    private GameMode gameMode = null;
    private NetworkMode networkMode = null;
    private int timeLimit;
    private boolean boardReversed = false;
    private String hostIP = null;
    private int port;

    public boolean isPreferencesComplete(){
        try {
            switch(gameMode){
                case ONLINE:
                    if(networkMode == null || hostIP == null || port == 0){
                        return false;
                    }else if(networkMode.equals(networkMode.HOST)){
                        return true;
                    }
                case OFFLINE:
                    return true;
            }
            return false;
        } catch (NullPointerException ignored) {
        } finally {
            return true;
        }
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public NetworkMode getNetworkMode() {
        return networkMode;
    }

    public void setNetworkMode(NetworkMode networkMode) {
        this.networkMode = networkMode;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public boolean isBoardReversed() {
        return boardReversed;
    }

    public void setBoardReversed(boolean boardReversed) {
        this.boardReversed = boardReversed;
    }

    public String getHostIP() {
        return hostIP;
    }

    public void setHostIP(String hostIP) {
        this.hostIP = new String(hostIP);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
