/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Boris
 */
public class DetalleFactura {

    private Integer id;
    private Integer factura;
    private Integer producto;
    private Integer cantidad;
    private double precio;
    private double total;

    public DetalleFactura() {
    }

    public DetalleFactura(Integer id, Integer factura, Integer producto, Integer cantidad, double precio, double total) {
        this.id = id;
        this.factura = factura;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFactura() {
        return factura;
    }

    public void setFactura(Integer factura) {
        this.factura = factura;
    }

    public Integer getProducto() {
        return producto;
    }

    public void setProducto(Integer producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public double obtenerTotal(){
        return cantidad * precio;
    }
    
}
