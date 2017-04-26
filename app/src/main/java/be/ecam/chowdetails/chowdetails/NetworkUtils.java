package be.ecam.chowdetails.chowdetails;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by lur on 07-02-17.
 */

public class NetworkUtils {
    public static String getResponseFromHttpUrl(String url) throws IOException {

        URL urlObject = new URL(url);

        HttpURLConnection urlConnection =
                (HttpURLConnection) urlObject.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}