import org.jdom.JDOMException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;

public class FrameMain extends JFrame {
    /**版本控制标识*/
    private static final long serialVersionUID = 1L;
    /**主面板属性*/
    private final static int PANEL_WIDTH = 780;
    private final static int PANEL_HEIGHT = 600;
    private final static String PANEL_TITLE_NAME = "RSS阅读器";

    /**菜单面板控件*/
    private JComboBox comboBox;
    private JButton readButton;
    private JButton saveButton;
    private JTextField newsTitleText;
    private JButton queryButton;

    private JTable newsTitleTable;
    private DefaultTableModel defaultModel;
    private JTextPane textPaneNewsDescription;
    private Service service;

    public FrameMain(){
        service = new Service(this);
        setSize(800,600);
        setTitle("RSS阅读器");
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //添加主面板
        setContentPane(getContentPanel());

        //将swing窗口在桌面居中显示
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width/2;
        int screenHeight = screenSize.height/2;
        int height = this.getHeight();
        int width = this.getWidth();

        setLocation(screenWidth - width / 2,
                screenHeight - height / 2);
    }

    private JPanel getContentPanel(){
        JPanel panel = new JPanel();

        panel.add(getMenuPanel());
        panel.add(getTableScrollPanel());
        panel.add(getTextScrollPanel());
        return panel;
    }

    private JPanel getMenuPanel(){
        JPanel menuPanel = new JPanel();
        menuPanel.add(new JLabel("站点"));
        menuPanel.add(getComboBox());
        menuPanel.add(getReadButton());
        menuPanel.add(getSaveButton());

        menuPanel.add(new JLabel("查询标题"));
        menuPanel.add(getNewsTitleText());
        menuPanel.add(getQueryButton());
        return menuPanel;
    }
    private JScrollPane getTableScrollPanel(){
        JScrollPane tableScrollPanel = new JScrollPane();
        tableScrollPanel.setViewportView(getNewsTitleTable());
        tableScrollPanel.setPreferredSize(new Dimension(750,250));
        return tableScrollPanel;
    }
    private JScrollPane getTextScrollPanel(){
        JScrollPane messageScrollPanel = new JScrollPane();
        messageScrollPanel.setViewportView(getTextPaneNewsDescription());
        messageScrollPanel.setPreferredSize(new Dimension(750,250));
        return messageScrollPanel;
    }
    /*控件初始化*/
    public JComboBox getComboBox(){
        if(comboBox == null){
            //创建下拉组合框
            comboBox = new JComboBox();
            comboBox.addItem(
                    new URL("新浪新闻",
                            "focus15.xml"));
            comboBox.addItem(
                    new URL("Walls Street Markets",
                            "RSSMarketsMain.xml"));
            comboBox.addItem(
                    new URL("Walls Street Opinion",
                            "RSSOpinion.xml"));
            comboBox.addItem(
                    new URL("Walls Street WorldNews",
                            "RSSWorldNews.xml"));
        }

        return comboBox;
    }

    public JButton getReadButton(){
        if(readButton == null){
            readButton = new JButton("读取");
            readButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inFile;
                    String errorInfo = " ";
                    String address = " ";


                    //获得下拉框选项
                    Object o = comboBox.getSelectedItem();
                    String dir = System.getProperty("user.dir");
                    address = ((URL)o).getAddress();
                    inFile = dir+"\\newsFeedFiles\\"+address;
                    try{
                        //转换路径编码为UTF-8
                        inFile = URLDecoder.decode(inFile,"utf-8");
                        service.fileDo(inFile);
                    }catch(IOException ioe){
                        errorInfo = "系统找不到指定文件";
                    }catch(JDOMException jde){
                        errorInfo = "解析文件异常";
                    }finally {
                        if (errorInfo != " ") {
                            showError(errorInfo);
                        }
                    }
                }
            });
        }
        return readButton;
    }

    @SuppressWarnings("Qt: Untested Windows version 10.0 detected!")
    public JButton getSaveButton(){
        if(saveButton == null){
            saveButton = new JButton("保存");
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    fileChooser.showSaveDialog(FrameMain.this);
                    File saveFile = fileChooser.getSelectedFile();
                    try{
                        BufferedWriter in = new BufferedWriter(new FileWriter(saveFile));
                        in.write(textPaneNewsDescription.getText());
                        in.close();
                        JOptionPane.showMessageDialog(FrameMain.this,"Save Successful","Success",JOptionPane.PLAIN_MESSAGE);
                    }catch(IOException e2){
                        e2.printStackTrace();
                    }
                }
            });
        }
        return saveButton;
    }

    public JTextField getNewsTitleText(){
        if(newsTitleText == null){
            newsTitleText = new JTextField();
            newsTitleText.setColumns(30);
        }
        return newsTitleText;
    }

    public JButton getQueryButton(){
        if(queryButton == null){
            queryButton = new JButton("查询");
            queryButton.addActionListener(new ActionListener() {
                int i = 0;
                @Override
                public void actionPerformed(ActionEvent e) {
                    readButton.doClick();
                    String checkUp = newsTitleText.getText();
                    String tempTitle = null;
                    while(i<newsTitleTable.getRowCount()){
                        tempTitle = (String) newsTitleTable.getValueAt(i,0);
                        if(tempTitle.contains(checkUp)){
                            newsTitleTable.setRowSelectionInterval(i,i);
                            break;
                        }
                        i++;
                    }
                    if(i == newsTitleTable.getRowCount()){
                        i = 0;
                    }
                }
            });
        }
        return queryButton;
    }

    public JTable getNewsTitleTable(){
        if(newsTitleTable == null){
            newsTitleTable = new JTable();
            defaultModel = new DefaultTableModel();
            defaultModel.addColumn("主题");
            defaultModel.addColumn("接收时间");
            defaultModel.addColumn("发布时间");
            defaultModel.addColumn("作者");

            newsTitleTable.setModel(defaultModel);
            newsTitleTable.getSelectionModel().setSelectionMode(
                    ListSelectionModel.SINGLE_SELECTION);

            newsTitleTable.getSelectionModel().addListSelectionListener(
                    new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    int row = newsTitleTable.getSelectedRow();

                    try{
                        service.showMessage(row);
                    }catch (Exception ex){
                        showError("文档转换操作异常： "+ex.getMessage());
                    }
                }
            });
        }
        return newsTitleTable;
    }

    public JTextPane getTextPaneNewsDescription(){
        if(textPaneNewsDescription == null){
            textPaneNewsDescription = new JTextPane();
            textPaneNewsDescription.setEditable(false);
        }
        return textPaneNewsDescription;
    }

    public void showError(String errorInfo){
        JOptionPane.showMessageDialog(this,errorInfo,"Error",JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        FrameMain frame = new FrameMain();
        frame.setVisible(true);
    }
}
