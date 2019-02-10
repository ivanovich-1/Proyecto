/** 
 * Proyecto: Juego de la vida.
 *  Prueba Junit5 de la clase DireccionPostal según el modelo 1.1
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

import modelo.DireccionPostal;

public class DireccionPostalTest {

	private DireccionPostal direccion1;
	private static DireccionPostal direccion2;

	/**
	 * Método que se ejecuta una sola vez al principio del conjunto pruebas.
	 */
	@BeforeAll
	public static void iniciarlizarDatosFijos() {
		// Objetos no modicados en las pruebas.
		try {
			direccion2 = new DireccionPostal("Flan", "21", "88888", "Murcia");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que se ejecuta antes de cada @Test para preparar datos de prueba.
	 */
	@BeforeEach
	public void iniciarlizarDatosVariables() {
		// Objetos para la prueba.
		try {
			direccion1 = new DireccionPostal();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que se ejecuta después de cada @Test para limpiar datos de prueba.
	 */
	@AfterEach
	public void borrarDatosPrueba() {
		direccion1 = null;
	}

	// Test con DATOS VALIDOS
	@Test
	public void testDireccionPostalConvencional() {
		assertEquals(direccion2.getCalle(), "Flan");
		assertEquals(direccion2.getNumero(), "21");
		assertEquals(direccion2.getCp(), "88888");
		assertEquals(direccion2.getPoblacion(), "Murcia");
	}

	@Test
	public void testDireccionPostal() {
		assertEquals(direccion1.getCalle(), "Calle");
		assertEquals(direccion1.getNumero(), "00");
		assertEquals(direccion1.getCp(), "01000");
		assertEquals(direccion1.getPoblacion(), "Población");
	}

	@Test
	public void testDireccionPostalDireccionPostal() {
		assertEquals(direccion2, new DireccionPostal(direccion2));
	}
	
	@Test
	public void testSetCalle() {
		try {
			direccion1.setCalle("Calle");
			assertEquals(direccion1.getCalle(), "Calle");
		} 
		catch (Exception e) {
			
		}	
	}
	
	@Test
	public void testSetNumero() {
		try {
			direccion1.setNumero("00");
			assertEquals(direccion1.getNumero(), "00");
		} 
		catch (Exception e) {
			fail("No debe llegar aquí...");
		}	
	}
	
	@Test
	public void testSetCP() {
		try {
			direccion1.setCp("99999");
			assertEquals(direccion1.getCp(), "99999");
		} 
		catch (Exception e) {
			fail("No debe llegar aquí...");
		}	
	}
	
	@Test
	public void testSetPoblacion() {
		try {
			direccion1.setPoblacion("Población");
			assertEquals(direccion1.getPoblacion(), "Población");
		} 
		catch (Exception e) { 
			fail("No debe llegar aquí...");
		}
	}
	
	@Test
	public void testToString() {
		assertEquals(direccion2.toString(), "Flan, 21, 88888, Murcia");
	}
	
	@Test
	public void testEqualsObject() {
		try {		
			assertTrue(direccion1.equals(new DireccionPostal()));
		} 
		catch (Exception e) {
			fail("No debe llegar aquí...");
		}
	}

	@Test
	public void testClone() {
		assertEquals(direccion2, direccion2.clone());
	}
	
	// Test con DATOS NO VALIDOS
	@Test
	public void testCorreoConvencionalCalleNull() {	

		try {
			new DireccionPostal(null, "21", "88888", "Murcia");
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError | Exception e) {
		}
	}

	@Test
	public void testCorreoConvencionalNumeroNull() {	

		try {
			new DireccionPostal("Flan", null, "88888", "Murcia");
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError | Exception e) {
		}
	}
	
	@Test
	public void testCorreoConvencionalCPNull() {	

		try {
			new DireccionPostal("Flan", "21", null, "Murcia");
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError | Exception e) {
		}
	}
	
	@Test
	public void testCorreoConvencionalPoblacionNull() {	

		try {
			new DireccionPostal("Flan", "21", "88888", null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError | Exception e) {
		}
	}
	
	@Test
	public void testSetCalleNull() {
		try {
			direccion1.setCalle(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError | Exception e) {
		}
	}

	@Test
	public void testSetNumeroNull() {
		try {
			direccion1.setNumero(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError | Exception e) {
		}
	}
	
	@Test
	public void testSetCPNull() {
		try {
			direccion1.setCp(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError | Exception e) {
		}
	}
	
	@Test
	public void testSetPoblacionNull() {
		try {
			direccion1.setPoblacion(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError | Exception e) {
		}
	}
	
} // class