package org.generation.ecommerce.controller;

import java.util.List;

import org.generation.ecommerce.model.Categoria;
import org.generation.ecommerce.service.CategoriaService;
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
@RequestMapping(path="/api/categorias/") // http://localhost:8080/api/categorias/
@RestController
public class CategoriaController {
	private final CategoriaService categoriaService;
	@Autowired
	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}//constructor
	@GetMapping
	public List<Categoria> getCategorias() {
		return categoriaService.getAllCategorias(); 
	}//getCategorias
	@GetMapping(path="{id}") // http://localhost:8080/api/categorias/1
	public Categoria getCategoria(@PathVariable("id") Long id) {
		return categoriaService.getCategoria(id); 
	}//getCategoria
	@PostMapping				 // http://localhost:8080/api/categorias/		
	public Categoria addCategoria(@RequestBody Categoria categoria) {
		return categoriaService.addCategoria(categoria);
	}//	addCategoria
	@DeleteMapping(path="{id}") // http://localhost:8080/api/categorias/1
	public Categoria deleteCategoria (@PathVariable("id") Long id) {
		return categoriaService.deleteCategoria(id);
	}//deleteCategoria
		@PutMapping(path="{id}") // http://localhost:8080/api/categorias/1
	public Categoria updateCategoria (@PathVariable("id") Long id,
								    @RequestParam(required=false) String nombre,	
									@RequestParam(required=false) String descripcion) {
		return categoriaService.updateCategoria(id, nombre, descripcion);
	}//updateCategoria
}//class CategoriaController
