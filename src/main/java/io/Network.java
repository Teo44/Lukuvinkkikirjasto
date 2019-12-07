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

public class Network {
    
    private final String baseUrl;
    private final String urlOptions;
    private URL url;
    
    public Network() {
        // ISBN code for Harry Potter and the Deadly Hallows
        // "9780545010221"
        // Fifty Shades of Gray: "97834UE0348"
        this.baseUrl = "https://openlibrary.org/api/books?bibkeys=ISBN:";
        this.urlOptions = "&jscmd=data&format=json";
    }

    /**
     * Method that takes an isbn-code and uses the openlibrary API to fetch
     * the Title and author of the book. Returns an String array of length 2
     * containing the Title in index 0 and Author in index 1 if fetching was
     * succesfull. Otherwise return null if fetching book details fail.
     * @param isbn as String
     * @return and String array of length 2, containing the book title and author.
     */
    public String[] fetchBookDetailsByISBN(String isbn) {
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
            //System.out.println("Error when trying to fetch book details: " + e.getMessage());
            return null;
        }
        
        JsonObject rootobj = rootJsonObj.getAsJsonObject();
        JsonObject book = rootobj.getAsJsonObject("ISBN:" + isbn);
        
        if (book == null)   {
            return null;
        }
        
        title = book.get("title").getAsString();
        
        JsonArray authors = book.getAsJsonArray("authors");
        if (authors != null && authors.size() != 0) {
            JsonObject authorJson = authors.get(0).getAsJsonObject();
            author = authorJson.get("name").getAsString();
        }
        
        return new String[] {title, author};
    }
    
    private void openConnection(String isbn) throws MalformedURLException {
            this.url = new URL(this.baseUrl + isbn + this.urlOptions);
    }
    
}
