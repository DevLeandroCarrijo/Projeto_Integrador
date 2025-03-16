# Desenvolvedores
- Leandro Carrijo
- Gustavo Arantes
- Luciana Freitas
- Beatriz de Freitas
- Maria Eduarda Sousa

# Monitor de Sensores - Aplicação JavaFX

Este projeto é uma aplicação JavaFX que simula a leitura de sensores de temperatura, umidade e luminosidade, exibindo os valores em um **display digital estilizado**.

## 📋 Funcionalidades
- Leitura simulada de três sensores:
  - **Temperatura**: exibe valores em graus Celsius.
  - **Umidade**: exibe valores em porcentagem.
  - **Luminosidade**: exibe valores em lúmens (lx).
- Atualização dos valores em tempo real (a cada 1 segundo).
- Interface gráfica com estilo de **display digital** (fundo preto e texto verde).

## 🛠️ Tecnologias Utilizadas
- **Java 11 ou superior** (compatível com JavaFX 17).
- **JavaFX** para criação da interface gráfica.
- **Threads** para atualização assíncrona dos valores dos sensores.

## 📂 Estrutura do Projeto
src/ ├── Application/ 
            │── AppMain.java # Classe principal que inicializa a interface gráfica. 
├── sensor/ │ 
            ├── TemperatureSensor.java # Simula o sensor de temperatura. │ 
            ├── HumiditySensor.java # Simula o sensor de umidade. │ 
            └── LuminositySensor.java # Simula o sensor de luminosidade.

## 🚀 Como Executar
1. Certifique-se de que o **JavaFX** está configurado no ambiente. Baixe o SDK do [OpenJFX](https://openjfx.io/) e configure o `module-path`.
2. Compile e execute o programa usando sua IDE preferida ou pelo terminal:
   - **IDE**: Certifique-se de configurar as dependências do JavaFX nas opções de execução.
   - **Terminal**: Use o seguinte comando para executar:
     ```bash
     java --module-path "caminho/para/javafx-sdk/lib" --add-modules javafx.controls,javafx.fxml -cp target/classes Application.AppMain
     ```

## 🔧 Configuração do Maven
No caso de usar Maven, certifique-se de que o arquivo `pom.xml` contém as dependências do JavaFX:
```xml
<dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-controls</artifactId>
    <version>17</version> <!-- Ajuste conforme necessário -->
</dependency>
