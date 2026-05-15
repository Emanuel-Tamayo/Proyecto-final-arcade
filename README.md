# 🎰 Proyecto_Final_Arcade

# 🎮 The Lucky Arcade — Slot Machine en Consola

 📌 Descripción

Este proyecto consiste en el desarrollo de un **simulador de máquina tragamonedas (Slot Machine)** en **Java**, ejecutado completamente en consola.

El sistema permite a los jugadores apostar, girar una matriz 3x3 de símbolos aleatorios y ganar premios según los patrones obtenidos.

Además, el proyecto implementa **persistencia de datos usando archivos CSV**, permitiendo guardar automáticamente el saldo de cada jugador para continuar partidas futuras.

El objetivo principal es integrar conceptos fundamentales de programación como:

* Matrices bidimensionales
* Métodos y modularidad
* Persistencia de archivos CSV
* Aleatoriedad
* ASCII Art
* Ciclos y validaciones
* Manejo de menús interactivos

---

# 🎯 Objetivos del proyecto

* Crear un casino funcional en consola usando Java.
* Implementar persistencia de saldo mediante archivos CSV.
* Utilizar matrices 3x3 para representar la tragamonedas.
* Detectar combinaciones ganadoras horizontales y diagonales.
* Aplicar modularidad usando métodos y diferentes clases.
* Mejorar la experiencia visual con animaciones y ASCII Art.
* Implementar rankings y sistema de apuestas.

---

# 👩‍💻 Integrantes

* Emanuel Tamayo Florez
* Juan Jose Alzate Escudero
* Juan Pablo Velez Lopera

---

# ⚙️ Tecnologías utilizadas

* Java ☕
* Git
* GitHub
* Visual Studio Code
* Archivos CSV

---

# 🎰 Funcionalidades del proyecto

El proyecto incluye las siguientes funcionalidades:

1. **Registro de jugadores**
2. **Persistencia de saldo en CSV**
3. **Carga automática del saldo**
4. **Máquina tragamonedas 3x3**
5. **Generación aleatoria de símbolos**
6. **Sistema de apuestas**
7. **Detección de líneas ganadoras**
8. **Premios horizontales**
9. **Premios diagonales**
10. **Multiplicadores de premios**
11. **Ranking de jugadores**
12. **Animación de giro**
13. **Menú interactivo**
14. **ASCII Art en consola**
15. **Easter Egg secreto**
16. **Bonificaciones especiales**

---

# 🧩 Lógica del juego

La tragamonedas utiliza una matriz de tamaño 3x3 y un conjunto de símbolos aleatorios para representar los rodillos del juego.

Cada vez que el jugador gira la máquina:

* La matriz se llena aleatoriamente.
* Se muestran los símbolos en consola.
* El sistema analiza combinaciones ganadoras.
* El saldo del jugador se actualiza automáticamente.

---

# 🏆 Sistema de premios

## Líneas horizontales

El jugador gana si obtiene 3 símbolos iguales en cualquiera de las filas de la matriz.

---

## Diagonales

También se detectan combinaciones ganadoras en:

* Diagonal principal
* Diagonal secundaria

---

## Multiplicadores

Si el jugador gana en varias líneas simultáneamente:

* El premio aumenta automáticamente.
* Los símbolos raros otorgan más dinero.
* Algunos símbolos especiales generan mayores recompensas.

---

# 💾 Persistencia de datos (CSV)

El sistema guarda la información de los jugadores en un archivo CSV.

El programa puede:

* Buscar jugadores existentes
* Crear nuevos jugadores
* Cargar saldo automáticamente
* Actualizar saldo al finalizar cada jugada
* Guardar información de forma segura

---

# 🎨 Experiencia de usuario

El proyecto incluye mejoras visuales en consola.

## Menú interactivo

El usuario puede:

* Apostar
* Ver saldo
* Consultar ranking
* Salir del juego

---

## Animación de giro

Se utiliza un bucle que:

* Genera símbolos aleatorios
* Actualiza la pantalla varias veces
* Simula el movimiento real de una tragamonedas

---

## ASCII Art

El juego incluye:

* Título decorado
* Máquina tragamonedas en texto
* Mensajes visuales personalizados

---

# ⚙️ Modularidad

El proyecto se organiza usando métodos y clases separadas.

## Métodos principales

* cargarSaldo()
* guardarSaldo()
* dibujarPantalla()
* calcularPremio()
* animarGiro()

Esto permite:

* Mejor organización
* Reutilización de código
* Mejor mantenimiento
* Trabajo en equipo más sencillo

---

# 🥚 BONUS — Easter Egg: El Amuleto

El juego incluye un secreto especial.

## Activación

El bonus puede activarse mediante:

* Una apuesta específica
* Un nombre secreto del jugador

---

## Efectos especiales

Cuando se activa:

* Se aumentan las probabilidades de ganar
* Puede iniciarse un minijuego especial
* El bonus dura varios turnos
* El jugador recibe recompensas adicionales

---

# ⚠️ Consideraciones

* El archivo CSV debe actualizarse constantemente.
* Validar apuestas para evitar números negativos.
* El saldo nunca puede ser menor que cero.
* La matriz debe llenarse correctamente con símbolos aleatorios.
* El ranking debe organizarse por mayor saldo.
* Se recomienda usar métodos para evitar repetir código.

---

# 📚 Referencias

* Documentación oficial de Java
* Apuntes de clase
* ChatGPT como guía de apoyo
* Oracle Java Docs

---

# 📚 Diapositivas

* Presentación del proyecto en Canva
* Explicación del funcionamiento del sistema
* Demostración de la tragamonedas
