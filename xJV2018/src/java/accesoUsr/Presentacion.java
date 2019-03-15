/** 
 * Proyecto: Juego de la vida.
 * Implementa los mecanismos de interaccion con el usuario.
 *  Tiene defectos:
 * - Clase acaparadora que realiza tareas diversas.
 * - Está demasiado acoplada la lógica con la tecnología de E/S de usuario.
 * @since: prototipo1.1
 * @source: Presentacion.java 
 * @version: 1.2 - 2019/02/13
 * @author: ajp
 */

package accesoUsr;

import java.util.Scanner;

import accesoDatos.Datos;
import modelo.ClaveAcceso;
import modelo.ModeloException;
import modelo.Simulacion;
import modelo.Usuario;

public class Presentacion {

	private static Datos datos = new Datos();
	public static final int MAX_INTENTOS_FALLIDOS = 3;
	private Usuario usrEnSesion;
	private Simulacion simulacion;

	public Presentacion() throws ModeloException  {
		simulacion = new Simulacion();
	}

	public Usuario getUsrEnSesion() {
		return this.usrEnSesion;
	}

	public Simulacion getSimulacion() {
		return this.simulacion;
	}

	/**
	 * Despliega en la consola el estado almacenado, corresponde
	 * a una generación del Juego de la vida.
	 */
	public void mostrarSimulacion() {
		int generacion = 0; 
		do {
			System.out.println("\nGeneración: " + generacion);
			simulacion.getMundo().actualizarMundo();
			generacion++;
			System.out.println(simulacion.getMundo().toStringEstadoMundo());
		}
		while (generacion < simulacion.CICLOS_SIMULACION);
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
			ClaveAcceso clave = null;
			Usuario aux = null;
			try {
				clave = new ClaveAcceso("Miau#0");
				clave.setTexto(teclado.nextLine());

				// Busca usuario coincidente con las credenciales.
				usrEnSesion = datos.buscarUsuario(id);

				// Encripta clave tecleada utilizando un objeto temporal
				// que ejecutará automáticamente el método privado.
				aux = new Usuario();
			} 
			catch (ModeloException e) {
				System.out.println(e.getMessage());
				//e.printStackTrace();
			}

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

} //class
