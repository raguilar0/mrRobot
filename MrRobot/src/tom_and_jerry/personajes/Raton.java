package tom_and_jerry.personajes;


import java.io.IOException;

import lejos.remote.nxt.NXTConnection;
import tom_and_jerry.sensor_color.Color;

public class Raton extends Jugador {
	private Color colorAreaMeta;

	
	public Raton() {
		super();
		colorAreaMeta = new Color(0.01f, 0.05f);
	}

	@Override
	protected boolean heGanado() {
		boolean gano = colorAreaMeta.estaEnRango(obtieneColor.obtenerMuestra());
		
		if(gano)
		{
			notificar(1);
		}
		
		return gano;
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
	public String obtenerNombreJugador() {
		return "Raton";
	}
}
