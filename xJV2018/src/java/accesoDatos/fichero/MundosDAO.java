/** 
 * Proyecto: Juego de la vida.
 * Resuelve todos los aspectos del almacenamiento del DTO Mundo utilizando un ArrayList.
 * Colabora en el patron Fachada.
 * @since: prototipo2.0
 * @source: MundosDAO.java 
 * @version: 2.0 - 2018/04/23
 * @author: ajp
 */

package accesoDatos.fichero;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import accesoDatos.DatosException;
import accesoDatos.OperacionesDAO;
import config.Configuracion;
import modelo.Mundo;
import modelo.Mundo.FormaEspacio;
import modelo.Posicion;

public class MundosDAO implements OperacionesDAO, Persistente {

	// Singleton.
	private static MundosDAO instancia;

	// Elementos de almacenamiento.
	private ArrayList<Mundo> datosMundos;
	private static File fMundos;
	
	/**
	 * Constructor por defecto de uso interno.
	 * Sólo se ejecutará una vez.
	 */
	private MundosDAO() {
		datosMundos = new ArrayList<Mundo>();
		fMundos = new File(Configuracion.get().getProperty("mundos.nombreFichero"));
		try {
			recuperarDatos();
		} 
		catch (DatosException e) {
			if (e.getMessage().equals("El fichero de datos: " + fMundos.getName() + " no existe...")) {	
				cargarPredeterminados();
			}
		}
	}

	/**
	 *  Método estático de acceso a la instancia única.
	 *  Si no existe la crea invocando al constructor interno.
	 *  Utiliza inicialización diferida.
	 *  Sólo se crea una vez; instancia única -patrón singleton-
	 *  @return instancia
	 */
	public static MundosDAO getInstancia() {
		if (instancia == null) {
			instancia = new MundosDAO();
		}
		return instancia;
	}

	/**
	 *  Método para generar de datos predeterminados.
	 */
	private void cargarPredeterminados() {
		try {
			// En este array los 0 indican celdas con célula muerta y los 1 vivas
			byte[][] espacioDemo =  new byte[][]{ 
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
			};
			alta(new Mundo("Demo1", espacioDemo, new ArrayList<Posicion>(), new HashMap<String, int[]>(), FormaEspacio.ESFERICO));
		} 
		catch (DatosException e) {
			e.printStackTrace();
		}
	}
	
	//OPERACIONES DE PERSISTENCIA.
	/**
	 *  Recupera el Arraylist datosMundos almacenados en fichero. 
	 * @throws DatosException 
	 */
	@Override
	public void recuperarDatos() throws DatosException {
		try {
			if (fMundos.exists()) {
				FileInputStream fisMundos = new FileInputStream(fMundos);
				ObjectInputStream oisMundos = new ObjectInputStream(fisMundos);
				datosMundos = (ArrayList<Mundo>) oisMundos.readObject();
				oisMundos.close();
				return;
			}
			throw new DatosException("El fichero de datos: " + fMundos.getName() + " no existe...");
		} 
		catch (ClassNotFoundException |IOException e) {
			e.printStackTrace();
		} 
	}

	/**
	 *  Guarda el Arraylist de mundos en fichero.
	 */
	@Override
	public void guardarDatos() {
		guardarDatos(datosMundos);
	}

	/**
	 *  Guarda la lista recibida en el fichero de datos.
	 */
	private void guardarDatos(List<Mundo> listaMundos) {
		try {
			FileOutputStream fosMundos = new FileOutputStream(fMundos);
			ObjectOutputStream oosSesiones = new ObjectOutputStream(fosMundos);
			oosSesiones.writeObject(listaMundos);		
			oosSesiones.flush();
			oosSesiones.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 *  Cierra almacenes de datos.
	 */
	@Override
	public void cerrar() {
		guardarDatos();
	}
	
	//OPERACIONES DAO
	/**
	 * Obtiene el objeto dado el id utilizado para el almacenamiento.
	 * @param id - id del mundo a obtener.
	 * @return - el Mundo encontrado; null si no encuentra.
	 */	
	@Override
	public Mundo obtener(String id) {
		assert id != null;
		int posicion = indexSort(id);					// En base 1
		if (posicion >= 0) {
			return datosMundos.get(posicion - 1);     	// En base 0
		}
		return null;
	}

	/**
	 *  Obtiene por búsqueda binaria, la posición que ocupa, o ocuparía,  un Mundo en 
	 *  la estructura.
	 *	@param id - id de Mundo a buscar.
	 *	@return - la posición, en base 1, que ocupa un objeto o la que ocuparía (negativo).
	 */
	private int indexSort(String id) {
		int comparacion;
		int inicio = 0;
		int fin = datosMundos.size() - 1;
		int medio = 0;
		while (inicio <= fin) {
			medio = (inicio + fin) / 2;			// Calcula posición central.
			// Obtiene > 0 si nombre va después que medio.
			comparacion = id.compareTo(datosMundos.get(medio).getId());
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
	 * obtiene todos los mundos en una lista.
	 * @return - la lista.
	 */
	@Override
	public List obtenerTodos() {
		return datosMundos;
	}

	/**
	 *  Alta de un objeto en el almacén de datos, 
	 *  sin repeticiones, según el campo id previsto. 
	 *	@param obj - Objeto a almacenar.
	 * @throws DatosException - si ya existe.
	 */
	@Override
	public void alta(Object obj) throws DatosException  {
		assert obj != null;
		Mundo mundoNuevo = (Mundo) obj;										// Para conversión cast
		int posInsercion = indexSort(mundoNuevo.getId()); 
		if (posInsercion < 0) {
			datosMundos.add(Math.abs(posInsercion)-1, mundoNuevo); 			// Inserta la sesión en orden.
		}
		else {
			throw new DatosException("MundosDAO.alta: "+ mundoNuevo.getId() + " ya existe");
		}
	}

	/**
	 * Elimina el objeto, dado el id utilizado para el almacenamiento.
	 * @param nombre - el nombre del Mundo a eliminar.
	 * @return - el Mundo eliminado.
	 * @throws DatosException - si no existe.
	 */
	@Override
	public Mundo baja(String nombre) throws DatosException  {
		assert (nombre != null);
		int posicion = indexSort(nombre); 									// En base 1
		if (posicion > 0) {
			return datosMundos.remove(posicion - 1); 						// En base 0
		}
		else {
			throw new DatosException("MundosDAO.baja: "+ nombre + " no existe");
		}
	}

	/**
	 *  Actualiza datos de un Mundo reemplazando el almacenado por el recibido.
	 *	@param obj - Mundo con las modificaciones.
	 * @throws DatosException - si no existe.
	 */
	@Override
	public void actualizar(Object obj) throws DatosException  {
		assert obj != null;
		Mundo mundoActualizado = (Mundo) obj;								// Para conversión cast
		int posicion = indexSort(mundoActualizado.getId()); 				// En base 1
		if (posicion > 0) {
			// Reemplaza elemento
			datosMundos.set(posicion - 1, mundoActualizado);  				// En base 0		
		}
		else {
			throw new DatosException("MundosDAO.actualizar : "+ mundoActualizado.getId() + " no existe");
		}
	}

	/**
	 * Obtiene el listado de todos los objetos Mundo almacenados.
	 * @return el texto con el volcado de datos.
	 */
	@Override
	public String listarDatos() {
		StringBuilder result = new StringBuilder();
		for (Mundo mundo: datosMundos) {
			result.append("\n" + mundo);
		}
		return result.toString();
	}

	/**
	 * Obtiene el listado de todos id de los objetos almacenados.
	 * @return el texto con el volcado de id.
	 */
	@Override
	public String listarId() {
		StringBuilder result = new StringBuilder();
		for (Mundo mundo: datosMundos) {
			result.append("\n" + mundo.getId());
		}
		return result.toString();
	}
	
	/**
	 * Elimina todos los mundos almacenados y regenera el demo predeterminado.
	 */
	@Override
	public void borrarTodo() {
		datosMundos.clear();
		cargarPredeterminados();	
	}

} // class