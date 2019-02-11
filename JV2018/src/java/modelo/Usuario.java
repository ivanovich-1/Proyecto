/** Proyecto: Juego de la vida.
 *  Implementa el concepto de Usuario según el modelo1.
 *  En esta versión se ha revisado el diseño OO previo.
 *  @since: prototipo1.0
 *  @source: Usuario.java 
 *  @version: 1.1 - 2019/01/21 
 *  @author: ajp
 */

package modelo;

import util.Fecha;


public class Usuario {
	
	public enum RolUsuario {INVITADO, NORMAL, ADMINSTRADOR};
	
	static final int EDAD_MINIMA = 16;
	private Nif nif;
	private String nombre;
	private String apellidos;
	private DireccionPostal domicilio;
	private Correo correo;
	private Fecha fechaNacimiento;
	private Fecha fechaAlta;
	private ClaveAcceso claveAcceso;
	private RolUsuario rol;

	
	/**
	 * Constructor convencional. Utiliza métodos set...()
	 * @param nif
	 * @param nombre
	 * @param apellidos
	 * @param domicilio
	 * @param correo
	 * @param fechaNacimiento
	 * @param fechaAlta
	 * @param claveAcceso
	 * @param rol
	 */
	public Usuario(Nif nif, String nombre, String apellidos,
			DireccionPostal domicilio, Correo correo, Fecha fechaNacimiento,
			Fecha fechaAlta, ClaveAcceso claveAcceso, RolUsuario rol) {
		setNif(nif);
		setNombre(nombre);
		setApellidos(apellidos);
		setDomicilio(domicilio);
		setCorreo(correo);
		setFechaNacimiento(fechaNacimiento);
		setFechaAlta(fechaAlta);
		setClaveAcceso(claveAcceso);
		setRol(rol);
	}

	/**
	 * Constructor por defecto. Reenvía al constructor convencional.
	 */
	public Usuario() {
		this(new Nif(), 
				"Nombre", 
				"Apellido Apellido", 
				new DireccionPostal(),
				new Correo(), 
				new Fecha().addAños(-EDAD_MINIMA), 
				new Fecha(), 
				new ClaveAcceso(), 
				RolUsuario.NORMAL);
	}

	/**
	 * Constructor copia.
	 * @param usr
	 */
	public Usuario(Usuario usr) {
		this.nif = new Nif(usr.nif);
		this.nombre = new String(usr.nombre);
		this.apellidos = new String(usr.apellidos);
		this.domicilio = new DireccionPostal(usr.domicilio);
		this.correo = new Correo(usr.correo);
		this.fechaNacimiento = new Fecha(usr.fechaNacimiento.getAño(), 
				usr.fechaNacimiento.getMes(), usr.fechaNacimiento.getDia());
		this.fechaAlta = new Fecha(usr.fechaAlta.getAño(), 
				usr.fechaAlta.getMes(), usr.fechaAlta.getDia());
		this.claveAcceso = new ClaveAcceso(usr.claveAcceso);
		this.rol = usr.rol;
	}

	/**
	 * Genera un identificador sintético a partir de:
	 * La letra inicial del nombre, 
	 * Las dos iniciales del primer y segundo apellido,
	 * Los dos último caracteres del nif.
	 * @return idUsr
	 */
	public String getIdUsr() {
		assert this.nif != null;
		assert this.nombre != null;
		assert this.apellidos != null;
		String[] apellidos = this.apellidos.split(" ");
		return this.nombre.charAt(0) 
				+ apellidos[0].charAt(0) + apellidos[1].charAt(0)
				+ this.nif.getTexto().substring(7);
	}
	
	/**
	 * Genera una variante cambiando la última letra del idUsr 
	 * por la siguiente en el alfabeto previsto para el nif.
	 * @param idUsr
	 * @return nuevo idUsr
	 */
	public String generarVarianteIdUsr(String idUsr) {
		String alfabetoNif = "ABCDEFGHJKLMNPQRSTUVWXYZ";
		String alfabetoNifDesplazado = "BCDEFGHJKLMNPQRSTUVWXYZA";
		return getIdUsr().substring(0, 4) 
				+ alfabetoNifDesplazado.charAt(alfabetoNif.indexOf(idUsr.charAt(4)));
	}
	
	public Nif getNif() {
		return nif;
	}

	public void setNif(Nif nif) {
		assert nif != null;	
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {	
		assert nombre != null;
		if (nombreValido(nombre)) {
			this.nombre = nombre;
		}
		// Todavía no se gestionan errores de usuario.
		if (this.nombre == null) {						// Tiempo de construcción.
			this.nombre = new Usuario().nombre; 		// Defecto.
		}
	}

	/**
	 * Comprueba validez del nombre.
	 * @param nombre.
	 * @return true si cumple.
	 */
	private boolean nombreValido(String nombre) {
		return nombre.matches("^[A-ZÑÁÉÍÓÚ][A-ZÑÁÉÍÓÚa-zñáéíóúü ]+");
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		assert apellidos != null;
		if (apellidosValidos(apellidos)) {
			this.apellidos = apellidos;
		}
		// Todavía no se gestionan errores de usuario.
		if (this.apellidos == null) {				  	// Tiempo de construcción.
			this.apellidos = new Usuario().apellidos; 	// Defecto.
		}
	}

	/**
	 * Comprueba validez de los apellidos.
	 * @param apellidos.
	 * @return true si cumple.
	 */
	private boolean apellidosValidos(String apellidos) {
		return apellidos.matches("^[A-ZÑÁÉÍÓÚ][A-ZÑÁÉÍÓÚa-zñáéíóúü ]*");
	}

	public DireccionPostal getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(DireccionPostal domicilio) {
		assert domicilio != null;
		this.domicilio = domicilio;
	}

	public Correo getCorreo() {
		return correo;
	}

	public void setCorreo(Correo correo) {
		assert correo != null;
			this.correo = correo;
	}

	public Fecha getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Fecha fechaNacimiento) {
		assert fechaNacimiento != null;
		if (fechaNacimientoValida(fechaNacimiento)) {
			this.fechaNacimiento = fechaNacimiento;
		}
		// Todavía no se gestionan errores de usuario.
		if (this.fechaNacimiento == null) {							// Tiempo de construcción.
			this.fechaNacimiento = new Usuario().fechaNacimiento;	// Defecto.
		}
	}

	/**
	 * Comprueba validez de una fecha de nacimiento.
	 * @param fechaNacimiento.
	 * @return true si cumple.
	 */
	private boolean fechaNacimientoValida(Fecha fechaNacimiento) {
		return !fechaNacimiento.after(new Fecha().addAños(-EDAD_MINIMA));
	}

	public Fecha getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Fecha fechaAlta) {
		assert fechaAlta != null;
		if (fechaAltaValida(fechaAlta)) {
			this.fechaAlta = fechaAlta;
		}
		// Todavía no se gestionan errores de usuario.
		if (this.fechaAlta == null) {						// Tiempo de construcción.
			this.fechaAlta = new Usuario().fechaAlta;		// Defecto.
		}
	}

	/**
	 * Comprueba validez de una fecha de alta.
	 * @param fechaAlta.
	 * @return true si cumple.
	 */
	private boolean fechaAltaValida(Fecha fechaAlta) {
		return !fechaAlta.after(new Fecha()); 	
	}

	public ClaveAcceso getClaveAcceso() {
		return claveAcceso;
	}

	public void setClaveAcceso(ClaveAcceso claveAcceso) {
		assert claveAcceso != null;
		this.claveAcceso = claveAcceso;
	}

	public RolUsuario getRol() {
		return rol;
	}

	public void setRol(RolUsuario rol) {
		assert	rol != null;
		this.rol = rol;
	}

	/**
	 * Redefine el método heredado de la clase Objecto.
	 * @return el texto formateado del estado -valores de atributos- de objeto de la clase Usuario.  
	 */
	@Override
	public String toString() {
		return String.format(
				"%-16s %s\n"
		+ "%-16s %s\n"
		+ "%-16s %s\n"
		+ "%-16s %s\n"
		+ "%-16s %s\n"
		+ "%-16s %s\n"
		+ "%-16s %s\n"
		+ "%-16s %s\n"
		+ "%-16s %s\n", 
		"nif:", nif, 
		"nombre:", this.nombre, 
		"apellidos:", this.apellidos,  
		"domicilio:", this.domicilio, 
		"correo:", this.correo, 
		"fechaNacimiento:", this.fechaNacimiento.getAño() + "." 
				+ this.fechaNacimiento.getMes() + "." 
				+ this.fechaNacimiento.getDia(),	
		"fechaAlta:", this.fechaAlta.getAño() + "." 
				+ this.fechaAlta.getMes() + "." 
				+ this.fechaAlta.getDia(), 
		"claveAcceso:", this.claveAcceso, 
		"rol:", this.rol
		);		
	}

} // class

