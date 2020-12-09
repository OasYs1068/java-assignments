import org.jdom.*;

import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;

public class Service {
    private Document doc = null;
    private List newsFeeds = null;
    private FrameMain parent = null;

    public Service(FrameMain frame){
        this.parent = frame;
    }

    public void fileDo(String inFile)
            throws IOException, JDOMException {
        this.doc = ParseXML.readXMLFile(inFile);

        this.newsFeeds = ParseXML.parse(this.doc);

        createTable();
    }

    private void createTable(){
        DefaultTableModel model
                = (DefaultTableModel)parent.getNewsTitleTable().getModel();
        //清空表格中原有数据
        if(model.getRowCount() > 0){
            for(int m = model.getRowCount() - 1; m>=0;m--){
                model.removeRow(m);//删除行
            }
        }
        //添加新的数据
        for(int i = 0;i<this.newsFeeds.size();i++){
            NewsFeed newsFeed = (NewsFeed)this.newsFeeds.get(i);
            String title = newsFeed.getTitle().trim();
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-mm-dd hh:mm:ss");
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            String pubDate = newsFeed.getPubDate();
            String author = newsFeed.getAuthor();

            model.addRow(new Object[]{title,currentDate,pubDate,author});
        }
    }

    public void showMessage(int row)
            throws BadLocationException {
        if(row >= 0 && this.newsFeeds != null){
            NewsFeed newsfeed = (NewsFeed)this.newsFeeds.get(row);
            String content = ParseXML.newsToStr(newsfeed);
            javax.swing.text.Document document = parent.getTextPaneNewsDescription().getDocument();
            document.remove(0,document.getLength());
            document.insertString(0,content,null);
        }
    }

}
