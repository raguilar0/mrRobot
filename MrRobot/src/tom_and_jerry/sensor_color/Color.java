package tom_and_jerry.sensor_color;

public class Color {
	private float limiteInferior;
	private float limiteSuperior;
	
	public Color(float limiteInferior, float limteSuperior) {
		this.limiteInferior = limiteInferior;
		this.limiteSuperior = limteSuperior;
	}
	
	public boolean estaEnRango(float muestra) {
		return ( (limiteInferior < muestra) && (limiteSuperior > muestra));
	}
}
