/** 
 *  Proyecto: Juego de la vida.
 *  Implementa el concepto de ClaveAcceso según el modelo 1.1
 *  Utiliza un string para representar el texto del ClaveAcceso.  
 *  @since: prototipo1.2
 *  @source: ClaveAcceso.java 
 *  @version: 1.1 - 2019/01/22 
 *  @author: ajp
 */

package modelo;

public class ClaveAcceso {

	private String texto;

	public ClaveAcceso() {
		setTexto("Miau#0");
	}

	public ClaveAcceso(String texto) {
		setTexto(texto);
	}

	public ClaveAcceso(ClaveAcceso ClaveAcceso) {
		this.texto = new String(ClaveAcceso.texto);
	}

	public String getTexto() {
		return this.texto;
	}

	public void setTexto(String texto) {
		assert texto != null;
		if (ClaveAccesoValido(texto)) {
			this.texto = encriptarCesar(texto);
		}
		// Todavía no se gestionan errores de usuario.
		if (this.texto == null) {						// Tiempo de construcción.
				this.texto = new ClaveAcceso().texto; 	// Defecto.
		}
	}

	/**
	 * Encripta un texto.
	 * Utiliza encriptación simple de Cesar basada en dos alfabetos desplazados 4 posiciones.
	 * Mantiene mayúsculas, minúsculas y espacios.
	 * @param textoClaro 
	 * @return textoEncriptado. 
	 * @ 
	 */
	private String encriptarCesar(String textoClaro) {
		String alfaNormal =     "AaBbCcDdEeFfGgHhIiJjKkLlMmNnÑñOoPpQqRrSsTtUuVvXxYyZz0123456789!?$%&/#";
		String alfaDesplazado = "EeFfGgHhIiJjKkLlMmNnÑñOoPpQqRrSsTtUuVvXxYyZz0123456789!?$%&/#AaBbCcDd";
		char charAEncriptar;
		int posCharAEncriptar;
		StringBuilder  textoEncriptado = new StringBuilder();

		for (int i=0; i < textoClaro.length(); i++ ){
			charAEncriptar = textoClaro.charAt(i);
			posCharAEncriptar = alfaNormal.indexOf(charAEncriptar);
			textoEncriptado.append(alfaDesplazado.charAt(posCharAEncriptar));

		}
		return textoEncriptado.toString();
	}
	
	private boolean ClaveAccesoValido(String texto) {
		return texto.matches("(?=.*\\d)(?=.*[A-ZÑ])(?=.*[a-zñ])(?=.*[#$*-+&!?%]).{6,}");
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
			if (texto.equals(((ClaveAcceso) obj).texto)) {
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
		return new ClaveAcceso(this);
	}

} // class
