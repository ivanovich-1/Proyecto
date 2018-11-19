/** 
 * Proyecto: Juego de la vida.
 * Clase JUnit5 de prueba automatizada de las características de la clase Usuario según el modelo1.
 * @since: prototipo1
 * @source: TestUsuario.java 
 * @version: 1.0 - 2018.11.21
 * @author: ajp
 */


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsuarioTest {
	private static Usuario usuario1; 
	private Usuario usuario2; 

	/**
	 * Método que se ejecuta antes de cada @Test para preparar datos de prueba.
	 * @throws DatosException 
	 */
	@BeforeAll
	public static void iniciarlizarDatosFijos() {
		// Objetos no modicados en las pruebas.
		usuario1 = new Usuario("00000001T", 
				"Luis", "Roca Mora",
				"Roncal, 10, 30130, Murcia", 
				"luis@gmail.com", 
				new GregorianCalendar(2000, 03, 21),
				new GregorianCalendar(2018,10,17), 
				"Miau#12", 
				Usuario.ROLES[1]);
	}

	/**
	 * Método que se ejecuta una sola vez al final del conjunto pruebas.
	 * No es necesario en este caso.
	 */
	@AfterAll
	public static void limpiarDatosFijos() {
		usuario1 = null;
	}

	/**
	 * Método que se ejecuta antes de cada pruebas.
	 */
	@BeforeEach
	public void iniciarlizarDatosVariables() {	
		usuario2 = new Usuario();
	}

	/**
	 * Método que se ejecuta después de cada @Test para limpiar datos de prueba.
	 */
	@AfterEach
	public void borrarDatosPrueba() {
		usuario2 = null;
	}

	// Test's CON DATOS VALIDOS
	
	@Test
	public void testSetNombre() {
		usuario2.setNombre("Luis");
		assertEquals(usuario2.getNombre(), "Luis");
	}
	
	@Test
	public void testSetApellidos() {
		usuario2.setApellidos("Roca Mora");
		assertEquals(usuario2.getApellidos(), "Roca Mora");
	}
	
	@Test
	public void testSetDomicilio() {
		usuario2.setDomicilio("Roncal, 10, 30130, Murcia");
		assertEquals(usuario2.getDomicilio(), "Roncal, 10, 30130, Murcia");
	}
	
	@Test
	public void testSetCorreo() {
		usuario2.setCorreo("luis@gmail.com");
		assertEquals(usuario2.getCorreo(), "luis@gmail.com");
	}
	@Test
	public void testSetFechaNacimiento() {
		usuario2.setFechaNacimiento(new GregorianCalendar(2000, 03, 21));
		assertEquals(usuario2.getFechaNacimiento(), new GregorianCalendar(2000, 03, 21));
	}
	
	@Test
	public void testSetFechaAlta() {
		usuario2.setFechaAlta(new GregorianCalendar(2018,11,17));
		assertEquals(usuario2.getFechaAlta(), new GregorianCalendar(2018,10,17));
	}

	@Test
	public void testSetClaveAcceso() {
		usuario2.setClaveAcceso("Miau#12");
		assertEquals(usuario2.getClaveAcceso(), "Miau#12");
	}

	@Test
	public void testSetRol() {
		usuario2.setRol(Usuario.ROLES[1]);
		assertEquals(usuario1.getRol(), Usuario.ROLES[1]);
	}

	@Test
	public void testToString() {
		assertEquals(usuario1.toString(), 
				"nif:             00000001T\n" +
				"nombre:          Luis\n" +
				"apellidos:       Roca Mora\n" +
				"domicilio:       Roncal, 10, 30130, Murcia\n" +
				"correo:          luis@gmail.com\n" +
				"fechaNacimiento: 2000.3.21\n" +
				"fechaAlta:       2018.10.17\n" +
				"claveAcceso:     Miau#12\n" +
				"rol:             INVITADO\n"
			);
	}

	// Test's CON DATOS NO VALIDOS

	@Test
	public void testSetNombreNull() {
		try {
			usuario2.setNombre(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
		}
	}
	
	@Test
	public void testSetApellidosNull() {
		try {
			usuario2.setApellidos(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
		}
	}
	
	@Test
	public void testSetDomicilioNull() {
		try {
			usuario2.setDomicilio(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
		}
	}
	
	@Test
	public void testSetCorreoNull() {
		try {
			usuario2.setCorreo(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
		}
	}
	
	@Test
	public void testSetFechaNacimientoNull() {
		try {
			usuario2.setFechaNacimiento(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
		}
	}
	
	@Test
	public void testSetFechaNacimientoFuturo() {	
			usuario1.setFechaNacimiento(new GregorianCalendar(3020, 9, 10));
			assertEquals(usuario1.getFechaNacimiento().get(Calendar.YEAR), 2000);
			assertEquals(usuario1.getFechaNacimiento().get(Calendar.MONTH), 03);
			assertEquals(usuario1.getFechaNacimiento().get(Calendar.DATE), 21);
	}
	
	@Test
	public void testSetFechaAltaNull() {
		try {
			usuario2.setFechaAlta(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) {	
		}
	}

	@Test
	public void testSetFechaAltaFuturo() {	
			usuario1.setFechaAlta(new GregorianCalendar(3020, 9, 10));
			assertEquals(usuario1.getFechaAlta().get(Calendar.YEAR), 2018);
			assertEquals(usuario1.getFechaAlta().get(Calendar.MONTH), 10);
			assertEquals(usuario1.getFechaAlta().get(Calendar.DATE), 17);
	}
	
	@Test
	public void testSetClaveAccesoNull() {
		try {
			usuario2.setNif(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
		}
	}

	@Test
	public void testSetClaveAccesoBlanco() {
			usuario2.setNif("  ");	
			assertEquals(usuario2.getClaveAcceso(), "Miau#0");
	}
	
	@Test
	public void testSetRolNull() {
		try {
			usuario2.setRol(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 		
		}
	}

} // class
