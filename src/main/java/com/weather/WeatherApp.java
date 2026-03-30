package com.weather;

import com.weather.model.Weather;
import com.weather.service.WeatherService;
import java.util.Scanner;

public class WeatherApp {
    private static final WeatherService weatherService = new WeatherService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\n========== WELCOME TO WEATHER FORECASTING APP ==========");
        System.out.println("This app uses mock data for demonstration (no API key required)\n");

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getUserChoice();
            running = handleMenuChoice(choice);
        }

        scanner.close();
        System.out.println("\nThank you for using Weather Forecasting App. Goodbye!");
    }

    private static void displayMenu() {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Get weather for a city");
        System.out.println("2. Compare weather between two cities");
        System.out.println("3. View weather history");
        System.out.println("4. Get average temperature from history");
        System.out.println("5. Get average humidity from history");
        System.out.println("6. Clear history");
        System.out.println("7. View last weather query");
        System.out.println("8. Exit");
        System.out.print("Enter your choice (1-8): ");
    }

    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number between 1-8.");
            return -1;
        }
    }

    private static boolean handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                getWeatherForCity();
                return true;
            case 2:
                compareWeatherBetweenCities();
                return true;
            case 3:
                viewWeatherHistory();
                return true;
            case 4:
                viewAverageTemperature();
                return true;
            case 5:
                viewAverageHumidity();
                return true;
            case 6:
                clearHistory();
                return true;
            case 7:
                viewLastWeatherQuery();
                return true;
            case 8:
                return false;
            default:
                System.out.println("Invalid choice. Please enter a number between 1-8.");
                return true;
        }
    }

    private static void getWeatherForCity() {
        System.out.print("\nEnter city name: ");
        String city = scanner.nextLine().trim();

        if (city.isEmpty()) {
            System.out.println("City name cannot be empty.");
            return;
        }

        System.out.println("\nFetching weather data...");
        Weather weather = weatherService.getWeatherByCity(city);

        if (weather != null) {
            System.out.println("\n" + weather);
        } else {
            System.out.println("Could not retrieve weather data for: " + city);
        }
    }

    private static void compareWeatherBetweenCities() {
        System.out.print("\nEnter first city name: ");
        String city1 = scanner.nextLine().trim();

        System.out.print("Enter second city name: ");
        String city2 = scanner.nextLine().trim();

        if (city1.isEmpty() || city2.isEmpty()) {
            System.out.println("City names cannot be empty.");
            return;
        }

        weatherService.compareWeather(city1, city2);
    }

    private static void viewWeatherHistory() {
        var history = weatherService.getWeatherHistory();

        if (history.isEmpty()) {
            System.out.println("\nNo weather history available. Get some weather data first!");
            return;
        }

        System.out.println("\n========== WEATHER HISTORY ==========");
        for (int i = 0; i < history.size(); i++) {
            Weather w = history.get(i);
            System.out.printf("%d. %s, %s - %.1f°C - %s\n",
                    i + 1, w.getCity(), w.getCountry(), w.getTemperature(), w.getDescription());
        }
        System.out.println("=====================================");
    }

    private static void viewAverageTemperature() {
        double avgTemp = weatherService.getAverageTemperature();
        if (avgTemp == 0 && weatherService.getWeatherHistory().isEmpty()) {
            System.out.println("\nNo weather data available. Get some weather data first!");
        } else {
            System.out.printf("\nAverage temperature from history: %.2f°C\n", avgTemp);
        }
    }

    private static void viewAverageHumidity() {
        double avgHumidity = weatherService.getAverageHumidity();
        if (avgHumidity == 0 && weatherService.getWeatherHistory().isEmpty()) {
            System.out.println("\nNo weather data available. Get some weather data first!");
        } else {
            System.out.printf("\nAverage humidity from history: %.2f%%\n", avgHumidity);
        }
    }

    private static void clearHistory() {
        weatherService.clearHistory();
        System.out.println("\nWeather history cleared!");
    }

    private static void viewLastWeatherQuery() {
        Weather lastWeather = weatherService.getLastWeather();

        if (lastWeather == null) {
            System.out.println("\nNo weather queries in history yet!");
        } else {
            System.out.println("\n" + lastWeather);
        }
    }
}
