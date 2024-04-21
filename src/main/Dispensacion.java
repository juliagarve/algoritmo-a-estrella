package main;

public class Dispensacion {

	private String medicamento;
	private Habitacion hab ;
	
	public Dispensacion(String medicamento, Habitacion hab) {
		this.setMedicamento(medicamento);
		this.setHab(hab);
	}

	public String getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}

	public Habitacion getHab() {
		return hab;
	}

	public void setHab(Habitacion hab) {
		this.hab = hab;
	}
}
