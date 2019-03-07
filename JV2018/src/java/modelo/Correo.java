/** 
 *  Proyecto: Juego de la vida.
 *  Implementa el concepto de correo según el modelo 1.1.
 *  Utiliza un string para representar el texto del correo.  
 *  @since: prototipo1.1
 *  @source: Correo.java 
 *  @version: 1.1 - 2019/01/22 
 *  @author: ajp
 */

package modelo;

public class Correo {

	private String texto;

	public Correo() throws ModeloException {
		this("correo@correo.es");
	}

	public Correo(String texto) throws ModeloException {
		setTexto(texto);
	}

	public Correo(Correo correo) {
		this.texto = new String(correo.texto);
	}

	public String getTexto() {
		return this.texto;
	}

	public void setTexto(String texto) throws ModeloException {
		assert texto != null;
		if (correoValido(texto)) {
			this.texto = texto;
		}
		else {
			throw new ModeloException("Correo: formato no válido.");
		}
	}

	private boolean correoValido(String texto) {	
		return texto.matches("^[\\w-\\+]+(\\.[\\w-\\+]+)*"
				+ "@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
				&& correoAutentico();
	}

	/**
	 * Comprueba que una dirección de correo existe.
	 * @return
	 */
	private boolean correoAutentico() {
		// -- Pendiente --
		return true;
	}
	
	@Override
	public String toString() {
		return texto;
	}
	
	/**
	 * hashcode() complementa al método equals y sirve para comparar objetos de forma 
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
			if (texto.equals(((Correo) obj).texto)) {
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
		return new Correo(this);
	}
		
} // class
