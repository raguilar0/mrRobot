package tom_and_jerry.personajes;


import tom_and_jerry.sensor_color.Color;

public class Raton extends Jugador {
	private Color colorAreaMeta;
	
	public Raton() {
		super();
		colorAreaMeta = new Color(0.32f, 0.39f);
	}

	@Override
	protected boolean heGanado() {
		return colorAreaMeta.estaEnRango(obtieneColor.obtenerMuestra());
	}

	@Override
	protected boolean hePerdido() {
		// TODO Comunicarse con Gato
		return false;
	}
}
