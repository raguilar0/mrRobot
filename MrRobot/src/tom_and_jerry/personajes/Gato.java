package tom_and_jerry.personajes;


import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class Gato implements Jugador {
    public Gato() {
    }

    @Override
    public boolean jugar() {
        return false;
    }
}
