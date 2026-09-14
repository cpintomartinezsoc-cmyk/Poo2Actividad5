package tareas;

import modelo.EstadoPedido;
import modelo.Pedido;
import modelo.ZonaDeCarga;

public class Repartidor implements Runnable {

    private String nombre;
    private ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    @Override
    public void run() {

        while (true) {

            Pedido pedido = zonaDeCarga.retirarPedido();

            if (pedido == null) {
                break;
            }

            pedido.setEstado(EstadoPedido.EN_REPARTO);

            System.out.println(
                    nombre +
                            " retiro el pedido " +
                            pedido.getId()
            );

            System.out.println(
                    nombre +
                            " esta entregando el pedido " +
                            pedido.getId() +
                            " en " +
                            pedido.getDireccionEntrega()
            );

            try {

                Thread.sleep(2000);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                System.out.println(
                        nombre + " fue interrumpido."
                );

                return;
            }

            pedido.setEstado(EstadoPedido.ENTREGADO);

            System.out.println(
                    nombre +
                            " entrego el pedido " +
                            pedido.getId()
            );
        }

        System.out.println(
                nombre + " termino sus entregas."
        );
    }
}