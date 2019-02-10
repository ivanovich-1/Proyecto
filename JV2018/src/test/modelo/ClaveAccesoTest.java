/** 
 * Proyecto: Juego de la vida.
 * Prueba Junit5 de la clase ClaveAcceso según el modelo 1.1
 * @since: prototipo 1.1
 * @source: ClaveAccesoTest.java 
 * @version: 1.1 - 2019.01.22
 * @author: ajp
 */

package modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.ClaveAcceso;

public class ClaveAccesoTest {
	private ClaveAcceso clave1;
	private static ClaveAcceso clave2;
	
	/**
	 * Método que se ejecuta una sola vez al principio del conjunto pruebas.
	 */
	@BeforeAll
	public static void iniciarlizarDatosFijos() {
		// Objetos no modicados en las pruebas.
		try {
			clave2 = new ClaveAcceso("Miau#2");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@BeforeEach
	public void iniciarlizarDatosVariables() {	
		try {
			clave1 = new ClaveAcceso();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterEach
	public void borrarDatosVariables() {	
		clave1 = null;
	}
	
	// Test con DATOS VALIDOS
	@Test
	public void testClaveAccesoConvencional() {	
		try {
			assertEquals(clave2.getTexto(), new ClaveAcceso("Miau#2").getTexto());
		} 
		catch (Exception e) {
			fail("No debe llegar aquí");
		}
	}

	@Test
	public void testClaveAcceso() {
		try {
			assertEquals(clave1.getTexto(), new ClaveAcceso("Miau#0").getTexto());
		} 
		catch (Exception e) {
			fail("No debe llegar aquí");
		}
	}

	@Test
	public void testClaveAccesoClaveAcceso() {
		try {
			assertEquals(clave2, new ClaveAcceso(clave2));
		} 
		catch (Exception e) {	
			fail("No debe llegar aquí");
		}
	}

	@Test
	public void testSetTexto() {
		try {
			clave1.setTexto("Miau#1");
			assertEquals(clave1.getTexto(), new ClaveAcceso("Miau#1").getTexto());
		} 
		catch (Exception e) {
			fail("No debe llegar aquí");
		}
	}

	@Test
	public void testToString() {
		try {
			assertEquals(clave2.toString(), new ClaveAcceso("Miau#2").getTexto());
		} 
		catch (Exception e) {
			fail("No debe llegar aquí");
		}
	}

	@Test
	public void testEquals() {
		try {
			assertTrue(clave2.equals(new ClaveAcceso("Miau#2")));
		} 
		catch (Exception e) { 
			fail("No debe llegar aquí");
		}
	}

	@Test
	public void testClone() {
		assertEquals(clave2, clave2.clone());
	}

	// Test con DATOS NO VALIDOS
	@Test
	public void testClaveAccesoConvencionalTextoNull() {	
		try {
			String texto = null;
			new ClaveAcceso(texto);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 	
		}
	}
	
	@Test
	public void testClaveAccesoConvencionalTextoMalFormato() {	
		clave1.setTexto("hola");
		assertEquals(clave1, new ClaveAcceso("Miau#0"));
	}
	
	@Test
	public void testSetTextoNull() {
		try {
			clave1.setTexto(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 	
		}
	}
	
	@Test
	public void testSetTextoMalFormato() {
		clave1.setTexto("hola");
		assertEquals(clave1, new ClaveAcceso("Miau#0"));
	}
	
} //class
