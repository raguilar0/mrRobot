package tom_and_jerry.personajes;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.remote.nxt.NXTConnection;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import tom_and_jerry.sensor_color.Color;
import tom_and_jerry.sensor_color.ObtenedorColor;

public abstract class Jugador {
    private final static long SLEEP_MILISEGS = 300;
    private MovePilot pilot;
    protected NXTConnection conexion;
    protected DataInputStream entrada;
    protected DataOutputStream salida;

    protected ObtenedorColor obtieneColor;
    protected Color colorAreaDeJuego;
	
	public Jugador() {
		obtieneColor = new ObtenedorColor(SensorPort.S4);
		colorAreaDeJuego = new Color(0.22f, 0.5f);
		
        Wheel ruedaIzquierda = WheeledChassis.modelWheel(Motor.A, 5).offset(6).gearRatio(1);
        Wheel ruedaDerecha = WheeledChassis.modelWheel(Motor.B, 5).offset(-6).gearRatio(1);
        Chassis chasis = new WheeledChassis(new Wheel[]{ruedaIzquierda, ruedaDerecha}, WheeledChassis.TYPE_DIFFERENTIAL);

        pilot = new MovePilot(chasis);
        pilot.setLinearSpeed(20);
        pilot.setLinearAcceleration(5);
	}
	
	public abstract String obtenerNombreJugador();
	
	public boolean jugar() {
		boolean resultado = false;
		try {
            while (!juegoTerminado()) {
                desplazarse();
            }
            resultado = heGanado();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return resultado;
	}
	
    private boolean juegoTerminado() {
        return (heGanado() || hePerdido());
    }

	abstract protected boolean heGanado();
	abstract protected boolean hePerdido();
	abstract protected void notificar(int codigo);
	abstract protected int recibir();
	
    private void desplazarse() throws InterruptedException {
        if (estaEnAreaDeJuego()) {
            mover();
        } else {
            girar();
        }
    }

    private void mover() throws InterruptedException {
        pilot.forward();
        Thread.sleep(Jugador.SLEEP_MILISEGS);
    }

    private void girar() {
        int angulo = (int) ((Math.random() * 140) + 1);
        pilot.rotate(angulo);
    }

    private boolean estaEnAreaDeJuego() {
        return colorAreaDeJuego.estaEnRango(obtieneColor.obtenerMuestra());
    }
    
    public void setConexion(NXTConnection conexion)
    {
    	this.conexion = conexion;
    	
    	setEntrada(this.conexion.openDataInputStream());
    	setSalida(this.conexion.openDataOutputStream());
    }
    
    private void setEntrada(DataInputStream entrada)
    {
    	this.entrada = entrada;
    }
    
    private void setSalida(DataOutputStream salida)
    {
    	this.salida = salida;
    }
}
