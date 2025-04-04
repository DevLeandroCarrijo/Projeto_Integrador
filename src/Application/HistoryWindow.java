package Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.SensorData;
import util.SensorDataLogger;

public class HistoryWindow {

    public static void display(SensorDataLogger logger) {
        Stage stage = new Stage();
        stage.setTitle("Histórico de Leituras");

        TableView<SensorData> table = new TableView<>();
        ObservableList<SensorData> data = FXCollections.observableArrayList(logger.getDataList());

        // Coluna para o timestamp formatado
        TableColumn<SensorData, String> timeColumn = new TableColumn<>("Timestamp");
        timeColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFormattedTimestamp())
        );

        // Coluna para Temperatura
        TableColumn<SensorData, Double> tempColumn = new TableColumn<>("Temperatura (ºC)");
        tempColumn.setCellValueFactory(new PropertyValueFactory<>("temperature"));

        // Coluna para Humidade
        TableColumn<SensorData, Double> humColumn = new TableColumn<>("Humidade (%)");
        humColumn.setCellValueFactory(new PropertyValueFactory<>("humidity"));

        // Coluna para Luminosidade
        TableColumn<SensorData, Double> lumColumn = new TableColumn<>("Luminosidade (lx)");
        lumColumn.setCellValueFactory(new PropertyValueFactory<>("luminosity"));

        table.getColumns().addAll(timeColumn, tempColumn, humColumn, lumColumn);
        table.setItems(data);

        VBox vbox = new VBox(table);
        Scene scene = new Scene(vbox, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
