/** 
 * Proyecto: Juego de la vida.
 * Clase de excepción para errores del modelo.
 * @since: prototipo1.2
 * @source: DatosException.java 
 * @version: 1.2 - 2019.03.02
 * @author: ajp
 */

package accesoDatos;

public class DatosException extends Exception {

	public DatosException(String mensaje) {
		super(mensaje);
	}

	public DatosException() {
		super();
	}
}
