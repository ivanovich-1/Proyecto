/** 
 * Proyecto: Juego de la vida.
 *  Implementa el concepto de SesionUsuario según el modelo1
 *  En esta versión sólo se ha aplicado un diseño OO básico.
 *  Se pueden detectar varios defectos y antipatrones de diseño:
 *  	- Clase perezosa, que asume poca responsabilidad.
 *  @since: prototipo1.0
 *  @source: SesionUsuario.java 
 *  @version: 1.0 - 2018/11/21 
 *  @author: ajp
 */


import java.util.Calendar;
import java.util.GregorianCalendar;

public class SesionUsuario {

	private Usuario usr;
	private Calendar fecha; 

	/**
	 * Constructor convencional. Utiliza métodos set...()
	 * @param usr
	 * @param fecha
	 */
	public SesionUsuario(Usuario usr, Calendar fecha) {
		setUsr(usr);
		setFecha(fecha);
	}

	/**
	 * Constructor por defecto. Utiliza constructor convencional.
	 */
	public SesionUsuario() {
		this(new Usuario(), new GregorianCalendar());
	}

	/**
	 * Constructor copia.
	 * @param sesion
	 */
	public SesionUsuario(SesionUsuario sesion) {
		this.usr = new Usuario(sesion.usr);
		this.fecha = new GregorianCalendar(sesion.fecha.get(Calendar.YEAR), 
				sesion.fecha.get(Calendar.MONTH), sesion.fecha.get(Calendar.DATE));
	}

	public Usuario getUsr() {
		return usr;
	}

	public void setUsr(Usuario usr) {
		assert usr != null;
		this.usr = usr;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		assert fecha != null;
		this.fecha = fecha;
	}

	/**
	 * Redefine el método heredado de la clase Object.
	 * @return el texto formateado del estado (valores de atributos) 
	 * del objeto de la clase SesionUsuario  
	 */
	@Override
	public String toString() {
		return usr.toString() 
				+ String.format("%-16s %s\n", "fecha:", fecha);	
	}

} // class
