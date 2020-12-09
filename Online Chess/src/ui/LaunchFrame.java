package ui;

import util.Core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchFrame extends JFrame {
    private JPanel bannerPanel;
    private JLabel bannerLabel;

    private JPanel buttonsPanel;
    private JPanel newGameButtonPanel;
    private JPanel exitGameButtonPanel;
    private JButton newGameButton;
    private JButton exitGameButton;

    public LaunchFrame(){
        super("Chess Launcher");
        loadInterface();
    }

    private void loadInterface(){
        initializeBannerPanel();
        initializeButtonsPanel();

        this.setLayout(new BorderLayout());
        this.add(bannerPanel, BorderLayout.NORTH);
        this.add(buttonsPanel, BorderLayout.SOUTH);

        this.pack();
        this.setDefaultCloseOperation(LaunchFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
    }

    private void initializeBannerPanel(){
        bannerLabel = new JLabel();
        bannerLabel.setIcon(new ImageIcon("res/images/launch_banner.png"));
        bannerPanel = new JPanel();
        bannerPanel.add(bannerLabel);
        bannerPanel.setPreferredSize(new Dimension(600,250));
        bannerPanel.setBackground(Color.WHITE);
    }

    private void initializeButtonsPanel(){
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PreferencesFrame preferencesFrame = new PreferencesFrame();
                preferencesFrame.setLocationRelativeTo(Core.getLaunchFrame());
                preferencesFrame.setVisible(true);
                Core.getLaunchFrame().dispose();
            }
        });
        newGameButtonPanel = new JPanel(new GridLayout(1,1));
        newGameButtonPanel.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
        newGameButtonPanel.add(newGameButton);
        exitGameButton = new JButton("Exit Game");
        exitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        exitGameButtonPanel = new JPanel(new GridLayout(1,1));
        exitGameButtonPanel.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
        exitGameButtonPanel.add(exitGameButton);

        buttonsPanel = new JPanel(new GridLayout(1,2));
        buttonsPanel.setPreferredSize(new Dimension(600,150));
        buttonsPanel.add(newGameButtonPanel);
        buttonsPanel.add(exitGameButtonPanel);
    }
}
