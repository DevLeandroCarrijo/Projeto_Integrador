package sensor;

import java.util.Random;

public class LuminositySensor {

    public double readLuminosity() {
        // Simula a leitura de um sensor de luminosidade
        Random random = new Random();
        return random.nextDouble() * 1000; // Exemplo: 0 lx a 1000 lx
    }
}

