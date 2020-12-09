public class NewsFeed {
    private String title = "";
    private String link = "";
    private String author = "";
    private String guid = "";
    private String category = "";
    private String pubDate = "";
    private String comments = "";
    private String description = "";

    public String getPubDate() {
        return pubDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getGuid() {
        return guid;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getComments() {
        return comments;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}
