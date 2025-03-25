CREATE DATABASE SensorDB;
USE SensorDB;

-- Tabela de Sensores
CREATE TABLE sensor (
    id_sensor INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    tipo ENUM('Temperatura', 'Umidade', 'Luminosidade') NOT NULL,
    unidade VARCHAR(10) NOT NULL
);

-- Tabela de Leituras
CREATE TABLE leitura (
    id_leitura INT AUTO_INCREMENT PRIMARY KEY,
    id_sensor INT,
    valor DECIMAL(10,2) NOT NULL,
    data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_sensor) REFERENCES sensor(id_sensor)
);

-- Inserção inicial dos sensores (DHT22, LDR)
INSERT INTO sensor (nome, tipo, unidade) VALUES 
('DHT22 - Temperatura', 'Temperatura', '°C'),
('DHT22 - Umidade', 'Umidade', '%'),
('LDR - Luminosidade', 'Luminosidade', 'lx');
