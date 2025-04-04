// https://www.tinkercad.com/things/cAAi7hCNx1U-daring-waasa/editel?returnTo=https%3A%2F%2Fwww.tinkercad.com%2Fdashboard&sharecode=iB7yMjELL8HYHLkJ2cNPTlGUAvCmR1mlqePtnos9Txw

const int LDR_PIN = A0;   // Pino do LDR
const int TMP_PIN = A1;   // Pino do TMP36
const int SOIL_PIN = A2;  // Pino do sensor de umidade 

void setup() {
  Serial.begin(9600);
}

void loop() {
  // Leitura do LDR
  int luminosidade = analogRead(LDR_PIN);

  // Leitura do TMP36 (conversão para Celsius)
  int leituraTMP = analogRead(TMP_PIN);
  float voltagem = leituraTMP * (5.0 / 1023.0);
  float temperatura = (voltagem - 0.5) * 100.0; // Fórmula do TMP36

  // Leitura do sensor de umidade
  int umidade = analogRead(SOIL_PIN);
  
  // Exibição dos valores no Monitor Serial
  Serial.print("Temperatura: ");
  Serial.print(temperatura);
  Serial.print(" C | Luminosidade: ");
  Serial.print(luminosidade);
  Serial.print(" | Umidade: ");
  Serial.println(umidade);

  delay(1000); // Aguarda 1 segundos
}
