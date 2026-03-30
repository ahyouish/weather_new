package com.weather;

import com.weather.model.Weather;
import com.weather.service.WeatherService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class WeatherAppGUI extends Application {
    private WeatherService weatherService;
    private TextArea weatherDisplayArea;
    private TextField cityInput;
    private TextArea historyArea;
    private Label statusLabel;

    @Override
    public void start(Stage primaryStage) {
        weatherService = new WeatherService();

        // Main layout
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f0f0f0;");

        // Header
        root.setTop(createHeader());

        // Center content with tabs
        root.setCenter(createTabPane());

        // Footer
        root.setBottom(createFooter());

        Scene scene = new Scene(root, 900, 700);
        primaryStage.setTitle("Weather Forecasting App");
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.show();
    }

    private VBox createHeader() {
        VBox header = new VBox(15);
        header.setPadding(new Insets(20));
        header.setStyle("-fx-background-color: #2c3e50;");

        Label titleLabel = new Label("Weather Forecasting Application");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        titleLabel.setTextFill(Color.WHITE);

        Label subtitleLabel = new Label("Get current weather information for any city");
        subtitleLabel.setFont(Font.font("Arial", 14));
        subtitleLabel.setTextFill(Color.LIGHTGRAY);

        header.getChildren().addAll(titleLabel, subtitleLabel);
        return header;
    }

    private TabPane createTabPane() {
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.setPadding(new Insets(10));

        // Weather Tab
        Tab weatherTab = new Tab("Weather Search", createWeatherTab());
        weatherTab.setStyle("-fx-font-size: 12;");

        // History Tab
        Tab historyTab = new Tab("History", createHistoryTab());
        historyTab.setStyle("-fx-font-size: 12;");

        // Compare Tab
        Tab compareTab = new Tab("Compare Cities", createCompareTab());
        compareTab.setStyle("-fx-font-size: 12;");

        tabPane.getTabs().addAll(weatherTab, historyTab, compareTab);
        return tabPane;
    }

    private VBox createWeatherTab() {
        VBox vbox = new VBox(15);
        vbox.setPadding(new Insets(20));

        // Input Section
        HBox inputSection = createInputSection();

        // Weather Display
        weatherDisplayArea = new TextArea();
        weatherDisplayArea.setEditable(false);
        weatherDisplayArea.setWrapText(true);
        weatherDisplayArea.setPrefHeight(400);
        weatherDisplayArea.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 12;");
        weatherDisplayArea.setText("Enter a city name and click 'Get Weather' to display weather information here.");

        ScrollPane scrollPane = new ScrollPane(weatherDisplayArea);
        scrollPane.setFitToWidth(true);

        vbox.getChildren().addAll(inputSection, new Separator(), scrollPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        return vbox;
    }

    private HBox createInputSection() {
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPadding(new Insets(10));
        hbox.setStyle("-fx-border-color: #ddd; -fx-border-radius: 5; -fx-background-color: #fff;");

        Label label = new Label("City Name:");
        label.setFont(Font.font("Arial", 12));

        cityInput = new TextField();
        cityInput.setPrefWidth(250);
        cityInput.setPromptText("Enter city name (e.g., London, New York)");
        cityInput.setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("ENTER")) {
                getWeather();
            }
        });

        Button searchButton = createStyledButton("Get Weather", "#3498db");
        searchButton.setOnAction(e -> getWeather());

        Button clearButton = createStyledButton("Clear", "#e74c3c");
        clearButton.setOnAction(e -> {
            cityInput.clear();
            weatherDisplayArea.clear();
        });

        hbox.getChildren().addAll(label, cityInput, searchButton, clearButton);
        return hbox;
    }

    private VBox createHistoryTab() {
        VBox vbox = new VBox(15);
        vbox.setPadding(new Insets(20));

        Label historyLabel = new Label("Weather Query History");
        historyLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        historyArea = new TextArea();
        historyArea.setEditable(false);
        historyArea.setWrapText(true);
        historyArea.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 11;");
        historyArea.setText("No history yet. Search for weather to add to history.");

        ScrollPane scrollPane = new ScrollPane(historyArea);
        scrollPane.setFitToWidth(true);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        Button refreshButton = createStyledButton("Refresh", "#27ae60");
        refreshButton.setOnAction(e -> refreshHistory());

        Button clearHistoryButton = createStyledButton("Clear History", "#e74c3c");
        clearHistoryButton.setOnAction(e -> {
            weatherService.clearHistory();
            refreshHistory();
        });

        buttonBox.getChildren().addAll(refreshButton, clearHistoryButton);

        vbox.getChildren().addAll(historyLabel, scrollPane, buttonBox);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        return vbox;
    }

    private VBox createCompareTab() {
        VBox vbox = new VBox(15);
        vbox.setPadding(new Insets(20));

        Label compareLabel = new Label("Compare Weather Between Two Cities");
        compareLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        // Input Section
        HBox inputBox = new HBox(10);
        inputBox.setAlignment(Pos.CENTER_LEFT);
        inputBox.setPadding(new Insets(10));
        inputBox.setStyle("-fx-border-color: #ddd; -fx-border-radius: 5; -fx-background-color: #fff;");

        Label city1Label = new Label("City 1:");
        TextField city1Input = new TextField();
        city1Input.setPrefWidth(180);
        city1Input.setPromptText("First city");

        Label city2Label = new Label("City 2:");
        TextField city2Input = new TextField();
        city2Input.setPrefWidth(180);
        city2Input.setPromptText("Second city");

        Button compareButton = createStyledButton("Compare", "#9b59b6");
        compareButton.setOnAction(e -> {
            if (city1Input.getText().isEmpty() || city2Input.getText().isEmpty()) {
                showAlert("Please enter both city names");
                return;
            }
            compareWeather(city1Input.getText(), city2Input.getText());
        });

        inputBox.getChildren().addAll(city1Label, city1Input, city2Label, city2Input, compareButton);

        // Display Area
        TextArea compareArea = new TextArea();
        compareArea.setEditable(false);
        compareArea.setWrapText(true);
        compareArea.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 12;");
        compareArea.setText("Select two cities and click 'Compare' to see side-by-side weather comparison.");

        ScrollPane scrollPane = new ScrollPane(compareArea);
        scrollPane.setFitToWidth(true);

        vbox.getChildren().addAll(compareLabel, inputBox, new Separator(), scrollPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        return vbox;
    }

    private HBox createFooter() {
        HBox footer = new HBox();
        footer.setPadding(new Insets(15));
        footer.setStyle("-fx-background-color: #34495e;");

        statusLabel = new Label("Ready");
        statusLabel.setFont(Font.font("Arial", 12));
        statusLabel.setTextFill(Color.LIGHTGRAY);

        footer.getChildren().add(statusLabel);
        return footer;
    }

    private void getWeather() {
        String city = cityInput.getText().trim();
        if (city.isEmpty()) {
            showAlert("Please enter a city name");
            return;
        }

        statusLabel.setText("Fetching weather for " + city + "...");
        weatherDisplayArea.setText("Loading...");

        // Fetch in background thread
        new Thread(() -> {
            Weather weather = weatherService.getWeatherByCity(city);
            javafx.application.Platform.runLater(() -> {
                if (weather != null) {
                    weatherDisplayArea.setText(weather.toString());
                    statusLabel.setText("Weather loaded for " + city);
                } else {
                    weatherDisplayArea.setText("Could not find weather for: " + city + "\n\n" +
                            "Available demo cities:\nLondon, New York, Tokyo, Sydney, Paris, Mumbai, Dubai, Berlin");
                    statusLabel.setText("City not found");
                }
            });
        }).start();
    }

    private void compareWeather(String city1, String city2) {
        statusLabel.setText("Comparing " + city1 + " and " + city2 + "...");

        new Thread(() -> {
            StringBuilder result = new StringBuilder();
            javafx.application.Platform.runLater(() -> {
                weatherService.compareWeather(city1, city2);
                statusLabel.setText("Comparison complete");
            });
        }).start();
    }

    private void refreshHistory() {
        var history = weatherService.getWeatherHistory();
        if (history.isEmpty()) {
            historyArea.setText("No history yet. Search for weather to add to history.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("==== WEATHER HISTORY ====\n\n");
        for (int i = 0; i < history.size(); i++) {
            Weather w = history.get(i);
            sb.append(String.format("%d. %s, %s\n", i + 1, w.getCity(), w.getCountry()))
                    .append(String.format("   Temperature: %.1f°C (Feels like %.1f°C)\n", w.getTemperature(), w.getFeelsLike()))
                    .append(String.format("   Condition: %s\n", w.getDescription()))
                    .append(String.format("   Humidity: %d%%, Wind: %.1f m/s\n\n", w.getHumidity(), w.getWindSpeed()));
        }
        historyArea.setText(sb.toString());
    }

    private Button createStyledButton(String text, String color) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 11));
        button.setPadding(new Insets(8, 20, 8, 20));
        button.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-border-radius: 4;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: " + adjustColor(color) + "; -fx-text-fill: white; -fx-border-radius: 4;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-border-radius: 4;"));
        return button;
    }

    private String adjustColor(String color) {
        // Slight adjustment for hover effect
        return color;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Weather App");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
