package ui;

import util.Core;
import util.GameModel;
import util.Preferences;
import util.Preferences.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreferencesFrame extends JFrame {

    private JPanel preferencesPanel;

    private JPanel bannerPanel;
    private JPanel gameModePanel;
    private JRadioButton onlineRadioButton;
    private JRadioButton offlineRadioButton;

    private JPanel settingsPanel;

    private JPanel gameSettingsPanel;
    private JPanel gameSettingsSubPanel;
    private JPanel reverseBoardPanel;
    private JCheckBox reverseBoardCheckBox;

    private JPanel networkSettingsPanel;
    private JPanel networkSettingsSubPanel;
    private JPanel networkModePanel;
    private JRadioButton hostGameRadioButton;
    private JRadioButton joinGameRadioButton;
    private JPanel connectionPanel;
    private JPanel ipAndPortPanel;
    private JLabel hostIPLabel;
    private JTextField hostIPTextField;
    private JLabel hostPortLabel;
    private JFormattedTextField hostPortFormattedTextField;

    private JPanel buttonsPanel;
    private JPanel buttonsSubPanel;
    private JButton okButton;
    private JButton cancelButton;

    public PreferencesFrame(){
        super("Preferences");
        loadInterface();
        loadPreferences();
        this.setDefaultCloseOperation(LaunchFrame.EXIT_ON_CLOSE);
    }

    private void loadPreferences(){
        Preferences preferences = Core.getPreferences();
        if(!preferences.isPreferencesComplete()){
            return;
        }
        if (preferences.getNetworkMode() != null) {
            switch (preferences.getGameMode()){
                case ONLINE:
                    onlineRadioButton.setSelected(true);
                    setNetworkSettingsEnabled(true);
                    switch (preferences.getNetworkMode()){
                        case HOST:
                            hostGameRadioButton.setSelected(true);
                            hostIPTextField.setEnabled(false);
                            hostIPTextField.setText(Core.getLocalIPAddress());
                            break;
                        case CLIENT:
                            joinGameRadioButton.setSelected(true);
                            hostIPTextField.setEnabled(true);
                            hostIPTextField.setText(preferences.getHostIP());
                            break;
                    }
                    break;
                case OFFLINE:
                    setNetworkSettingsEnabled(true);
                    offlineRadioButton.setSelected(true);
                    break;
            }
        }
        reverseBoardCheckBox.setSelected(preferences.isBoardReversed());

        if(Core.isInGame()){
            setNetworkSettingsEnabled(false);
        }
    }

    private void loadInterface(){
        initializeBannerPanel();
        initializeSettingsPanel();
        initializeButtonsPanel();

        preferencesPanel = new JPanel(new BorderLayout());
        preferencesPanel.add(bannerPanel,BorderLayout.PAGE_START);
        preferencesPanel.add(settingsPanel, BorderLayout.CENTER);
        preferencesPanel.add(buttonsPanel, BorderLayout.PAGE_END);
        preferencesPanel.setPreferredSize(new Dimension(600,450));

        this.add(preferencesPanel);
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(Core.getLaunchFrame());
    }

    private void initializeBannerPanel(){
        onlineRadioButton = new JRadioButton("Online");
        onlineRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGameSettingsEnabled(!joinGameRadioButton.isSelected());
                setNetworkSettingsEnabled(true);
            }
        });
        offlineRadioButton = new JRadioButton("Offline");
        offlineRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGameSettingsEnabled(true);
                setNetworkSettingsEnabled(false);
            }
        });
        ButtonGroup gameModeButtonGroup = new ButtonGroup();
        gameModeButtonGroup.add(onlineRadioButton);
        gameModeButtonGroup.add(offlineRadioButton);
        gameModePanel = new JPanel();
        gameModePanel.setBackground(Color.LIGHT_GRAY);
        gameModePanel.add(onlineRadioButton);
        gameModePanel.add(offlineRadioButton);

        bannerPanel = new JPanel(new GridBagLayout()){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(
                        new ImageIcon("res/images/preferences_banner.png").getImage(),
                        0,
                        0,
                        null);

            }
        };
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.SOUTHWEST;
        bannerPanel.setPreferredSize(new Dimension(600,120));
        bannerPanel.setBackground(Color.LIGHT_GRAY);
        bannerPanel.add(gameModePanel,c);
    }

    private void initializeSettingsPanel(){
        initializeGameSettingsPanel();
        initializeNetworkSettingsPanel();

        settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        settingsPanel.add(gameSettingsPanel);
        settingsPanel.add(networkSettingsPanel);
    }

    private void initializeGameSettingsPanel(){
        //reverse board
        reverseBoardCheckBox = new JCheckBox("Reverse Board");
        reverseBoardPanel = new JPanel(new BorderLayout());
        reverseBoardPanel.add(reverseBoardCheckBox, BorderLayout.WEST);

        gameSettingsSubPanel = new JPanel(new BorderLayout());
        gameSettingsSubPanel.add(reverseBoardPanel, BorderLayout.CENTER);
        gameSettingsPanel = new JPanel();
        gameSettingsPanel.add(gameSettingsSubPanel);
        gameSettingsPanel.setBorder(BorderFactory.createTitledBorder("Game Settings: "));

    }

    private void initializeNetworkSettingsPanel(){
        //network mode
        joinGameRadioButton = new JRadioButton("Join");
        joinGameRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reverseBoardCheckBox.setSelected(true);
                setGameSettingsEnabled(false);
                hostIPTextField.setEnabled(true);
                onlineRadioButton.setSelected(true);
            }
        });
        hostGameRadioButton = new JRadioButton("Host");
        hostGameRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reverseBoardCheckBox.setSelected(false);
                setGameSettingsEnabled(true);
                hostIPTextField.setText(Core.getLocalIPAddress());
                hostIPTextField.setEnabled(false);
                onlineRadioButton.setSelected(true);
            }
        });
        ButtonGroup networkModeButtonGroup = new ButtonGroup();
        networkModeButtonGroup.add(joinGameRadioButton);
        networkModeButtonGroup.add(hostGameRadioButton);
        networkModePanel = new JPanel();
        networkModePanel.add(joinGameRadioButton);
        networkModePanel.add(hostGameRadioButton);

        //connection panel
        hostIPLabel = new JLabel("Host IP: ");
        hostIPTextField = new JTextField(10);
        hostPortLabel = new JLabel(":");
        hostPortFormattedTextField = new JFormattedTextField("2020");

        connectionPanel = new JPanel();
        ipAndPortPanel = new JPanel();
        ipAndPortPanel.add(hostIPTextField);
        ipAndPortPanel.add(hostPortLabel);
        ipAndPortPanel.add(hostPortFormattedTextField);
        hostIPLabel.setLabelFor(ipAndPortPanel);

        connectionPanel.add(hostIPLabel);
        connectionPanel.add(ipAndPortPanel);
        connectionPanel.setVisible(true);

        networkSettingsSubPanel = new JPanel(new BorderLayout());
        networkSettingsSubPanel.add(networkModePanel, BorderLayout.PAGE_START);
        networkSettingsSubPanel.add(connectionPanel, BorderLayout.PAGE_END);
        networkSettingsPanel = new JPanel();
        networkSettingsPanel.add(networkSettingsSubPanel);
        networkSettingsPanel.setBorder(BorderFactory.createTitledBorder("Network Settings: "));

    }

    private void initializeButtonsPanel(){
        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(submitPreference()){
                    if(!Core.isInGame()){
                        GameModel gameModel = Core.startGame();
                        new GameFrame(gameModel);
                    }
                    dispose();
                }else{
                    showIncompleteDialog();
                }
            }
        });
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Core.getLaunchFrame().setVisible(true);
                dispose();
            }
        });
        buttonsSubPanel = new JPanel();
        buttonsSubPanel.add(cancelButton);
        buttonsSubPanel.add(okButton);
        buttonsPanel = new JPanel(new BorderLayout());
        buttonsPanel.add(buttonsSubPanel, BorderLayout.EAST);
    }

    private void setGameSettingsEnabled(boolean b){
        reverseBoardCheckBox.setEnabled(b);
        gameSettingsPanel.setEnabled(b);
    }

    private void setNetworkSettingsEnabled(boolean b){
        joinGameRadioButton.setEnabled(b);
        hostGameRadioButton.setEnabled(b);
        hostIPLabel.setEnabled(b);
        hostIPTextField.setEnabled(b);
        hostIPTextField.setEditable(b);
        hostPortLabel.setEnabled(b);
        hostPortFormattedTextField.setEnabled(b);
        networkSettingsPanel.setEnabled(b);
    }

    private boolean submitPreference(){
        Preferences preferences = Core.getPreferences();
        if(onlineRadioButton.isSelected()){
            preferences.setGameMode(GameMode.ONLINE);
            if(joinGameRadioButton.isSelected()){
                preferences.setNetworkMode(NetworkMode.CLIENT);
            }
            if(hostGameRadioButton.isSelected()){
                preferences.setNetworkMode(NetworkMode.HOST);
            }
            preferences.setHostIP(hostIPTextField.getText());
            preferences.setPort(Integer.parseInt(hostPortFormattedTextField.getText()));
        }
        if(offlineRadioButton.isSelected()) {
            preferences.setGameMode(GameMode.OFFLINE);
        }
        preferences.setBoardReversed(reverseBoardCheckBox.isSelected());
        return preferences.isPreferencesComplete();
    }

    private void showIncompleteDialog(){
        JOptionPane.showMessageDialog(this,
                "Please set all necessary preferences.",
                "Unfinished Preferences",
                JOptionPane.WARNING_MESSAGE);
    }

}
