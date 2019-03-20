/** Proyecto: Juego de la vida.
 *  Implementa el concepto de Persona según el modelo2.
 *  @since: prototipo2.0
 *  @source: Persona.java 
 *  @version: 2.0 - 2019/03/12 
 *  @author: ajp
 */

package modelo;

import util.Fecha;


public abstract class Persona {
	
	protected Nif nif;
	protected String nombre;
	protected String apellidos;
	protected DireccionPostal domicilio;
	protected Correo correo;
	protected Fecha fechaNacimiento;

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
	public Persona(Nif nif, String nombre, String apellidos,
			DireccionPostal domicilio, Correo correo, Fecha fechaNacimiento) 
					throws ModeloException {
		setNif(nif);
		setNombre(nombre);
		setApellidos(apellidos);
		setDomicilio(domicilio);
		setCorreo(correo);
		setFechaNacimiento(fechaNacimiento);
	}

	/**
	 * Constructor por defecto. Reenvía al constructor convencional.
	 * @throws ModeloException 
	 */
	public Persona() throws ModeloException {
		this(new Nif(), 
				"Nombre", 
				"Apellido Apellido", 
				new DireccionPostal(),
				new Correo(), 
				new Fecha());
	}

	/**
	 * Constructor copia.
	 * @param persona
	 */
	public Persona(Persona persona) {
		this.nif = new Nif(persona.nif);
		this.nombre = new String(persona.nombre);
		this.apellidos = new String(persona.apellidos);
		this.domicilio = new DireccionPostal(persona.domicilio);
		this.correo = new Correo(persona.correo);
		this.fechaNacimiento = new Fecha(persona.fechaNacimiento.getAño(), 
				persona.fechaNacimiento.getMes(), persona.fechaNacimiento.getDia());
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

	public void setNombre(String nombre) throws ModeloException {	
		assert nombre != null;
		if (nombreValido(nombre)) {
			this.nombre = nombre;
		}
		else {
			throw new ModeloException("Nombre Usuario: Formato no válido.");
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
			throw new ModeloException("Apellidos Usuario: Formato no válido.");
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
			throw new ModeloException("Fecha nacimiento Usuario: no válida.");
		}
	}

	/**
	 * Comprueba validez de una fecha de nacimiento.
	 * @param fechaNacimiento.
	 * @return true si cumple.
	 */
	protected abstract boolean fechaNacimientoValida(Fecha fechaNacimiento);

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
			+ "%-16s %s\n",
			"nif:", nif, 
			"nombre:", this.nombre, 
			"apellidos:", this.apellidos,  
			"domicilio:", this.domicilio, 
			"correo:", this.correo, 
			"fechaNacimiento:", this.fechaNacimiento.getAño() + "." 
				+ this.fechaNacimiento.getMes() + "." 
				+ this.fechaNacimiento.getDia()	
		);		
	}
	
} // class

