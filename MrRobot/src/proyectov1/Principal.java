package proyectov1;

import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class Principal {
	
	public static void main(String[] args) throws InterruptedException {
		Raton mRaton = new Raton();
		//Gato mGato = new Gato();
		Controlador controladorRaton = new Controlador(mRaton);
		//Controlador controladorGato = new Controlador (mGato);
		System.exit(0);

	}

}