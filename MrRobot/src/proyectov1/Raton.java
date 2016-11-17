package proyectov1;


import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;

public class Raton {
	static Wheel leftWheel = WheeledChassis.modelWheel(Motor.A, 5).offset(6).gearRatio(1);
	static Wheel rightWheel = WheeledChassis.modelWheel(Motor.B, 5).offset(-6).gearRatio(1);
	static Chassis chassis = new WheeledChassis(new Wheel[]{leftWheel, rightWheel}, WheeledChassis.TYPE_DIFFERENTIAL);
	static MovePilot mrRobot = new MovePilot(chassis);
	EV3ColorSensor sensorColor = new EV3ColorSensor(SensorPort.S4);
	
	float limiteInferior = (float) 0.17;
	float limiteSuperior = (float) 0.23;
	float limiteInferiorGanar = (float) 0.32;
	float limiteSuperiorGanar = (float) 0.39;
	private boolean yaGane = false;
	public Raton(){
		
	}
	
	public void init(){
		while(true){
			corre();
			if(yaGane){
				System.exit(0);
			}
			girar();
		}
	}
	
	public void corre(){
		try {
			mrRobot.setLinearSpeed(20);
			mrRobot.setLinearAcceleration(5);
			SensorMode color = sensorColor.getRGBMode();
			float[] colorSample = new float[color.sampleSize()];
			color.fetchSample(colorSample, 0);
			while(estaLimite(colorSample[0])&&!gane(colorSample[0])){
				System.out.println(colorSample[0]);
				mrRobot.forward();
				Thread.sleep(300);
				color.fetchSample(colorSample, 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void girar(){
		try {
			int angulo = 0;
			angulo = (int)((Math.random()*140)+1);
			mrRobot.rotate(angulo);
		} catch (Exception e) {
			System.out.print(e);
		}
	}
	
	public boolean estaLimite(float color){
		boolean esta = false;
			if(color > limiteInferior && color < limiteSuperior){
				esta = true;
			}
		return esta;
	}
	
	public boolean gane(float color){
		boolean gane = false;
		if(color > limiteInferiorGanar && color < limiteSuperiorGanar){
			gane = true;
			yaGane = gane;
		}
		return gane;
	}

}
