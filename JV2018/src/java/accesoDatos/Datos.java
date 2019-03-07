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
	private int indexSortUsuarios(String id){

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
		int posicion = indexSortUsuarios(id)-1;    // Base 1 de índices.

		if (posicion < 0) {
			return null;
		}
		return datosUsuarios.get(posicion);	
	}

	/**
	 * Registro de nuevo usuario.
	 * @param usr 
	 * @throws DatosException 
	 */
	public void altaUsuario(Usuario usr) throws DatosException {
		assert usr != null;
		int posInsercion = indexSortUsuarios(usr.getId());

		if (posInsercion < 0) {
			datosUsuarios.add(-posInsercion-1 ,usr);  // Inserta en orden.
			registrarEquivalencias(usr);
		}
		else {		
			// Coincidencia de id. Hay que generar variante.
			if (!datosUsuarios.get(posInsercion-1).equals(usr)) {
				intentarVariantesId(usr);		
			} 
			else {
				throw new DatosException("ALTA Usuario: ya existe.");
			}
		}
	}

	/**
	 * Intenta obtener variante de Id de usuario. Hace 22 intentos.
	 * @param usr 
	 * @throws DatosException 
	 */
	private void intentarVariantesId(Usuario usr) throws DatosException {
		int intentos = "ABCDEFGHJKLMNPQRSTUVWXYZ".length();
		do {
			// Generar variante y comprueba de nuevo.
			usr = new Usuario(usr, usr.getId());	
			int posInsercion = indexSortUsuarios(usr.getId());
			if (posInsercion < 0) {
				datosUsuarios.add(-posInsercion - 1, usr); // Inserta el usuario en orden.
				registrarEquivalencias(usr);
				return;
			}
			intentos--;
		} while (intentos >= 0);
		throw new DatosException("ALTA Usuario: imposible generar variante del " + usr.getId());
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
			try {
				altaUsuario(new Usuario(new Nif("0000000" + i + "TRWAGMYFPDXBNJZSQVHLCKE".charAt(i)), 
						"Pepe", "López Pérez", 
						new DireccionPostal("Luna", "27", "30132", "Murcia"),
						new Correo("pepe" + i + "gmail.com"), 
						new Fecha(1999, 11, 12), 
						new Fecha(2018, 01, 03), 
						new ClaveAcceso("Miau#" + i), RolUsuario.NORMAL));
			} 
			catch (ModeloException | DatosException e) {
				//e.printStackTrace();
			}
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
	 * Registro de nueva sesión de usuario.
	 * @param sesionUsuario 
	 * @throws DatosException 
	 */
	public void altaSesion(SesionUsuario sesion) throws DatosException {
		assert sesion != null;
		int posInsercion = indexSortSesiones(sesion.getId());

		if (posInsercion < 0) {
			datosSesiones.add(-posInsercion-1, sesion);
		}
		else {
			throw new DatosException("ALTA Sesion: ya existe.");
		}	  
	}

	/**
	 * Busca sesionUsaurio dado su id.
	 * @param id 
	 * @return - la SesionUsuario encontrada o null si no existe.
	 */
	public SesionUsuario buscarSesion(String id) {
		int posicion = indexSortSesiones(id)-1;    // Base 1 de índices.
		if (posicion < 0) {
			return null;
		}
		return datosSesiones.get(posicion);
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
	 * Registro de nueva simulación.
	 * @param simulacion 
	 * @throws DatosException 
	 */
	public void altaSimulacion(Simulacion simulacion) throws DatosException {
		assert simulacion != null;
		int posInsercion = indexSortSimulaciones(simulacion.getId());

		if (posInsercion < 0) {
			datosSimulaciones.add(-posInsercion-1, simulacion);
		}
		else {	
			throw new DatosException("ALTA Simulacion: ya existe.");		
		}	  
	}

	/**
	 * Busca simulación dado su id.
	 * @param id 
	 * @return - la simulacion encontrada o null si no existe.
	 */
	public Simulacion buscarSimulacion(String id) {
		int posicion = indexSortSimulaciones(id)-1;    // Base 1 de índices.
		
		if (posicion < 0) {
			return null;
		}
		return datosSimulaciones.get(posicion);
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
	 * Registro de nuevo mundo.
	 * @param mundo 
	 * @throws DatosException 
	 */
	public void altaMundo(Mundo mundo) throws DatosException {
		assert mundo != null;
		int posInsercion = indexSortMundos(mundo.getId());

		if (posInsercion < 0) {
			datosMundos.add(-posInsercion-1 ,mundo);  // Inserta en orden.
		}
		else {
			throw new DatosException("ALTA Mundo: ya existe.");
		}
	}

	/**
	 * Busca simulación dado su id.
	 * @param id 
	 * @return - el mundo encontrado o null si no existe.
	 */
	public Mundo buscarMundo(String id) {
		int posicion = indexSortMundos(id)-1;    // Base 1 de índices.
		
		if (posicion < 0) {
			return null;
		}
		return datosMundos.get(posicion);	
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
		try {
			altaMundo(mundoDemo);
		} 
		catch (DatosException e) {
			//e.printStackTrace();
		}
	}

} // class
