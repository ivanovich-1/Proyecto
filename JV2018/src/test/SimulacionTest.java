/** Proyecto: Juego de la vida.
 *  Prueba Junit5 de la clase Simulacion según el modelo1.
 *  @since: prototipo1.0
 *  @source: CorreoTest.java 
 *  @version: 1.0 - 2018/11/19
 *  @author: ajp
 */


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimulacionTest {
	private static Usuario usr;
	private static GregorianCalendar fecha;
	private static byte[][] espacioMundo;
	private static Simulacion simulacion1;
	private Simulacion simulacion2;

	/**
	 * Método que se ejecuta una sola vez al principio del conjunto pruebas.
	 * @throws DatosException 
	 */
	@BeforeAll
	public static void iniciarlizarDatosFijos() {
		// Objetos no modicados en las pruebas.
		usr = new Usuario("00000001T", 
				"Luis", "Roca Mora",
				"Roncal, 10, 30130, Murcia", 
				"luis@gmail.com", 
				new GregorianCalendar(2000, 03, 21),
				new GregorianCalendar(2018,10,17), 
				"Miau#12", 
				Usuario.ROLES[1]);
		fecha = new GregorianCalendar(2018, 10, 20, 10, 35, 2);
		espacioMundo = new byte[10][10];
		simulacion1 = new Simulacion(usr, fecha, espacioMundo);
	}

	@AfterAll
	public static void borrarDatosPrueba() {	
		simulacion1 = null;
	}

	@BeforeEach
	public void iniciarlizarDatosVariables() {	
		simulacion2 = new Simulacion();
	}


	// Test's con DATOS VALIDOS
	@Test
	public void testSimulacionConvencional() {	
		assertSame(simulacion1.getUsr(), usr);
		assertSame(simulacion1.getFecha(), fecha);
		assertSame(simulacion1.getMundo(), espacioMundo);
	}

	@Test
	public void testSimulacionDefecto() {
		assertEquals(simulacion2.getUsr().getNif(), new Usuario().getNif());
		assertEquals(simulacion2.getFecha().get(Calendar.YEAR), new GregorianCalendar().get(Calendar.YEAR));
		assertEquals(simulacion2.getFecha().get(Calendar.MONTH), new GregorianCalendar().get(Calendar.MONTH));
		assertEquals(simulacion2.getFecha().get(Calendar.DATE), new GregorianCalendar().get(Calendar.DATE));
		assertNotNull(simulacion2.getMundo());
	}

	@Test
	public void testSimulacionCopia() {
		assertNotSame(simulacion2, new Simulacion(simulacion2));
	}

	@Test
	public void testSetUsr() {
		simulacion2.setUsr(usr);
		assertSame(simulacion2.getUsr(), usr);
	}

	@Test
	public void testSetMundo() {
		simulacion2.setMundo(espacioMundo);
		assertSame(simulacion2.getMundo(), espacioMundo);
	}

	@Test
	public void testSetFecha() {
		simulacion2.setFecha(fecha);
		assertSame(simulacion2.getFecha(), fecha);
	}

	@Test
	public void testToString() {
		assertNotNull(simulacion1.toString());
	}

	// Test's CON DATOS NO VALIDOS

	@Test
	public void testSetUsrNull() {
		try {
			simulacion2.setUsr(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
		}
	}
	
	@Test
	public void testSetMundoNull() {
		try {
			simulacion2.setMundo(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
		}
	}
	
	@Test
	public void testSetFechaNull() {
		try {
			simulacion2.setFecha(null);
			fail("No debe llegar aquí...");
		} 
		catch (AssertionError e) { 
		}
	}
	
} // class
