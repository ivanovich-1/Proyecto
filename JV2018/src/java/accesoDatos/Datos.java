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
import java.util.HashMap;

import modelo.*;
import modelo.Usuario.RolUsuario;
import util.Fecha;

public class Datos {

	private static ArrayList<Usuario> datosUsuarios = new ArrayList<Usuario>();
	private static ArrayList<SesionUsuario> datosSesiones = new ArrayList<SesionUsuario>();	
	private static ArrayList<Simulacion> datosSimulaciones = new ArrayList<Simulacion>();
	private static HashMap<String, String> equivalenciasId = new HashMap<String, String>();

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
	public Usuario buscarUsuario(String id) {		
		
		id = equivalenciasId.get(id);	
		int posicion = indexSort(id)-1;    // Base 1 de índices.
		
		if (posicion < 0) {
			// error
			return null;
		}
		return datosUsuarios.get(posicion);	
	}


	/**
	 * Obtiene posición ordenada de un objeto dado su id utilizado
	 * como criterio de ordenación. Utiliza búsqueda binaria. 
	 * @param id - el id del objeto a buscar.
	 * @return - el indice, base 1, que ocupa un objeto, o ocuparía (negativo).
	 */
	private int indexSort(String id){

		int inf = 0;
		int sup = datosUsuarios.size()-1;

		while (inf <= sup) {		
			int medio = (sup + inf) / 2;
			
			int comparacion =  id.compareTo(datosUsuarios.get(medio).getId());

			if (comparacion == 0) {
				return medio + 1;  	// Posición ocupada, base 1
			}
			if (comparacion > 0){
				inf = medio + 1;
			}
			else {
				sup = medio - 1;
			}
		}
		return -(inf + 1);			// Posición que ocuparía, negativo, base 1  	
	}


	/**
	 * Registro de la sesión de usuario.
	 * @param sesionUsuario 
	 */
	public void altaUsuario(Usuario usr) {
		assert usr != null;
		int posInsercion = indexSort(usr.getId());

		if (posInsercion < 0) {
			datosUsuarios.add(-posInsercion-1 ,usr);  // Inserta en orden.
			registrarEquivalencias(usr);
		}
		else {
			
			// Error usuario ya existe.
			
			// Coincidencia de id. Hay que generar variante
			
				// Generar variante y comprueba de nuevo.
			usr = new Usuario(usr, usr.getId());	
			posInsercion = indexSort(usr.getId());
		}
	}

	private void registrarEquivalencias(Usuario usr) {
		equivalenciasId.put(usr.getNif().getTexto().toUpperCase(), usr.getId());
		equivalenciasId.put(usr.getCorreo().getTexto().toUpperCase(), usr.getId());
		equivalenciasId.put(usr.getId().toUpperCase(), usr.getId());
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
