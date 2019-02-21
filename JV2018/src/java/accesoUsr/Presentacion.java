/** 
 * Proyecto: Juego de la vida.
 * Implementa los mecanismos de interaccion con el usuario. 
 * @since: prototipo1.1
 * @source: Presentacion.java 
 * @version: 1.2 - 2019/02/13
 * @author: ajp
 */

package accesoUsr;

import java.util.Scanner;

import accesoDatos.Datos;
import modelo.ClaveAcceso;
import modelo.Nif;
import modelo.Simulacion;
import modelo.Usuario;

public class Presentacion {

	public static final int MAX_INTENTOS_FALLIDOS = 3;
	private Usuario usrEnSesion;
	private static Datos datos = new Datos();
	
	public Usuario getUsrEnSesion() {
		return this.usrEnSesion;
	}
	
	/**
	 * Despliega en la consola el estado almacenado, corresponde
	 * a una generación del Juego de la vida.
	 */
	public void mostrarMundo(Simulacion simulacion) {
		
		for (int i = 0; i < simulacion.getMundo().length; i++) {
			for (int j = 0; j < simulacion.getMundo().length; j++) {		
				System.out.print((simulacion.getMundo()[i][j] == 1) ? "|o" : "| ");
			}
			System.out.println("|");
		}
	}

	/**
	 * Controla el acceso de usuario.
	 * @return true si la sesión se inicia correctamente.
	 */
	public boolean inicioSesionCorrecto() {
		Scanner teclado = new Scanner(System.in);	// Entrada por consola.
		int intentosPermitidos = MAX_INTENTOS_FALLIDOS;

		do {
			// Pide usuario y contraseña.
			System.out.print("Introduce el id de usuario: ");
			String id = teclado.nextLine().toUpperCase();
			System.out.print("Introduce clave acceso: ");
			ClaveAcceso clave = new ClaveAcceso(teclado.nextLine());

			// Busca usuario coincidente con las credenciales.
			usrEnSesion = datos.buscarUsuario(id);

			// Encripta clave tecleada utilizando un objeto temporal
			// que ejecutará automáticamente el método privado.
			Usuario aux = new Usuario();
			aux.setClaveAcceso(new ClaveAcceso(clave));
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

} //class
