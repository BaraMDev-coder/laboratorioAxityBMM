# 🦕 Parque Turístico de Dinosaurios

Simulación secuencial de un parque turístico de dinosaurios desarrollada en Java 17 utilizando Maven.

---

# 🛠️ Herramientas y tecnologías utilizadas

- Java 17
- Apache Maven 3.x
- JUnit Jupiter 5.10.0
- JaCoCo 0.8.10
- IntelliJ IDEA Ultimate 2025
- Git y GitHub

---

# ⚙️ Configuración

El sistema es configurable mediante el archivo:

```text
src/main/resources/park.properties
```

Ejemplo de configuración:

```properties
simulation.seed=42
simulation.totalSteps=100
simulation.arrivalBatchSize=5
tourists=50
dinosaurs.carnivores=5
dinosaurs.herbivores=15
```

Parámetros configurables:
- Cantidad de turistas
- Dinosaurios carnívoros y herbívoros
- Semilla de simulación
- Número total de pasos
- Tamaño de lotes de llegada

---

# ▶️ Ejecución del proyecto

## Compilar el proyecto

```bash
mvn compile
```

## Ejecutar simulación

```bash
mvn exec:java
```

---

# 🧪 Pruebas Unitarias

El proyecto incluye pruebas unitarias desarrolladas con JUnit 5.

## Ejecutar pruebas

```bash
mvn clean test
```

## Generar reporte de cobertura

```bash
mvn clean test
```

El reporte de JaCoCo se genera en:

```text
target/site/jacoco/index.html
```

## Cobertura alcanzada

- Cobertura total: 79%

---

# 📋 Explicación general del sistema

El sistema simula el funcionamiento de un parque turístico de dinosaurios.

Durante la simulación:

- Los turistas llegan en lotes y compran boletos en la zona de arribo.
- Los visitantes recorren distintas zonas del parque.
- Se generan ingresos por venta de boletos, souvenirs y servicios.
- La planta de energía consume recursos y puede presentar fallas.
- Ocurren eventos aleatorios como:
    - Escape de dinosaurio
    - Apagón masivo
    - Tormenta torrencial
- Los guardias recapturan dinosaurios escapados.
- Los técnicos reparan la planta de energía.
- Se generan archivos CSV con:
    - ingresos
    - gastos
    - eventos
    - monitoreo

---

# 🦖 Zonas del parque

El parque está dividido en distintas áreas:

## Lugar de arribo
- Entrada de turistas
- Venta de boletos
- Control de capacidad

## Recinto central
- Información general
- Venta de souvenirs
- Distribución de visitantes

## Baños
- Capacidad limitada
- Servicios adicionales

## Planta de energía
- Suministro eléctrico
- Mantenimiento y fallas

## Recintos de observación
- Experiencias BASIC, PREMIUM y VIP
- Límite de visitantes
- Encuestas de satisfacción

---

# 🎨 Patrones de diseño utilizados

## 1. Singleton — ParkConfig

La clase `ParkConfig` implementa el patrón Singleton para garantizar una única instancia de configuración durante toda la ejecución del sistema.

Esto permite:
- Compartir configuraciones globales
- Evitar múltiples lecturas del archivo `.properties`
- Centralizar parámetros del sistema

---

## 2. Factory Method — SimulationEngine

La clase `SimulationEngine` encapsula la creación de objetos complejos mediante métodos especializados:

```java
createDinosaurs()
createWorkers()
createEnclosures()
```

Esto facilita:
- Organización del código
- Reutilización
- Mantenimiento del sistema
- Separación de responsabilidades

---

# 📁 Estructura del proyecto

```text
src
├── main
│   ├── java
│   │   └── com.axity.dinosaurpark
│   │       ├── config
│   │       ├── event
│   │       ├── model
│   │       ├── monitoring
│   │       ├── persistence
│   │       ├── simulation
│   │       └── zone
│   └── resources
│       └── park.properties
│
└── test
    └── java
```

---

# 📊 Sistema de monitoreo

El sistema de monitoreo permite visualizar:

- Turistas activos
- Dinosaurios que hay en el parque
- Estado de energía
- Eventos ocurridos
- Estado general del parque

---

# 💾 Persistencia

El sistema genera automáticamente archivos CSV dentro de la carpeta:

```text
output/
```

Archivos generados:
- `ingresos.csv`
- `gastos.csv`
- `eventos.csv`

Estos archivos almacenan información relacionada con:
- ingresos del parque
- gastos operativos
- eventos ocurridos durante la simulación

---

# 👨‍💻 Autor: Brayan Martinez Martinez 