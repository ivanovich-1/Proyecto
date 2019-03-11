/** 
 * Proyecto: Juego de la vida.
 * Organiza aspectos de gestión de la simulación según el modelo1.2
 * @since: prototipo1.0
 * @source: Simulacion.java 
 * @version: 1.2 - 2019.02.25
 * @author: ajp
 */

package modelo;

import util.Fecha;

public class Simulacion {

	public static final int CICLOS_SIMULACION = 20;
	private Usuario usr;
	private Fecha fecha;
	private Mundo mundo;

	/**
	 * Constructor convencional.
	 * Establece el valor inicial de cada uno de los atributos.
	 * Recibe parámetros que se corresponden con los atributos.
	 * Utiliza métodos set... para la posible verificación.
	 * @param usr
	 * @param fecha
	 * @param mundo
	 */
	public Simulacion(Usuario usr, Fecha fecha, Mundo mundo) {
		setUsr(usr);
		setFecha(fecha);
		setMundo(mundo);
	}

	/**
	 * Constructor por defecto.
	 * Establece el valor inicial, por defecto, de cada uno de los atributos.
	 * Llama al constructor convencional de la propia clase.
	 * @throws ModeloException 
	 */
	public Simulacion() throws ModeloException {
		this(new Usuario(), new Fecha(), new Mundo());
	}

	/**
	 * Constructor copia.
	 * Establece el valor inicial de cada uno de los atributos a partir de
	 * los valores obtenidos de un objeto de su misma clase.
	 * El objeto Usuario es compartido (agregación).
	 * @param simul - la Simulacion a clonar
	 */
	public Simulacion(Simulacion simul) {
		setUsr(simul.usr);
		setFecha((Fecha) simul.fecha.clone());
		setMundo(new Mundo(simul.mundo));
	}

	public Usuario getUsr() {
		return usr;
	}

	public void setUsr(Usuario usr) {
		assert usr != null;
		this.usr = usr;
	}

	public Mundo getMundo() {
		return mundo;
	}

	public void setMundo(Mundo mundo) {
		assert mundo != null;
		this.mundo = mundo;
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		assert fecha != null;
		this.fecha = fecha;
	}

	public String getId() {	
		return this.usr.getId() + "-" + fecha.toStringMarcaTiempo();
	}
	
	/**
	 * Reproduce el estado -valores de atributos- de objeto en forma de texto. 
	 * @return el texto formateado.  
	 */
	@Override
	public String toString() {
		return String.format(
				"Simulacion [usr=%s, fecha=%s, mundo=%s]", usr, fecha, mundo);
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
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((mundo == null) ? 0 : mundo.hashCode());
		result = prime * result + ((usr == null) ? 0 : usr.hashCode());
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
			if (usr.equals(((Simulacion)obj).usr) 
					&& fecha.equals(((Simulacion)obj).fecha) 
					&& mundo.equals(((Simulacion)obj).mundo)
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
		return new Simulacion(this);
	}

} //class
