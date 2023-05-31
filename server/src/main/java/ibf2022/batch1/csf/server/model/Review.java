package ibf2022.batch1.csf.server.model;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Review {
    private String displayTitle;
    private String mpaaRating;
    private String byline;
    private String headline;
    private String summaryShort;
    private Link link;
    private Multimedia multimedia;
    private Integer reviewCount;

    public String getDisplayTitle() {
        return displayTitle;
    }
    public void setDisplayTitle(String displayTitle) {
        this.displayTitle = displayTitle;
    }
    public String getMpaaRating() {
        return mpaaRating;
    }
    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }
    public String getByline() {
        return byline;
    }
    public void setByline(String byline) {
        this.byline = byline;
    }
    public String getHeadline() {
        return headline;
    }
    public void setHeadline(String headline) {
        this.headline = headline;
    }
    public String getSummaryShort() {
        return summaryShort;
    }
    public void setSummaryShort(String summaryShort) {
        this.summaryShort = summaryShort;
    }
    public Link getLink() {
        return link;
    }
    public void setLink(Link link) {
        this.link = link;
    }
    public Multimedia getMultimedia() {
        return multimedia;
    }
    public void setMultimedia(Multimedia multimedia) {
        this.multimedia = multimedia;
    }
    public Integer getReviewCount() {
        return reviewCount;
    }
    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }
    
    @Override
    public String toString() {
        return "Review [displayTitle=" + displayTitle + ", mpaaRating=" + mpaaRating + ", byline=" + byline
                + ", headline=" + headline + ", summaryShort=" + summaryShort + ", link=" + link + ", multimedia="
                + multimedia + "]";
    }
    
    public Review() {
    }
    public Review(String displayTitle, String mpaaRating, String byline, String headline, String summaryShort, Link link, Multimedia multimedia, Integer reviewCount) {
        this.displayTitle = displayTitle;
        this.mpaaRating = mpaaRating;
        this.byline = byline;
        this.headline = headline;
        this.summaryShort = summaryShort;
        this.link = link;
        this.multimedia = multimedia;
        this.reviewCount = reviewCount;
    }

    public static Review convertFromJson(JsonObject jo) {
        Review r = new Review();
        Link l = new Link();
        Multimedia m = new Multimedia();
        if (!jo.isNull("link")) {
            l.setUrl(jo.getJsonObject("link").getString("url"));
        }
        if (!jo.isNull("multimedia")) {
            m.setSrc(jo.getJsonObject("multimedia").getString("src"));
        } else {
            m.setSrc("null");
        }
        r.setDisplayTitle(jo.getString("display_title"));
        r.setMpaaRating(jo.getString("mpaa_rating"));
        r.setByline(jo.getString("byline"));
        r.setHeadline(jo.getString("headline"));
        r.setSummaryShort(jo.getString("summary_short"));
        r.setLink(l);
        r.setMultimedia(m);
        return r;
    }
    
    public JsonObject toJson() {
        return Json.createObjectBuilder()
        .add("display_title", getDisplayTitle())
        .add("mpaa_rating", getMpaaRating())
        .add("byline", getByline())
        .add("headline", getHeadline())
        .add("summary_short", getSummaryShort())
        .add("link.url", getLink().getUrl())
        .add("multimedia.src", getMultimedia().getSrc())
        .add("reviewCount", getReviewCount())
        .build();
    }
}

class Link {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Link [url=" + url + "]";
    }
}

class Multimedia {
    private String src;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "Multimedia [src=" + src + "]";
    }
}
