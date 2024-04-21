// AUTOR : JULIA GARCÍA VEGA

package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AlgoritmoA_Estrella {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<Dispensacion> lista_dispensaciones = new ArrayList<>();
		double cargaRestante = 100.0;
		ArrayList<Nodo> camino;

		Habitacion habObjetivo = new Habitacion("h1", 9, 3, 2);
		Dispensacion dis1 = new Dispensacion("nolotil", habObjetivo);
		lista_dispensaciones.add(dis1);
		
		Habitacion habOrigen = new Habitacion("Helipuerto", 0, 0, 8);

		for (Dispensacion dis : lista_dispensaciones) {
			
			System.out.println("\nProcedo a entregar desde la habitación " + habOrigen.getNombre() + 
					" ( " + habOrigen.getX() + ", " + habOrigen.getY() + ", " + habOrigen.getZ() + ") el medicamento "
					+ dis.getMedicamento() + " a la habitación " + dis.getHab().getNombre() + 
					" ( " + dis.getHab().getX() + ", " + dis.getHab().getY() + ", " + dis.getHab().getZ() + ")");
			
			camino = algoritmoA(habOrigen, dis, cargaRestante);
			if (camino == null) {
				System.err.println("No hay carga suficiente");
				System.exit(1);
			}

			System.out.println("-------------------------------------------");
			System.out.println("-------------- CAMINO FINAL ---------------");
			System.out.println("-------------------------------------------");

			for (int i = 0; i < camino.size(); i++) {
				System.out.println("Movimiento "+ (i+1) + " : Coordenada: ( " + camino.get(i).getX() + ", " + camino.get(i).getY() + ", "
						+ camino.get(i).getZ() + ")");
			}
			cargaRestante = cargaRestante - camino.get(camino.size() - 1).getCosteAcumulado() - 3;
			System.out.printf("\nCarga restante: %f\n", cargaRestante);
			habOrigen = dis.getHab();
		}
	}

	private static ArrayList<Nodo> obtenerCamino(Nodo nodoObjetivo) {

		Nodo nodo = nodoObjetivo;

		ArrayList<Nodo> camino = new ArrayList<>();

		while (nodo.getPredecesor() != null) {
			camino.add(nodo);
			nodo = nodo.getPredecesor();
		}
		camino.add(nodo);

		Collections.reverse(camino);
		return camino;

	}

	private static ArrayList<Nodo> algoritmoA(Habitacion habitacionOrigen, Dispensacion dis, double cargaRestante) {

		ArrayList<Nodo> abiertos = new ArrayList<Nodo>();
		ArrayList<Nodo> cerrados = new ArrayList<Nodo>();
		ArrayList<Nodo> sucesores;
		ArrayList<Nodo> camino;
		Nodo nodo;

		double costeActual, posibleCoste = 0;

		Nodo nodoObjetivo = new Nodo(dis.getHab().getX(), dis.getHab().getY(), dis.getHab().getZ());
		Nodo nodoOrigen = new Nodo(habitacionOrigen.getX(), habitacionOrigen.getY(), habitacionOrigen.getZ());

		nodoObjetivo.calcularDistanciaRestante(nodoObjetivo);

		nodoOrigen.setPredecesor(null);
		nodoOrigen.calcularDistanciaRestante(nodoObjetivo);
		nodoOrigen.setCosteAcumulado(0);
		nodoOrigen.calcularValorFuncionEvaluacion();
		abiertos.add(nodoOrigen);

		while (true) {
			if (abiertos.isEmpty()) {
				System.err.println("Error");
				System.exit(1);
			}

			nodo = abiertos.get(0);

			if (nodo.getCosteAcumulado() > (cargaRestante - 3))
				return null;

			abiertos.remove(0);
			cerrados.add(nodo);

			if (nodo.getX() == nodoObjetivo.getX() && nodo.getY() == nodoObjetivo.getY()
					&& nodo.getZ() == nodoObjetivo.getZ()) {
				camino = obtenerCamino(nodo);
				break;
			}

			if (nodo.getSucesores().isEmpty())
				nodo.CalcularSucesores();

			sucesores = nodo.getSucesores();

			for (Nodo sucesor : sucesores) {
				if (!contiene(cerrados, sucesor)) {

					if (nodo.getX() == (sucesor.getX() + 1) || nodo.getX() == (sucesor.getX() - 1)
							|| nodo.getY() == (sucesor.getY() + 1) || nodo.getY() == (sucesor.getY() - 1)) {
						posibleCoste = nodo.getCosteAcumulado() + 1;
					} else if (nodo.getZ() == (sucesor.getZ() + 1)) {
						posibleCoste = nodo.getCosteAcumulado() + 0.5;
					} else if (nodo.getZ() == (sucesor.getZ() - 1)) {
						posibleCoste = nodo.getCosteAcumulado() + 2;
					}

					if (contiene(abiertos, sucesor)) {

						sucesor = obtener(abiertos, sucesor);
						costeActual = sucesor.getCosteAcumulado();

						if (costeActual > posibleCoste) {

							abiertos.remove(sucesor);

							sucesor.setCosteAcumulado(posibleCoste);
							sucesor.setPredecesor(nodo);
							sucesor.calcularDistanciaRestante(nodoObjetivo);
							sucesor.calcularValorFuncionEvaluacion();

							abiertos.add(sucesor);
							abiertos.sort(Comparator.comparingDouble(Nodo::getValorFuncionEvaluacion));
						}
					} else {
						sucesor.setPredecesor(nodo);
						sucesor.calcularDistanciaRestante(nodoObjetivo);
						sucesor.setCosteAcumulado(posibleCoste);
						sucesor.calcularValorFuncionEvaluacion();
						abiertos.add(sucesor);
						abiertos.sort(Comparator.comparingDouble(Nodo::getValorFuncionEvaluacion));
					}
				}
			}
		}

		return camino;
	}

	private static boolean contiene(ArrayList<Nodo> lista, Nodo nodo) {
		for (Nodo n : lista) {
			if (nodo.getX() == n.getX() && nodo.getY() == n.getY() && nodo.getZ() == n.getZ())
				return true;
		}
		return false;
	}

	private static Nodo obtener(ArrayList<Nodo> lista, Nodo nodo) {
		for (Nodo n : lista) {
			if (nodo.getX() == n.getX() && nodo.getY() == n.getY() && nodo.getZ() == n.getZ())
				return n;
		}
		return null;
	}

}
