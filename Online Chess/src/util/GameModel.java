package util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import board.Board;

import piecesMechanic.Piece;

import ui.*;

public class GameModel extends Observable {
    private Preferences preferences;

    private GameFrame gameFrame;
    private BoardPanel boardPanel;
    private TimerPanel timerPanel;
    private ControlPanel controlPanel;
    private MoveHistoryPanel moveHistoryPanel;
    private WaitingDialog waitingDialog;

    private Timer whiteTimer;
    private Timer blackTimer;

    private NetworkHandler networkHandler;
    private String opponentName;

    public GameModel(){
        this.preferences = Core.getPreferences();
        initialize();
    }

    private void initialize() {
        initializeTimers();
        initializeUIComponents();
        if (preferences.getGameMode().equals(Preferences.GameMode.ONLINE)) {
            initializeNetworkHandler();
            if (Preferences.NetworkMode.HOST.equals(preferences.getNetworkMode())) {
                gameFrame.setVisible(false);
                waitingDialog.setVisible(true);
            }
        }
    }

    public void onHandshake() {
        if (waitingDialog != null) {
            waitingDialog.setVisible(false);
            waitingDialog.dispose();
        }
        gameFrame.setVisible(true);
    }

    public void onMoveRequest(char originFile, int originRank, char destinationFile, int destinationRank) {
        switch (preferences.getGameMode()) {
            case ONLINE:
                onOutboundRemoteMoveRequest(originFile, originRank, destinationFile, destinationRank);
                break;
            case OFFLINE:
                onLocalMoveRequest(originFile, originRank, destinationFile, destinationRank);
                break;
        }
    }

    private void onLocalMoveRequest(char originFile, int originRank, char destinationFile, int destinationRank) {
        Move move = new Move(originFile, originRank, destinationFile, destinationRank);
        if (MoveValidator.isMoveValid(move)) {
            executeMove(move);
        } else {

        }
    }

    public boolean onOutboundRemoteMoveRequest(char originFile, int originRank, char destinationFile, int destinationRank) {
        Move move = new Move(originFile, originRank, destinationFile, destinationRank);
        switch (preferences.getNetworkMode()) {
            case HOST:
                if (MoveValidator.isMoveValid(move)) {
                    executeMove(move);
                    if (!networkHandler.sendMoveMessage(move)) {
                        // failed to send
                        return false;
                    }
                    return true;
                } else {
                    return false;
                }
            case CLIENT:
                if (!networkHandler.sendMoveMessage(move)) {
                    // failed to send
                    return false;
                }
                return true;
        }
        return false;
    }

    public boolean onInboundRemoteMoveRequest(char originFile, int originRank, char destinationFile, int destinationRank) {
        Move move = new Move(originFile, originRank, destinationFile, destinationRank);
        switch (preferences.getNetworkMode()) {
            case HOST:
                if (MoveValidator.isMoveValid(move)) {
                    executeMove(move);
                    return true;
                } else {
                    return false;
                }
            case CLIENT:
                executeMove(move);
                return true;
        }
        return false;
    }

    private void executeMove(Move move) {
        MoveLogger.addMove(move);
        Board.executeMove(move);
        moveHistoryPanel.printMove(move);
        boardPanel.executeMove(move);
        switchTimer(move);
        //check every time a piece is captured if it is a king
        if (move.getCapturedPiece() != null) {
            if(move.getCapturedPiece().getType().equals(Piece.Type.KING)){
                if(move.getCapturedPiece().getColor().equals(Piece.Color.WHITE)){
                    gameFrame.showBlackWinDialog();
                }else{
                    gameFrame.showWhiteWinDialog();
                }
            }
        }
    }

    public void onUndoRequest() {
        Move lastMove = MoveLogger.undoLastMove();
        if (lastMove != null) {
            if (MoveValidator.isUndoValid(lastMove)) {
                boardPanel.executeUndo(lastMove);
                moveHistoryPanel.deleteLastMove();
                Board.executeUndo(lastMove);
                switchTimer(lastMove);
            }
        }
        // TODO timer update
    }

    public Piece queryPiece(char file, int rank) {
        return Board.getSquare(file, rank).getCurrentPiece();
    }


    private void initializeUIComponents() {
        boardPanel = new BoardPanel(this);
        timerPanel = new TimerPanel(this);
        controlPanel = new ControlPanel(this);
        moveHistoryPanel = new MoveHistoryPanel(this);
        gameFrame = new GameFrame(this);
        if (Preferences.GameMode.ONLINE.equals(preferences.getGameMode()) && Preferences.NetworkMode.HOST.equals(preferences.getNetworkMode())) {
            waitingDialog = new WaitingDialog(this);
        }
    }

    private void initializeTimers() {
        whiteTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerPanel.whiteTimerTikTok();
            }
        });
        blackTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerPanel.blackTimerTikTok();
            }
        });
    }

    private void initializeNetworkHandler() {
        networkHandler = new NetworkHandler(this);
        new Thread(networkHandler).start();
    }

    private void switchTimer(Move move) {
        if (move.getPiece().getColor().equals(Piece.Color.BLACK)) {
            whiteTimer.start();
            blackTimer.stop();
        } else {
            blackTimer.start();
            whiteTimer.stop();
        }
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public TimerPanel getTimerPanel() {
        return timerPanel;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public MoveHistoryPanel getMoveHistoryPanel() {
        return moveHistoryPanel;
    }

    public WaitingDialog getWaitingDialog() {
        return waitingDialog;
    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

}
