package org.generation.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.generation.ecommerce.model.Categoria;
import org.generation.ecommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
	public final CategoriaRepository categoriaRepository; 
	@Autowired
	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}//constructor
	public List<Categoria> getAllCategorias() {
		return categoriaRepository.findAll();
	}//getAllCategorias
	public Categoria getCategoria(Long id) {
		return categoriaRepository.findById(id).orElseThrow(
				()-> new IllegalArgumentException("El Categoria con el id [" + id
						+ "] no existe."));
	}//getCategoria
	public Categoria deleteCategoria(Long id) {
		Categoria cat=null;
		if(categoriaRepository.existsById(id)) {
			cat=categoriaRepository.findById(id).get();
			categoriaRepository.deleteById(id);
		}//if exists
		return cat;
	}//deleteCategoria
	public Categoria updateCategoria(Long id, String nombre, String descripcion) {
		Categoria cat=null;
		if(categoriaRepository.existsById(id)) {
			Categoria categoria = categoriaRepository.findById(id).get();
			if (nombre != null) categoria.setNombre(nombre);
			if(descripcion!=null) categoria.setDescripcion(descripcion);
			categoriaRepository.save(categoria);
			cat=categoria;
		}//exists
		return cat;
	}//updateCategoria
	public Categoria addCategoria(Categoria categoria) {
		Optional<Categoria> cat = categoriaRepository.findByNombre(categoria.getNombre());
		if(cat.isEmpty()) { // No existe el nombre
			return categoriaRepository.save(categoria);	
		} else {
			System.out.println("El Categoria [" + categoria.getNombre()
					+ "] ya se encuentra registrado");
			return null;
		}//if isEmpty
	}//addCategoria
}//class CategoriaService