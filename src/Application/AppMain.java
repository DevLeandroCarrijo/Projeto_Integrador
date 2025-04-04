package Application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sensor.TemperatureSensor;
import sensor.HumiditySensor;
import sensor.LuminositySensor;
import model.SensorData;
import util.SensorDataLogger;
import java.time.LocalDateTime;

public class AppMain extends Application {

    private Label tempLabel;
    private Label humLabel;
    private Label lightLabel;
    private Label avgLabel;
    private SensorDataLogger logger = new SensorDataLogger();

    @Override
    public void start(Stage primaryStage) {
        // Layout principal
        VBox root = new VBox(15);
        root.setStyle("-fx-padding: 20px; -fx-alignment: center; -fx-background-color: black;");

        // Título
        Label title = new Label("Monitor");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: green;");

        // Etiquetas de display
        tempLabel = createDisplayLabel("Temperatura: -- ºC");
        humLabel = createDisplayLabel("Humidade: -- %");
        lightLabel = createDisplayLabel("Luminosidade: -- lx");
        avgLabel = createDisplayLabel("Médias: Temp -- ºC | Hum -- % | Lum -- lx");

        // Botão para exibir histórico de leituras
        Button historyButton = new Button("Mostrar Histórico");
        historyButton.setOnAction(e -> HistoryWindow.display(logger));

        // Adicionando componentes ao layout
        root.getChildren().addAll(title, tempLabel, humLabel, lightLabel, avgLabel, historyButton);

        // Configuração de cena
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Sensores");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Inicializar a leitura dos sensores em uma thread separada
        new Thread(this::readSensors).start();
    }

    // Método para criar um label com estilo "display digital"
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

            // Cria um objeto SensorData e registra a leitura
            SensorData data = new SensorData(LocalDateTime.now(), temperature, humidity, luminosity);
            logger.addData(data);

            // Cálculo das médias
            double avgTemp = logger.getAverageTemperature();
            double avgHum = logger.getAverageHumidity();
            double avgLum = logger.getAverageLuminosity();

            // Atualização da interface gráfica
            Platform.runLater(() -> {
                tempLabel.setText(String.format("Temperatura: %.1f ºC", temperature));
                humLabel.setText(String.format("Humidade: %.1f %%", humidity));
                lightLabel.setText(String.format("Luminosidade: %.1f lx", luminosity));
                avgLabel.setText(String.format("Médias: Temp %.1f ºC | Hum %.1f %% | Lum %.1f lx", avgTemp, avgHum, avgLum));

                // Alerta visual: muda para vermelho se ultrapassar os limites
                if (temperature > 33) {
                    tempLabel.setStyle("-fx-font-size: 16px; -fx-font-family: 'Courier New'; -fx-text-fill: red; -fx-background-color: black; -fx-padding: 5px;");
                } else {
                    tempLabel.setStyle("-fx-font-size: 16px; -fx-font-family: 'Courier New'; -fx-text-fill: green; -fx-background-color: black; -fx-padding: 5px;");
                }

                if (humidity < 35) {
                    humLabel.setStyle("-fx-font-size: 16px; -fx-font-family: 'Courier New'; -fx-text-fill: red; -fx-background-color: black; -fx-padding: 5px;");
                } else {
                    humLabel.setStyle("-fx-font-size: 16px; -fx-font-family: 'Courier New'; -fx-text-fill: green; -fx-background-color: black; -fx-padding: 5px;");
                }

                if (luminosity > 900) {
                    lightLabel.setStyle("-fx-font-size: 16px; -fx-font-family: 'Courier New'; -fx-text-fill: red; -fx-background-color: black; -fx-padding: 5px;");
                } else {
                    lightLabel.setStyle("-fx-font-size: 16px; -fx-font-family: 'Courier New'; -fx-text-fill: green; -fx-background-color: black; -fx-padding: 5px;");
                }
            });

            try {
                Thread.sleep(1000); // Atualiza a cada 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
