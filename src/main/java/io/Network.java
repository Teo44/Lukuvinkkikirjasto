package io;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import logic.Logic;


public class Network {
    
    private final String baseUrl;
    private final String urlOptions;
    private Logic logic;
    private URL url;
    
    public Network(Logic logic) {
        // ISBN code for Harry Potter and the Deadly Hallows
        // "9780545010221"
        // Fifty Shades of Gray: "97834UE0348"
        this.logic = logic;
        this.baseUrl = "https://openlibrary.org/api/books?bibkeys=ISBN:";
        this.urlOptions = "&jscmd=data&format=json";
    }

    public boolean fetchAndSaveBookByISBN(String isbn) {
        JsonParser jp = new JsonParser();   
        JsonElement rootJsonObj = null;
        
        String author = "";
        String title = null;
        
        try {
            openConnection(isbn);
            URLConnection req = this.url.openConnection();
            req.connect();
            rootJsonObj = jp.parse(new InputStreamReader((InputStream) req.getContent()));
        } catch (Exception e) {
            System.out.println("Error when trying to fetch book details: " + e.getMessage());
            return false;
        }
        
        JsonObject rootobj = rootJsonObj.getAsJsonObject();
        JsonObject book = rootobj.getAsJsonObject("ISBN:"+isbn);
        
        if (book == null)   {
            return false;
        }
        
        title = book.get("title").getAsString();
        
        JsonArray authors = book.getAsJsonArray("authors");
        if (authors != null && authors.size() != 0) {
            JsonObject authorJson = authors.get(0).getAsJsonObject();
            author = authorJson.get("name").getAsString();
        }
        
        logic.saveVink(title, "book", new ArrayList<>(), "", "", author);
        
        return true;
    }
    
    private void openConnection(String isbn) throws MalformedURLException {
            this.url = new URL(this.baseUrl + isbn + this.urlOptions);
    }
    
}
