# Weather Forecasting App

A Java-based weather forecasting application that displays current weather information for various cities.

## Features

- **Get Weather for Any City**: View current weather conditions including temperature, humidity, wind speed, and more
- **Compare Cities**: Compare weather between two cities side by side
- **Weather History**: Keep track of all weather queries made during the session
- **Statistics**: View average temperature and humidity from your query history
- **Easy Navigation**: Interactive menu-driven interface

## Available Demo Cities

The app comes with mock weather data for these cities:
- London (GB)
- New York (US)
- Tokyo (JP)
- Sydney (AU)
- Paris (FR)
- Mumbai (IN)
- Dubai (AE)
- Berlin (DE)

## Project Structure

```
src/
├── main/
│   └── java/
│       └── com/
│           └── weather/
│               ├── WeatherApp.java              # Main application entry point
│               ├── model/
│               │   └── Weather.java             # Weather data model
│               ├── service/
│               │   └── WeatherService.java      # Business logic layer
│               └── api/
│                   ├── WeatherAPIClient.java    # Real API integration (OpenWeatherMap)
│                   └── MockWeatherAPIClient.java # Mock data for testing
```

## System Requirements

- Java 11 or higher
- Maven 3.6 or higher

## Dependencies

- **Gson** (2.10.1) - JSON processing
- **Apache HttpClient** (5.2.1) - HTTP requests
- **SLF4J** (2.0.5) - Logging

## Installation

1. Clone or download the project
2. Navigate to the project directory:
   ```bash
   cd "Java Project"
   ```

3. Build the project:
   ```bash
   mvn clean install
   ```

## Running the Application

### Using Maven
```bash
mvn exec:java -Dexec.mainClass="com.weather.WeatherApp"
```

### Using JAR file
```bash
# Build the jar
mvn clean package

# Run the jar
java -jar target/weather-forecasting-app-1.0.0.jar
```

## Using Real Weather API (Optional)

To use real weather data from OpenWeatherMap:

1. Sign up at [OpenWeatherMap](https://openweathermap.org/api)
2. Get your free API key
3. Open `src/main/java/com/weather/api/WeatherAPIClient.java`
4. Replace `YOUR_API_KEY_HERE` with your actual API key
5. In `WeatherService.java`, change `getWeatherByCity()` to call `getWeatherByCityFromAPI()` instead of `mockClient.getWeatherByCity()`

## Usage Examples

### Get Weather for London
```
Enter your choice (1-8): 1
Enter city name: London
```

### Compare Two Cities
```
Enter your choice (1-8): 2
Enter first city name: London
Enter second city name: New York
```

### View Weather History
```
Enter your choice (1-8): 3
```

## Weather Information Displayed

- **Temperature**: Current temperature in Celsius
- **Feels Like**: Perceived temperature considering wind chill
- **Humidity**: Percentage of moisture in the air
- **Wind Speed**: Speed in meters per second
- **Description**: Current weather condition (Sunny, Rainy, Cloudy, etc.)
- **Pressure**: Atmospheric pressure in hPa
- **Visibility**: Visibility distance in meters
- **Cloudiness**: Cloud coverage percentage

## Future Enhancements

- [ ] 5-day weather forecast
- [ ] Weather alerts and warnings
- [ ] Multiple language support
- [ ] GUI using Swing or JavaFX
- [ ] Database integration for persistent storage
- [ ] Weather trends and analytics
- [ ] Geolocation-based weather
- [ ] Unit conversion (Celsius/Fahrenheit)

## License

This project is open source and available for educational purposes.

## Author

Weather Forecasting App - v1.0.0

## Troubleshooting

### "City not found" error
- Make sure you're using one of the available demo cities
- Check the spelling of the city name
- The search is case-insensitive

### API connection errors
- If using the real API, verify your API key is correct
- Check your internet connection
- Ensure the OpenWeatherMap API is accessible

### Maven build errors
- Ensure you have Java 11+ installed: `java -version`
- Ensure Maven is installed: `mvn -version`
- Try cleaning the project: `mvn clean`

## Support

For issues or questions, please check the code comments or refer to the README sections above.
