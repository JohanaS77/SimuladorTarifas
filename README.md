# 🧮 Simulador de Tarifas

Aplicación backend desarrollada con **Java y Spring Boot** que calcula tarifas finales a partir de un valor base y un porcentaje de impuesto, aplicando principios de inyección de dependencias y separación de responsabilidades (Service / Runner).

## 📌 Descripción del proyecto

Este proyecto corresponde a una actividad de Desarrollo de Software Backend de la Fundación Universitaria Compensar.

El objetivo principal es construir una aplicación en Spring Boot que, al iniciar, calcule automáticamente una tarifa final aplicando un impuesto sobre un valor base. Los valores de configuración (`tarifa.base` y `tarifa.impuesto`) se gestionan de forma externa mediante el archivo `application.properties`, evitando que estén "quemados" en el código (hardcoded).

La lógica de cálculo se encuentra aislada en una clase de servicio (`TarifaService`), gestionada por el contenedor de Spring, mientras que la ejecución y presentación del resultado se realiza mediante un `CommandLineRunner` (`TarifaRunner`) que se dispara automáticamente al levantar la aplicación.

<a name="indice"></a>
## 📑 Índice

- [Características principales](#caracteristicas-principales)
- [Cómo funciona el cálculo](#como-funciona-el-calculo)
- [Tecnologías utilizadas](#tecnologias-utilizadas)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Instalación y ejecución](#instalacion-y-ejecucion)
- [Configuración de la tarifa](#configuracion-de-la-tarifa)
- [Ejemplo de salida](#ejemplo-de-salida)
- [Evidencias](#evidencias-de-funcionamiento)
- [Mejoras futuras](#mejoras-futuras)
- [Desarrollador](#desarrollador)
- [Licencia](#licencia)

<a name="caracteristicas-principales"></a>
## ✨ Características principales

### ⚙️ Cálculo automático al iniciar

Al ejecutar la aplicación, `TarifaRunner` calcula y muestra por consola el resultado del cálculo de la tarifa, sin necesidad de exponer endpoints ni realizar peticiones externas.

### 🧩 Inyección de dependencias

`TarifaService` se declara como un componente `@Service`, gestionado por el contenedor de Spring e inyectado mediante `@Autowired` en `TarifaRunner`.

### 🔧 Configuración externa

Los valores `tarifa.base` y `tarifa.impuesto` se leen desde `application.properties` usando la anotación `@Value`, permitiendo modificar el comportamiento de la aplicación sin tocar el código fuente.

### 🧱 Separación de responsabilidades

- **`service/TarifaService.java`** → contiene la lógica de negocio (fórmula del cálculo).
- **`runner/TarifaRunner.java`** → orquesta la ejecución y la presentación del resultado.
- **`SimuladorTarifasApplication.java`** → punto de entrada de la aplicación Spring Boot.

**[⬆ Volver al índice](#indice)**

<a name="como-funciona-el-calculo"></a>
## 🧮 Cómo funciona el cálculo

La fórmula aplicada por `TarifaService` es:

```
Tarifa final = Base + (Base × Impuesto)
```

Por ejemplo, con los valores por defecto del proyecto:

- **Base:** 150000
- **Impuesto:** 0.19 (19%)
- **Tarifa final:** 150000 + (150000 × 0.19) = **178500**

**[⬆ Volver al índice](#indice)**

<a name="tecnologias-utilizadas"></a>
## 🛠️ Tecnologías utilizadas

- **Java 17**
- **Spring Boot 4.1.1** (Spring Web / Core)
- **Maven** — Gestión de dependencias y construcción del proyecto
- **Git / GitHub** — Control de versiones y almacenamiento del proyecto

**[⬆ Volver al índice](#indice)**

<a name="estructura-del-proyecto"></a>
## 📁 Estructura del proyecto

```
SimuladorTarifas/
│
├── src/
│   ├── main/
│   │   ├── java/com/example/simuladortarifas/
│   │   │   ├── runner/
│   │   │   │   └── TarifaRunner.java        # Ejecuta y muestra el resultado
│   │   │   ├── service/
│   │   │   │   └── TarifaService.java       # Lógica de cálculo de la tarifa
│   │   │   └── SimuladorTarifasApplication.java  # Punto de entrada
│   │   └── resources/
│   │       └── application.properties       # Configuración (base e impuesto)
│   └── test/
│       └── java/com/example/simuladortarifas/
│           └── SimuladorTarifasApplicationTests.java
│
├── pom.xml            # Configuración y dependencias Maven
└── .gitignore         # Archivos y carpetas excluidos de Git
```

**[⬆ Volver al índice](#indice)**

<a name="instalacion-y-ejecucion"></a>
## ⚙️ Instalación y ejecución

**1. Clonar el repositorio**

```bash
git clone https://github.com/JohanaS77/SimuladorTarifas.git
```

**2. Ingresar al proyecto**

```bash
cd SimuladorTarifas
```

**3. Ejecutar la aplicación con Maven**

```bash
./mvnw spring-boot:run
```

> En Windows, si `./mvnw` no funciona directamente en PowerShell, usa `mvnw.cmd spring-boot:run` o ejecuta el proyecto directamente desde IntelliJ dándole **Run** a `SimuladorTarifasApplication`.

**4. Ver el resultado**

Al iniciar, la aplicación imprimirá el resultado del cálculo directamente en la consola.

**[⬆ Volver al índice](#indice)**

<a name="configuracion-de-la-tarifa"></a>
## ⚙️ Configuración de la tarifa

Los valores utilizados para el cálculo se definen en:

```
src/main/resources/application.properties
```

```properties
tarifa.base=150000
tarifa.impuesto=0.19
```

Puedes modificar estos valores para simular diferentes escenarios sin cambiar el código Java.

**[⬆ Volver al índice](#indice)**

<a name="ejemplo-de-salida"></a>
## 💻 Ejemplo de salida

```
=== Simulador de Tarifas ===
Base: 150000.0
Impuesto: 0.19
Tarifa final: 178500.0
```

**[⬆ Volver al índice](#indice)**

<a name="evidencias-de-funcionamiento"></a>
## 📸 Evidencias de funcionamiento

**Ejecución del proyecto**

![Ejecución del Simulador de Tarifas](docs/evidencia-ejecucion.png)

**[⬆ Volver al índice](#indice)**

<a name="mejoras-futuras"></a>
## 🔮 Mejoras futuras

Como posibles ampliaciones del proyecto se plantean:

- Exponer el cálculo mediante un endpoint REST (`GET /api/v1/tarifa`).
- Permitir enviar `base` e `impuesto` como parámetros de la petición.
- Validación de datos de entrada (valores negativos, nulos, etc.).
- Persistencia del historial de cálculos en una base de datos.
- Pruebas unitarias para `TarifaService`.
- Documentación de la API mediante Swagger / OpenAPI.
- Interfaz web para simular tarifas de forma interactiva.

**[⬆ Volver al índice](#indice)**

<a name="desarrollador"></a>
## 🎓 Desarrollador

<img src="docs/johana.png" width="120" height="120" style="border-radius: 50%;" alt="Foto de perfil"/>

Proyecto desarrollado como parte de las actividades académicas del programa de Desarrollo de Software Backend de la Fundación Universitaria Compensar.

- **Estudiante:** Johana Jazmín Saavedra Tafur
- **Institución:** Fundación Universitaria Compensar
- **Proyecto:** Simulador de Tarifas

**[⬆ Volver al índice](#indice)**

<a name="licencia"></a>
## 📄 Licencia

Este proyecto fue desarrollado con fines académicos como parte del proceso de formación en desarrollo de software backend.

**[⬆ Volver al índice](#indice)**
