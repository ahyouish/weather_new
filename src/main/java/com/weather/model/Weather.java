package com.weather.model;

public class Weather {
    private String city;
    private String country;
    private double temperature;
    private double feelsLike;
    private int humidity;
    private double windSpeed;
    private String description;
    private double pressure;
    private double visibility;
    private int cloudiness;
    private long timestamp;

    public Weather() {}

    public Weather(String city, String country, double temperature, double feelsLike,
                   int humidity, double windSpeed, String description, double pressure,
                   double visibility, int cloudiness) {
        this.city = city;
        this.country = country;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.description = description;
        this.pressure = pressure;
        this.visibility = visibility;
        this.cloudiness = cloudiness;
        this.timestamp = System.currentTimeMillis();
    }

    // Getters and Setters
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public double getFeelsLike() { return feelsLike; }
    public void setFeelsLike(double feelsLike) { this.feelsLike = feelsLike; }

    public int getHumidity() { return humidity; }
    public void setHumidity(int humidity) { this.humidity = humidity; }

    public double getWindSpeed() { return windSpeed; }
    public void setWindSpeed(double windSpeed) { this.windSpeed = windSpeed; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPressure() { return pressure; }
    public void setPressure(double pressure) { this.pressure = pressure; }

    public double getVisibility() { return visibility; }
    public void setVisibility(double visibility) { this.visibility = visibility; }

    public int getCloudiness() { return cloudiness; }
    public void setCloudiness(int cloudiness) { this.cloudiness = cloudiness; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

    @Override
    public String toString() {
        return "================== WEATHER FORECAST ==================\n" +
                "Location: " + city + ", " + country + "\n" +
                "Temperature: " + temperature + "°C (Feels like " + feelsLike + "°C)\n" +
                "Condition: " + description + "\n" +
                "Humidity: " + humidity + "%\n" +
                "Wind Speed: " + windSpeed + " m/s\n" +
                "Pressure: " + pressure + " hPa\n" +
                "Visibility: " + visibility + " m\n" +
                "Cloudiness: " + cloudiness + "%\n" +
                "=====================================================";
    }
}
