package proyectov1;

import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;

public class ObtieneColor {
	private SensorMode color;
	
	public ObtieneColor(Port port) {
		EV3ColorSensor sensorColor = new EV3ColorSensor(port);
		color = sensorColor.getRGBMode();
	}
	
	public float obtieneMuestra() {	
		// TODO cerrar sensor
		float[] colorSample = new float[color.sampleSize()];
		color.fetchSample(colorSample, 0);
		return colorSample[0];
	}
}
