# 🦕 Parque Turístico de Dinosaurios

Simulación secuencial de un parque turístico de dinosaurios desarrollada en Java 17 con Maven.

## 🛠️ Herramientas utilizadas

- Java 17 (Oracle OpenJDK 17.0.18)
- Maven 3.x
- JUnit Jupiter 5.10.0
- IntelliJ IDEA 2025.3.1.1

## ⚙️ Configuración

El sistema es configurable mediante el archivo `src/main/resources/park.properties`:

```properties
simulation.seed=42
simulation.totalSteps=100
simulation.arrivalBatchSize=5
tourists=50
dinosaurs.carnivores=5
dinosaurs.herbivores=15
```

## ▶️ Ejecución del proyecto

**Compilar:**
```bash
mvn compile
```

**Ejecutar:**
```bash
mvn exec:java
```

## 📋 Explicación general del sistema

El sistema simula el funcionamiento de un parque turístico de dinosaurios. Durante la simulación:

- Los turistas llegan en lotes y compran boletos en la zona de arribo
- Visitan el Recinto Central, Baños y Recintos de Observación
- La Planta de Energía consume energía por cada paso
- Ocurren eventos aleatorios: escape de dinosaurio, apagón masivo y tormenta torrencial
- Los guardias recapturan dinosaurios y los técnicos reparan la planta
- Se generan archivos CSV con ingresos, gastos y eventos

## 🎨 Patrones de diseño utilizados

### 1. Singleton — ParkConfig

Garantiza que la configuración del parque se lea una sola vez desde `park.properties` y sea compartida por todas las clases.