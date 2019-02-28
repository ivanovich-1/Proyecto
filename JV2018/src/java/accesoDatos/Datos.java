/** 
 * Proyecto: Juego de la vida.
 * Implementa el almacén de datos del programa en la API Collection.
 * Tiene defectos de metodos repetidos.
 * @since: prototipo1.1
 * @source: Datos.java 
 * @version: 1.2 - 2019/02/26
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
	private static HashMap<String, String> equivalenciasId = new HashMap<String, String>();
	private static ArrayList<SesionUsuario> datosSesiones = new ArrayList<SesionUsuario>();	
	private static ArrayList<Simulacion> datosSimulaciones = new ArrayList<Simulacion>();
	private static ArrayList<Mundo> datosMundos = new ArrayList<Mundo>();
	
	// USUARIOS
	
	/**
	 * Obtiene posición ordenada de un objeto dado su id utilizado
	 * como criterio de ordenación. Utiliza búsqueda binaria. 
	 * @param id - el id del objeto a buscar.
	 * @return - el indice, base 1, que ocupa un objeto, o ocuparía (negativo).
	 */
	private int indexSortUsuario(String id){

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
	
	public int getUsuariosRegistradas() {
		return datosUsuarios.size();
	}

	/**
	 * Busca usuario dado su id.
	 * @param id - el id del Usuario a buscar.
	 * @return - el Usuario encontrado o null si no existe.
	 */
	public Usuario buscarUsuario(String id) {		
		
		id = equivalenciasId.get(id);	
		int posicion = indexSortUsuario(id)-1;    // Base 1 de índices.
		
		if (posicion < 0) {
			// error
			return null;
		}
		return datosUsuarios.get(posicion);	
	}

	/**
	 * Registro de la sesión de usuario.
	 * @param sesionUsuario 
	 */
	public void altaUsuario(Usuario usr) {
		assert usr != null;
		int posInsercion = indexSortUsuario(usr.getId());

		if (posInsercion < 0) {
			datosUsuarios.add(-posInsercion-1 ,usr);  // Inserta en orden.
			registrarEquivalencias(usr);
		}
		else {
			
			// Error usuario ya existe.
			
			// Coincidencia de id. Hay que generar variante
			
				// Generar variante y comprueba de nuevo.
			usr = new Usuario(usr, usr.getId());	
			posInsercion = indexSortUsuario(usr.getId());
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
	
	// SESIONES
	
	/**
	 * Obtiene posición ordenada de un objeto dado su id utilizado
	 * como criterio de ordenación. Utiliza búsqueda binaria. 
	 * @param id - el id del objeto a buscar.
	 * @return - el indice, base 1, que ocupa un objeto, o ocuparía (negativo).
	 */
	private int indexSortSesiones(String id){

		int inf = 0;
		int sup = datosSesiones.size()-1;

		while (inf <= sup) {		
			int medio = (sup + inf) / 2;
			
			int comparacion =  id.compareTo(datosSesiones.get(medio).getId());

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
	
	public int getSesionesRegistradas() {
		return datosSesiones.size();
	}

	/**
	 * Registro de la sesión de usuario.
	 * @param sesionUsuario 
	 */
	public void altaSesion(SesionUsuario sesion) {
		assert sesion != null;
		int posInsercion = indexSortSesiones(sesion.getId());

		if (posInsercion < 0) {
			datosSesiones.add(sesion);
		}
		else {	
			// Error sesion ya existe.		
		}	  
	}

	/**
	 * Busca sesionUsaurio dado su id.
	 * @param id 
	 * @return - la SesionUsuario encontrada o null si no existe.
	 */
	public SesionUsuario buscarSesion(String id) {
		// Busca sesionUsuario coincidente con la credencial.
		for (SesionUsuario sesion : datosSesiones) {
			if (sesion != null
					&& sesion.getId().equalsIgnoreCase(id)) {
				return sesion;	// Devuelve la sesionUsuario encontrada.
			}
		}
		return null;			// No encuentra.
	}

	
	// SIMULACIONES
	
	/**
	 * Obtiene posición ordenada de un objeto dado su id utilizado
	 * como criterio de ordenación. Utiliza búsqueda binaria. 
	 * @param id - el id del objeto a buscar.
	 * @return - el indice, base 1, que ocupa un objeto, o ocuparía (negativo).
	 */
	private int indexSortSimulaciones(String id){

		int inf = 0;
		int sup = datosSimulaciones.size()-1;

		while (inf <= sup) {		
			int medio = (sup + inf) / 2;
			
			int comparacion =  id.compareTo(datosSimulaciones.get(medio).getId());

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
	
	public int getSimulacionesRegistradas() {
		return datosSimulaciones.size();
	}

	/**
	 * Registro de la simulación.
	 * @param simulacion 
	 */
	public void altaSimulacion(Simulacion simulacion) {
		assert simulacion != null;
		int posInsercion = indexSortSimulaciones(simulacion.getId());
		if (posInsercion < 0) {
			datosSimulaciones.add(simulacion);
		}
		else {	
			// Error simulacion ya existe.		
		}	  
	}

	/**
	 * Busca simulación dado su id.
	 * @param id 
	 * @return - la simulacion encontrada o null si no existe.
	 */
	public Simulacion buscarSimulacion(String id) {
		// Busca simulacion coincidente con la credencial.
		for (Simulacion simulacion : datosSimulaciones) {
			if (simulacion != null
					&& simulacion.getId().equalsIgnoreCase(id)) {
				return simulacion;	// Devuelve la simulación encontrada.
			}
		}
		return null;				// No encuentra.
	}

	// MUNDOS
	
	/**
	 * Obtiene posición ordenada de un objeto dado su id utilizado
	 * como criterio de ordenación. Utiliza búsqueda binaria. 
	 * @param id - el id del objeto a buscar.
	 * @return - el indice, base 1, que ocupa un objeto, o ocuparía (negativo).
	 */
	private int indexSortMundos(String id){

		int inf = 0;
		int sup = datosMundos.size()-1;

		while (inf <= sup) {		
			int medio = (sup + inf) / 2;
			
			int comparacion =  id.compareTo(datosMundos.get(medio).getId());

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
	
	public int getMundosRegistradas() {
		return datosMundos.size();
	}

	/**
	 * Registro de la simulación.
	 * @param simulacion 
	 */
	public void altaMundo(Mundo mundo) {
		assert mundo != null;
		int posInsercion = indexSortUsuario(mundo.getId());

		if (posInsercion < 0) {
			datosMundos.add(-posInsercion-1 ,mundo);  // Inserta en orden.
		}
		else {
			// Error mundo ya existe.
		}
	}

	/**
	 * Busca simulación dado su id.
	 * @param id 
	 * @return - el mundo encontrado o null si no existe.
	 */
	public Mundo buscarMundo(String id) {
		// Busca mundo coincidente con el id.
		for (Mundo mundo : datosMundos) {
			if (mundo != null
					&& mundo.getId().equalsIgnoreCase(id)) {
				return mundo;	// Devuelve el mundo encontrado.
			}
		}
		return null;			// No encuentra.
	}


	/**
	 * Carga datos demo en la matriz que representa el mundo. 
	 */
	public void cargarMundoDemo() {
		
		Mundo mundoDemo = new Mundo();
		mundoDemo.setEspacio(new byte[][] { 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 }, // 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 }, // 
			{ 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 1x Planeador
			{ 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 1x Flip-Flop
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 1x Still Life
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }  //
		});
		
		altaMundo(mundoDemo);
	}
	
} // class
