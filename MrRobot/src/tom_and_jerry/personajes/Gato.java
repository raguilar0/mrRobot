package tom_and_jerry.personajes;

import java.io.IOException;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.remote.nxt.NXTConnection;
import lejos.robotics.SampleProvider;

public class Gato extends Jugador {
	private SensorModes ojos;
	private static final Port PUERTO = LocalEV3.get().getPort("S2");
	private static final float MINIMA_DISTANCIA = 0.040f;

	
	public Gato() {
		super();
		
		ojos = new EV3UltrasonicSensor(PUERTO);

	}


	
	@Override
	protected boolean heGanado(){
		boolean gano = false;
		SampleProvider  distancia = ojos.getMode("Distance");
		float[] sample = new float[distancia.sampleSize()];
		distancia.fetchSample(sample, 0);
		
		if(sample[0] < MINIMA_DISTANCIA)
		{
			notificar(1);
			gano = true;
		}
		
		
		return gano;
	}
	
	@Override
	protected void notificar(int codigo)
	
	{
		try
		{
			salida.writeInt(codigo);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	protected int recibir()
	{
		int codigo = -1;
		try
		{
			codigo = entrada.readInt();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return codigo;
		
	}
	
	@Override
	protected boolean hePerdido() {
		boolean perdio = false;
		
		if(recibir() == 1)
		{
			perdio = true;
		}
		
		return perdio;
	}

	@Override
	public String obtenerNombreJugador() {
		return "Gato";
	}
}
