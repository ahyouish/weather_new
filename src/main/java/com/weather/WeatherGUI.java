package com.weather;

import com.weather.model.Weather;
import com.weather.service.WeatherService;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Weather Forecasting Application - JavaFX GUI
 * Modern, responsive interface for weather queries
 */
public class WeatherGUI extends Application {
    private WeatherService weatherService;
    private TextArea weatherDisplayArea;
    private TextField cityInput;
    private TextArea historyArea;
    private Label statusLabel;
    private ProgressIndicator loadingIndicator;

    @Override
    public void start(Stage primaryStage) {
        try {
            weatherService = new WeatherService();

            // Main layout
            BorderPane root = new BorderPane();
            root.setStyle("-fx-background-color: #ecf0f1;");

            // Header
            root.setTop(createHeader());

            // Center content with tabs
            root.setCenter(createTabPane());

            // Footer
            root.setBottom(createFooter());

            Scene scene = new Scene(root, 1000, 750);
            primaryStage.setTitle("Weather Forecasting Application");
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(800);
            primaryStage.setMinHeight(600);
            primaryStage.setOnCloseRequest(e -> System.exit(0));
            primaryStage.show();

            statusLabel.setText("Ready to search weather");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to initialize application: " + e.getMessage());
        }
    }

    private VBox createHeader() {
        VBox header = new VBox(10);
        header.setPadding(new Insets(25, 20, 25, 20));
        header.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, #2c3e50 0%, #34495e 100%);");

        Label titleLabel = new Label("🌤️ Weather Forecasting Application");
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 32));
        titleLabel.setTextFill(Color.WHITE);

        Label subtitleLabel = new Label("Get accurate weather information for cities worldwide");
        subtitleLabel.setFont(Font.font("Segoe UI", 14));
        subtitleLabel.setTextFill(Color.web("#ecf0f1"));

        header.getChildren().addAll(titleLabel, subtitleLabel);
        return header;
    }

    private TabPane createTabPane() {
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.setPadding(new Insets(15));
        tabPane.setStyle("-fx-font-size: 12;");

        // Weather Tab
        Tab weatherTab = new Tab("🔍 Weather Search", createWeatherTab());
        weatherTab.setStyle("-fx-font-size: 13;");

        // History Tab
        Tab historyTab = new Tab("📋 History", createHistoryTab());
        historyTab.setStyle("-fx-font-size: 13;");

        // Compare Tab
        Tab compareTab = new Tab("⚖️ Compare Cities", createCompareTab());
        compareTab.setStyle("-fx-font-size: 13;");

        // Statistics Tab
        Tab statsTab = new Tab("📊 Statistics", createStatisticsTab());
        statsTab.setStyle("-fx-font-size: 13;");

        tabPane.getTabs().addAll(weatherTab, historyTab, compareTab, statsTab);
        return tabPane;
    }

    private VBox createWeatherTab() {
        VBox vbox = new VBox(15);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-background-color: #ffffff;");

        // Input Section
        HBox inputSection = createInputSection();

        // Weather Display
        weatherDisplayArea = new TextArea();
        weatherDisplayArea.setEditable(false);
        weatherDisplayArea.setWrapText(true);
        weatherDisplayArea.setPrefHeight(400);
        weatherDisplayArea.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 12; -fx-control-inner-background: #f8f9fa;");
        weatherDisplayArea.setText("Enter a city name and click 'Get Weather' to display weather information here.\n\n" +
                "Available Demo Cities:\nLondon, New York, Tokyo, Sydney, Paris, Mumbai, Dubai, Berlin");

        ScrollPane scrollPane = new ScrollPane(weatherDisplayArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-border-color: #bdc3c7;");

        vbox.getChildren().addAll(inputSection, new Separator(), scrollPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        return vbox;
    }

    private HBox createInputSection() {
        HBox hbox = new HBox(12);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPadding(new Insets(15));
        hbox.setStyle("-fx-border-color: #ddd; -fx-border-radius: 5; -fx-background-color: #f8f9fa; -fx-border-width: 1;");

        Label label = new Label("City Name:");
        label.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
        label.setStyle("-fx-text-fill: #2c3e50;");

        cityInput = new TextField();
        cityInput.setPrefWidth(280);
        cityInput.setPromptText("e.g., London, New York, Tokyo...");
        cityInput.setStyle("-fx-font-size: 12; -fx-padding: 8;");
        cityInput.setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("ENTER")) {
                getWeather();
            }
        });

        Button searchButton = createStyledButton("Get Weather", "#3498db", "#2980b9");
        searchButton.setPrefWidth(120);
        searchButton.setOnAction(e -> getWeather());

        Button clearButton = createStyledButton("Clear", "#95a5a6", "#7f8c8d");
        clearButton.setPrefWidth(100);
        clearButton.setOnAction(e -> {
            cityInput.clear();
            weatherDisplayArea.clear();
        });

        loadingIndicator = new ProgressIndicator();
        loadingIndicator.setPrefSize(30, 30);
        loadingIndicator.setVisible(false);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        hbox.getChildren().addAll(label, cityInput, searchButton, clearButton, spacer, loadingIndicator);
        return hbox;
    }

    private VBox createHistoryTab() {
        VBox vbox = new VBox(15);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-background-color: #ffffff;");

        Label historyLabel = new Label("Weather Query History");
        historyLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        historyLabel.setStyle("-fx-text-fill: #2c3e50;");

        historyArea = new TextArea();
        historyArea.setEditable(false);
        historyArea.setWrapText(true);
        historyArea.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 11; -fx-control-inner-background: #f8f9fa;");
        historyArea.setText("No history yet. Search for weather to add to history.");

        ScrollPane scrollPane = new ScrollPane(historyArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-border-color: #bdc3c7;");

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));

        Button refreshButton = createStyledButton("Refresh", "#27ae60", "#229954");
        refreshButton.setPrefWidth(100);
        refreshButton.setOnAction(e -> refreshHistory());

        Button clearHistoryButton = createStyledButton("Clear History", "#e74c3c", "#c0392b");
        clearHistoryButton.setPrefWidth(120);
        clearHistoryButton.setOnAction(e -> {
            weatherService.clearHistory();
            refreshHistory();
            showAlert("Success", "History cleared successfully");
        });

        buttonBox.getChildren().addAll(refreshButton, clearHistoryButton);

        vbox.getChildren().addAll(historyLabel, scrollPane, buttonBox);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        return vbox;
    }

    private VBox createCompareTab() {
        VBox vbox = new VBox(15);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-background-color: #ffffff;");

        Label compareLabel = new Label("Compare Weather Between Two Cities");
        compareLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        compareLabel.setStyle("-fx-text-fill: #2c3e50;");

        // Input Section
        HBox inputBox = new HBox(12);
        inputBox.setAlignment(Pos.CENTER_LEFT);
        inputBox.setPadding(new Insets(15));
        inputBox.setStyle("-fx-border-color: #ddd; -fx-border-radius: 5; -fx-background-color: #f8f9fa; -fx-border-width: 1;");

        Label city1Label = new Label("City 1:");
        city1Label.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
        TextField city1Input = new TextField();
        city1Input.setPrefWidth(160);
        city1Input.setPromptText("First city");
        city1Input.setStyle("-fx-font-size: 12; -fx-padding: 8;");

        Label city2Label = new Label("City 2:");
        city2Label.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
        TextField city2Input = new TextField();
        city2Input.setPrefWidth(160);
        city2Input.setPromptText("Second city");
        city2Input.setStyle("-fx-font-size: 12; -fx-padding: 8;");

        Button compareButton = createStyledButton("Compare", "#9b59b6", "#8e44ad");
        compareButton.setPrefWidth(120);
        compareButton.setOnAction(e -> {
            if (city1Input.getText().trim().isEmpty() || city2Input.getText().trim().isEmpty()) {
                showAlert("Input Error", "Please enter both city names");
                return;
            }
            compareWeather(city1Input.getText(), city2Input.getText());
        });

        inputBox.getChildren().addAll(city1Label, city1Input, city2Label, city2Input, compareButton);

        // Display Area
        TextArea compareArea = new TextArea();
        compareArea.setEditable(false);
        compareArea.setWrapText(true);
        compareArea.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 12; -fx-control-inner-background: #f8f9fa;");
        compareArea.setText("Select two cities and click 'Compare' to see side-by-side weather comparison.");

        ScrollPane scrollPane = new ScrollPane(compareArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-border-color: #bdc3c7;");

        vbox.getChildren().addAll(compareLabel, inputBox, new Separator(), scrollPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        return vbox;
    }

    private VBox createStatisticsTab() {
        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(30));
        vbox.setStyle("-fx-background-color: #ffffff;");
        vbox.setAlignment(Pos.TOP_CENTER);

        Label statsLabel = new Label("Weather Statistics");
        statsLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        statsLabel.setStyle("-fx-text-fill: #2c3e50;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(40);
        gridPane.setVgap(40);
        gridPane.setPadding(new Insets(30));
        gridPane.setStyle("-fx-background-color: #ecf0f1; -fx-border-color: #bdc3c7; -fx-border-radius: 5;");

        // Average Temperature Card
        VBox tempCard = createStatCard("Average Temperature", "0.00°C", "#3498db");
        
        // Average Humidity Card
        VBox humidityCard = createStatCard("Average Humidity", "0.00%", "#27ae60");

        // Total Queries Card
        VBox queriesCard = createStatCard("Total Queries", "0", "#e74c3c");

        Button refreshStatsButton = createStyledButton("Refresh Statistics", "#34495e", "#2c3e50");
        refreshStatsButton.setPrefWidth(150);
        refreshStatsButton.setOnAction(e -> updateStatistics(tempCard, humidityCard, queriesCard));

        gridPane.add(tempCard, 0, 0);
        gridPane.add(humidityCard, 1, 0);
        gridPane.add(queriesCard, 2, 0);

        vbox.getChildren().addAll(statsLabel, gridPane, refreshStatsButton);
        updateStatistics(tempCard, humidityCard, queriesCard);
        
        return vbox;
    }

    private VBox createStatCard(String title, String value, String color) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(20));
        card.setAlignment(Pos.CENTER);
        card.setStyle("-fx-background-color: " + color + "; -fx-border-radius: 5;");

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
        titleLabel.setTextFill(Color.WHITE);

        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        valueLabel.setTextFill(Color.WHITE);

        card.getChildren().addAll(titleLabel, valueLabel);
        card.setPrefWidth(200);
        card.setPrefHeight(150);
        return card;
    }

    private void updateStatistics(VBox tempCard, VBox humidityCard, VBox queriesCard) {
        double avgTemp = weatherService.getAverageTemperature();
        double avgHumidity = weatherService.getAverageHumidity();
        int totalQueries = weatherService.getWeatherHistory().size();

        updateCardValue(tempCard, String.format("%.2f°C", avgTemp));
        updateCardValue(humidityCard, String.format("%.2f%%", avgHumidity));
        updateCardValue(queriesCard, String.valueOf(totalQueries));
    }

    private void updateCardValue(VBox card, String value) {
        Label valueLabel = (Label) card.getChildren().get(1);
        valueLabel.setText(value);
    }

    private HBox createFooter() {
        HBox footer = new HBox();
        footer.setPadding(new Insets(15, 20, 15, 20));
        footer.setStyle("-fx-background-color: #2c3e50;");

        statusLabel = new Label("Ready");
        statusLabel.setFont(Font.font("Segoe UI", 11));
        statusLabel.setTextFill(Color.LIGHTGRAY);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label versionLabel = new Label("Weather App v1.0");
        versionLabel.setFont(Font.font("Segoe UI", 10));
        versionLabel.setTextFill(Color.GRAY);

        footer.getChildren().addAll(statusLabel, spacer, versionLabel);
        return footer;
    }

    private void getWeather() {
        String city = cityInput.getText().trim();
        if (city.isEmpty()) {
            showAlert("Input Error", "Please enter a city name");
            return;
        }

        loadingIndicator.setVisible(true);
        statusLabel.setText("Fetching weather for " + city + "...");
        weatherDisplayArea.setText("Loading...");

        new Thread(() -> {
            try {
                Weather weather = weatherService.getWeatherByCity(city);
                javafx.application.Platform.runLater(() -> {
                    loadingIndicator.setVisible(false);
                    if (weather != null) {
                        weatherDisplayArea.setText(weather.toString());
                        statusLabel.setText("Weather loaded for " + city);
                        refreshHistory();
                    } else {
                        weatherDisplayArea.setText("❌ Could not find weather for: " + city + "\n\n" +
                                "Available demo cities:\n" +
                                "• London (GB)\n" +
                                "• New York (US)\n" +
                                "• Tokyo (JP)\n" +
                                "• Sydney (AU)\n" +
                                "• Paris (FR)\n" +
                                "• Mumbai (IN)\n" +
                                "• Dubai (AE)\n" +
                                "• Berlin (DE)");
                        statusLabel.setText("City not found");
                    }
                });
            } catch (Exception e) {
                javafx.application.Platform.runLater(() -> {
                    loadingIndicator.setVisible(false);
                    weatherDisplayArea.setText("Error: " + e.getMessage());
                    statusLabel.setText("Error occurred");
                });
            }
        }).start();
    }

    private void compareWeather(String city1, String city2) {
        statusLabel.setText("Comparing " + city1 + " and " + city2 + "...");

        new Thread(() -> {
            try {
                // Get both weather objects
                Weather w1 = weatherService.getWeatherByCity(city1);
                Weather w2 = weatherService.getWeatherByCity(city2);

                javafx.application.Platform.runLater(() -> {
                    if (w1 != null && w2 != null) {
                        StringBuilder comparison = new StringBuilder();
                        comparison.append("=============== WEATHER COMPARISON ===============\n\n");
                        comparison.append(String.format("%-30s %-30s\n", city1, city2));
                        comparison.append("---------------------------------------------------\n");
                        comparison.append(String.format("Temp:     %-20.1f°C %-20.1f°C\n", 
                            w1.getTemperature(), w2.getTemperature()));
                        comparison.append(String.format("Feels:    %-20.1f°C %-20.1f°C\n", 
                            w1.getFeelsLike(), w2.getFeelsLike()));
                        comparison.append(String.format("Humidity: %-19d%% %-19d%%\n", 
                            w1.getHumidity(), w2.getHumidity()));
                        comparison.append(String.format("Wind:     %-19.1f m/s %-19.1f m/s\n", 
                            w1.getWindSpeed(), w2.getWindSpeed()));
                        comparison.append(String.format("Pressure: %-19.0f hPa %-19.0f hPa\n", 
                            w1.getPressure(), w2.getPressure()));
                        comparison.append(String.format("Condition:%-30s %-30s\n", 
                            w1.getDescription(), w2.getDescription()));
                        comparison.append("==================================================\n");

                        // Find the compare tab and update its textarea
                        Tab compareTab = null;
                        TabPane tabPane = null;
                        statusLabel.setText("Comparison complete");
                    } else {
                        showAlert("Error", "Could not find weather for one or both cities");
                        statusLabel.setText("Comparison failed");
                    }
                });
            } catch (Exception e) {
                javafx.application.Platform.runLater(() -> {
                    showAlert("Error", "Error comparing cities: " + e.getMessage());
                    statusLabel.setText("Error occurred");
                });
            }
        }).start();
    }

    private void refreshHistory() {
        var history = weatherService.getWeatherHistory();
        if (history.isEmpty()) {
            historyArea.setText("No history yet. Search for weather to add to history.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("════════════ WEATHER HISTORY ════════════\n\n");
        for (int i = history.size() - 1; i >= 0; i--) {
            Weather w = history.get(i);
            sb.append(String.format("%d. %s, %s\n", history.size() - i, w.getCity(), w.getCountry()))
                    .append(String.format("   🌡️  Temperature: %.1f°C (Feels like %.1f°C)\n", w.getTemperature(), w.getFeelsLike()))
                    .append(String.format("   ☁️  Condition: %s\n", w.getDescription()))
                    .append(String.format("   💧 Humidity: %d%% | 💨 Wind: %.1f m/s\n\n", w.getHumidity(), w.getWindSpeed()));
        }
        historyArea.setText(sb.toString());
    }

    private Button createStyledButton(String text, String color, String hoverColor) {
        Button button = new Button(text);
        button.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 11));
        button.setPadding(new Insets(10, 20, 10, 20));
        button.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-border-radius: 4; -fx-cursor: hand;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: " + hoverColor + "; -fx-text-fill: white; -fx-border-radius: 4; -fx-cursor: hand;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-border-radius: 4; -fx-cursor: hand;"));
        return button;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
