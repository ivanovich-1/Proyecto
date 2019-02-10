/** Proyecto: Juego de la vida.
 *  Implementa el concepto de Nif según el modelo 1.1  
 *  @since: prototipo1.1
 *  @source: Nif.java 
 *  @version: 1.1 - 2019/01/22 
 *  @author: ajp
 */

package modelo;

public class Nif {

	private String texto;

	public Nif() {
		setTexto("00000000T");
	}

	public Nif(String texto) {
		setTexto(texto);
	}

	public Nif(Nif nif) {
		this.texto = new String(nif.texto);
	}

	public String getTexto() {
		return this.texto;
	}

	public void setTexto(String texto) {
		assert texto != null;
		if (nifValido(texto)) {
			this.texto = texto;
		}
		// Todavía no se gestionan errores de usuario.
		if (this.texto == null) {						// Tiempo de construcción.
				this.texto = new Nif().texto; 		    // Defecto.
		}
	}

	private boolean nifValido(String texto) {	
		texto = texto.toUpperCase();
		return texto.matches("^[\\d]{8}[TRWAGMYFPDXBNJZSQVHLCKE]") 
				&& letraNIFValida(texto);
	}

	/**
	 * Comprueba la validez de la letra de un NIF
	 * @param texto del NIF
	 * @return true si la letra es correcta.
	 */
	private boolean letraNIFValida(String texto) {
		int numeroNIF = Integer.parseInt(texto.substring(0,8));
		return texto.charAt(8) == "TRWAGMYFPDXBNJZSQVHLCKE".charAt(numeroNIF % 23);
	} 
	
	@Override
	public String toString() {
		return this.texto;
	}
	
	/**
	 * hashCode() complementa al método equals y sirve para comparar objetos de forma 
	 * rápida en estructuras Hash. 
	 * Cuando Java compara dos objetos en estructuras de tipo hash (HashMap, HashSet etc)
	 * primero invoca al método hashcode y luego el equals.
	 * @return un número entero de 32 bit.
	 */
	@Override
	public int hashCode() {
		final int primo = 31;
		int result = 1;
		result = primo * result + ((texto == null) ? 0 : texto.hashCode());
		return result;
	}

	/**
	 * Dos objetos son iguales si: 
	 * Son de la misma clase.
	 * Tienen los mismos valores en los atributos; o son el mismo objeto.
	 * @return falso si no cumple las condiciones.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj != null && getClass() == obj.getClass()) {
			if (this == obj) {
				return true;
			}
			if (texto.equals(((Nif) obj).texto)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Genera un clon del propio objeto realizando una copia profunda.
	 * @return el objeto clonado.
	 */
	@Override
	public Object clone() {
		// Utiliza el constructor copia.
		return new Nif(this);
	}
	
}
