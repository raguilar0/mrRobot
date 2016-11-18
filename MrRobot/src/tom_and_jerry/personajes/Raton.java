package tom_and_jerry.personajes;


import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import tom_and_jerry.sensor_color.Color;
import tom_and_jerry.sensor_color.ObtenedorColor;

public class Raton implements Jugador {
    private final static long SLEEP_MILISEGS = 300;

    private MovePilot jerry;

    private ObtenedorColor obtieneColor;
    private Color colorAreaDeJuego;
    private Color colorAreaMeta;

    public Raton() {
        obtieneColor = new ObtenedorColor(SensorPort.S4);
        colorAreaDeJuego = new Color(0.17f, 0.23f);
        colorAreaMeta = new Color(0.32f, 0.39f);

        Wheel ruedaIzquierda = WheeledChassis.modelWheel(Motor.A, 5).offset(6).gearRatio(1);
        Wheel ruedaDerecha = WheeledChassis.modelWheel(Motor.B, 5).offset(-6).gearRatio(1);
        Chassis chasis = new WheeledChassis(new Wheel[]{ruedaIzquierda, ruedaDerecha}, WheeledChassis.TYPE_DIFFERENTIAL);

        jerry = new MovePilot(chasis);
        jerry.setLinearSpeed(20);
        jerry.setLinearAcceleration(5);
    }

    @Override
    public boolean jugar() {
        boolean personajeGano = false;

        try {
            while (!juegoTerminado()) {
                desplazarse();
            }
            personajeGano = heGanado();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return personajeGano;
    }


    private boolean juegoTerminado() {
        return (heGanado() || hePerdido());
    }

    private boolean heGanado() {
        return estaEnLaMeta();
    }

    private boolean estaEnLaMeta() {
        return colorAreaMeta.estaEnRango(obtieneColor.obtenerMuestra());
    }

    private boolean hePerdido() {
        // TODO: Comunicación con gato
        return false;
    }

    private void desplazarse() throws InterruptedException {
        if (estaEnAreaDeJuego()) {
            mover();
        }
        else {
            girar();
        }
    }

    private void mover() throws InterruptedException {
        jerry.forward();
        Thread.sleep(Raton.SLEEP_MILISEGS);
    }

    private void girar() {
        int angulo = (int) ((Math.random() * 140) + 1);
        jerry.rotate(angulo);
    }

    private boolean estaEnAreaDeJuego() {
        return colorAreaDeJuego.estaEnRango(obtieneColor.obtenerMuestra());
    }
}
