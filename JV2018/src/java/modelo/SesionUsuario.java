/** 
 * Proyecto: Juego de la vida.
 *  Implementa el concepto de SesionUsuario según el modelo1.1
 *  @since: prototipo1.0
 *  @source: SesionUsuario.java 
 *  @version: 1.1 - 2019/01/25
 *  @author: ajp
 */

package modelo;

import util.Fecha;

public class SesionUsuario {

	private Usuario usr;
	private Fecha fecha; 

	/**
	 * Constructor convencional. Utiliza métodos set...()
	 * @param usr
	 * @param fecha
	 */
	public SesionUsuario(Usuario usr, Fecha fecha) {
		setUsr(usr);
		setFecha(fecha);
	}

	/**
	 * Constructor por defecto. Utiliza constructor convencional.
	 * @throws ModeloException 
	 */
	public SesionUsuario() throws ModeloException {
		this(new Usuario(), new Fecha());
	}

	/**
	 * Constructor copia.
	 * @param sesion
	 */
	public SesionUsuario(SesionUsuario sesion) {
		this.usr = new Usuario(sesion.usr);
		this.fecha = new Fecha(sesion.fecha.getAño(), 
				sesion.fecha.getMes(), sesion.fecha.getDia());
	}

	public Usuario getUsr() {
		return usr;
	}

	public void setUsr(Usuario usr) {
		assert usr != null;
		this.usr = usr;
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		assert fecha != null;
		this.fecha = fecha;
	}

	public String getId() {	
		return this.usr.getId() + ":" + fecha.toStringMarcaTiempo();
	}
	
	/**
	 * Redefine el método heredado de la clase Object.
	 * @return el texto formateado del estado (valores de atributos) 
	 * del objeto de la clase SesionUsuario  
	 */
	@Override
	public String toString() {
		return usr.toString() 
				+ String.format("%s\n", fecha);	
	}

} // class
