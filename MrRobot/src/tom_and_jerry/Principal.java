package tom_and_jerry;


import lejos.hardware.Bluetooth;
import lejos.remote.nxt.NXTCommConnector;
import lejos.remote.nxt.NXTConnection;
import tom_and_jerry.personajes.Gato;
import tom_and_jerry.personajes.Jugador;
import tom_and_jerry.personajes.Raton;

public class Principal {
    public static void main(String[] args) throws InterruptedException {
        boolean esEV3Raton = true;
        Jugador personaje = (esEV3Raton) ? new Raton() : new Gato();
        NXTCommConnector connector = Bluetooth.getNXTCommConnector();
        NXTConnection conexion;
        
        if(esEV3Raton)
        {
        	  conexion = connector.waitForConnection(3000, 1);
        }
        else
        {        	
        	conexion = connector.connect("raton", 0);
        }
        
        personaje.setConexion(conexion);
        
        
        
        if(personaje.jugar()) {
        	System.out.println("Gano " + personaje.obtenerNombreJugador());
        	Thread.sleep(3000);
        }
        System.exit(0);
    }
}