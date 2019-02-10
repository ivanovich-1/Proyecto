/** 
 *  Proyecto: Juego de la vida.
 *  Prueba Junit5 de la clase Correo según el modelo 1.1
 *  @since: prototipo1.1
 *  @source: CorreoTest.java 
 *  @version: 1.1 - 2019/01/22
 *  @author: ajp
 */

package modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Correo;

public class CorreoTest {
	private Correo correo1;
	private static Correo correo2;

	/**
	 * Método que se ejecuta una sola vez al principio del conjunto pruebas.
	 */
	@BeforeAll
	public static void iniciarlizarDatosFijos() {
		// Objetos no modicados en las pruebas.
		try {
			correo2 = new Correo("correo@correo.com");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@BeforeEach
	public void iniciarlizarDatosVariables() {
		try {
			correo1 = new Correo();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterEach
	public void borrarDatosVariables() {
		correo1 = null;
	}
	
	// Test con DATOS VALIDOS
	@Test
	public void testCorreoConvencional() {
		assertEquals(correo2.getTexto(), "correo@correo.com");
	}

	@Test
	public void testCorreo() {
		assertEquals(correo2.getTexto(), "correo@correo.com");
	}

	@Test
	public void testCorreoCorreo() {
		assertEquals(correo2, new Correo(correo2));
	}
	
	@Test
	public void testSetTexto() {
		try {
			correo2.setTexto("correo@correo.com");
			assertEquals(correo2.getTexto(), "correo@correo.com");
		} 
		catch (Exception e) {
			fail("No debe llegar aquí...");
		}
	}

	@Test
	public void testToString() {
		assertEquals("correo@correo.com", correo2.toString());
	}
	
	@Test
	public void testEqualsObject() {
		try {		
			assertTrue(correo2.equals(new Correo("correo@correo.com")));
		} 
		catch (Exception e) {
			fail("No debe llegar aquí...");
		}
	}

	@Test
	public void testClone() {
		assertEquals(correo2, correo2.clone());
	}

	@Test
	public void testHashCode() {
		assertEquals(correo1.hashCode(), 1596933627);
	}
	
	// Test con DATOS NO VALIDOS
		@Test
		public void testCorreoConvencionalTextoNull() {	

			try {
				String texto = null;
				new Correo(texto);
				fail("No debe llegar aquí...");
			} 
			catch (AssertionError | Exception e) {
			}
		}

		@Test
		public void testSetTextoNull() {
			try {
				correo1.setTexto(null);
				fail("No debe llegar aquí...");
			} 
			catch (AssertionError | Exception e) {
				assertTrue(true);
			}
		}
		
} //class
