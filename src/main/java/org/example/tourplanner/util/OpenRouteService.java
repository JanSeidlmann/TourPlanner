package org.example.tourplanner.util;

import org.example.tourplanner.BL.models.TourModel;
import org.example.tourplanner.Injectable;
import org.example.tourplanner.config.ApplicationContext;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

// created with the help of team5 - https://github.com/helhar1234/TourPlanner_SWEN2_Team5
public class OpenRouteService implements Injectable {
    private static final String API_URL_GEOCODE = "https://api.openrouteservice.org/geocode/search?api_key=%s&text=%s";
    private static final String API_URL_DIRECTIONS = "https://api.openrouteservice.org/v2/directions/driving-car?api_key=%s&start=%s&end=%s";


    public static String getGeocode(String location) throws IOException {
        String urlStr = String.format(
                API_URL_GEOCODE, ApplicationContext.API_KEY_ORS, location
        );

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            // log error "responseCode = " + responseCode
            return null;
        }

        String response = new String(conn.getInputStream().readAllBytes());
        JSONObject jsonResponse = new JSONObject(response);
        JSONArray features = jsonResponse.getJSONArray("features");

        if (!features.isEmpty()) {
            JSONObject geometry = features.getJSONObject(0).getJSONObject("geometry");
            JSONArray coordinates = geometry.getJSONArray("coordinates");
            double lon = coordinates.getDouble(0);
            double lat = coordinates.getDouble(1);

            // log info
            return lon + "," + lat;
        }

        // log error
        return null;
    }

    public static String getRouteData(TourModel tour) {
        try {
            String start = getGeocode(tour.getFrom());
            String end = getGeocode(tour.getTo());

            String urlStr = String.format(
                API_URL_DIRECTIONS, ApplicationContext.API_KEY_ORS, start, end
            );

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                // log error "responseCode = " + responseCode
                throw new IOException("Error while trying to fetch route data");
            }
            return new String(conn.getInputStream().readAllBytes());

        } catch (Exception e) {
            // log error
            return null;
        }
    }
}
