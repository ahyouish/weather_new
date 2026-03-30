# Weather Forecasting Application - GUI Guide

## 🎨 GUI Features

The application includes a modern JavaFX GUI with the following features:

### 🔍 **Weather Search Tab**
- Search for weather by city name
- Real-time weather display
- Shows: Temperature, Feels Like, Humidity, Wind Speed, Pressure, Visibility, Cloud Coverage
- Status indicator for loading states
- Quick clear functionality

### 📋 **History Tab**
- View all previous weather queries
- Displays latest searches first
- Formatted with emojis for better readability
- Refresh button to update history
- Clear history option

### ⚖️ **Compare Cities Tab**
- Compare weather between two cities side-by-side
- Shows differences in temperature, humidity, wind, pressure, and conditions
- Easy-to-read comparison format

### 📊 **Statistics Tab**
- Average temperature from your search history
- Average humidity statistics
- Total number of weather queries
- Auto-updates with each search

---

## 🚀 How to Run the GUI

### **Option 1: Maven Command (Recommended)**

```bash
cd "Java Project"
mvn clean javafx:run
```

This command:
1. Cleans the previous build
2. Compiles all Java files
3. Packages dependencies
4. Launches the JavaFX GUI application

### **Option 2: Build and Run JAR**

```bash
cd "Java Project"
mvn clean package -q -DskipTests
java -jar target/weather-app.jar
```

### **Option 3: Using the GUI Launcher Class**

From the project root directory:

```bash
mvn clean package -q && mvn exec:java -Dexec.mainClass="com.weather.GUILauncher"
```

Or compile and run directly:

```bash
javac src/main/java/com/weather/GUILauncher.java
java com.weather.GUILauncher
```

### **Option 4: Using Python Script (Optional)**

Create a file named `run_gui.py`:

```python
#!/usr/bin/env python3
import subprocess
import os

os.chdir("Java Project")
print("Building Weather GUI Application...")
result = subprocess.run(["mvn", "clean", "javafx:run"], capture_output=False)
exit(result.returncode)
```

Run it:
```bash
python run_gui.py
```

---

## 📦 Available Demo Cities

The application comes with weather data for:
- **London** (GB)
- **New York** (US)
- **Tokyo** (JP)
- **Sydney** (AU)
- **Paris** (FR)
- **Mumbai** (IN)
- **Dubai** (AE)
- **Berlin** (DE)

---

## 🎯 Using the GUI Application

### **1. Search for Weather**
1. Go to the "Weather Search" tab
2. Enter a city name (e.g., "London")
3. Click "Get Weather" or press Enter
4. View detailed weather information

### **2. View History**
1. Go to the "History" tab
2. See all your previous searches
3. Click "Refresh" to update
4. Click "Clear History" to reset

### **3. Compare Cities**
1. Go to the "Compare Cities" tab
2. Enter two city names
3. Click "Compare"
4. View side-by-side weather comparison

### **4. Check Statistics**
1. Go to the "Statistics" tab
2. See average temperature and humidity
3. View total number of queries
4. Click "Refresh Statistics" to update

---

## 🔧 Technical Details

### **Java Classes**

| Class | Purpose |
|-------|---------|
| `WeatherGUI.java` | Main JavaFX GUI application |
| `GUILauncher.java` | Helper to build and launch the GUI |
| `GUIQuickStart.java` | Quick start entry point |
| `WeatherService.java` | Business logic and data management |
| `Weather.java` | Data model for weather information |
| `WeatherAPIClient.java` | OpenWeatherMap API integration |
| `MockWeatherAPIClient.java` | Mock data provider |

### **Dependencies**
- **JavaFX 21** - GUI Framework
- **Gson 2.10.1** - JSON Processing
- **Apache HttpClient 5.2.1** - HTTP Requests
- **SLF4J 2.0.5** - Logging

### **System Requirements**
- Java 11+ (JDK, not JRE)
- Maven 3.6+
- 100MB free disk space

---

## 🎨 GUI Design

The application features:
- **Modern Material Design** with gradient headers
- **Dark Color Scheme** (#2c3e50, #34495e) for reduced eye strain
- **Responsive Layout** that adapts to window size
- **Smooth Animations** with loading indicators
- **Styled Buttons** with hover effects
- **Professional Typography** using Segoe UI font

---

## 🐛 Troubleshooting

### **"JavaFX runtime components are missing"**
- Solution: Ensure you're using JDK (not JRE)
- Verify: `java -version` should show "Java Development Kit"

### **"mvn: command not found"**
- Solution: Install Maven from maven.apache.org
- Or run from VS Code integrated terminal with proper PATH

### **GUI Window Doesn't Appear**
- Check if it's hidden in background - look for "Weather Forecasting Application" in taskbar
- Try maximizing the window
- Restart the application

### **Build Takes Too Long**
- First run downloads all dependencies (~200MB)
- Subsequent runs are faster (cached dependencies)

---

## 💡 Tips & Tricks

1. **Quick Search**: Press Enter after typing a city name instead of clicking the button
2. **History Management**: Clear old history to keep statistics relevant
3. **Comparison**: Compare cities from different regions to see climate differences
4. **Statistics**: Check average values to understand your search patterns

---

## 📝 Future Enhancements

Planned features:
- [ ] 5-day weather forecast
- [ ] Weather alerts
- [ ] Dark/Light theme toggle
- [ ] Favorite cities list
- [ ] Map integration
- [ ] Export weather data to CSV

---

## 📞 Support

For issues or questions:
1. Check that all dependencies are installed
2. Ensure you're in the correct project directory
3. Review terminal output for error messages
4. Check pom.xml for configuration issues

---

**Happy Weather Forecasting! 🌤️**
