/** 
 *  Proyecto: Juego de la vida.
 *  Implementa el concepto de direccion postal según el modelo 1.1
 *  Utiliza un varios string para representar los distintos campos.  
 *  @since: prototipo1.1
 *  @source: DireccionPostal.java 
 *  @version: 1.1 - 2019/01/22 
 *  @author: ajp
 */

package modelo;

public class DireccionPostal {

	private String calle;
	private String numero;
	private String cp;
	private String poblacion;

	public DireccionPostal(String calle, String numero, String cp, String poblacion) {
		setCalle(calle);
		setNumero(numero);
		setCp(cp);
		setPoblacion(poblacion);
	}

	public DireccionPostal() {
		this("Calle", "00", "01000", "Población");
	}

	public DireccionPostal(DireccionPostal dp) {
		calle = new String(dp.calle);
		numero = new String(dp.numero);
		cp = new String(dp.cp);
		poblacion = new String(dp.poblacion);
	}

	public void setCalle(String calle) {
		assert calle != null;
		if  (calleValida(calle)) {
			this.calle = calle;
		}
		// Todavía no se gestionan errores de usuario.
		if (this.calle == null) {						// Tiempo de construcción.
			this.calle = new DireccionPostal().calle; 	// Defecto.
		}
	}

	/**
	 * Comprueba validez de una calle.
	 * @param calle.
	 * @return true si cumple.
	 */
	private boolean calleValida(String calle) {
		return	calle.matches("[A-ZÑÁÉÍÓÚa-zñáéíóú/\\d ]+");
	}

	public void setNumero(String numero) {
		assert numero != null;
		if (numeroValido(numero)) {
			this.numero = numero;
		}
		// Todavía no se gestionan errores de usuario.
		if (this.numero == null) {						// Tiempo de construcción.
			this.numero = new DireccionPostal().numero; // Defecto.
		}
	}

	/**
	 * Comprueba validez de un numero postal.
	 * @param numero.
	 * @return true si cumple.
	 */
	private boolean numeroValido(String numero) {
		return	numero.matches("[\\d]+[A-Z]?");
	}

	public void setCp(String cp) {
		assert cp != null;
		if (cpValido(cp)) {
			this.cp = cp;
		}
		// Todavía no se gestionan errores de usuario.
		if (this.cp == null) {							// Tiempo de construcción.
			this.cp = new DireccionPostal().cp; 		// Defecto.
		}
	}

	/**
	 * Comprueba validez de un código postal.
	 * @param cp.
	 * @return true si cumple.
	 */
	private boolean cpValido(String cp) {
		// Número entre 01000 y 52999
		return cp.matches("^([1-9]{2}|[0-9][1-9]|[1-9][0-9])[0-9]{3}$");
	}

	public void setPoblacion(String poblacion) {
		assert poblacion != null;
		if (poblacionValida(poblacion)) {
			this.poblacion = poblacion;
		}
		// Todavía no se gestionan errores de usuario.
		if (this.poblacion == null) {							// Tiempo de construcción.
			this.poblacion = new DireccionPostal().poblacion; 	// Defecto.
		}
	}

	/**
	 * Comprueba validez de una población.
	 * @param poblacion.
	 * @return true si cumple.
	 */
	private boolean poblacionValida(String poblacion) {
		return	poblacion.matches("^[A-ZÑÁÉÍÓÚ][áéíóúña-z \\w]+");
	}

	public String getCalle() {
		return calle;
	}

	public String getNumero() {
		return numero;
	}

	public String getCp() {
		return cp;
	}

	public String getPoblacion() {
		return poblacion;
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calle == null) ? 0 : calle.hashCode());
		result = prime * result + ((cp == null) ? 0 : cp.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((poblacion == null) ? 0 : poblacion.hashCode());
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
			if (calle.equals(((DireccionPostal)obj).calle) 
					&& cp.equals(((DireccionPostal)obj).cp) 
					&& numero.equals(((DireccionPostal)obj).numero) 
					&& poblacion.equals(((DireccionPostal)obj).poblacion)
					) {
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
		return new DireccionPostal(this);
	}

	@Override
	public String toString() {
		return calle + ", " + numero + ", " + cp + ", " + poblacion;
	}

} // class
