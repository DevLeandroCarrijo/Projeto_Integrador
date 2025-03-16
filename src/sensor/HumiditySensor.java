package sensor;

import java.util.Random;

public class HumiditySensor {

    public double readHumidity() {
        // Simula a leitura de um sensor de umidade
        Random random = new Random();
        return 30 + random.nextDouble() * 50; // Exemplo: 30% a 80%
    }
}
