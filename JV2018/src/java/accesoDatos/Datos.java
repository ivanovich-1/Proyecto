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

	public static final int MAX_USUARIOS = 15;
	public static final int MAX_SESIONES = 15;
	public static final int MAX_SIMULACIONES = 15;
	private static Usuario[] datosUsuarios = new Usuario[MAX_USUARIOS];
	private static SesionUsuario[] datosSesiones = new SesionUsuario[MAX_SESIONES];
	private static Simulacion[] datosSimulaciones = new Simulacion[MAX_SIMULACIONES];
	private static int sesionesRegistradas = 0;				// Control de sesiones registradas.
	private static int usuariosRegistrados = 0;
	private static int simulacionesRegistradas = 0;

	public int getSesionesRegistradas() {
		return sesionesRegistradas;
	}


	/**
	 * Busca usuario dado su nif.
	 * @param idUsr - el nif del Usuario a buscar.
	 * @return - el Usuario encontrado o null si no existe.
	 */
	public Usuario buscarUsuario(String nif) {
		// Busca usuario coincidente con la credencial.
		for (Usuario usr : datosUsuarios) {
			if (usr != null
					&& usr.getNif().getTexto().equalsIgnoreCase(nif)) {
				return usr;	// Devuelve el usuario encontrado.
			}
		}
		return null;						// No encuentra.
	}

	/**
	 * Registro de la sesión de usuario.
	 * @param sesionUsuario 
	 */
	public void altaUsuario(Usuario usr) {
		// Registra usuario a partir de la última posición ocupada.
		if (buscarUsuario(usr.getIdUsr()) == null) {
			datosUsuarios[usuariosRegistrados] = usr;  
			usuariosRegistrados++; 
		}
	}


	/**
	 * Muestra por consola todos los usuarios almacenados.
	 */
	public void mostrarTodosUsuarios() {
		for (Usuario usr: datosUsuarios) {
			if (usr == null) {
				break;
			}
			System.out.println("\n" + usr);
			
		}
	}

	/**
	 * Genera datos de prueba válidos dentro 
	 * del almacén de datos.
	 */
	public void cargarUsuariosPrueba() {	
		for (int i = 0; i < 10; i++) {
			altaUsuario(new Usuario(new Nif("0000000" + i + "TRWAGMYFPDXBNJZSQVHLCKE".charAt(i)), 
					"Pepe", "López Pérez", 
					new DireccionPostal("Luna", "27", "30132", "Murcia"),
					new Correo("pepe" + i + "@gmail.com"), 
					new Fecha(1999, 11, 12), 
					new Fecha(2018, 01, 03), 
					new ClaveAcceso("Miau#" + i), RolUsuario.NORMAL));
		}
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
	 * Busca sesionUsaurio dado su id.
	 * @param idSesion 
	 * @return - la SesionUsuario encontrada o null si no existe.
	 */
	public SesionUsuario buscarSesion(String idSesion) {
		// Busca sesionUsuario coincidente con la credencial.
		for (SesionUsuario sesion : datosSesiones) {
			if (sesion != null
					&& sesion.getIdSesion().equalsIgnoreCase(idSesion)) {
				return sesion;	// Devuelve la sesionUsuario encontrada.
			}
		}
		return null;			// No encuentra.
	}
	
	/**
	 * Registro de la simulación.
	 * @param simulacion 
	 */
	public void altaSimulacion(Simulacion simulacion) {
		// Registra sesion de usuario a partir de la última posición ocupada.
		datosSimulaciones[simulacionesRegistradas] = simulacion;  
		simulacionesRegistradas++; 							
	}

	/**
	 * Busca simulación dado su id.
	 * @param idSesion 
	 * @return - la simulacion encontrada o null si no existe.
	 */
	public Simulacion buscarSimulacion(String idSimulacion) {
		// Busca simulacion coincidente con la credencial.
		for (Simulacion simulacion : datosSimulaciones) {
			if (simulacion != null
					&& simulacion.getIdSimulacion().equalsIgnoreCase(idSimulacion)) {
				return simulacion;	// Devuelve la simulación encontrada.
			}
		}
		return null;				// No encuentra.
	}

} // class
