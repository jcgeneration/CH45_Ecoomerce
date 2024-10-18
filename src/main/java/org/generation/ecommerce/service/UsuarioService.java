package org.generation.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.generation.ecommerce.dto.ChangePassword;
import org.generation.ecommerce.model.Usuario;
import org.generation.ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	@Autowired
	private PasswordEncoder encoder;

	private final UsuarioRepository usuarioRepository;
	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
			this.usuarioRepository = usuarioRepository;
	}//constructor
	
	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}//getAllUsuarios

	public Usuario getUsuario(Long id) {
		return usuarioRepository.findById(id).orElseThrow(
				()-> new IllegalArgumentException("El usuario con el id [" + id
						+ "] no existe.")
				);
	}//getUsuario
	public Usuario deleteUsuario(Long id) {
		Usuario user = null;
		if(usuarioRepository.existsById(id)) {
			user=usuarioRepository.findById(id).get();
			usuarioRepository.deleteById(id);
		}//if exists
		return user;
	}//deleteUsuario

	public Usuario updateUsuario(Long id, ChangePassword changePassword) {
		Usuario user = null;
			if (usuarioRepository.existsById(id)) {
				Usuario usuario = usuarioRepository.findById(id).get();
				//if(usuario.getPassword().equals(changePassword.getPassword() ) ) {
  	            if(encoder.matches(changePassword.getPassword(), usuario.getPassword())) {			
  	            	usuario.setPassword( encoder.encode(changePassword.getNpassword()) );
					user=usuario;
					usuarioRepository.save(usuario);
				}//if passwords
			}//if exists
		return user;
	}//updateUsuario
	
	public Usuario addUsuario(Usuario usuario) {
		Optional<Usuario> user = usuarioRepository.findByEmail(usuario.getEmail());
		if (user.isEmpty()) {
			usuario.setPassword( encoder.encode(usuario.getPassword()) );
			return usuarioRepository.save(usuario);
		} else {
			return null;
		}//else 
	}//addUsuario

	public boolean validateUser(Usuario usuario) {
		Optional<Usuario> user = usuarioRepository.findByEmail(usuario.getEmail());
		if(user.isPresent()) {
			Usuario tmpUser=user.get();
			if(encoder.matches(usuario.getPassword(), tmpUser.getPassword())) {
				return true;
			}//if matches
		}//isPresent
		return false;
	}//validateUser

}//class UsuarioService
