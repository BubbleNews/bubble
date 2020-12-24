package us.bubblenews.bubbleserver.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ScrapedArticle {

    private String title;

    private String url;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
    private Date timePublished;

    private String sourceName;

    private String rawContent;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getTimePublished() {
        return timePublished;
    }

    public void setTimePublished(Date timePublished) {
        this.timePublished = timePublished;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getRawContent() {
        return rawContent;
    }

    public void setRawContent(String rawContent) {
        this.rawContent = rawContent;
    }
}
