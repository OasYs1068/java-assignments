package ui;

import util.Core;
import util.GameModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class GameFrame extends JFrame implements Observer {

    private GameModel gameModel;

    private JMenuBar menuBar;
    private JMenu gameMenu;
    private JMenuItem newGameMenuItem;
    private JMenuItem exitMenuItem;

    private JMenu helpMenu;
    private JMenuItem aboutMenuItem;

    private JPanel boardPanel;
    private JPanel timerPanel;
    private JPanel controlPanel;
    private JPanel moveHistoryPanel;

    public GameFrame(GameModel gameModel){
        this.boardPanel = gameModel.getBoardPanel();
        this.timerPanel = gameModel.getTimerPanel();
        this.controlPanel = gameModel.getControlPanel();
        this.moveHistoryPanel = gameModel.getMoveHistoryPanel();
        loadInterface();
        this.setDefaultCloseOperation(LaunchFrame.EXIT_ON_CLOSE);
        gameModel.addObserver(this);
    }

    public void update(Observable o, Object arg){}

    public void showCheckDialog(){
        JOptionPane.showMessageDialog(null,"That's a Check!","Check",JOptionPane.WARNING_MESSAGE);
    }

    public void showWhiteWinDialog(){
        this.setVisible(false);
        JOptionPane.showMessageDialog(null,"Game Over, the White wins!", "Result", JOptionPane.PLAIN_MESSAGE);
    }

    public void showBlackWinDialog(){
        this.setVisible(false);
        JOptionPane.showMessageDialog(null,"Game Over, the Black wins!", "Result", JOptionPane.PLAIN_MESSAGE);
    }

    private void loadInterface(){
        initializeMenuBar();
        initializePanels();
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(Core.getLaunchFrame());
        this.setVisible(true);
    }

    private void initializeMenuBar(){
        //game menu
        newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int decision = JOptionPane.showConfirmDialog(getParent(),"Do you really want to start a new game?", "New Game",JOptionPane.OK_CANCEL_OPTION);
                if(decision == JOptionPane.OK_OPTION){
                    Core.launch();
                    dispose();
                }
            }
        });

        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userChoice = JOptionPane.showConfirmDialog(getContentPane(),
                        "Are you sure you want to exit?",
                        "Confirm exit",
                        JOptionPane.YES_NO_OPTION);
                if(userChoice == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });

        gameMenu = new JMenu("Game");
        gameMenu.add(newGameMenuItem);
        gameMenu.addSeparator();
        gameMenu.add(exitMenuItem);

        //help menu
        aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(GameFrame.this,"This is a chess game.","About",JOptionPane.PLAIN_MESSAGE);
            }
        });

        helpMenu = new JMenu("Help");
        helpMenu.add(aboutMenuItem);

        // menu bar
        menuBar = new JMenuBar();
        menuBar.add(gameMenu);
        menuBar.add(helpMenu);
        menuBar.setVisible(true);
        this.setJMenuBar(menuBar);
    }

    private void initializePanels(){
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(gridBagLayout);

        //BoardPanel
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        c.gridheight = 4;
        this.add(boardPanel,c);

        //TimerPanel
        c.gridx = 4;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        this.add(timerPanel,c);

        //MoveHistoryPanel
        c.gridx = 4;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 2;
        this.add(moveHistoryPanel,c);

        //ControlPanel
        c.gridx = 4;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        this.add(controlPanel,c);

    }

}
