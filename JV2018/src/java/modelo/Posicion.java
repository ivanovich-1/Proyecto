/** 
 * Proyecto: Juego de la vida.
 * Coordenada de una celda del espacio según el modelo1.1.
 * @since: prototipo1.2
 * @source: Posicion.java 
 * @version: 1.2 - 2019.02.25
 * @author: ajp
 */

package modelo;

public class Posicion {
	private int x;
	private int y;
	
	public Posicion(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Posicion() {
		this(0, 0);
	}

	public Posicion(Posicion posicion) {
		this.x = posicion.x;
		this.y = posicion.y;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) throws ModeloException {
		if (coordenadaValida(x)) {
			this.x = x;
		}
		else {
			throw new ModeloException("Posicion: coordenada negativa");
		}
	}
	
	private boolean coordenadaValida(int c) {
		return c >= 0;
	}

	public int getY() {
		return y;
	}
	
	public void setY(int y) throws ModeloException {
		if (coordenadaValida(y)) {
			this.y = y;
		}
		else {
			throw new ModeloException("Posicion: coordenada negativa");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posicion other = (Posicion) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	public Posicion clone() {
		return new Posicion(this);
	}

	@Override
	public String toString() {
		return String.format("Posicion [x=%s, y=%s]", x, y);
	}
	
} // class
