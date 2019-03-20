/** Proyecto: Juego de la vida.
 *  Implementa el concepto de Usuario según el modelo2.
 *  @since: prototipo2.0
 *  @source: Usuario.java 
 *  @version: 2.0 - 2019/03/14
 *  @author: ajp
 */

package modelo;

import config.Configuracion;
import util.Fecha;


public class Usuario extends Persona {
	
	public enum RolUsuario {INVITADO, NORMAL, ADMINISTRADOR};
	private String id;
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
	public Usuario(Nif nif, String nombre, String apellidos,
			DireccionPostal domicilio, Correo correo, Fecha fechaNacimiento,
			Fecha fechaAlta, ClaveAcceso claveAcceso, RolUsuario rol) 
					throws ModeloException {
		super(nif, nombre, apellidos,
				domicilio, correo, fechaNacimiento);
		setFechaAlta(fechaAlta);
		setClaveAcceso(claveAcceso);
		setRol(rol);
		generarId();
		
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
				new Fecha().addAños(-Integer.parseInt(Configuracion.get().getProperty("usuario.edadMinima"))), 
				new Fecha(), 
				new ClaveAcceso(), 
				RolUsuario.NORMAL);
	}

	/**
	 * Constructor copia.
	 * @param usr
	 */
	public Usuario(Usuario usr) {
		super(usr);
		this.id = new String(usr.id);
		this.fechaAlta = new Fecha(usr.fechaAlta.getAño(), 
				usr.fechaAlta.getMes(), usr.fechaAlta.getDia());
		this.claveAcceso = new ClaveAcceso(usr.claveAcceso);
		this.rol = usr.rol;
		this.generarVarianteIdUsr();
		
	}
	
	/**
	 * Genera un identificador sintético a partir de:
	 * La letra inicial del nombre, 
	 * Las dos iniciales del primer y segundo apellido,
	 * Los dos último caracteres del nif.
	 */
	private void generarId() {
		assert this.nif != null;
		assert this.nombre != null;
		assert this.apellidos != null;
		String[] apellidos = this.apellidos.split(" ");
		this.id =  ""+ this.nombre.charAt(0) 
				+ apellidos[0].charAt(0) + apellidos[1].charAt(0)
				+ this.nif.getTexto().substring(7);
	}
	
	/**
	 * Genera una variante cambiando la última letra del idUsr 
	 * por la siguiente en el alfabeto previsto para el nif.
	 * @param idUsr
	 */
	private void generarVarianteIdUsr() {
		String alfabetoNif = "ABCDEFGHJKLMNPQRSTUVWXYZ";
		String alfabetoNifDesplazado = "BCDEFGHJKLMNPQRSTUVWXYZA";
		this.id = this.id.substring(0, 4) 
				+ alfabetoNifDesplazado.charAt(alfabetoNif.indexOf(id.charAt(4)));
	}
	
	public String getId() {
		return id;
	}

	/**
	 * Comprueba validez de una fecha de nacimiento.
	 * @param fechaNacimiento.
	 * @return true si cumple.
	 */
	@Override
	protected boolean fechaNacimientoValida(Fecha fechaNacimiento) {
		return !fechaNacimiento.after(new Fecha().addAños(
				-Integer.parseInt(Configuracion.get().getProperty("usuario.edadMinima"))));
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
			throw new ModeloException("Fecha alta Usuario: no válida.");
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
		return super.toString() + String.format(
				"%-16s %s\n"
		+ "%-16s %s\n"
		+ "%-16s %s\n"
		+ "%-16s %s\n",
		"id:", id,	
		"fechaAlta:", this.fechaAlta.getAño() + "." 
				+ this.fechaAlta.getMes() + "." 
				+ this.fechaAlta.getDia(), 
		"claveAcceso:", this.claveAcceso, 
		"rol:", this.rol
		);		
	}

	public Usuario clone() {
		return new Usuario(this);
	}
	
} // class

