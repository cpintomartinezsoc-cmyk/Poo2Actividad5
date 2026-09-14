package modelo;

import interfaz.Cancelable;
import interfaz.Despachable;
import interfaz.Rastreable;

public abstract class Pedido implements Despachable, Cancelable, Rastreable {

    protected int id;
    protected String direccionEntrega;
    protected EstadoPedido estado;

    public Pedido(int id, String direccionEntrega) {
        this.id = id;
        this.direccionEntrega = direccionEntrega;
        this.estado = EstadoPedido.PENDIENTE;
    }

    public int getId() {
        return id;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public void setEstado(EstadoPedido nuevoEstado) {
        this.estado = nuevoEstado;
    }

    @Override
    public String toString() {
        return "Pedido " + id +
                " | Direccion: " + direccionEntrega +
                " | Estado: " + estado;
    }

    @Override
    public void despachar() {
        estado = EstadoPedido.EN_REPARTO;
    }

    @Override
    public void cancelar() {
        if (estado != EstadoPedido.ENTREGADO) {
            estado = EstadoPedido.PENDIENTE;
        }
    }

    @Override
    public void verHistorial() {
        System.out.println(toString());
    }

    public abstract void mostrarResumen();
}