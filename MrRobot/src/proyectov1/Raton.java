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
	private final static long SLEEP_MINUTES = 300;
	
	private ObtieneColor obtieneColor;
	
	private Color piso;
	private Color meta;
	
	MovePilot jerry;
	Wheel ruedaIzquierda;
	Wheel ruedaDerecha;
	Chassis chasis;
	
	public Raton() {
		obtieneColor = new ObtieneColor(SensorPort.S4);
		piso = new Color((float) 0.17, (float) 0.23);
		meta = new Color((float) 0.32, (float) 0.39 );
		
		ruedaIzquierda = WheeledChassis.modelWheel(Motor.A, 5).offset(6).gearRatio(1);
		ruedaDerecha = WheeledChassis.modelWheel(Motor.B, 5).offset(-6).gearRatio(1);
		chasis = new WheeledChassis(new Wheel[]{ruedaIzquierda, ruedaDerecha}, WheeledChassis.TYPE_DIFFERENTIAL);
		jerry = new MovePilot(chasis);
		
		jerry.setLinearSpeed(20);
		jerry.setLinearAcceleration(5);
	}
	
	public void jugar() {
		try {
			while(!heGanado()) {
				// TODO: Me ataraparon?
				moverse();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void moverse() throws InterruptedException {
		if(piso.estaEnRango(obtieneColor.obtieneMuestra())) {
			corre();
		}
		else {
			girar();
		}
	}
	
	public void corre() throws InterruptedException{
		jerry.forward();
		Thread.sleep(Raton.SLEEP_MINUTES);
	}
	
	private boolean heGanado() {
		if(meta.estaEnRango(obtieneColor.obtieneMuestra())){
			return true;
		}
		return false;
	}
		
	public void girar() {
		int angulo = 0;
		angulo = (int)((Math.random()*140)+1);
		jerry.rotate(angulo);
	}
}
