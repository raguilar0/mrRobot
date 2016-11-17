package proyectov1;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class Gato {
	static Wheel leftWheel = WheeledChassis.modelWheel(Motor.A, 5).offset(6).gearRatio(1);
	static Wheel rightWheel = WheeledChassis.modelWheel(Motor.B, 5).offset(-6).gearRatio(1);
	static Chassis chassis = new WheeledChassis(new Wheel[]{leftWheel, rightWheel}, WheeledChassis.TYPE_DIFFERENTIAL);
	static MovePilot gato = new MovePilot(chassis);
	EV3ColorSensor sensorColor = new EV3ColorSensor(SensorPort.S4);
	
	public Gato(){
		
	}
	
	public void buscar(){
		
	}

}
