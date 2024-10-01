package com.campusdual.classroom;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Phonebook {
    private Map<String, Contact> contacts;
    private Scanner scanner;

    public Phonebook() {
        contacts = new HashMap<>();
        scanner = new Scanner(System.in);
    }

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

    private void showMenu() {
        System.out.println("\n--- Agenda Telefónica ---");
        System.out.println("1. Añadir un contacto");
        System.out.println("2. Mostrar todos los contactos");
        System.out.println("3. Seleccionar un contacto y realizar acciones");
        System.out.println("4. Eliminar un contacto");
        System.out.println("5. Salir");
    }

    private void addContact() {
        System.out.println("\n--- Añadir Nuevo Contacto ---");
        String name = readStringInput("Ingrese el nombre: ");
        String surnames = readStringInput("Ingrese los apellidos: ");
        String phoneNumber = readStringInput("Ingrese el número de teléfono: ");

        Contact contact = new Contact(name, surnames, phoneNumber);
        addContact(contact);
    }

    public void addContact(Contact contact) {
        if (contacts.containsKey(contact.getCode())) {
            System.out.println("Ya existe un contacto con el código: " + contact.getCode());
            System.out.println("No se ha añadido el contacto.");
        } else {
            contacts.put(contact.getCode(), contact);
            System.out.println("Contacto añadido exitosamente con el código: " + contact.getCode());
        }
    }

    private void showAllContacts() {
        System.out.println("\n--- Lista de Contactos ---");
        showPhonebook();
    }

    public void showPhonebook() {
        if (contacts.isEmpty()) {
            System.out.println("La agenda telefónica está vacía.");
        } else {
            for (Contact contact : contacts.values()) {
                System.out.println(contact.toString());
            }
        }
    }

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

    private void deleteContact() {
        System.out.println("\n--- Eliminar Contacto ---");
        String code = readStringInput("Ingrese el código del contacto a eliminar: ");
        deleteContact(code);
    }

    public void deleteContact(String code) {
        if (contacts.containsKey(code)) {
            contacts.remove(code);
            System.out.println("Contacto con el código " + code + " ha sido eliminado.");
        } else {
            System.out.println("No se encontró ningún contacto con el código: " + code);
        }
    }

    public Map<String, Contact> getData() {
        return contacts;
    }

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

    private String readStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
