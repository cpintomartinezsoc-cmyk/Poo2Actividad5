# 🔄 Actividad Formativa – Sincronizando procesos en sistemas concurrentes 👤

---

## 👨‍💻 Datos del estudiante

**Nombre:** Camilo Pinto
**Carrera:** Analista Programador
**Asignatura:** Desarrollo Orientado a Objetos II
**Actividad:** Semana 5
**Caso:** SpeedFast

---

## 📌 Descripción

En esta quinta semana se continúa desarrollando el sistema de la empresa **SpeedFast**, incorporando mecanismos de **sincronización** para controlar el acceso de múltiples repartidores a un recurso compartido.

El problema planteado consiste en que varios repartidores pueden intentar retirar pedidos al mismo tiempo desde una misma **zona de carga**, lo que podría provocar que un pedido sea retirado más de una vez.

Para solucionar este problema se implementa un sistema concurrente utilizando:

* **Thread**
* **Runnable**
* **synchronized**
* **Thread.sleep()**
* **join()**
* Recursos compartidos
* Clases
* Herencia
* Polimorfismo
* Interfaces
* **Enum**

---

## 🚚 Caso SpeedFast

SpeedFast cuenta con una zona de carga donde se almacenan los pedidos que deben ser entregados.

Los diferentes repartidores trabajan de forma independiente, pero todos utilizan la misma zona de carga.

El sistema debe garantizar que cada pedido sea retirado y entregado por **un único repartidor**.

El flujo principal es:

```text
Pedido
   ↓
Zona de Carga
   ↓
Repartidor
   ↓
EN_REPARTO
   ↓
Entrega
   ↓
ENTREGADO
```

---

## 📂 Estructura del proyecto

```text
src/main/java
│
├── app
│   └── Main.java
│
├── interfaz
│   ├── Cancelable.java
│   ├── Despachable.java
│   └── Rastreable.java
│
├── modelo
│   ├── EstadoPedido.java
│   ├── Pedido.java
│   ├── PedidoComida.java
│   ├── PedidoEncomienda.java
│   ├── PedidoExpress.java
│   └── ZonaDeCarga.java
│
└── tareas
    └── Repartidor.java
```

---

## 🧱 Clase `Pedido`

La clase abstracta **Pedido** continúa siendo la clase principal del sistema SpeedFast.

Contiene información común para los distintos tipos de pedidos:

* `id`
* `direccionEntrega`
* `estado`

Además, mantiene la implementación de las interfaces utilizadas anteriormente:

```text
Cancelable
Despachable
Rastreable
```

La clase también incorpora métodos como:

```java
getId()
getDireccionEntrega()
getEstado()
setEstado()
toString()
```

El estado del pedido se controla mediante el enum:

```java
EstadoPedido
```

---

## 🍔 `PedidoComida`

Representa un pedido proveniente de un restaurante.

Hereda de:

```java
Pedido
```

Cuenta con información adicional sobre el restaurante y mantiene la estructura de herencia y polimorfismo utilizada en las actividades anteriores.

---

## 📦 `PedidoEncomienda`

Representa una encomienda que debe ser entregada a un determinado destino.

Hereda de:

```java
Pedido
```

Cuenta con información adicional sobre el destino de la encomienda.

---

## 🛒 `PedidoExpress`

Representa un pedido que utiliza el servicio de entrega express.

Hereda de:

```java
Pedido
```

Cuenta con información adicional relacionada con el tipo de compra.

---

## 🔄 Enum `EstadoPedido`

El enum define los estados que puede tener un pedido durante el proceso de entrega:

```java
public enum EstadoPedido {
    PENDIENTE,
    EN_REPARTO,
    ENTREGADO
}
```

El flujo de estados utilizado es:

```text
PENDIENTE
     ↓
EN_REPARTO
     ↓
ENTREGADO
```

El uso de `enum` permite trabajar con estados definidos y evita errores de escritura.

---

## 📍 Clase `ZonaDeCarga`

La clase **ZonaDeCarga** representa el recurso compartido de SpeedFast.

En ella se almacenan los pedidos que deben ser retirados por los repartidores.

Utiliza una lista de pedidos:

```java
List<Pedido>
```

Los métodos principales son:

```java
public synchronized void agregarPedido(Pedido pedido)
```

y:

```java
public synchronized Pedido retirarPedido()
```

---

## 🔐 Sincronización con `synchronized`

El método `retirarPedido()` utiliza:

```java
synchronized
```

para controlar el acceso simultáneo de los repartidores.

Los tres repartidores utilizan la misma instancia de:

```text
ZonaDeCarga
```

Por lo tanto, varios hilos pueden intentar acceder al mismo recurso.

El uso de `synchronized` permite que solamente un hilo pueda ejecutar el método de retiro a la vez.

Esto evita que un mismo pedido sea retirado por más de un repartidor.

---

## 🚴 Clase `Repartidor`

La clase **Repartidor** representa a los trabajadores encargados de realizar las entregas.

Implementa:

```java
Runnable
```

Cada repartidor posee:

* Nombre
* Referencia a la zona de carga compartida

Al ejecutar `run()`, el repartidor:

1. Retira un pedido de la zona de carga.
2. Cambia el estado a `EN_REPARTO`.
3. Muestra la información de la entrega.
4. Simula el tiempo de entrega.
5. Cambia el estado a `ENTREGADO`.
6. Continúa con el siguiente pedido.

---

## 🧵 `Runnable` y `Thread`

Cada repartidor se ejecuta como una tarea independiente mediante `Runnable`.

En `Main` se crean tres hilos:

```java
Thread repartidor1 = new Thread(
        new Repartidor("Carlos", zonaDeCarga)
);

Thread repartidor2 = new Thread(
        new Repartidor("Maria", zonaDeCarga)
);

Thread repartidor3 = new Thread(
        new Repartidor("Pedro", zonaDeCarga)
);
```

Los hilos se inician mediante:

```java
repartidor1.start();
repartidor2.start();
repartidor3.start();
```

De esta manera, los tres repartidores pueden trabajar en paralelo.

---

## ⏱️ `Thread.sleep()`

Para simular el tiempo necesario para realizar una entrega se utiliza:

```java
Thread.sleep(2000);
```

Durante este tiempo el repartidor se encuentra realizando la entrega.

Una vez terminada la espera, el pedido cambia a:

```java
EstadoPedido.ENTREGADO
```

---

## ⏳ `join()`

Después de iniciar los tres repartidores, `Main` utiliza `join()`:

```java
repartidor1.join();
repartidor2.join();
repartidor3.join();
```

Esto permite que el programa principal espere hasta que los tres repartidores hayan terminado sus tareas.

Finalmente se muestra:

```text
Todos los pedidos han sido entregados correctamente.
```

---

## 🔄 Funcionamiento concurrente

Los tres repartidores trabajan sobre la misma zona de carga:

```text
                    ZONA DE CARGA
                         │
             ┌───────────┼───────────┐
             │           │           │
             ▼           ▼           ▼
          Carlos       Maria       Pedro
          Thread       Thread      Thread
             │           │           │
             └───────────┼───────────┘
                         │
                  retirarPedido()
                         │
                    synchronized
                         │
                         ▼
                      Pedido
                         │
                         ▼
                    EN_REPARTO
                         │
                         ▼
                   Thread.sleep()
                         │
                         ▼
                     ENTREGADO
```

---

## 🏠 Datos utilizados

Para simular el funcionamiento de SpeedFast se utilizaron direcciones hipotéticas de **Puerto Varas y sus alrededores**.

Algunos ejemplos son:

* Avenida Vicente Pérez Rosales
* Avenida Gramado
* Avenida Costanera
* Camino a Ensenada
* Camino a Nueva Braunau

Estos datos son utilizados solamente para representar las entregas del sistema.

---

## 🧠 Conceptos aplicados

### **Clase abstracta**

`Pedido` se mantiene como clase abstracta para representar las características comunes de los diferentes tipos de pedidos.

### **Herencia**

Las clases:

```text
PedidoComida
PedidoEncomienda
PedidoExpress
```

heredan de:

```text
Pedido
```

### **Polimorfismo**

Los diferentes tipos de pedidos son tratados mediante referencias de tipo:

```java
Pedido
```

### **Interfaces**

Se mantienen las interfaces utilizadas anteriormente:

```text
Cancelable
Despachable
Rastreable
```

### **Runnable**

`Repartidor` implementa `Runnable` para definir las tareas que ejecutará cada hilo.

### **Thread**

Se utilizan objetos `Thread` para ejecutar los repartidores de manera independiente.

### **synchronized**

Se utiliza para controlar el acceso concurrente a la zona de carga.

### **Thread.sleep()**

Permite simular el tiempo necesario para realizar una entrega.

### **join()**

Permite esperar la finalización de los hilos antes de terminar la ejecución principal.

### **Enum**

`EstadoPedido` permite representar los estados posibles de cada pedido.

---

## 📊 Flujo del sistema

```text
                    SPEEDFAST
                        │
                        ▼
                 Pedidos pendientes
                        │
                        ▼
                  Zona de Carga
                        │
                 ┌──────┴──────┐
                 │ synchronized │
                 └──────┬──────┘
                        │
          ┌─────────────┼─────────────┐
          ▼             ▼             ▼
       Carlos         Maria         Pedro
       Thread         Thread        Thread
          │             │             │
          └─────────────┼─────────────┘
                        │
                        ▼
                    EN_REPARTO
                        │
                        ▼
                  Thread.sleep()
                        │
                        ▼
                    ENTREGADO
                        │
                        ▼
                  Todos los pedidos
                     entregados
```

---

## ▶️ Ejecución

Para ejecutar el proyecto:

1. Abrir el proyecto en **IntelliJ IDEA**.
2. Ubicar la clase:

```text
Main.java
```

3. Ejecutar:

```java
public static void main(String[] args)
```

4. Observar cómo los tres repartidores trabajan simultáneamente.
5. Verificar que cada pedido sea retirado una sola vez.
6. Esperar el mensaje final de la simulación.

---

## 📚 Contenidos utilizados

| Contenido       | Aplicación                                |
| --------------- | ----------------------------------------- |
| Clase abstracta | `Pedido`                                  |
| Herencia        | Tipos de pedidos                          |
| Polimorfismo    | Referencias de tipo `Pedido`              |
| Interfaces      | `Cancelable`, `Despachable`, `Rastreable` |
| Enum            | `EstadoPedido`                            |
| Runnable        | `Repartidor`                              |
| Thread          | Ejecución de repartidores                 |
| synchronized    | Control de acceso a la zona de carga      |
| Thread.sleep()  | Simulación de entregas                    |
| join()          | Espera de finalización de los hilos       |
| List            | Almacenamiento de pedidos                 |

---

## 📁 Repositorio

**Repositorio GitHub:**

`Poo2Semana5`

**Actividad:** Sincronizando procesos en sistemas concurrentes

---

## ✅ Conclusión

En esta actividad se continuó desarrollando el sistema **SpeedFast**, incorporando mecanismos de sincronización para solucionar problemas relacionados con el acceso concurrente a la zona de carga.

Se utilizó una instancia compartida de `ZonaDeCarga`, a la cual acceden tres repartidores ejecutados mediante hilos.

Mediante el uso de `synchronized`, se controla el retiro de pedidos y se evita que un mismo pedido pueda ser retirado por más de un repartidor.

De esta manera, el proyecto continúa aplicando los conceptos de **Programación Orientada a Objetos, hilos, Runnable y sincronización de procesos en Java**.
