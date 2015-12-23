/*
 The MIT License (MIT)

 Copyright (c) 2015 Manuel Kasiske

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */
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
