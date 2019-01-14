/** Proyecto: Juego de la vida.
 *  Implementa el concepto de Usuario según el modelo1.
 *  En esta versión sólo se ha aplicado un diseño OO básico.
 *  Se pueden detectar varios defectos y antipatrones de diseño:
 *  	- Obsesión por los tipos primitivos.
 *  	- Clase demasiado grande.
 *  	- Clase acaparadora, que delega poca responsabilidad.  
 *  @since: prototipo1.0
 *  @source: Usuario.java 
 *  @version: 1.0 - 2018/11/21 
 *  @author: ajp
 */


import java.util.Calendar;
import java.util.GregorianCalendar;

public class Usuario {

	private String nif;
	private String nombre;
	private String apellidos;
	private String domicilio;
	private String correo;
	private Calendar fechaNacimiento;
	private Calendar fechaAlta;
	private String claveAcceso;
	private String rol;
	public final static String[] ROLES = {
			"INVITADO",
			"NORMAL",
			"ADMINSTRADOR"
	};
	
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
	public Usuario(String nif, String nombre, String apellidos,
			String domicilio, String correo, Calendar fechaNacimiento,
			Calendar fechaAlta, String claveAcceso, String rol) {
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
		this("00000000T", 
				"Nombre", 
				"Apellido Apellido", 
				"Domicilio",
				"correo@correo.es", 
				new GregorianCalendar(), 
				new GregorianCalendar(), 
				"Miau#0", 
				ROLES[1]);
	}

	/**
	 * Constructor copia.
	 * @param usr
	 */
	public Usuario(Usuario usr) {
		this.nif = new String(usr.nif);
		this.nombre = new String(usr.nombre);
		this.apellidos = new String(usr.apellidos);
		this.domicilio = new String(usr.domicilio);
		this.correo = new String(usr.correo);
		this.fechaNacimiento = new GregorianCalendar(usr.fechaNacimiento.get(Calendar.YEAR), 
				usr.fechaNacimiento.get(Calendar.MONTH), usr.fechaNacimiento.get(Calendar.DATE));
		this.fechaAlta = new GregorianCalendar(usr.fechaAlta.get(Calendar.YEAR), 
				usr.fechaAlta.get(Calendar.MONTH), usr.fechaAlta.get(Calendar.DATE));
		this.claveAcceso = new String(usr.claveAcceso);
		this.rol = new String(usr.rol);
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		assert nif != null;
		if (nifValido(nif)) {
			this.nif = nif;
		}
		// Todavía no se gestionan errores de usuario.
		if (this.nif == null) {							// Tiempo de construcción.
			this.nif = new Usuario().nif; 				// Defecto.
		}
	}

	/**
	 * Comprueba validez del nif.
	 * @param nif.
	 * @return true si cumple.
	 */
	private boolean nifValido(String nif) {
		return !nif.matches("[ ]+");					// Que no sea en blanco.
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
		return !nombre.matches("[ ]+");					// Que no sea en blanco.
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
		return !apellidos.matches("[ ]+");				// Que no sea en blanco.
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		assert domicilio != null;
		if (direccionValida(domicilio)) {
			this.domicilio = domicilio;
		}
		// Todavía no se gestionan errores de usuario.
		if (this.domicilio == null) {				  	// Tiempo de construcción.
			this.domicilio = new Usuario().domicilio; 	// Defecto.
		}
	}

	/**
	 * Comprueba validez de una dirección.
	 * @param direccion.
	 * @return true si cumple.
	 */
	private boolean direccionValida(String direccion) {
		return !direccion.matches("[ ]+");				// Que no sea en blanco.
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		assert correo != null;
		if (correoValido(correo)) {
			this.correo = correo;
		}
		// Todavía no se gestionan errores de usuario.
		if (this.correo == null) {						// Tiempo de construcción.
			this.correo = new Usuario().correo; 		// Defecto.
		}
	}

	/**
	 * Comprueba validez de un dirección de correo.
	 * @param correo.
	 * @return true si cumple.
	 */
	private boolean correoValido(String correo) {
		return !correo.matches("[ ]+"); 				// Que no sea en blanco.
	}

	public Calendar getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Calendar fechaNacimiento) {
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
	private boolean fechaNacimientoValida(Calendar fechaNacimiento) {
		return fechaNacimiento.before(new GregorianCalendar());
	}

	public Calendar getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Calendar fechaAlta) {
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
	private boolean fechaAltaValida(Calendar fechaAlta) {
		return !fechaAlta.after(new GregorianCalendar()); 	// Que no sea en blanco.
	}

	public String getClaveAcceso() {
		return claveAcceso;
	}

	public void setClaveAcceso(String claveAcceso) {
		assert claveAcceso != null;
		if (claveAccesoValida(claveAcceso)) {
			this.claveAcceso = encriptar(claveAcceso);
		}
		// Todavía no se gestionan errores de usuario.
		if (this.claveAcceso == null) {				  		// Tiempo de construcción.
			this.claveAcceso = new Usuario().claveAcceso; 	// Defecto.
		}
	}

	/**
	 * Comprueba validez de una clave de acceso.
	 * @param claveAcceso.
	 * @return true si cumple.
	 */
	private boolean claveAccesoValida(String claveAcceso) {
		return !claveAcceso.matches("[ ]+");   				// Que no sea en blanco.
	}
	
	/**
	 * Encripta una clave de acceso.
	 * @param claveAcceso.
	 * @return claveEncriptada.
	 */
	private String encriptar(String claveEnClaro) {
		// No encripta todavía.
		String claveEncriptada = claveEnClaro;
		return claveEncriptada;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		assert	rol != null;
		if (rolValido(rol)) {
			this.rol = rol;
		}
		// Todavía no se gestionan errores de usuario.
		if (this.rol == null) {							// Tiempo de construcción.
			this.rol = new Usuario().rol; 				// Defecto.
		}
	}

	/**
	 * Comprueba validez de un rol.
	 * @param rol.
	 * @return true si cumple.
	 */
	private boolean rolValido(String rol) {
		return ROLES[0].equals(rol) 
				|| ROLES[1].equals(rol)
				|| ROLES[2].equals(rol);
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
		"fechaNacimiento:", this.fechaNacimiento.get(Calendar.YEAR) + "." 
				+ this.fechaNacimiento.get(Calendar.MONTH) + "." 
				+ this.fechaNacimiento.get(Calendar.DATE),	
		"fechaAlta:", this.fechaAlta.get(Calendar.YEAR) + "." 
				+ this.fechaAlta.get(Calendar.MONTH) + "." 
				+ this.fechaAlta.get(Calendar.DATE), 
		"claveAcceso:", this.claveAcceso, 
		"rol:", this.rol
		);		
	}

} // class

