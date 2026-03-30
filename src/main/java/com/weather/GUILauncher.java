package com.weather;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * GUI Launcher for Weather Forecasting Application
 * Builds the project and launches the JavaFX GUI
 */
public class GUILauncher {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║   Weather Forecasting Application - GUI Launcher           ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        try {
            // Check if Maven is available
            if (!isMavenAvailable()) {
                System.err.println("❌ Maven is not installed or not in PATH");
                System.err.println("   Please install Maven or add it to your system PATH");
                System.exit(1);
            }

            System.out.println("✓ Maven found");
            System.out.println("\n📦 Building Weather Forecasting Application...\n");

            // Run Maven build
            ProcessBuilder buildProcess = new ProcessBuilder("mvn", "clean", "package", "-q", "-DskipTests");
            buildProcess.directory(Paths.get("").toAbsolutePath().toFile());
            buildProcess.inheritIO();

            int buildExitCode = buildProcess.start().waitFor();

            if (buildExitCode != 0) {
                System.err.println("\n❌ Build failed! Check the error messages above.");
                System.exit(1);
            }

            System.out.println("\n✓ Build completed successfully!");
            System.out.println("\n🚀 Launching Weather GUI Application...\n");

            // Run Maven javafx:run
            ProcessBuilder runProcess = new ProcessBuilder("mvn", "javafx:run");
            runProcess.directory(Paths.get("").toAbsolutePath().toFile());
            runProcess.inheritIO();

            Process process = runProcess.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("\n✓ Application closed successfully");
            } else {
                System.out.println("\n⚠️  Application exited with code: " + exitCode);
            }

        } catch (IOException e) {
            System.err.println("❌ Error: " + e.getMessage());
            System.err.println("   Make sure you're running this from the Java Project directory");
            System.exit(1);
        } catch (InterruptedException e) {
            System.err.println("❌ Application interrupted: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Check if Maven is available in the system PATH
     */
    private static boolean isMavenAvailable() {
        try {
            ProcessBuilder pb = new ProcessBuilder("mvn", "--version");
            pb.redirectErrorStream(true);
            Process process = pb.start();
            process.waitFor();
            return process.exitValue() == 0;
        } catch (IOException | InterruptedException e) {
            return false;
        }
    }
}
