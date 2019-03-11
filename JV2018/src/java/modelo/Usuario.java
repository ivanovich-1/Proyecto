/** Proyecto: Juego de la vida.
 *  Implementa el concepto de Usuario según el modelo1.2
 *  En esta versión se ha revisado el diseño OO previo.
 *  @since: prototipo1.0
 *  @source: Usuario.java 
 *  @version: 1.2 - 2019/03/05 
 *  @author: ajp
 */

package modelo;

import util.Fecha;

public class Usuario {

	public enum RolUsuario {INVITADO, NORMAL, ADMINSTRADOR};

	static final int EDAD_MINIMA = 16;
	private String id;
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
	 * @throws ModeloException 
	 */
	public Usuario(Nif nif, String nombre, String apellidos,DireccionPostal domicilio, 
			Correo correo, Fecha fechaNacimiento, Fecha fechaAlta, 
			ClaveAcceso claveAcceso, RolUsuario rol) throws ModeloException {
		setNif(nif);
		setNombre(nombre);
		setApellidos(apellidos);
		setDomicilio(domicilio);
		setCorreo(correo);
		setFechaNacimiento(fechaNacimiento);
		setFechaAlta(fechaAlta);
		setClaveAcceso(claveAcceso);
		setRol(rol);
		generarIdUsr();
	}

	/**
	 * Constructor por defecto. Reenvía al constructor convencional.
	 * @throws ModeloException 
	 */
	public Usuario() throws ModeloException {
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
	 * genera siempre una variante del id.
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
		this.id = new String(usr.id);
		generarVarianteIdUsr();
	}

	/**
	 * Genera un identificador sintético a partir de:
	 * La letra inicial del nombre, 
	 * Las dos iniciales del primer y segundo apellido,
	 * Los dos último caracteres del nif
	 */
	private void generarIdUsr() {
		assert this.nif != null;
		assert this.nombre != null;
		assert this.apellidos != null;
		String[] apellidos = this.apellidos.split(" ");
		this.id = ""+ this.nombre.charAt(0) 
		+ apellidos[0].charAt(0) + apellidos[1].charAt(0)
		+ this.nif.getTexto().substring(7);
	}

	/**
	 * Genera una variante cambiando la última letra del idUsr 
	 * por la siguiente en el alfabeto previsto para el nif.
	 */
	private void generarVarianteIdUsr() {
		String alfabetoNif = "ABCDEFGHJKLMNPQRSTUVWXYZ";
		String alfabetoNifDesplazado = "BCDEFGHJKLMNPQRSTUVWXYZA";
		this.id = this.id.substring(0, 4) 
				+ alfabetoNifDesplazado.charAt(alfabetoNif.indexOf(id.charAt(4)));
	}

	public String getId() {
		return this.id;
	}

	public Nif getNif() {
		return this.nif;
	}

	public void setNif(Nif nif) {
		assert nif != null;	
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) throws ModeloException {	
		assert nombre != null;
		if (nombreValido(nombre)) {
			this.nombre = nombre;
		}
		else {
			if (this.nombre == null) {						// En tiempo de constructor.	
				this.nombre = new Usuario().getNombre();	// Valor por defecto.
			}
			throw new ModeloException("Usuario: formato nombre no válido.");
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

	public void setApellidos(String apellidos) throws ModeloException {
		assert apellidos != null;
		if (apellidosValidos(apellidos)) {
			this.apellidos = apellidos;
		}
		else {
			if (this.apellidos == null) {						// En tiempo de constructor.	
				this.apellidos = new Usuario().getApellidos();	// Valor por defecto.
			}
			throw new ModeloException("Usuario: formato apellidos no válidos.");
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

	public void setFechaNacimiento(Fecha fechaNacimiento) throws ModeloException {
		assert fechaNacimiento != null;
		if (fechaNacimientoValida(fechaNacimiento)) {
			this.fechaNacimiento = fechaNacimiento;
		}
		else {
			if (this.fechaNacimiento == null) {						    	// En tiempo de constructor.	
				this.fechaNacimiento = new Usuario().getFechaNacimiento();	// Valor por defecto.
			}
			throw new ModeloException("Usuario: fecha nacimiento no válida.");
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

	public void setFechaAlta(Fecha fechaAlta) throws ModeloException {
		assert fechaAlta != null;
		if (fechaAltaValida(fechaAlta)) {
			this.fechaAlta = fechaAlta;
		}
		else {
			if (this.fechaAlta == null) {						// En tiempo de constructor.	
				this.fechaAlta = new Usuario().getFechaAlta();	// Valor por defecto.
			}
			throw new ModeloException("Usuario: fecha alta no válida.");
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
	 * Reproduce el estado -valores de atributos- de objeto en forma de texto. 
	 * @return el texto formateado.  
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
						+ "%-16s %s\n"
						+ "%-16s %s\n",
						"id: ", id,
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
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((claveAcceso == null) ? 0 : claveAcceso.hashCode());
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
		result = prime * result + ((domicilio == null) ? 0 : domicilio.hashCode());
		result = prime * result + ((fechaAlta == null) ? 0 : fechaAlta.hashCode());
		result = prime * result + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nif == null) ? 0 : nif.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((rol == null) ? 0 : rol.hashCode());
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
			if (id.equals(((Usuario)obj).id) 
					&& nif.equals(((Usuario)obj).nif) 
					&& nombre.equals(((Usuario)obj).nombre) 
					&& apellidos.equals(((Usuario)obj).apellidos)  
					&& domicilio.equals(((Usuario)obj).domicilio) 
					&& correo.equals(((Usuario)obj).correo) 
					&& fechaNacimiento.equals(((Usuario)obj).fechaNacimiento) 
					&& fechaAlta.equals(((Usuario)obj).fechaAlta) 
					&& claveAcceso.equals(((Usuario)obj).claveAcceso) 
					&& rol.equals(((Usuario)obj).rol) 
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
	public Usuario clone() {
		return new Usuario(this);
	}
	
} // class

