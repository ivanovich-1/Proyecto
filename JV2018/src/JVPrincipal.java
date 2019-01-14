/** 
Proyecto: Juego de la vida.
 * Implementa el control de inicio de sesión y ejecución de la simulación por defecto. 
 * En esta versión sólo se ha aplicado un diseño OO básico.
 *  Se pueden detectar varios defectos y antipatrones de diseño:
 *  	- Obsesión por los tipos primitivos.
 *      - Exceso de métodos estáticos.
 *  	- Clase acaparadora, que delega poca responsabilidad. 
 *  	- Clase demasiado grande.
 * @since: prototipo1.0
 * @source: JVPrincipal.java 
 * @version: 1.0 - 2018/11/21
 * @author: ajp
 */

import java.util.GregorianCalendar;
import java.util.Scanner;

public class JVPrincipal {

	static final int MAX_USUARIOS = 10;
	static final int MAX_SESIONES = 10;
	static final int MAX_INTENTOS_FALLIDOS = 3;
	static Usuario[] datosUsuarios = new Usuario[MAX_USUARIOS];
	static SesionUsuario[] datosSesiones = new SesionUsuario[MAX_SESIONES];
	static int sesionesRegistradas = 0;				// Control de sesiones registradas.
	static Usuario usrEnSesion;

	/**
	 * Secuencia principal del programa.
	 */
	public static void main(String[] args) {		
		
		cargarUsuariosPrueba();		
		mostrarTodosUsuarios();

		if (inicioSesionCorrecto()) {
			registrarSesion();	
			System.out.println("Sesión: " + sesionesRegistradas + '\n' + "Iniciada por: " 
					+ 	usrEnSesion.getNombre() + " " + usrEnSesion.getApellidos());	
			
			new Simulacion().lanzarDemo();
		}
		else {
			System.out.println("\nDemasiados intentos fallidos...");
		}		
		System.out.println("Fin del programa.");
	}

	/**
	 * Controla el acceso de usuario.
	 * @return true si la sesión se inicia correctamente.
	 */
	static boolean inicioSesionCorrecto() {
		Scanner teclado = new Scanner(System.in);	// Entrada por consola.
		int intentosPermitidos = MAX_INTENTOS_FALLIDOS;

		do {
			// Pide usuario y contraseña.
			System.out.print("Introduce el nif de usuario: ");
			String nif = teclado.nextLine();
			System.out.print("Introduce clave acceso: ");
			String clave = teclado.nextLine();

			// Busca usuario coincidente con las credenciales.
			usrEnSesion = buscarUsuario(nif);

			// Encripta clave tecleada utilizando un objeto temporal
			// que ejecutará automáticamente el método privado.
			Usuario aux = new Usuario();
			aux.setClaveAcceso(clave);
			clave = aux.getClaveAcceso();
			if (usrEnSesion != null && usrEnSesion.getClaveAcceso().equals(clave)) {
				return true;
			} else {
				intentosPermitidos--;
				System.out.print("Credenciales incorrectas: ");
				System.out.println("Quedan " + intentosPermitidos + " intentos... ");
			} 
		} while (intentosPermitidos > 0);

		return false;
	}

	/**
	 * Busca usuario dado su nif.
	 * @param idUsr - el nif del Usuario a buscar.
	 * @return - el Usuario encontrado o null si no existe.
	 */
	private static Usuario buscarUsuario(String idUsr) {
		// Busca usuario coincidente con la credencial.
		for (Usuario usr : datosUsuarios) {
			if (usr.getNif().equalsIgnoreCase(idUsr)) {
				return usr;	// Devuelve el usuario encontrado.
			}
		}
		return null;						// No encuentra.
	}

	/**
	 * Registro de la sesión de usuario.
	 */
	static void registrarSesion() {
		SesionUsuario sesion = new SesionUsuario();
		sesion.setUsr(usrEnSesion);
		sesion.setFecha(new GregorianCalendar());  // Hoy

		// Registra sesion de usuario a partir de la última posición ocupada.
		datosSesiones[sesionesRegistradas] = sesion;  
		sesionesRegistradas++; 							
	}

	/**
	 * Muestra por consola todos los usuarios almacenados.
	 */
	static void mostrarTodosUsuarios() {
		for (Usuario u: datosUsuarios) {
			System.out.println("\n" + u);
		}
	}

	/**
	 * Genera datos de prueba válidos dentro 
	 * del almacén de datos.
	 */
	static void cargarUsuariosPrueba() {	
		for (int i = 0; i < MAX_USUARIOS; i++) {
			datosUsuarios[i] = new Usuario("1234567" + i + "K", "Pepe", "López Pérez", "C/ Luna 27 30132 Murcia",
					"pepe" + i + "@gmail.com", new GregorianCalendar(1999, 11, 12), new GregorianCalendar(2018, 01, 03), 
					"Miau#" + i, Usuario.ROLES[0]);
		}
	}

} //class
