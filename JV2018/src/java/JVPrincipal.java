/** 
Proyecto: Juego de la vida.
 * Implementa el control de inicio de sesión y ejecución de la simulación por defecto.
 * Todavía resulta demasiado grande.
 * @since: prototipo1.0
 * @source: JVPrincipal.java 
 * @version: 1.2 - 2019/03/05
 * @author: ajp
 */

import accesoDatos.Datos;
import accesoDatos.DatosException;
import accesoUsr.Presentacion;
import modelo.ModeloException;
import modelo.SesionUsuario;
import util.Fecha;

public class JVPrincipal {

	private Datos datos;
	private Presentacion interfazUsr;

	/**
	 * Secuencia principal del programa.
	 * @throws ModeloException 
	 * @throws DatosException 
	 */
	public static void main(String[] args) throws ModeloException, DatosException {		
			JVPrincipal jv = new JVPrincipal();
			jv.datos = new Datos();
			jv.datos.cargarUsuariosPrueba();		
			jv.datos.mostrarTodosUsuarios();
			jv.datos.cargarMundoDemo();

			jv.interfazUsr = new Presentacion();
			if (jv.interfazUsr.inicioSesionCorrecto()) {

				SesionUsuario sesion = new SesionUsuario();
				sesion.setUsr(jv.interfazUsr.getUsrEnSesion());
				sesion.setFecha(new Fecha()); 
				jv.datos.altaSesion(sesion);

				jv.interfazUsr.getSimulacion().setUsr(jv.interfazUsr.getUsrEnSesion());
				jv.interfazUsr.getSimulacion().setFecha(new Fecha());
				jv.interfazUsr.getSimulacion().setMundo(jv.datos.buscarMundo("Demo1"));
				jv.datos.altaSimulacion(jv.interfazUsr.getSimulacion());

				System.out.println("Sesión: " + jv.datos.getSesionesRegistradas() + '\n' + "Iniciada por: " 
						+ 	jv.interfazUsr.getUsrEnSesion().getNombre() + " " 
						+ jv.interfazUsr.getUsrEnSesion().getApellidos());	

				jv.interfazUsr.mostrarSimulacion();
			}
			else {
				System.out.println("\nDemasiados intentos fallidos...");
			}		
			System.out.println("Fin del programa.");
	}


} //class
