package Seguridad;

import java.util.Scanner;

public class Diccionario {

    private String nombreArchivo;

    public Diccionario(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public boolean Contiene(String búsqueda) {
        Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream(nombreArchivo));
        while (scanner.hasNextLine())
            if (scanner.nextLine().equals(búsqueda))
                return true;
        return false;
    }
}
