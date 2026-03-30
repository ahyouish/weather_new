package com.weather.api;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.weather.model.Weather;

public class WeatherAPIClient {
    private static final String API_BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String API_KEY = "c7465ccf8569c4cdcd2154a9beb8db85"; // OpenWeatherMap API Key

    public Weather getWeatherByCity(String city) {
        try {
            String url = String.format("%s?q=%s&appid=%s&units=metric", API_BASE_URL, city, API_KEY);
            return fetchWeatherData(url);
        } catch (Exception e) {
            System.err.println("Error fetching weather for city: " + city);
            e.printStackTrace();
            return null;
        }
    }

    public Weather getWeatherByCoordinates(double latitude, double longitude) {
        try {
            String url = String.format("%s?lat=%.4f&lon=%.4f&appid=%s&units=metric",
                    API_BASE_URL, latitude, longitude, API_KEY);
            return fetchWeatherData(url);
        } catch (Exception e) {
            System.err.println("Error fetching weather for coordinates");
            e.printStackTrace();
            return null;
        }
    }

    private Weather fetchWeatherData(String url) throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("User-Agent", "WeatherApp/1.0");

            return httpClient.execute(httpGet, response -> {
                if (response.getCode() == HttpStatus.SC_OK) {
                    String responseBody = EntityUtils.toString(response.getEntity());
                    return parseWeatherResponse(responseBody);
                } else if (response.getCode() == HttpStatus.SC_NOT_FOUND) {
                    System.err.println("City not found. Please check the city name.");
                } else if (response.getCode() == HttpStatus.SC_UNAUTHORIZED) {
                    System.err.println("Invalid API key. Please update your API key in the code.");
                }
                return null;
            });
        }
    }

    private Weather parseWeatherResponse(String responseBody) {
        try {
            JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();

            String city = jsonObject.get("name").getAsString();
            String country = jsonObject.getAsJsonObject("sys").get("country").getAsString();
            double temperature = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
            double feelsLike = jsonObject.getAsJsonObject("main").get("feels_like").getAsDouble();
            int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();
            double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
            String description = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject()
                    .get("main").getAsString();
            double pressure = jsonObject.getAsJsonObject("main").get("pressure").getAsDouble();
            double visibility = jsonObject.get("visibility").getAsDouble();
            int cloudiness = jsonObject.getAsJsonObject("clouds").get("all").getAsInt();

            return new Weather(city, country, temperature, feelsLike, humidity, windSpeed,
                    description, pressure, visibility, cloudiness);
        } catch (Exception e) {
            System.err.println("Error parsing weather response: " + e.getMessage());
            return null;
        }
    }
}
