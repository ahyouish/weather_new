package com.weather.api;

import com.weather.model.Weather;
import java.util.HashMap;
import java.util.Map;

/**
 * Mock Weather API Client for testing without API key
 * Provides pre-defined weather data for various cities
 */
public class MockWeatherAPIClient {
    private static final Map<String, Weather> mockWeatherData = new HashMap<>();

    static {
        // Initialize mock weather data
        mockWeatherData.put("London".toLowerCase(), new Weather(
                "London", "GB", 15.0, 13.0, 65, 4.5, "Cloudy", 1013.0, 10000.0, 75));

        mockWeatherData.put("New York".toLowerCase(), new Weather(
                "New York", "US", 22.5, 21.0, 55, 5.2, "Sunny", 1012.0, 10000.0, 20));

        mockWeatherData.put("Tokyo".toLowerCase(), new Weather(
                "Tokyo", "JP", 18.0, 17.0, 70, 3.5, "Partly Cloudy", 1015.0, 10000.0, 50));

        mockWeatherData.put("Sydney".toLowerCase(), new Weather(
                "Sydney", "AU", 25.0, 24.0, 60, 6.5, "Clear", 1010.0, 10000.0, 10));

        mockWeatherData.put("Paris".toLowerCase(), new Weather(
                "Paris", "FR", 16.0, 14.5, 70, 3.8, "Rainy", 1011.0, 8000.0, 90));

        mockWeatherData.put("Mumbai".toLowerCase(), new Weather(
                "Mumbai", "IN", 32.0, 35.0, 80, 2.5, "Humid", 1008.0, 5000.0, 40));

        mockWeatherData.put("Dubai".toLowerCase(), new Weather(
                "Dubai", "AE", 35.0, 38.0, 45, 7.5, "Clear", 1005.0, 10000.0, 5));

        mockWeatherData.put("Berlin".toLowerCase(), new Weather(
                "Berlin", "DE", 14.0, 12.5, 68, 4.0, "Overcast", 1014.0, 9000.0, 85));
    }

    public Weather getWeatherByCity(String city) {
        Weather weather = mockWeatherData.get(city.toLowerCase());
        if (weather == null) {
            System.out.println("City '" + city + "' not found in mock data. Available cities:");
            System.out.println("London, New York, Tokyo, Sydney, Paris, Mumbai, Dubai, Berlin");
            return null;
        }
        return weather;
    }

    public Weather getWeatherByCoordinates(double latitude, double longitude) {
        // For demo purposes, return weather based on latitude
        if (latitude > 50) {
            return mockWeatherData.get("london");
        } else if (latitude > 40) {
            return mockWeatherData.get("new york");
        } else if (latitude > 30) {
            return mockWeatherData.get("tokyo");
        } else {
            return mockWeatherData.get("sydney");
        }
    }
}
