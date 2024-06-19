package org.example.tourplanner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Logger;

public class OpenRouteService implements Injectable {
    private static final String API_KEY = "5b3ce3597851110001cf62486acb2b459204460482443fd08db8f0de";
    private static final String DIRECTIONS_API_URL = "https://api.openrouteservice.org/v2/directions";
    private static final String PROFILE = "driving-car";
    private static final Logger logger = Logger.getLogger(OpenRouteService.class.getName());


    public static String getRoute(double startLat, double startLng, double endLat, double endLng) throws Exception {
        String urlStr = String.format("%s/%s?api_key=%s&start=%f,%f&end=%f,%f", DIRECTIONS_API_URL, PROFILE, API_KEY, startLng, startLat, endLng, endLat);
        logger.info("Request URL: " + urlStr);
        System.out.println("Request URL: " + urlStr);

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        }

        Scanner scanner = new Scanner(url.openStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }
        scanner.close();
        return response.toString();
    }
}
