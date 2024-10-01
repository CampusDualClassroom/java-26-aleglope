package com.campusdual.classroom;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * La clase {@code Phonebook} representa una agenda telefónica.
 * <p>
 * Permite gestionar contactos mediante un menú interactivo, ofreciendo funcionalidades para:
 * <ul>
 *     <li>Añadir contactos.</li>
 *     <li>Mostrar todos los contactos.</li>
 *     <li>Seleccionar un contacto y realizar acciones.</li>
 *     <li>Eliminar contactos.</li>
 * </ul>
 * </p>
 * <p>
 * Utiliza un {@code Map<String, Contact>} para almacenar los contactos, donde la clave es el código del contacto.
 * </p>
 *
 * @author
 * @version 1.0
 */
public class Phonebook {
    /** Mapa que almacena los contactos con su código como clave. */
    private Map<String, Contact> contacts;

    /** Scanner para leer la entrada del usuario. */
    private Scanner scanner;

    /**
     * Constructor de la clase {@code Phonebook}.
     * <p>
     * Inicializa el mapa de contactos y el scanner.
     * </p>
     */
    public Phonebook() {
        contacts = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    /**
     * Inicia el menú interactivo de la agenda telefónica.
     * <p>
     * Permite al usuario navegar entre las distintas opciones hasta que decida salir.
     * </p>
     */
    public void start() {
        int option = 0;
        do {
            showMenu();
            option = readIntegerInput("Seleccione una opción: ");
            switch (option) {
                case 1:
                    addContact();
                    break;
                case 2:
                    showAllContacts();
                    break;
                case 3:
                    selectContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    System.out.println("Saliendo de la agenda telefónica. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        } while (option != 5);
    }

    /**
     * Muestra el menú principal de la agenda telefónica.
     */
    private void showMenu() {
        System.out.println("\n--- Agenda Telefónica ---");
        System.out.println("1. Añadir un contacto");
        System.out.println("2. Mostrar todos los contactos");
        System.out.println("3. Seleccionar un contacto y realizar acciones");
        System.out.println("4. Eliminar un contacto");
        System.out.println("5. Salir");
    }

    /**
     * Añade un nuevo contacto a la agenda telefónica.
     * <p>
     * Solicita al usuario los datos del contacto y lo añade utilizando {@code addContact(Contact contact)}.
     * </p>
     */
    private void addContact() {
        System.out.println("\n--- Añadir Nuevo Contacto ---");
        String name = readStringInput("Ingrese el nombre: ");
        String surnames = readStringInput("Ingrese los apellidos: ");
        String phoneNumber = readStringInput("Ingrese el número de teléfono: ");

        Contact contact = new Contact(name, surnames, phoneNumber);
        addContact(contact);
    }

    /**
     * Añade un contacto al mapa de contactos.
     * <p>
     * Verifica que no exista otro contacto con el mismo código antes de añadirlo.
     * </p>
     *
     * @param contact El contacto a añadir.
     */
    public void addContact(Contact contact) {
        if (contacts.containsKey(contact.getCode())) {
            System.out.println("Ya existe un contacto con el código: " + contact.getCode());
            System.out.println("No se ha añadido el contacto.");
        } else {
            contacts.put(contact.getCode(), contact);
            System.out.println("Contacto añadido exitosamente con el código: " + contact.getCode());
        }
    }

    /**
     * Muestra todos los contactos almacenados en la agenda telefónica.
     */
    private void showAllContacts() {
        System.out.println("\n--- Lista de Contactos ---");
        showPhonebook();
    }

    /**
     * Muestra todos los contactos utilizando el mapa de contactos.
     * <p>
     * Si no hay contactos, informa al usuario.
     * </p>
     */
    public void showPhonebook() {
        if (contacts.isEmpty()) {
            System.out.println("La agenda telefónica está vacía.");
        } else {
            for (Contact contact : contacts.values()) {
                System.out.println(contact.toString());
            }
        }
    }

    /**
     * Permite al usuario seleccionar un contacto y realizar acciones.
     * <p>
     * Solicita el código del contacto y muestra el submenú de acciones si existe.
     * </p>
     */
    private void selectContact() {
        System.out.println("\n--- Seleccionar Contacto ---");
        String code = readStringInput("Ingrese el código del contacto: ");
        if (contacts.containsKey(code)) {
            Contact contact = contacts.get(code);
            contactMenu(contact);
        } else {
            System.out.println("No se encontró ningún contacto con el código: " + code);
        }
    }

    /**
     * Muestra el submenú de acciones para un contacto específico.
     *
     * @param contact El contacto seleccionado.
     */
    private void contactMenu(Contact contact) {
        int option = 0;
        do {
            System.out.println("\n--- Menú de Acciones para " + contact.getFullName() + " ---");
            System.out.println("1. Llamar a mi número");
            System.out.println("2. Llamar a otro número");
            System.out.println("3. Mostrar detalles del contacto");
            System.out.println("4. Volver al menú principal");
            option = readIntegerInput("Seleccione una opción: ");
            switch (option) {
                case 1:
                    contact.callMyNumber();
                    break;
                case 2:
                    String number = readStringInput("Ingrese el número al que desea llamar: ");
                    contact.callOtherNumber(number);
                    break;
                case 3:
                    contact.showContactDetails();
                    break;
                case 4:
                    System.out.println("Volviendo al menú principal.");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        } while (option != 4);
    }

    /**
     * Elimina un contacto de la agenda telefónica.
     * <p>
     * Solicita el código del contacto y lo elimina utilizando {@code deleteContact(String code)}.
     * </p>
     */
    private void deleteContact() {
        System.out.println("\n--- Eliminar Contacto ---");
        String code = readStringInput("Ingrese el código del contacto a eliminar: ");
        deleteContact(code);
    }

    /**
     * Elimina un contacto del mapa de contactos basado en su código.
     *
     * @param code El código del contacto a eliminar.
     */
    public void deleteContact(String code) {
        if (contacts.containsKey(code)) {
            contacts.remove(code);
            System.out.println("Contacto con el código " + code + " ha sido eliminado.");
        } else {
            System.out.println("No se encontró ningún contacto con el código: " + code);
        }
    }

    /**
     * Obtiene el mapa de contactos almacenados en la agenda.
     *
     * @return El mapa de contactos.
     */
    public Map<String, Contact> getData() {
        return contacts;
    }

    /**
     * Lee y valida una entrada entera del usuario.
     * <p>
     * Solicita al usuario que ingrese un número entero y valida la entrada.
     * </p>
     *
     * @param prompt El mensaje a mostrar al usuario.
     * @return El número entero ingresado.
     */
    private int readIntegerInput(String prompt) {
        int number = -1;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                number = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
            }
        }
        return number;
    }

    /**
     * Lee una entrada de cadena de texto del usuario.
     *
     * @param prompt El mensaje a mostrar al usuario.
     * @return La cadena de texto ingresada.
     */
    private String readStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
