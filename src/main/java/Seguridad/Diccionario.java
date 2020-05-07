package Seguridad;

import java.util.Scanner;

public class Diccionario {

    private String nombreArchivo;

    public Diccionario(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public boolean contiene(String busqueda) {
        Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream(nombreArchivo));
        while (scanner.hasNextLine())
            if (scanner.nextLine().equalsIgnoreCase(busqueda))
                return true;
        return false;
    }
}
