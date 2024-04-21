package main;

import java.util.ArrayList;

public class Nodo {
	
	private int x;
	private int y;
	private int z;
	private double costeAcumulado;
	private int distanciaRestante;
	private double valorFuncionEvaluacion;
	
	private ArrayList<Nodo> sucesores; 
	private Nodo predecesor;
	
	public Nodo(int x, int y, int z) {
		this.setX(x);
		this.setY(y);
		this.setZ(z);
		this.costeAcumulado = Double.POSITIVE_INFINITY;
		sucesores = new ArrayList<>(); 
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

	public double getCosteAcumulado() {
		return costeAcumulado;
	}

	public void setCosteAcumulado(double costeAcumulado) {
		this.costeAcumulado = costeAcumulado;
	}

	public int getDistanciaRestante() {
		return distanciaRestante;
	}

	public void calcularDistanciaRestante(Nodo nodoObjetivo) {
		int d = Math.abs(nodoObjetivo.x-this.x)+Math.abs(nodoObjetivo.y-this.y)+Math.abs(nodoObjetivo.z-this.z);
		this.distanciaRestante = d;
	}
	
	public void calcularValorFuncionEvaluacion() {
		this.valorFuncionEvaluacion = costeAcumulado + distanciaRestante;
	}
	
	public double getValorFuncionEvaluacion() {
		return valorFuncionEvaluacion;
	}

	public ArrayList<Nodo> getSucesores() {
		return sucesores;
	}
	
	public void CalcularSucesores() {
		Nodo Dx1, Dx2, Dy1, Dy2, Dz1, Dz2;
		
		Dx1 = new Nodo (x+1,y,z);
		Dx2 = new Nodo (x-1,y,z);
		Dy1 = new Nodo (x,y+1,z);
		Dy2 = new Nodo (x,y-1,z);
		Dz1 = new Nodo (x,y,z+1);
		Dz2 = new Nodo (x,y,z-1);
		
		//System.out.printf(" Compruebo seguridad Coordenada (%d, %d, %d)\n", Dx1.getX(),Dx1.getY(),Dx1.getZ());
		if(comprobarSeguridad(Dx1)) {
			sucesores.add(Dx1);
		}
		//System.out.printf(" Compruebo seguridad Coordenada (%d, %d, %d)\n", Dx2.getX(),Dx2.getY(),Dx2.getZ());
		if(comprobarSeguridad(Dx2)) {
			sucesores.add(Dx2);
		}
		//System.out.printf(" Compruebo seguridad Coordenada (%d, %d, %d)\n", Dy1.getX(),Dy1.getY(),Dy1.getZ());
		if(comprobarSeguridad(Dy1)) {
			sucesores.add(Dy1);
		}
		//System.out.printf(" Compruebo seguridad Coordenada (%d, %d, %d)\n", Dy2.getX(),Dy2.getY(),Dy2.getZ());
		if(comprobarSeguridad(Dy2)) {
			sucesores.add(Dy2);
		}
		//System.out.printf(" Compruebo seguridad Coordenada (%d, %d, %d)\n", Dz1.getX(),Dz1.getY(),Dz1.getZ());
		if(comprobarSeguridad(Dz1)) {
			sucesores.add(Dz1);
		}
		//System.out.printf(" Compruebo seguridad Coordenada (%d, %d, %d)\n", Dz2.getX(),Dz2.getY(),Dz2.getZ());
		if(comprobarSeguridad(Dz2)) {
			sucesores.add(Dz2);
		}
	}
	
	public boolean comprobarSeguridad(Nodo nodo) {
		/*if (nodo.getX()<-6 || nodo.getX()>17) {
			//System.out.println("Coordenadas x inválidas");
			return false;
		}
		if (nodo.getY()<0 || nodo.getY()>7) {
			//System.out.println("Coordenadas y inválidas");
			return false;
		
		}*/
		
		//Obstáculo: suelo
		if (nodo.getZ()<0) {
			//System.out.println("Coordenadas z inválidas");
			return false;
		}
		
		//Obstáculo: edificios alas
		if ( (nodo.getX()%5 ==(-4) || nodo.getX()%5 == 0 || nodo.getX()%5 == 1) && 
				(nodo.getY()>=1 && nodo.getY()<=6) && nodo.getZ()<=7) {
			//System.out.println("Hay un edificio");
			return false;
		}
		
		//Obstáculo: edificio central
		if ( (nodo.getX()>=-7 && nodo.getX()<=16) && nodo.getY()==0 && nodo.getZ()<=7) {
			//System.out.println("Hay un edificio");
			return false;
		}
		
		return true;
	}

	public Nodo getPredecesor() {
		return predecesor;
	}

	public void setPredecesor(Nodo predecesor) {
		this.predecesor = predecesor;
	}
	
}
