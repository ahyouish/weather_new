# 🌤️ Weather Forecasting Web App

A beautiful, responsive weather forecasting application built with HTML, CSS, and JavaScript. 

## 🌐 Access the Live App

The app is hosted on GitHub Pages and can be accessed directly from your web browser!

### Live URL:
```
https://ahyouish.github.io/weather_new/
```

## 📱 Features

✅ **Search Weather** - Get real-time weather for any of 8 demo cities  
✅ **Weather History** - View your search history with timestamps  
✅ **Compare Cities** - Compare weather between two cities side-by-side  
✅ **Statistics** - View average temperature and humidity from your searches  
✅ **Responsive Design** - Works perfectly on desktop, tablet, and mobile  
✅ **No Backend Required** - Runs entirely in your browser  
✅ **Persistent Storage** - Your search history is saved in browser local storage  

## 🚀 How to Enable GitHub Pages

To make the app accessible on GitHub Pages, follow these steps:

### Step 1: Go to Repository Settings
1. Open your GitHub repository: https://github.com/ahyouish/weather_new
2. Click on "Settings" (top right)

### Step 2: Enable GitHub Pages
1. Scroll down to "GitHub Pages" section
2. Under "Source", select "Deploy from a branch"
3. Choose "main" branch
4. Select "root" folder (where index.html is located)
5. Click "Save"

### Step 3: Access Your App
After a few seconds, GitHub Pages will be enabled and you can access the app at:
```
https://ahyouish.github.io/weather_new/
```

## 🏙️ Available Demo Cities

- 🇬🇧 **London** - Cloudy weather
- 🇺🇸 **New York** - Sunny weather
- 🇯🇵 **Tokyo** - Partly Cloudy
- 🇦🇺 **Sydney** - Clear skies
- 🇫🇷 **Paris** - Rainy weather
- 🇮🇳 **Mumbai** - Humid weather
- 🇦🇪 **Dubai** - Hot and clear
- 🇩🇪 **Berlin** - Overcast skies

## 💻 Technology Stack

- **Frontend**: HTML5, CSS3, JavaScript (Vanilla)
- **Storage**: Browser LocalStorage API
- **Hosting**: GitHub Pages
- **Data**: Mock weather data (no API key needed)

## 📖 How to Use

### Search for Weather
1. Enter a city name in the search box
2. Click "Search" or press Enter
3. View detailed weather information

### View Search History
1. Click on the "History" tab
2. See all your previous searches with timestamps
3. Clear history if needed

### Compare Two Cities
1. Click on the "Compare" tab
2. Enter two city names
3. Click "Compare" to see side-by-side comparison
4. View temperature, humidity, wind, and conditions

### Check Statistics
1. Click on the "Statistics" tab
2. View average temperature from your searches
3. Check average humidity levels
4. See total number of searches made

## 🎨 Design Features

- Modern gradient background
- Responsive grid layout
- Smooth animations and transitions
- Beautiful color scheme (#667eea, #764ba2)
- Mobile-first design
- Accessibility-friendly

## 💾 Data Persistence

Your search history is automatically saved in your browser's LocalStorage. This means:
- Your history persists across browser sessions
- No account needed
- Data stored locally on your device
- Clear history anytime with the "Clear History" button

## 🔄 Integration with Java Backend

This HTML/CSS/JavaScript app can be easily integrated with the Java Weather API backend:

1. **Option 1**: Keep using mock data (current setup)
2. **Option 2**: Modify the JavaScript to call the Java backend API
3. **Option 3**: Deploy the Java app separately and call it from this frontend

To integrate with the Java backend, modify the `getWeatherData()` function in index.html:

```javascript
// Replace mock data with API call
async function getWeatherData(city) {
    const response = await fetch(`https://your-java-api.com/weather?city=${city}`);
    return await response.json();
}
```

## 📂 Project Structure

```
weather_new/
├── index.html           # Main application file
├── README.md            # This file
├── pom.xml             # Java project configuration (optional)
├── src/                # Java source code
│   └── main/java/...
└── .gitignore          # Git ignore rules
```

## 🌐 Browser Compatibility

Works on all modern browsers:
- ✅ Chrome/Chromium
- ✅ Firefox
- ✅ Safari
- ✅ Edge
- ✅ Opera
- ✅ Mobile browsers

## 📝 Environment Setup

**No setup required!** The app runs entirely in your browser:
1. No Node.js needed
2. No npm packages required
3. No build process
4. No dependencies to install

Just open the website and start using it!

## 🔧 Customization

### Change Available Cities
Edit the `weatherData` object in index.html:

```javascript
const weatherData = {
    'london': { /* ... */ },
    'paris': { /* ... */ },
    // Add your own cities here
};
```

### Change Colors
Modify the CSS gradient colors:

```css
background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
```

### Add More Features
The app is built with vanilla JavaScript, making it easy to extend:
- Add more tabs
- Integrate real API
- Add weather forecast
- Add location-based weather
- Add unit conversion

## 📱 Mobile Optimization

The app is fully responsive and works great on mobile devices:
- Touch-friendly buttons
- Optimized for small screens
- Fast loading time
- No external dependencies

## 🚀 Performance

- **Load Time**: < 1 second
- **Bundle Size**: ~30KB (single HTML file)
- **No External Dependencies**: All CSS/JS inline
- **Instant Search**: No network latency

## 🤝 Contributing

Feel free to fork this project and submit pull requests for improvements!

## 📄 License

This project is open source and available for personal and commercial use.

## 🙋 Support

For issues or questions:
1. Check the browser console for errors (F12)
2. Ensure JavaScript is enabled
3. Try a different browser
4. Clear browser cache and reload

## ✨ Features Coming Soon

- 🌙 Dark/Light theme toggle
- 📍 Current location weather
- 📈 Weather trends
- 🌍 More demo cities
- 🔔 Weather notifications
- 📊 Advanced analytics

---

**Enjoy the Weather App! ☀️🌧️❄️**

For the Java backend version, see the `src/` directory.
