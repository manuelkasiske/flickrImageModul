package de.kasiske.flickrapi;


import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;


public class FlickrJsonApi {

    public String getJsonString(String tag) throws IOException, URISyntaxException {
        HttpClient client = HttpClientBuilder.create().build();

        URIBuilder builder = new URIBuilder();
        builder.setScheme("http").setHost("api.flickr.com").setPath("/services/feeds/photos_public.gne")
                .setParameter("format", "json")
                .setParameter("tags", tag);
        URI uri = builder.build();
        HttpGet request = new HttpGet(uri);
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return toJsonString(result);
    }

    public FlickrJsonFeed getResult(String tag) throws IOException, URISyntaxException {
        Gson gson = new Gson();
        FlickrJsonFeed feed = gson.fromJson(getJsonString(tag), FlickrJsonFeed.class);
        return feed;
    }


    private String toJsonString(StringBuffer result) {
        return result.toString().replace("jsonFlickrFeed(", "").replace("]})", "]}");
    }


}
