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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import java.io.IOException;
import java.net.URISyntaxException;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FlickrJsonApiTest {

    @Spy
    FlickrJsonApi flickrJsonApi = new FlickrJsonApi();

    @Test
    public void testParseFlickrJsonFeed() throws IOException, URISyntaxException {
        String answer = "{\n" +
                "\t\t\"title\": \"Recent Uploads tagged silvester\",\n" +
                "\t\t\"link\": \"http://www.flickr.com/photos/tags/silvester/\",\n" +
                "\t\t\"description\": \"\",\n" +
                "\t\t\"modified\": \"2015-11-19T17:40:22Z\",\n" +
                "\t\t\"generator\": \"http://www.flickr.com/\",\n" +
                "\t\t\"items\": [\n" +
                "\t   {\n" +
                "\t\t\t\"title\": \"IMG_0022\",\n" +
                "\t\t\t\"link\": \"http://www.flickr.com/photos/kleinemaus50/22728772797/\",\n" +
                "\t\t\t\"media\": {\"m\":\"http://farm1.staticflickr.com/715/22728772797_8b6f1c19b6_m.jpg\"},\n" +
                "\t\t\t\"date_taken\": \"2015-10-25T16:10:20-08:00\",\n" +
                "\t\t\t\"description\": \" <p><a href=\\\"http://www.flickr.com/people/kleinemaus50/\\\">spoeker<\\/a> posted a photo:<\\/p> <p><a href=\\\"http://www.flickr.com/photos/kleinemaus50/22728772797/\\\" title=\\\"IMG_0022\\\"><img src=\\\"http://farm1.staticflickr.com/715/22728772797_8b6f1c19b6_m.jpg\\\" width=\\\"240\\\" height=\\\"154\\\" alt=\\\"IMG_0022\\\" /><\\/a><\\/p> \",\n" +
                "\t\t\t\"published\": \"2015-11-19T17:40:22Z\",\n" +
                "\t\t\t\"author\": \"nobody@flickr.com (spoeker)\",\n" +
                "\t\t\t\"author_id\": \"8151787@N05\",\n" +
                "\t\t\t\"tags\": \"party feet analog cn 35mm germany hearts stars deutschland rainbow lomo lomography kiss colours cologne newyear lips confetti analogue colourful unicorn herz silvester kb neujahr bunt regenbogen kuss einhorn sterne köln singleuse kodak800 lippen konfetti einwegkamera füse unicornsrainbowsandothercrazyshit vorbelichtet\"\n" +
                "\t   }]}";
        when(flickrJsonApi.getJsonString(anyString())).thenReturn(answer);
        FlickrJsonFeed feed = flickrJsonApi.getResult("Silvester");
        assertNotNull(feed);
        assertEquals(1, feed.getItems().size());
    }



}
