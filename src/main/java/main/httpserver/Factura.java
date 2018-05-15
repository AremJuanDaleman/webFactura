/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.httpserver;

/**
 *
 * @author danie
 */
class Factura {
    
    private int id;
    private String nombreEmpleado;
    private String nombreCliente;
    
    public Factura(){}
    
    public Factura(int id, String nombreEmpleado, String nombreCliente){          
        this.id = id;
        this.nombreEmpleado = nombreEmpleado;
        this.nombreCliente = nombreCliente;        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }        
}
