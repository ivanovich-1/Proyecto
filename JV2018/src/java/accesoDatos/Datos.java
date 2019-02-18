/** 
 * Proyecto: Juego de la vida.
 * Implementa el almacén de datos del programa. 
 * @since: prototipo1.1
 * @source: Datos.java 
 * @version: 1.2 - 2019/02/13
 * @author: ajp
 */

package accesoDatos;

import java.util.ArrayList;

import modelo.*;
import modelo.Usuario.RolUsuario;
import util.Fecha;

public class Datos {

	private static ArrayList<Usuario> datosUsuarios = new ArrayList<Usuario>();
	private static ArrayList<SesionUsuario> datosSesiones = new ArrayList<SesionUsuario>();	
	private static ArrayList<Simulacion> datosSimulaciones = new ArrayList<Simulacion>();


	public int getUsuariosRegistradas() {
		return datosUsuarios.size();
	}

	public int getSesionesRegistradas() {
		return datosSesiones.size();
	}

	public int getSimulacionesRegistradas() {
		return datosSimulaciones.size();
	}

	/**
	 * Busca usuario dado su id.
	 * @param id - el id del Usuario a buscar.
	 * @return - el Usuario encontrado o null si no existe.
	 */
	public Usuario buscarUsuarioB(String id) {
		// Busca usuario coincidente con la credencial.
		for (Usuario usr : datosUsuarios) {
			if (usr != null
					&& usr.getIdUsr().equalsIgnoreCase(id)) {
				return usr;	// Devuelve el usuario encontrado.
			}
		}
		return null;						// No encuentra.
	}


	/**
	 * Busca usuario dado su id usando el método de búsqueda binaria. 
	 * @param id - el id del Usuario a buscar.
	 * @return - el Usuario encontrado o null si no existe.
	 */
	public Usuario buscarUsuario(String id){
		
		int centro;
		int inf = 0;
		int sup = datosUsuarios.size()-1;
		
		while (inf <= sup) {
			centro = (sup + inf) / 2;

			if (datosUsuarios.get(centro).getIdUsr().equals(id)) {
				return datosUsuarios.get(centro);
			}
			else {
				if (datosUsuarios.get(centro).getIdUsr().compareTo(id) > 0){
					sup = centro-1;
				}
				else {
					inf = centro+1;
				}
			}
		}
		return null; 				// No encuentra.
	}


	/**
	 * Registro de la sesión de usuario.
	 * @param sesionUsuario 
	 */
	public void altaUsuario(Usuario usr) {
		if (buscarUsuario(usr.getIdUsr()) == null) {
			datosUsuarios.add(usr);   
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
		datosSesiones.add(sesion);  
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
		datosSimulaciones.add(simulacion);
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
