package com.alpha.century;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;

public class AgendaDeContactos {
    private Map<String, String> agenda = new HashMap<>();

    public boolean agregarContacto(String nombre, String numero) {
        if (agenda.containsKey(nombre)) {
            System.out.println("El contacto '" + nombre + "' ya existe.");
            return false;
        } else {
            agenda.put(nombre, numero);
            System.out.println("Contacto '" + nombre + "' agregado con exito.");
            return true;
        }
    }

    public String buscarContacto(String nombre) {
        return agenda.get(nombre);
    }

    public void eliminarContacto(String nombre) {
        if (agenda.containsKey(nombre)) {
            agenda.remove(nombre);
            System.out.println("Contacto eliminado con exito.");
        } else {
            System.out.println("El contacto no existe.");
        }
    }
    
    public void guardarContactos() {
        try (PrintWriter writer = new PrintWriter("contactos.txt", "UTF-8")) {
            for (Map.Entry<String, String> contacto : agenda.entrySet()) {
                writer.println(contacto.getKey() + "," + contacto.getValue());
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar los contactos.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AgendaDeContactos agenda = new AgendaDeContactos();
        Scanner scanner = new Scanner(System.in);

        while (true) {
        	System.out.println("Bienvenido A La Agenda De Contactos");
        	System.out.println("Por favor Marque una de las 4 Opciones");
        	System.out.println("ATENCION! Despues De Colocar Todos los contactos Elije la opcion 5 Para Guardarlos Si No Lo haces Se Perderan Mucho Cuidado!");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Buscar contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Salir");
            System.out.println("5. Guardar Contactos");
            System.out.print("Elige una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // consume newline

            if (opcion == 1) {
                System.out.print("Nombre del contacto: ");
                String nombre = scanner.nextLine();
                System.out.print("Numero del contacto: ");
                String numero = scanner.nextLine();
                boolean contactoAgregado = agenda.agregarContacto(nombre, numero);
                if (contactoAgregado) {
                    System.out.println("Contacto agregado.");
                }
            } else if (opcion == 2) {
                System.out.print("Nombre del contacto: ");
                String nombre = scanner.nextLine();
                String numero = agenda.buscarContacto(nombre);
                if (numero != null) {
                    System.out.println("Numero del contacto: " + numero);
                } else {
                    System.out.println("Contacto no encontrado.");
                }
            } else if (opcion == 3) {
                System.out.print("Nombre del contacto: ");
                String nombre = scanner.nextLine();
                agenda.eliminarContacto(nombre);
                
            } else if (opcion == 5) {
                agenda.guardarContactos();
                System.out.println("Contactos guardados con éxito.");
              
            } else if (opcion == 4) {            	
            	scanner.close();
                break;
            }
        }
    }
}