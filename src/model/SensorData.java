package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SensorData {
    private LocalDateTime timestamp;
    private double temperature;
    private double humidity;
    private double luminosity;

    public SensorData(LocalDateTime timestamp, double temperature, double humidity, double luminosity) {
        this.timestamp = timestamp;
        this.temperature = temperature;
        this.humidity = humidity;
        this.luminosity = luminosity;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getLuminosity() {
        return luminosity;
    }

    public String getFormattedTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return timestamp.format(formatter);
    }
}
