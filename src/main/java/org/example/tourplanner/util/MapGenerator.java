package org.example.tourplanner.util;

import lombok.extern.slf4j.Slf4j;
import org.example.tourplanner.BL.models.TourModel;
import org.example.tourplanner.TourPlannerApplication;
import org.example.tourplanner.config.ApplicationContext;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

// created with the help of team5 - https://github.com/helhar1234/TourPlanner_SWEN2_Team5
@Slf4j
public class MapGenerator {


    private static final String API_URL_MAPBOX = "https://api.mapbox.com/styles/v1/mapbox/streets-v11/static/pin-s+000(%s),pin-s+f44(%s)/auto/600x300?access_token=%s";
    private static final String USER_DIR = System.getProperty("user.dir");
    private static final File MAP_DIR = new File(USER_DIR, "/src/main/resources/org/example/tourplanner/maps");
    private static final File DIRECTIONS = new File(MAP_DIR, "directions.js");


    public static String getMapImage(TourModel tour) throws IOException {
        String start = OpenRouteService.getGeocode(tour.getFrom());
        String end = OpenRouteService.getGeocode(tour.getTo());

        if (start == null || end == null) {
            log.warn("Failed to find start/destination coordinates [from:" + tour.getFrom() + "], [to:" + tour.getTo() +"].");
            throw new IOException("Invalid coordinates");
        }

        try {
            String urlStr = String.format(
                API_URL_MAPBOX, start, end, ApplicationContext.API_KEY_MB
            );

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                log.error("[responseCode:" + responseCode + "] for [url:" + urlStr + "].");
                throw new IOException("Error while trying to fetch  image");
            }

            BufferedImage image = ImageIO.read(conn.getInputStream());

            String filename = "map_" + tour.getId() + ".png";
            File mapFile = new File(MAP_DIR, filename);
            ImageIO.write(image, "png", mapFile);

            log.info("Success fetching map image.");
            return filename;
        } catch (Exception e) {
            log.error("An error occurred while fetching the map image for tour [id:" + tour.getId() + "], error : " + e);
            return null;
        }
    }

    public static void openTourMapInBrowser(TourModel tour) {
        try {
            String routeData = OpenRouteService.getRouteData(tour);

            try (FileWriter fileWriter = new FileWriter(DIRECTIONS)) {
                fileWriter.write("var directions = " + routeData + ";");
                log.info("Success writing directions to file.");
            } catch (IOException e) {
                log.error("An error occurred while trying to write directions to file for tour [id:" + tour.getId() + "], error : " + e);
            }

            TourPlannerApplication.openTourMapInBrowser();
            log.info("Success opening map in browser.");
        } catch (Exception e) {
            log.error("An error occurred while trying to open map in browser for tour [id:" + tour.getId() + "], error : " + e);
        }
    }
}
