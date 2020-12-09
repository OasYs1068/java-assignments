import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ParseXML {
    public static Document readXMLFile(String inFile)
            throws JDOMException, IOException {
        Document doc = null;

        SAXBuilder sb = new SAXBuilder(false);

        File fXml = new File(inFile);
        if(fXml.exists() && fXml.isFile()){

            doc = sb.build(fXml);

        }
        return doc;
    }
    private static void testRead(Document doc){
        try{
            String xmlStr = null;
            Format format = Format.getPrettyFormat();
            format.setEncoding("utf-8");
            XMLOutputter xmlOut = new XMLOutputter(format);
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            xmlOut.output(doc,bo);
            xmlStr = bo.toString();
            System.out.println(xmlStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List parse(Document doc){
        List newsFeeds = new ArrayList();
        NewsFeed newsFeed = null;
        Element root = doc.getRootElement();

        Element eChannel = root.getChild("channel");
        List<Element> itemList = eChannel.getChildren("item");

        for(int i=0; i < itemList.size(); i++){
            Element item = itemList.get(i);
            newsFeed = itemToFeed(item);
            newsFeeds.add(newsFeed);
        }
        return newsFeeds;
    }

    public static String newsToStr(NewsFeed newsfeed){
        String content = null;
        String title = newsfeed.getTitle().trim();
        String author = newsfeed.getAuthor();
        String pubDate = newsfeed.getPubDate();
        String link = newsfeed.getLink();
        String description = newsfeed.getDescription().trim();

        content = "标题： "
                + title
                + "\r\n"
                + "链接： "
                + link
                + "\r\n"
                + "作者： "
                + author
                + "\r\n"
                + "发布时间： "
                + pubDate
                + "\r\n"
                + "--------------------------------------------------------------\n"
                + description+"\r\n\r\n";

        return content;
    }

    public static NewsFeed itemToFeed(Element item){
        NewsFeed newsFeed = new NewsFeed();

        String title = item.getChildText("title").trim();
        String link = item.getChildText("link");
        String author = item.getChildText("author");
        String guid = item.getChildText("pubDate");
        String category = item.getChildText("category");
        String description = item.getChildText("description");

        newsFeed.setTitle(title);
        newsFeed.setAuthor(author);
        newsFeed.setCategory(category);
        newsFeed.setDescription(description);
        newsFeed.setGuid(guid);
        newsFeed.setLink(link);

        return newsFeed;
    }

    private static void testParse(List newsFeeds){
        for(int i = 0; i<newsFeeds.size();i++){
            NewsFeed newsFeed2 = (NewsFeed)newsFeeds.get(i);
            System.out.println("标题： "+newsFeed2.getTitle());
            System.out.println("链接： "+newsFeed2.getLink());
            System.out.println("作者： "+newsFeed2.getAuthor());
            System.out.println("地址： "+newsFeed2.getGuid());
            System.out.println("发布时间： "+newsFeed2.getPubDate());
            System.out.println("新闻类型： "+newsFeed2.getCategory());
            System.out.println("新闻描述： "+newsFeed2.getDescription());
            System.out.println("-----------------------------------------------------------------------");
        }
    }
}
