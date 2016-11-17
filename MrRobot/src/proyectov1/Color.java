package proyectov1;

public class Color {
	float limiteInferior;
	float limiteSuperior;
	
	public Color(float limiteInferior, float limteSuperior) {
		this.limiteInferior = limiteInferior;
		this.limiteSuperior = limteSuperior;
	}
	
	public boolean estaEnRango(float muestra) {
		return ( (limiteInferior < muestra) && (limiteSuperior > muestra));
	}
}
