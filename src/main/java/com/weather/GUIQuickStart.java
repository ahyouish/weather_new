package com.weather;

/**
 * Quick Start - Run this to launch the Weather GUI Application
 * Compile: javac GUIQuickStart.java
 * Run: java com.weather.GUIQuickStart
 */
public class GUIQuickStart {
    
    public static void main(String[] args) {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║     Weather Forecasting Application - Quick Start         ║");
        System.out.println("║              Starting GUI Application...                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");

        // Launch the main GUI application
        try {
            WeatherGUI.main(args);
        } catch (Exception e) {
            System.err.println("❌ Error launching GUI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
