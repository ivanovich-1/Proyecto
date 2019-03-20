/** 
 * Proyecto: Juego de la vida.
 * Resuelve todos los aspectos del almacenamiento del DTO Usuario 
 * utilizando un ArrayList y un Map - Hashtable.
 * Colabora en el patron Fachada.
 * @since: prototipo2.0
 * @source: UsuariosDAO.java 
 * @version: 2.0 - 2019/03/15 
 * @author: ajp
 */

package accesoDatos.memoria;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import accesoDatos.DatosException;
import accesoDatos.OperacionesDAO;
import modelo.ClaveAcceso;
import modelo.Correo;
import modelo.DireccionPostal;
import modelo.ModeloException;
import modelo.Nif;
import modelo.Usuario;
import modelo.Usuario.RolUsuario;
import util.Fecha;

public class UsuariosDAO  implements OperacionesDAO {

	// Singleton. 
	private static UsuariosDAO instancia = null;

	// Elementos de almacenamiento.
	private static ArrayList <Usuario> datosUsuarios;
	private static Map <String,String> equivalenciasId;

	/**
	 * Constructor por defecto de uso interno.
	 * Sólo se ejecutará una vez.
	 */
	private UsuariosDAO()  {
		datosUsuarios = new ArrayList <Usuario>();
		equivalenciasId = new Hashtable <String, String>();
		cargarPredeterminados();
	}

	/**
	 *  Método estático de acceso a la instancia única.
	 *  Si no existe la crea invocando al constructor interno.
	 *  Utiliza inicialización diferida.
	 *  Sólo se crea una vez; instancia única -patrón singleton-
	 *  @return instancia
	 */
	public static UsuariosDAO getInstancia() {
		if (instancia == null) {
			instancia = new UsuariosDAO();
		}
		return instancia;
	}

	/**
	 *  Método para generar de datos predeterminados.
	 */
	private void cargarPredeterminados() {
		String nombreUsr = "Admin";
		String password = "Miau#0";	
		Usuario usrPredeterminado = null;
		try {
			usrPredeterminado = new Usuario(new Nif("00000000T"), nombreUsr, "Admin Admin", 
					new DireccionPostal("Iglesia", "0", "30012", "Murcia"), 
					new Correo("jv.admin" + "@gmail.com"), new Fecha(2000, 01, 01), 
					new Fecha(), new ClaveAcceso(password), RolUsuario.ADMINISTRADOR);
		} 
		catch (ModeloException e) {
			e.printStackTrace();
		}
		datosUsuarios.add(usrPredeterminado);
		registrarEquivalenciaId(usrPredeterminado);

		nombreUsr = "Invitado";
		password = "Miau#0";	
		try {
			usrPredeterminado = new Usuario(new Nif("00000001R"), nombreUsr, "Invitado Invitado", 
					new DireccionPostal("Iglesia", "00", "30012", "Murcia"), 
					new Correo("jv.invitado" + "@gmail.com"), new Fecha(2000, 01, 01), 
					new Fecha(), new ClaveAcceso(password), RolUsuario.INVITADO);
		} 
		catch (ModeloException e) {
			e.printStackTrace();
		}
		datosUsuarios.add(usrPredeterminado);
		registrarEquivalenciaId(usrPredeterminado);
	}

	//OPERACIONES DAO
	/**
	 * Búsqueda de usuario dado su id, el correo o su nif.
	 * @param id - el id de Usuario a buscar.
	 * @return - el Usuario encontrado o null si no existe. 
	 */
	@Override
	public Usuario obtener(String id) {
		assert id != null;
		String idUsr = equivalenciasId.get(id);
		int posicion = indexSort(idUsr);			  // En base 1
		if (posicion > 0) {
			return datosUsuarios.get(posicion - 1);   // En base 0
		}
		return null;				
	}

	/**
	 * obtiene todas los usuarios en una lista.
	 * @return - la lista.
	 */
	@Override
	public List obtenerTodos() {
		return datosUsuarios;
	}

	/**
	 *  Obtiene por búsqueda binaria, la posición que ocupa, o ocuparía,  un usuario en 
	 *  la estructura.
	 *	@param Id - id de Usuario a buscar.
	 *	@return - la posición, en base 1, que ocupa un objeto o la que ocuparía (negativo).
	 */
	private int indexSort(String id) {
		int comparacion;
		int inicio = 0;
		int fin = datosUsuarios.size() - 1;
		int medio = 0;
		while (inicio <= fin) {
			medio = (inicio + fin) / 2;			// Calcula posición central.
			// Obtiene > 0 si idUsr va después que medio.
			comparacion = id.compareTo(datosUsuarios.get(medio).getId());
			if (comparacion == 0) {			
				return medio + 1;   			// Posción ocupada, base 1	  
			}		
			if (comparacion > 0) {
				inicio = medio + 1;
			}			
			else {
				fin = medio - 1;
			}
		}	
		return -(inicio + 1);					// Posición que ocuparía -negativo- base 1
	}

	/**
	 * Búsqueda de Usuario dado un objeto, reenvía al método que utiliza id.
	 * @param obj - el Usuario a buscar.
	 * @return - el Usuario encontrado; null si no encuentra.
	 */
	@Override
	public Usuario obtener(Object obj) {
		return this.obtener(((Usuario) obj).getId());
	}	

	/**
	 * Registro de nuevo usuario.
	 * @param usr 
	 * @throws DatosException 
	 */
	@Override
	public void alta(Object usr) throws DatosException {
		assert usr != null;
		int posInsercion = indexSort(((Usuario) usr).getId());

		if (posInsercion < 0) {
			datosUsuarios.add(Math.abs(posInsercion)-1 ,(Usuario) usr);  // Inserta en orden.
			registrarEquivalenciaId((Usuario) usr);
		}
		else {
			if (!datosUsuarios.get(posInsercion-1).equals(usr)) {
				producirVariantesIdUsr((Usuario) usr);
			}
			else {
				throw new DatosException("ALTA Usuario: Ya existe.");
			}
		}
	}
	/**
	 *  Si hay coincidencia de identificador hace 23 intentos de variar la última letra
	 *  procedente del NIF. Llama al generarVarianteIdUsr() de la clase Usuario.
	 * @param usrNuevo
	 * @param posicionInsercion
	 * @throws DatosException
	 */
	private void producirVariantesIdUsr(Usuario usr) throws DatosException {
		int posInsercion;
		// Coincidencia de id. Hay que generar variante
		int intentos = "ABCDEFGHJKLMNPQRSTUVWXYZ".length();
		do {
			// Generar variante y comprueba de nuevo.
			usr = new Usuario(usr);	
			posInsercion = indexSort(usr.getId());
			if (posInsercion < 0) {
				datosUsuarios.add(-posInsercion - 1, usr); // Inserta el usuario en orden.
				registrarEquivalenciaId(usr);
				return;
			}
			intentos--;
		} while (intentos >= 0);
		throw new DatosException("ALTA Usuario: imposible generar variante del " + usr.getId());
	}


	/**
	 *  Añade nif y correo como equivalencias de idUsr para el inicio de sesión. 
	 *	@param usr - Usuario a registrar equivalencias. 
	 */
	private void registrarEquivalenciaId(Usuario usr) {
		assert usr != null;
		equivalenciasId.put(usr.getId(), usr.getId());
		equivalenciasId.put(usr.getNif().getTexto(), usr.getId());
		equivalenciasId.put(usr.getCorreo().getTexto(), usr.getId());
	}

	/**
	 * Elimina el objeto, dado el id utilizado para el almacenamiento.
	 * @param idUsr - el identificador del objeto a eliminar.
	 * @return - el Objeto eliminado. 
	 * @throws DatosException - si no existe.
	 */
	@Override
	public Object baja(String idUsr) throws DatosException {
		assert (idUsr != null);
		int posicion = indexSort(idUsr); 							// En base 1
		if (posicion > 0) {
			Usuario usrEliminado = datosUsuarios.remove(posicion-1); 	// En base 0
			equivalenciasId.remove(usrEliminado.getId());
			equivalenciasId.remove(usrEliminado.getNif().getTexto());
			equivalenciasId.remove(usrEliminado.getCorreo().getTexto());
			return usrEliminado;
		}
		else {
			throw new DatosException("Baja: "+ idUsr + " no existe");
		}
	} 

	/**
	 *  Actualiza datos de un Usuario reemplazando el almacenado por el recibido. 
	 *  No admitirá cambios en el idUsr.
	 *	@param obj - Usuario con los cambios.
	 * @throws DatosException - si no existe.
	 */
	@Override
	public void actualizar(Object obj) throws DatosException  {
		assert obj != null;
		Usuario usrActualizado = (Usuario) obj;							// Para conversión cast
		int posicion = indexSort(usrActualizado.getId()); 		// En base 1
		if (posicion > 0) {
			// Reemplaza equivalencias de Nif y Correo
			Usuario usrModificado = datosUsuarios.get(posicion-1); 	    // En base 0
			equivalenciasId.remove(usrModificado.getNif().getTexto());
			equivalenciasId.remove(usrModificado.getCorreo().getTexto());
			equivalenciasId.put(usrActualizado.getNif().getTexto(), usrActualizado.getId());
			equivalenciasId.put(usrActualizado.getCorreo().getTexto(), usrActualizado.getId());	
			// Reemplaza elemento
			datosUsuarios.set(posicion-1, usrActualizado);  			// En base 0		
		}
		else {
			throw new DatosException("actualizar: "+ usrActualizado.getId() + " no existe");
		}
	} 

	/**
	 * Obtiene el listado de todos los usuarios almacenados.
	 * @return el texto con el volcado de datos.
	 */
	@Override
	public String listarDatos() {
		StringBuilder listado = new StringBuilder();
		for (Usuario usuario: datosUsuarios) {
			if (usuario != null) {
				listado.append("\n" + usuario); 
			}
		}
		return listado.toString();
	}

	/**
	 * Elimina todos los usuarios almacenados y regenera los predeterminados.
	 */
	@Override
	public void borrarTodo() {
		datosUsuarios.clear();
		equivalenciasId.clear();
		cargarPredeterminados();
	}

	/**
	 *  Cierra almacenes de datos.
	 */
	@Override
	public void cerrar() {
		// Nada que hacer si no hay persistencia.	
	}

} //class