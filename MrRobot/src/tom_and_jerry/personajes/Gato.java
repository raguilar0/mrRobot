package tom_and_jerry.personajes;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class Gato extends Jugador {
	private SensorModes ojos;
	private static final Port PUERTO = LocalEV3.get().getPort("S2");
	private static final int MINIMA_DISTANCIA = 10;
	
	
	public Gato() {
		super();
		
		ojos = new EV3UltrasonicSensor(PUERTO);
	}

	@Override
	protected boolean heGanado() {
		SampleProvider  distancia = ojos.getMode("Distance");
		float[] sample = new float[distancia.sampleSize()];
		distancia.fetchSample(sample, 0);
		
		return (sample[0] < MINIMA_DISTANCIA);
	}

	@Override
	protected boolean hePerdido() {
		// TODO Comunicarse con Raton
		return false;
	}
}
