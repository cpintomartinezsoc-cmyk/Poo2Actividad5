<img width="488" height="157" alt="image" src="https://github.com/user-attachments/assets/9687512f-3b36-46c6-aacc-45c480cae33b" />

# 🔄 Actividad Formativa – Sincronizando procesos en sistemas concurrentes

---

## 👤 Datos del estudiante

**Nombre:** Camilo Pinto

**Carrera:** Analista Programador

**Asignatura:** Desarrollo Orientado a Objetos II

**Semana:** 5

**Caso:** SpeedFast


---

## 📌 Descripción

En esta actividad se continúa desarrollando el sistema de entregas **SpeedFast**, incorporando el uso de **sincronización de procesos concurrentes**.

El sistema cuenta con una **zona de carga compartida**, donde los pedidos son almacenados antes de ser retirados por los repartidores.

El objetivo principal es evitar que dos repartidores retiren el mismo pedido al mismo tiempo.

---

## 🏗️ Estructura del proyecto

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

## 📦 Descripción de las clases

### `Pedido`

Clase abstracta que representa un pedido de SpeedFast. Contiene los datos generales del pedido, como su identificador, dirección de entrega y estado.

### `PedidoComida`

Representa un pedido correspondiente a un restaurante.

### `PedidoEncomienda`

Representa un pedido de tipo encomienda.

### `PedidoExpress`

Representa un pedido de entrega express.

### `EstadoPedido`

Enum que permite controlar los estados de cada pedido:

* `PENDIENTE`
* `EN_REPARTO`
* `ENTREGADO`

### `ZonaDeCarga`

Representa el recurso compartido por los repartidores.

Utiliza métodos `synchronized` para controlar el acceso de los distintos hilos y evitar que un mismo pedido sea retirado por más de un repartidor.

### `Repartidor`

Implementa `Runnable` y representa a cada repartidor que trabaja de manera independiente.

Cada repartidor retira un pedido de la zona de carga, cambia su estado a `EN_REPARTO`, simula la entrega y finalmente cambia el estado a `ENTREGADO`.

### Interfaces

El proyecto mantiene las interfaces utilizadas en las actividades anteriores:

* `Cancelable`
* `Despachable`
* `Rastreable`

Estas permiten mantener la continuidad del sistema SpeedFast desarrollado durante las semanas anteriores.

---

## 🧵 Ejecución

En `Main` se crea la zona de carga y se agregan **5 pedidos** de distintos tipos.

Luego se crean **3 repartidores**, cada uno ejecutándose mediante un `Thread`.

Los repartidores trabajan simultáneamente y retiran pedidos desde la misma zona de carga.

El uso de `synchronized` permite que el acceso a la zona de carga s

