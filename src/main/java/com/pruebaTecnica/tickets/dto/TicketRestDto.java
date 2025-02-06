package com.pruebaTecnica.tickets.dto;

public class TicketRestDto {

    private String usuario;

    private Estatus estatus;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }
}
