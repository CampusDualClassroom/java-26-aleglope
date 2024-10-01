package com.campusdual.classroom;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class Contact implements ICallActions {
    private String name;
    private String surnames;
    private String phoneNumber;
    private String code;

    public Contact(String name, String surnames, String phoneNumber) {
        this.name = name;
        this.surnames = surnames;
        this.phoneNumber = phoneNumber;
        this.code = generateCode(name, surnames);
    }

    private String generateCode(String name, String surnames) {
        // Eliminar diacríticos y convertir a minúsculas
        String normalizedName = removeDiacritics(name).toLowerCase(Locale.ROOT);
        String normalizedSurnames = removeDiacritics(surnames).toLowerCase(Locale.ROOT);

        // Separar apellidos
        String[] surnameParts = normalizedSurnames.split("\\s+");
        StringBuilder codeBuilder = new StringBuilder();

        // Añadir la primera letra del nombre
        if (!normalizedName.isEmpty()) {
            codeBuilder.append(normalizedName.charAt(0));
        }

        if (surnameParts.length == 1) {
            // Un solo apellido: añadir el apellido completo
            codeBuilder.append(surnameParts[0]);
        } else if (surnameParts.length >= 2) {
            // Múltiples apellidos: añadir la primera letra del primer apellido + concatenación de los demás apellidos
            codeBuilder.append(surnameParts[0].charAt(0));
            for (int i = 1; i < surnameParts.length; i++) {
                codeBuilder.append(surnameParts[i]);
            }
        }

        return codeBuilder.toString();
    }

    private String removeDiacritics(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("");
    }

    // Getters y Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.code = generateCode(this.name, this.surnames); // Actualizar el código al cambiar el nombre
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
        this.code = generateCode(this.name, this.surnames); // Actualizar el código al cambiar los apellidos
    }

    public String getPhone() {
        return phoneNumber;
    }

    public void setPhone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCode() {
        return code;
    }

    // Implementación de los métodos de ICallActions

    @Override
    public void callMyNumber() {
        System.out.println("El contacto " + getFullName() + " se está llamando a sí mismo al número " + phoneNumber + ".");
    }

    @Override
    public void callOtherNumber(String number) {
        System.out.println("El contacto " + getFullName() + " se está llamando al número " + number + ".");
    }

    @Override
    public void showContactDetails() {
        System.out.println("Código: " + code + ", Nombre: " + getFullName() + ", Número de teléfono: " + phoneNumber + ".");
    }

    public String getFullName() {
        return name + " " + surnames;
    }

    @Override
    public String toString() {
        return "Código: " + code + ", Nombre: " + getFullName() + ", Teléfono: " + phoneNumber;
    }
}
