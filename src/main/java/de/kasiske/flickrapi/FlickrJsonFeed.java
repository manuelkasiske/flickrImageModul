package de.kasiske.flickrapi;


import java.util.List;

public class FlickrJsonFeed {

    private String title;
    private String link;
    private List<FlickrImage> items;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<FlickrImage> getItems() {
        return items;
    }

    public void setItems(List<FlickrImage> items) {
        this.items = items;
    }
}
