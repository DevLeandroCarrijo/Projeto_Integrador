package sensor;

import java.util.Random;

public class TemperatureSensor {

    public double readTemperature() {
        // Simula a leitura de um sensor de temperatura
        Random random = new Random();
        return 20 + random.nextDouble() * 15; // Exemplo: 20°C a 35°C
    }
}
