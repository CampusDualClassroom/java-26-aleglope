package com.campusdual.classroom;

/**
 * La clase principal {@code Exercise26} inicia la aplicación de la agenda telefónica.
 * <p>
 * Crea una instancia de {@code Phonebook} y llama al método {@code start()} para comenzar.
 * </p>
 *
 * @author
 * @version 1.0
 */
public class Exercise26 {

    /**
     * Método principal que inicia la aplicación.
     *
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook();
        phonebook.start();
    }
}
