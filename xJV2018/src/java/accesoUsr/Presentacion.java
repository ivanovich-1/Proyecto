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
import accesoDatos.DatosException;
import modelo.ClaveAcceso;
import modelo.ModeloException;
import modelo.Simulacion;
import modelo.Usuario;
import util.Fecha;

public class Presentacion {

	public static final int MAX_INTENTOS_FALLIDOS = 3;
	private Datos datos;
	private Usuario usrEnSesion;
	private Simulacion simulacion;

	public Presentacion() throws ModeloException, DatosException  {
		datos = new Datos();
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
	 * @throws ModeloException 
	 */
	public void mostrarSimulacion() throws ModeloException {
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
			try {
				clave = new ClaveAcceso();
				clave = new ClaveAcceso(teclado.nextLine());

				// Busca usuario coincidente con las credenciales.

				usrEnSesion = datos.obtenerUsuario(id);

				// Encripta clave tecleada utilizando un objeto temporal
				// que ejecutará automáticamente el método privado.
				Usuario aux = new Usuario();
				aux.setClaveAcceso(new ClaveAcceso(clave));
				clave = aux.getClaveAcceso();
			} 
			catch (ModeloException e) { 	
				//System.out.println(e.getMessage());
				//e.printStackTrace();
			}
			if (usrEnSesion != null && usrEnSesion.getClaveAcceso().equals(clave)) {
				simulacion = datos.obtenerSimulacion("III1R-" + new Fecha(0001, 01, 01, 01, 01, 01).toStringMarcaTiempo());
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
