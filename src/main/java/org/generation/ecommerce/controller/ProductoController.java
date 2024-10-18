package org.generation.ecommerce.controller;

import java.util.List;

import org.generation.ecommerce.model.Producto;
import org.generation.ecommerce.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://127.0.0.1:5500", methods = {RequestMethod.GET, RequestMethod.POST})

@RequestMapping(path="/api/productos/") // http://localhost:8080/api/productos/
@RestController
public class ProductoController {

	private final ProductoService productoService;
	
	@Autowired
	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}//constructor

	@GetMapping
	public List<Producto> getProductos() {
		return productoService.getAllProductos(); 
	}//getProductos
	
	@GetMapping(path="{prodId}") // http://localhost:8080/api/productos/1
	public Producto getProducto(@PathVariable("prodId") Long prodId) {
		return productoService.getProducto(prodId); 
	}//getProducto
	@PostMapping				 // http://localhost:8080/api/productos/		
	public Producto addProducto(@RequestBody Producto producto) {
		return productoService.addProducto(producto);
	}//	addProducto
	
	@DeleteMapping(path="{prodId}") // http://localhost:8080/api/productos/1
	public Producto deleteProducto (@PathVariable("prodId") Long prodId) {
		return productoService.deleteProducto(prodId);
	}//deleteProducto
	
	@PutMapping(path="{prodId}") // http://localhost:8080/api/productos/1
	public Producto updateProducto (@PathVariable("prodId") Long prodId,
								    @RequestParam(required=false) String nombre,	
									@RequestParam(required=false) String descripcion,
									@RequestParam(required=false) String imagen,
									@RequestParam(required=false) Double precio,
									@RequestParam(required=false) Long categoria_id) {
		return productoService.updateProducto(prodId, nombre, descripcion, imagen, 
							precio, categoria_id);
	}//updateProducto
}//class ProductoController
