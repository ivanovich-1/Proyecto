/** 
Proyecto: Juego de la vida.
 * Implementa el control de inicio de sesión y ejecución de la simulación por defecto. 
 * @since: prototipo1.0
 * @source: JVPrincipal.java 
 * @version: 1.2 - 2019/02/25
 * @author: ajp
 */

import accesoDatos.Datos;
import accesoUsr.Presentacion;
import modelo.SesionUsuario;
import util.Fecha;

public class JVPrincipal {

	private static Datos datos = new Datos();
	private static Presentacion interfazUsr = new Presentacion();
	
	/**
	 * Secuencia principal del programa.
	 */
	public static void main(String[] args) {		
		
		datos.cargarUsuariosPrueba();		
		datos.mostrarTodosUsuarios();

		if (interfazUsr.inicioSesionCorrecto()) {
			SesionUsuario sesion = new SesionUsuario();
			sesion.setUsr(interfazUsr.getUsrEnSesion());
			sesion.setFecha(new Fecha());  		// Hoy
			
			datos.altaSesion(sesion);	
			
			System.out.println("Sesión: " + datos.getSesionesRegistradas() + '\n' + "Iniciada por: " 
					+ 	interfazUsr.getUsrEnSesion().getNombre() + " " 
					+ interfazUsr.getUsrEnSesion().getApellidos());	
			
			interfazUsr.mostrarSimulacion();
		}
		else {
			System.out.println("\nDemasiados intentos fallidos...");
		}		
		System.out.println("Fin del programa.");
	}


} //class
