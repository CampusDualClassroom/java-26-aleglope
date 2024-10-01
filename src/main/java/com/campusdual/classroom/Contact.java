package com.campusdual.classroom;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * La clase {@code Contact} representa un contacto en la agenda telefónica.
 * <p>
 * Implementa la interfaz {@code ICallActions} para proporcionar acciones de llamada y mostrar detalles.
 * Cada contacto tiene un código único generado automáticamente basado en su nombre y apellidos.
 * </p>
 *
 * @author TuNombre
 * @version 1.0
 */
public class Contact implements ICallActions {
    /** El nombre del contacto. */
    private String name;

    /** Los apellidos del contacto. */
    private String surnames;

    /** El número de teléfono del contacto. */
    private String phoneNumber;

    /** El código único del contacto. */
    private String code;

    /**
     * Constructor de la clase {@code Contact}.
     * <p>
     * Inicializa un nuevo contacto con el nombre, apellidos y número de teléfono proporcionados.
     * Genera automáticamente el código del contacto.
     * </p>
     *
     * @param name        El nombre del contacto.
     * @param surnames    Los apellidos del contacto.
     * @param phoneNumber El número de teléfono del contacto.
     */
    public Contact(String name, String surnames, String phoneNumber) {
        this.name = name;
        this.surnames = surnames;
        this.phoneNumber = phoneNumber;
        this.code = generateCode(name, surnames);
    }

    /**
     * Genera el código único del contacto basado en su nombre y apellidos.
     * <p>
     * Las reglas para generar el código son:
     * <ul>
     *     <li>Todo en minúsculas y sin signos diacríticos.</li>
     *     <li>Si tiene un solo apellido: primera letra del nombre + apellido completo.</li>
     *     <li>Si tiene múltiples apellidos: primera letra del nombre + primera letra del primer apellido + concatenación de los demás apellidos sin espacios.</li>
     * </ul>
     * </p>
     *
     * @param name     El nombre del contacto.
     * @param surnames Los apellidos del contacto.
     * @return El código generado.
     */
    private String generateCode(String name, String surnames) {
        // Convierte el nombre y los apellidos a minúsculas y elimina signos diacríticos (acentos)

        String normalizedName = removeDiacritics(name).toLowerCase(Locale.ROOT);
        String normalizedSurnames = removeDiacritics(surnames).toLowerCase(Locale.ROOT);

        // Separa los apellidos en partes (si son múltiples apellidos)

        String[] surnameParts = normalizedSurnames.split("\\s+");
        StringBuilder codeBuilder = new StringBuilder();

        if (!normalizedName.isEmpty()) {
            codeBuilder.append(normalizedName.charAt(0));
        }

        // Genera el código basado en si tiene uno o varios apellidos

        if (surnameParts.length == 1) {
            // Un solo apellido: código = primera letra del nombre + apellido completo
            codeBuilder.append(surnameParts[0]);
        } else if (surnameParts.length >= 2) {
            // Múltiples apellidos: código = primera letra del nombre + primera letra del primer apellido

            codeBuilder.append(surnameParts[0].charAt(0));// Primera letra del primer apellido

            // Añade las partes restantes de los apellidos concatenadas

            for (int i = 1; i < surnameParts.length; i++) {
                codeBuilder.append(surnameParts[i]);
            }
        }

        return codeBuilder.toString();
    }

    /**
     * Elimina los signos diacríticos de una cadena.
     * <p>
     * Utiliza {@code Normalizer} y expresiones regulares para eliminar acentos y otros signos.
     * </p>
     *
     * @param input La cadena de entrada.
     * @return La cadena sin signos diacríticos.
     */
    private String removeDiacritics(String input) {
        // Normaliza el texto eliminando los caracteres diacríticos

        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        // Utiliza un patrón regex para eliminar las marcas diacríticas

        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("");
    }

    // Getters y Setters

    /**
     * Obtiene el nombre del contacto.
     *
     * @return El nombre del contacto.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece un nuevo nombre para el contacto.
     * <p>
     * También actualiza el código del contacto.
     * </p>
     *
     * @param name El nuevo nombre.
     */
    public void setName(String name) {
        this.name = name;
        this.code = generateCode(this.name, this.surnames);
    }

    /**
     * Obtiene los apellidos del contacto.
     *
     * @return Los apellidos del contacto.
     */
    public String getSurnames() {
        return surnames;
    }

    /**
     * Establece nuevos apellidos para el contacto.
     * <p>
     * También actualiza el código del contacto.
     * </p>
     *
     * @param surnames Los nuevos apellidos.
     */
    public void setSurnames(String surnames) {
        this.surnames = surnames;
        this.code = generateCode(this.name, this.surnames);
    }

    /**
     * Obtiene el número de teléfono del contacto.
     *
     * @return El número de teléfono.
     */
    public String getPhone() {
        return phoneNumber;
    }

    /**
     * Establece un nuevo número de teléfono para el contacto.
     *
     * @param phoneNumber El nuevo número de teléfono.
     */
    public void setPhone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Obtiene el código único del contacto.
     *
     * @return El código del contacto.
     */
    public String getCode() {
        return code;
    }

    // Implementación de ICallActions

    /**
     * Realiza una llamada al propio número del contacto.
     * <p>
     * Muestra un mensaje indicando que el contacto se está llamando a sí mismo.
     * </p>
     */
    @Override
    public void callMyNumber() {
        System.out.println("El contacto " + getFullName() + " se está llamando a sí mismo al número " + phoneNumber + ".");
    }

    /**
     * Realiza una llamada a otro número especificado.
     * <p>
     * Muestra un mensaje indicando que el contacto está llamando al número proporcionado.
     * </p>
     *
     * @param number El número de teléfono al que se desea llamar.
     */
    @Override
    public void callOtherNumber(String number) {
        System.out.println("El contacto " + getFullName() + " se está llamando al número " + number + ".");
    }

    /**
     * Muestra los detalles del contacto.
     * <p>
     * Incluye código, nombre completo y número de teléfono.
     * </p>
     */
    @Override
    public void showContactDetails() {
        System.out.println("Código: " + code + ", Nombre: " + getFullName() + ", Número de teléfono: " + phoneNumber + ".");
    }

    /**
     * Obtiene el nombre completo del contacto (nombre y apellidos).
     *
     * @return El nombre completo del contacto.
     */
    public String getFullName() {
        return name + " " + surnames;
    }

    /**
     * Retorna una representación en cadena del contacto.
     *
     * @return Una cadena con el código, nombre completo y número de teléfono.
     */
    @Override
    public String toString() {
        return "Código: " + code + ", Nombre: " + getFullName() + ", Teléfono: " + phoneNumber;
    }
}
