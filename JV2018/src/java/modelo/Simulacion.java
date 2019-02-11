/** 
 * Proyecto: Juego de la vida.
 * Organiza aspectos de gestión de la simulación según el modelo1.
 * En esta versión sólo se ha aplicado un diseño OO básico.
 * Se pueden detectar varios defectos y antipatrones de diseño:
 *  	- Obsesión por los tipos primitivos.
 *  	- Clase demasiado grande.
 *  	- Clase acaparadora, que delega poca responsabilidad.  
 * @since: prototipo1.0
 * @source: Simulacion.java 
 * @version: 1.1 - 2019.01.25
 * @author: ajp
 */

package modelo;

import accesoUsr.Presentacion;
import util.Fecha;

public class Simulacion {

	public static final int TAMAÑO_MUNDO = 18;
	private static final int CICLOS_SIMULACION = 20;
	private byte[][] mundo = new byte[TAMAÑO_MUNDO][TAMAÑO_MUNDO];
	
	private Usuario usr;
	private Fecha fecha;
	//Reglas simulación
	private int[] valoresSobrevivir;
	private int[] valoresRenacer;

	enum FormaEspacio { PLANO, ESFERICO }
	private static final FormaEspacio TIPO_MUNDO = FormaEspacio.PLANO;

	/**
	 * Constructor convencional.
	 * Establece el valor inicial de cada uno de los atributos.
	 * Recibe parámetros que se corresponden con los atributos.
	 * Utiliza métodos set... para la posible verificación.
	 * @param usr
	 * @param fecha
	 * @param mundo
	 */
	public Simulacion(Usuario usr, Fecha fecha, byte[][] espacioMundo) {
		setUsr(usr);
		setFecha(fecha);
		setMundo(espacioMundo);
		leyesEstandar();
	}

	private void leyesEstandar() {
		valoresSobrevivir = new int[] {2, 3};
		valoresRenacer = new int[] {3};
	}

	/**
	 * Constructor por defecto.
	 * Establece el valor inicial, por defecto, de cada uno de los atributos.
	 * Llama al constructor convencional de la propia clase.
	 */
	public Simulacion() {
		setUsr(new Usuario());
		setFecha(new Fecha());
		leyesEstandar();
	}

	/**
	 * Constructor copia.
	 * Establece el valor inicial de cada uno de los atributos a partir de
	 * los valores obtenidos de un objeto de su misma clase.
	 * El objeto Usuario es compartido (agregación).
	 * Llama al constructor convencional.
	 * @param s - la Simulacion a clonar
	 */
	public Simulacion(Simulacion s) {
		this(s.usr, (Fecha)s.fecha.clone(), new byte[s.mundo.length][s.mundo.length]);
	}

	public Usuario getUsr() {
		return usr;
	}

	public void setUsr(Usuario usr) {
		assert usr != null;
		this.usr = usr;
	}


	public byte[][] getMundo() {
		return mundo;
	}

	public void setMundo(byte[][] mundo) {
		assert mundo != null;
		this.mundo = mundo;
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		assert fecha != null;
		this.fecha = fecha;
	}

	public String getIdSimulacion() {	
		return this.usr.getIdUsr() + "-" + fecha.toStringMarcaTiempo();
	}
	
	@Override
	public String toString() {
		return String.format(
				"Simulacion [usr=%s, fecha=%s, mundo=%s]", usr, fecha, mundo);
	}

	/**
	 * Ejecuta una simulación del juego de la vida en la consola.
	 */
	public void lanzarDemo() {
		cargarMundoDemo();
		int generacion = 0; 
		do {
			System.out.println("\nGeneración: " + generacion);
			new Presentacion().mostrarMundo(this);
			actualizarMundo();
			generacion++;
		}
		while (generacion < CICLOS_SIMULACION);
	}

	/**
	 * Carga datos demo en la matriz que representa el mundo. 
	 */
	private void cargarMundoDemo() {
		// En este array los 0 indican celdas con células muertas y los 1 vivas.
		mundo = new byte[][] { 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 }, // 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 }, // 
			{ 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 1x Planeador
			{ 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 1x Flip-Flop
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 1x Still Life
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }  //
		};
	}

	/**
	 * Actualiza el estado del Juego de la Vida.
	 * Actualiza según la configuración establecida para la forma del espacio.
	 */
	private void actualizarMundo() {
		if (TIPO_MUNDO == FormaEspacio.PLANO) {
			actualizarMundoPlano();
		}
		if (TIPO_MUNDO == FormaEspacio.ESFERICO) {
			actualizarMundoEsferico();
		}
	}

	/**
	 * Actualiza el estado almacenado del Juego de la Vida.
	 * Las celdas periféricas son adyacentes con las del lado contrario.
	 * El mundo representado sería esférico cerrado sin límites para células de dos dimensiones.
	 */
	private void actualizarMundoEsferico()  {     					
		// Pendiente de implementar.
	}

	/**
	 * Actualiza el estado de cada celda almacenada del Juego de la Vida.
	 * Las celdas periféricas son los límites absolutos.
	 * El mundo representado sería plano, cerrado y con límites para células de dos dimensiones.
	 */
	private void actualizarMundoPlano()  {     					
		byte[][] nuevoEstado = new byte[TAMAÑO_MUNDO][TAMAÑO_MUNDO];

		for (int i = 0; i < TAMAÑO_MUNDO; i++) {
			for (int j = 0; j < TAMAÑO_MUNDO; j++) {
				int vecinas = 0;							
				vecinas += visitarCeldaNoroeste(i, j);		
				vecinas += visitarCeldaNorte(i, j);			// 		NO | N | NE
				vecinas += visitarCeldaNoreste(i, j);		//    	-----------
				vecinas += visitarCeldaEste(i, j);			// 		 O | * | E
				vecinas += visitarCeldaSureste(i, j);		// 	  	----------- 
				vecinas += visitarCeldaSur(i, j); 			// 		SO | S | SE
				vecinas += visitarCeldaSuroeste(i, j); 	  
				vecinas += visitarCeldaOeste(i, j);		          			                                     	

				actualizarCelda(nuevoEstado, i, j, vecinas);
			}
		}
		mundo = nuevoEstado;
	}

	/**
	 * Aplica las leyes del mundo a la celda indicada dada la cantidad de células adyacentes vivas.
	 * @param nuevoEstado
	 * @param fila
	 * @param col
	 * @param vecinas
	 */
	private void actualizarCelda(byte[][] nuevoEstado, int fila, int col, int vecinas) {	

		for (int valor : valoresRenacer) {
			if (vecinas == valor) {									// Pasa a estar viva.
				nuevoEstado[fila][col] = 1;
				return;
			}
		}

		for (int valor : valoresSobrevivir) {
			if (vecinas == valor && mundo[fila][col] == 1) {		// Permanece viva, si lo estaba.
				nuevoEstado[fila][col] = 1;
				return;
			}
		}
	}

	/**
	 * Obtiene el estado o valor de la celda vecina situada al Oeste de la indicada por la coordenada. 
	 * @param fila de la celda evaluada.
	 * @param col de la celda evaluada.
	 * @return el estado o valor de la celda Oeste.
	 */
	private byte visitarCeldaOeste(int fila, int col) {
		if (col-1 >= 0) {
			return mundo[fila][col-1]; 			// Celda O.
		}
		return 0;
	}

	/**
	 * Obtiene el estado o valor de la celda vecina situada al Suroeste de la indicada por la coordenada. 
	 * @param fila de la celda evaluada.
	 * @param col de la celda evaluada.
	 * @return el estado o valor de la celda Suroeste.
	 */
	private byte visitarCeldaSuroeste(int fila, int col) {
		if (fila+1 < TAMAÑO_MUNDO && col-1 >= 0) {
			return mundo[fila+1][col-1]; 		// Celda SO.
		}
		return 0;
	}

	/**
	 * Obtiene el estado o valor de la celda vecina situada al Sur de la indicada por la coordenada. 
	 * @param fila de la celda evaluada.
	 * @param col de la celda evaluada.
	 * @return el estado o valor de la celda Sur.
	 */
	private byte visitarCeldaSur(int fila, int col) {
		if (fila+1 < TAMAÑO_MUNDO) {
			return mundo[fila+1][col]; 			// Celda S.
		}
		return 0;
	}

	/**
	 * Obtiene el estado o valor de la celda vecina situada al Sureste de la indicada por la coordenada. 
	 * @param fila de la celda evaluada.
	 * @param col de la celda evaluada.
	 * @return el estado o valor de la celda Sureste.
	 */
	private byte visitarCeldaSureste(int fila, int col) {
		if (fila+1 < TAMAÑO_MUNDO && col+1 < TAMAÑO_MUNDO) {
			return mundo[fila+1][col+1]; 		// Celda SE.
		}
		return 0;
	}

	/**
	 * Obtiene el estado o valor de la celda vecina situada al Este de la indicada por la coordenada. 
	 * @param fila de la celda evaluada.
	 * @param col de la celda evaluada.
	 * @return el estado o valor de la celda Este.
	 */
	private byte visitarCeldaEste(int fila, int col) {
		if (col+1 < TAMAÑO_MUNDO) {
			return mundo[fila][col+1]; 			// Celda E.
		}
		return 0;
	}

	/**
	 * Obtiene el estado o valor de la celda vecina situada al Noreste de la indicada por la coordenada. 
	 * @param fila de la celda evaluada.
	 * @param col de la celda evaluada.
	 * @return el estado o valor de la celda Noreste.
	 */
	private byte visitarCeldaNoreste(int fila, int col) {
		if (fila-1 >= 0 && col+1 < TAMAÑO_MUNDO) {
			return mundo[fila-1][col+1]; 		// Celda NE.
		}
		return 0;
	}

	/**
	 * Obtiene el estado o valor de la celda vecina situada al NO de la indicada por la coordenada. 
	 * @param fila de la celda evaluada.
	 * @param col de la celda evaluada.
	 * @return el estado o valor de la celda NO.
	 */
	private byte visitarCeldaNorte(int fila, int col) {
		if (fila-1 >= 0) {
			return mundo[fila-1][col]; 			// Celda N.
		}
		return 0;
	}

	/**
	 * Obtiene el estado o valor de la celda vecina situada al Noroeste de la indicada por la coordenada. 
	 * @param fila de la celda evaluada.
	 * @param col de la celda evaluada.
	 * @return el estado o valor de la celda Noroeste.
	 */
	private byte visitarCeldaNoroeste(int fila, int col) {
		if (fila-1 >= 0 && col-1 >= 0) {
			return mundo[fila-1][col-1]; 		// Celda NO.
		}
		return 0;
	}

} //class
