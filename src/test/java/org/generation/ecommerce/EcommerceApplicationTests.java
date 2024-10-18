package org.generation.ecommerce;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.generation.ecommerce.model.Producto;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;
import com.fasterxml.jackson.databind.ObjectMapper;
@SpringBootTest
@AutoConfigureMockMvc
class EcommerceApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	private final String token="Bearer: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlbGJhcnRvQGdtYWlsLmNvbSIsInJvbGUiOiJ1c2VyIiwiaWF0IjoxNzI4OTI2OTkyLCJleHAiOjE3Mjg5NzAxOTJ9.g7mP_1T8N5zDqmcthzOiblRfqfc_8JEopSHngK3RH9w";
	
	@Test
	@DisplayName("Se prueba el endpoint http://localhost:8080/api/productos/3")
	void pruebaGET() throws Exception {
		this.mockMvc.perform( get("/api/productos/3") ) 
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(
						    content().string(containsString("87808.jpeg")) 	
							);
	}//pruebaGET
	
	@Test
	@Disabled("Probado la primera vez, deshabilitado después de la primera vez")
	@DisplayName("Se prueba borrar el producto con id 6 http://localhost:8080/api/productos/6")
	void pruebaDelete() throws Exception {
		this.mockMvc.perform( delete("/api/productos/6").header("Authorization", token) ) 
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(
						    content().string(containsString("Plumas Bic Cristal")) 	
							);
	}//pruebaDelete
	@Test
	@DisplayName("Se prueba actualizar el producto con id 7 http://localhost:8080/api/productos/7")
	void pruebaPut() throws Exception {
		this.mockMvc.perform( 
				put("/api/productos/7?precio=89.90&nombre=Plumas Bic Cristal Dura Más Combo")
				.header("Authorization", token) ) 
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(
						    content().string(containsString("Combo")) 	
							);
	}//pruebaPut
	
	@Test
	@DisplayName("Se prueba crear un nuevo producto http://localhost:8080/api/productos/")
	void pruebaPost() throws Exception {
		Producto p = new Producto();
		p.setNombre("Pluma y Lapicero Zebra Z-Grip Silver Azul");
		p.setDescripcion("Pluma y Lapicero Zebra Z-Grip Silver Azul");
		p.setImagen("100161506.jpeg");
		p.setPrecio(79.99);
		this.mockMvc.perform(
				post("/api/productos/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(p) )
				.header("Authorization", token) ) 
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(
						    content().string(containsString("Z-Grip Silver")) 	
							);
	}//pruebaPost
	
	
	private static String asJsonString(final Object obj) {
	    try {
	      return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }//catch
	 } // asJsonString
	
	
	
	

}//class EcommerceApplicationTests
