package Application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sensor.TemperatureSensor;
import sensor.HumiditySensor;
import sensor.LuminositySensor;
import javafx.application.Platform;

public class AppMain extends Application {

    private Label tempLabel;
    private Label humLabel;
    private Label lightLabel;

    @Override
    public void start(Stage primaryStage) {
        // Layout principal
        VBox root = new VBox(15);
        root.setStyle("-fx-padding: 20px; -fx-alignment: center; -fx-background-color: black;");

        // Titulo
        Label title = new Label("Monitor");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: green;");

        // Etiquetas de IU estilizadas como uma "exibição digital"
        tempLabel = createDisplayLabel("Temperatura: -- ºC");
        humLabel = createDisplayLabel("Humidade: -- %");
        lightLabel = createDisplayLabel("Luminosidade: -- lx");

        // Adicionando componentes ao layout
        root.getChildren().addAll(title, tempLabel, humLabel, lightLabel);

        // Configuração de cena
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Sensores");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Inicialize a leitura do sensor
        new Thread(this::readSensors).start();
    }

    // Método para criar um estilo personalizado de "display digital"
    private Label createDisplayLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 16px; -fx-font-family: 'Courier New'; -fx-text-fill: green; "
                + "-fx-background-color: black; -fx-padding: 5px;");
        return label;
    }

    private void readSensors() {
        TemperatureSensor sensorTemp = new TemperatureSensor();
        HumiditySensor sensorHum = new HumiditySensor();
        LuminositySensor sensorLight = new LuminositySensor();

        while (true) {
            double temperature = sensorTemp.readTemperature();
            double humidity = sensorHum.readHumidity();
            double luminosity = sensorLight.readLuminosity();

            // Atualizar a GUI
            Platform.runLater(() -> {
                tempLabel.setText(String.format("Temperatura: %.1f ºC", temperature));
                humLabel.setText(String.format("Humidade: %.1f %%", humidity));
                lightLabel.setText(String.format("Luminosidade: %.1f lx", luminosity));
            });

            try {
                Thread.sleep(1000); // Update every 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
