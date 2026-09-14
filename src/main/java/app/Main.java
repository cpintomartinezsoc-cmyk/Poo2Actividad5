package app;

import modelo.EstadoPedido;
import modelo.Pedido;
import modelo.PedidoComida;
import modelo.PedidoEncomienda;
import modelo.PedidoExpress;
import modelo.ZonaDeCarga;
import tareas.Repartidor;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("=== SPEEDFAST ===\n");

        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

        Pedido pedido1 = new PedidoComida(
                1,
                "Av. Vicente Perez Rosales 120",
                "Cafe Puerto Varas"
        );

        Pedido pedido2 = new PedidoExpress(
                2,
                "Av. Gramado 450",
                "Electronica"
        );

        Pedido pedido3 = new PedidoEncomienda(
                3,
                "Camino a Ensenada 800",
                "Ensenada"
        );

        Pedido pedido4 = new PedidoComida(
                4,
                "Av. Costanera 300",
                "Restaurante Puerto Varas"
        );

        Pedido pedido5 = new PedidoExpress(
                5,
                "Camino a Nueva Braunau 500",
                "Ropa"
        );

        zonaDeCarga.agregarPedido(pedido1);
        zonaDeCarga.agregarPedido(pedido2);
        zonaDeCarga.agregarPedido(pedido3);
        zonaDeCarga.agregarPedido(pedido4);
        zonaDeCarga.agregarPedido(pedido5);

        System.out.println("\nComienzan las entregas:\n");

        Thread repartidor1 = new Thread(
                new Repartidor("Carlos", zonaDeCarga)
        );

        Thread repartidor2 = new Thread(
                new Repartidor("Maria", zonaDeCarga)
        );

        Thread repartidor3 = new Thread(
                new Repartidor("Pedro", zonaDeCarga)
        );

        repartidor1.start();
        repartidor2.start();
        repartidor3.start();

        repartidor1.join();
        repartidor2.join();
        repartidor3.join();

        System.out.println(
                "\nTodos los pedidos han sido entregados correctamente."
        );
    }
}