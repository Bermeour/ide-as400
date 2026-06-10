# Base AS400

Emulador de terminal TN5250 para sistemas IBM AS/400 (iSeries), desarrollado en Java.

## Descripción

Base AS400 es una aplicación de escritorio que permite conectarse a sistemas IBM AS/400 a través del protocolo TN5250 (Telnet 5250). Proporciona una interfaz gráfica completa para interactuar con el sistema operativo IBM i, con soporte para múltiples sesiones simultáneas, configuración de pantalla, teclado y codificación de caracteres.

## Tecnologías

- **Java 8**
- **Swing / AWT** — Interfaz gráfica de usuario
- **Jython** — Motor de scripting para automatización de sesiones
- **JT400 (IBM Toolbox)** — Librería de conectividad AS/400
- **SLF4J / Log4j** — Sistema de logging
- **Maven** — Gestión de dependencias y construcción

## Estructura del proyecto

```
src/com/ide/as400/
├── My5250.java               # Punto de entrada principal
├── framework/                # Núcleo del emulador TN5250
│   ├── tn5250/               # Motor de pantalla y protocolo
│   └── transport/            # Conexión de red y SSL
├── gui/                      # Componentes de interfaz gráfica
├── connectdialog/            # Diálogos de conexión y configuración
├── session/                  # Gestión de sesiones
├── keyboard/                 # Manejo de teclado y atajos
├── encoding/                 # Páginas de código EBCDIC/Unicode
├── sessionsettings/          # Paneles de configuración de sesión
├── spoolfile/                # Gestión de colas de impresión
├── sql/                      # Transferencia de datos vía SQL
├── scripting/                # Motor de scripts Jython
├── mailtools/                # Envío de correo electrónico
└── tools/                    # Utilidades generales
```

## Compilar y ejecutar

### Requisitos
- Java 8 o superior
- Maven 3.x

### Compilar
```bash
mvn compile
```

### Ejecutar pruebas
```bash
mvn test
```

### Generar JAR ejecutable
```bash
mvn package
```

### Ejecutar
```bash
java -jar target/tn5250j-0.7.6.4-jar-with-dependencies.jar
```

## Ejecución embebida (modo programático)

```java
System.setProperty("emulator.settingsDirectory", "/ruta/configuracion/");
ConfigureFactory.getInstance();
LangTool.init();

SessionBean sb = new SessionBean(session);
sb.setHostName("ip-del-as400");
sb.setCodePage("Cp273");
sb.connect();
```
