package main;

public class Habitacion {
	
	private String nombre;
	private int x;
	private int y;
	private int z;
	
	public Habitacion(String nombre, int x, int y, int z) {
		this.setNombre(nombre);
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	
}
