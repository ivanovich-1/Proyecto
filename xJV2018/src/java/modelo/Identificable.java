/** 
 * Proyecto: Juego de la vida.
 * Interface de métodos que deben implementar los objetos que requieren identificador.
 * @since: prototipo2.0
 * @source: Identificable.java 
 * @version: 1.2 - 2019.03.20
 * @author: ajp
 */

package modelo;

public interface Identificable {
	
	/**
	 * Devuelve el identificador único del objeto.
	 * @return el identificador.
	 */
	String getId();

	/**
	 * Devuelve el número de elementos del .
	 * @return el identificador.
	 */
	int size();
}
