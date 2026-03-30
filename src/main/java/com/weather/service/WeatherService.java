package com.weather.service;

import com.weather.api.MockWeatherAPIClient;
import com.weather.api.WeatherAPIClient;
import com.weather.model.Weather;
import java.util.*;

public class WeatherService {
    private final MockWeatherAPIClient mockClient;
    private final WeatherAPIClient apiClient;
    private final List<Weather> weatherHistory;
    private static final int MAX_HISTORY = 50;

    public WeatherService() {
        this.mockClient = new MockWeatherAPIClient();
        this.apiClient = new WeatherAPIClient();
        this.weatherHistory = new ArrayList<>();
    }

    /**
     * Get weather for a city using mock data (no API key required)
     */
    public Weather getWeatherByCity(String city) {
        Weather weather = mockClient.getWeatherByCity(city);
        if (weather != null) {
            addToHistory(weather);
        }
        return weather;
    }

    /**
     * Get weather for a city using real API (requires API key)
     */
    public Weather getWeatherByCityFromAPI(String city) {
        Weather weather = apiClient.getWeatherByCity(city);
        if (weather != null) {
            addToHistory(weather);
        }
        return weather;
    }

    /**
     * Get weather for coordinates using mock data
     */
    public Weather getWeatherByCoordinates(double latitude, double longitude) {
        return mockClient.getWeatherByCoordinates(latitude, longitude);
    }

    /**
     * Get weather history
     */
    public List<Weather> getWeatherHistory() {
        return new ArrayList<>(weatherHistory);
    }

    /**
     * Get last weather query
     */
    public Weather getLastWeather() {
        if (weatherHistory.isEmpty()) {
            return null;
        }
        return weatherHistory.get(weatherHistory.size() - 1);
    }

    /**
     * Clear weather history
     */
    public void clearHistory() {
        weatherHistory.clear();
    }

    /**
     * Add weather to history
     */
    private void addToHistory(Weather weather) {
        weatherHistory.add(weather);
        if (weatherHistory.size() > MAX_HISTORY) {
            weatherHistory.remove(0);
        }
    }

    /**
     * Get average temperature from history
     */
    public double getAverageTemperature() {
        if (weatherHistory.isEmpty()) {
            return 0;
        }
        return weatherHistory.stream()
                .mapToDouble(Weather::getTemperature)
                .average()
                .orElse(0);
    }

    /**
     * Get average humidity from history
     */
    public double getAverageHumidity() {
        if (weatherHistory.isEmpty()) {
            return 0;
        }
        return weatherHistory.stream()
                .mapToDouble(Weather::getHumidity)
                .average()
                .orElse(0);
    }

    /**
     * Compare weather between two cities
     */
    public void compareWeather(String city1, String city2) {
        Weather w1 = mockClient.getWeatherByCity(city1);
        Weather w2 = mockClient.getWeatherByCity(city2);

        if (w1 != null && w2 != null) {
            System.out.println("\n=============== WEATHER COMPARISON ===============");
            System.out.println(String.format("%-20s %-20s", city1, city2));
            System.out.println("---------------------------------------------------");
            System.out.println(String.format("Temp: %-15.1f°C %-15.1f°C", w1.getTemperature(), w2.getTemperature()));
            System.out.println(String.format("Humidity: %-12d%% %-12d%%", w1.getHumidity(), w2.getHumidity()));
            System.out.println(String.format("Wind: %-14.1f m/s %-14.1f m/s", w1.getWindSpeed(), w2.getWindSpeed()));
            System.out.println(String.format("Condition: %-12s %-12s", w1.getDescription(), w2.getDescription()));
            System.out.println("==================================================");
        }
    }
}
