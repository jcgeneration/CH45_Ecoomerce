package org.generation.ecommerce.model;
//POJO Plain Old Java Object

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="productos")
public class Producto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	@Column(nullable=false)
	private String nombre;
	private String descripcion;
	private String imagen;
	@Column(nullable=false)
	private double precio;
	@Column(nullable=false)
	private Long categoria_id;
	
//	private static Long total= Long.valueOf(0);  // Sólo para la versión con ArrayList
    public Producto(String nombre, String descripcion, String imagen, double precio) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.precio = precio;
//		Producto.total++;
//		id = Producto.total;
	}//constructor
	public Producto() {
//		Producto.total++;
//		id = Producto.total;
	}//constructor
		
	public Long getId() {
		return id;
	}//getId

	public void setId(Long id) {
		this.id = id;
	}//setId

	public String getNombre() {
		return nombre;
	}//getNombre

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}//setNombre

	public String getDescripcion() {
		return descripcion;
	}//getDescripcion

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}//setDescripcion

	public String getImagen() {
		return imagen;
	}//getImagen

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}//setImagen

	public double getPrecio() {
		return precio;
	}//getPrecio

	public void setPrecio(double precio) {
		this.precio = precio;
	}//setPrecio

	public Long getCategoria_id() {
		return categoria_id;
	}//getCategoria_id
	public void setCategoria_id(Long categoria_id) {
		this.categoria_id = categoria_id;
	}//setCategoria_id
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", imagen=" + imagen
				+ ", precio=" + precio + "]";
	}//toString

}//class Producto
