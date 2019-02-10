/** 
Proyecto: Juego de la vida.
 * Implementa el almacén de datos del programa. 
 * @since: prototipo1.1
 * @source: Datos.java 
 * @version: 1.1 - 2019/02/06
 * @author: ajp
 */

package accesoDatos;

import modelo.ClaveAcceso;
import modelo.Correo;
import modelo.DireccionPostal;
import modelo.Nif;
import modelo.SesionUsuario;
import modelo.Simulacion;
import modelo.Usuario;
import modelo.Usuario.RolUsuario;
import util.Fecha;

public class Datos {

	public static final int MAX_USUARIOS = 10;
	public static final int MAX_SESIONES = 10;
	public static final int MAX_SIMULACIONES = 0;
	private static Usuario[] datosUsuarios = new Usuario[MAX_USUARIOS];
	private static SesionUsuario[] datosSesiones = new SesionUsuario[MAX_SESIONES];
	private static Simulacion[] datosSimulaciones = new Simulacion[MAX_SIMULACIONES];
	private static int sesionesRegistradas = 0;				// Control de sesiones registradas.
	
	
	public int getSesionesRegistradas() {
		return sesionesRegistradas;
	}

	/**
	 * Busca usuario dado su nif.
	 * @param idUsr - el nif del Usuario a buscar.
	 * @return - el Usuario encontrado o null si no existe.
	 */
	public Usuario buscarUsuario(String idUsr) {
		// Busca usuario coincidente con la credencial.
		for (Usuario usr : datosUsuarios) {
			if (usr.getNif().getTexto().equalsIgnoreCase(idUsr)) {
				return usr;	// Devuelve el usuario encontrado.
			}
		}
		return null;						// No encuentra.
	}

	/**
	 * Registro de la sesión de usuario.
	 * @param sesionUsuario 
	 */
	public void altaSesion(SesionUsuario sesion) {
		// Registra sesion de usuario a partir de la última posición ocupada.
		datosSesiones[sesionesRegistradas] = sesion;  
		sesionesRegistradas++; 							
	}

	/**
	 * Muestra por consola todos los usuarios almacenados.
	 */
	public void mostrarTodosUsuarios() {
		for (Usuario u: datosUsuarios) {
			System.out.println("\n" + u);
		}
	}

	/**
	 * Genera datos de prueba válidos dentro 
	 * del almacén de datos.
	 */
	public void cargarUsuariosPrueba() {	
		for (int i = 0; i < MAX_USUARIOS; i++) {
			datosUsuarios[i] = new Usuario(new Nif("0000000" + i + "TRWAGMYFPDXBNJZSQVHLCKE".charAt(i)), 
					"Pepe", "López Pérez", 
					new DireccionPostal("Luna", "27", "30132", "Murcia"),
					new Correo("pepe" + i + "@gmail.com"), 
					new Fecha(1999, 11, 12), 
					new Fecha(2018, 01, 03), 
					new ClaveAcceso("Miau#" + i), RolUsuario.NORMAL);
		}
	}
}
