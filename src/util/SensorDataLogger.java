package util;

import model.SensorData;
import java.util.ArrayList;
import java.util.List;

public class SensorDataLogger {
    private List<SensorData> dataList = new ArrayList<>();

    public void addData(SensorData data) {
        dataList.add(data);
    }

    public List<SensorData> getDataList() {
        return dataList;
    }

    public double getAverageTemperature() {
        return dataList.stream().mapToDouble(SensorData::getTemperature).average().orElse(0);
    }

    public double getAverageHumidity() {
        return dataList.stream().mapToDouble(SensorData::getHumidity).average().orElse(0);
    }

    public double getAverageLuminosity() {
        return dataList.stream().mapToDouble(SensorData::getLuminosity).average().orElse(0);
    }
}
