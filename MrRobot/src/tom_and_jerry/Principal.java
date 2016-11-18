package tom_and_jerry;


import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import tom_and_jerry.personajes.Gato;
import tom_and_jerry.personajes.Jugador;
import tom_and_jerry.personajes.Raton;

public class Principal {
	
	public static void main(String[] args) throws InterruptedException {
		boolean esEV3Raton = true;
        Jugador personaje = (esEV3Raton) ? new Raton() : new Gato();

        // TODO: Si gana jugador mostrar en panatalla
        personaje.jugar();
	}
}