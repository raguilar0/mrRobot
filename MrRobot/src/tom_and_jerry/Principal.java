package tom_and_jerry;


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