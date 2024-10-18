package org.generation.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.generation.ecommerce.model.Producto;
import org.generation.ecommerce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
	public final ProductoRepository productoRepository; 
	
	@Autowired
	public ProductoService(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}//constructor

	public List<Producto> getAllProductos() {
		return productoRepository.findAll();
	}//getAllProductos

	public Producto getProducto(Long prodId) {
		return productoRepository.findById(prodId).orElseThrow(
				()-> new IllegalArgumentException("El producto con el id [" + prodId
						+ "] no existe.")
				);
	}//getProducto
	
	public Producto deleteProducto(Long prodId) {
		Producto prod=null;
		if(productoRepository.existsById(prodId)) {
			prod=productoRepository.findById(prodId).get();
			productoRepository.deleteById(prodId);
		}//if exists
		return prod;
	}//deleteProducto


	public Producto updateProducto(Long prodId, String nombre, String descripcion, 
											 String imagen,Double precio, Long categoria_id) {
		Producto prod=null;
		if(productoRepository.existsById(prodId)) {
			Producto producto = productoRepository.findById(prodId).get();
			if (nombre != null) producto.setNombre(nombre);
			if(descripcion!=null) producto.setDescripcion(descripcion);
			if(imagen!=null) producto.setImagen(imagen);
			if(categoria_id!=null) producto.setCategoria_id(categoria_id);
			if(precio!=null) producto.setPrecio(precio.doubleValue());
			productoRepository.save(producto);
			prod=producto;
		}//exists
		return prod;
	}//updateProducto

	public Producto addProducto(Producto producto) {
		Optional<Producto> prod = productoRepository.findByNombre(producto.getNombre());
		if(prod.isEmpty()) { // No existe el nombre
			return productoRepository.save(producto);	
		} else {
			System.out.println("El producto [" + producto.getNombre()
					+ "] ya se encuentra registrado");
			return null;
		}//if isEmpty
	}//addProducto
	
}//class ProductoService