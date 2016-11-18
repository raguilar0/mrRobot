package tom_and_jerry.sensor_color;

import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;

public class ObtenedorColor {
    private SensorMode sensorColor;

    public ObtenedorColor(Port port) {
        EV3ColorSensor sensorColor = new EV3ColorSensor(port);
        this.sensorColor = sensorColor.getRGBMode();
    }

    public float obtenerMuestra() {
        // TODO cerrar sensor
        float[] colorSample = new float[sensorColor.sampleSize()];
        sensorColor.fetchSample(colorSample, 0);
        return colorSample[0];
    }
}
