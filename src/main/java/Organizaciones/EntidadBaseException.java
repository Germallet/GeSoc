package Organizaciones;

public class EntidadBaseException extends RuntimeException {

    String mensaje;

    EntidadBaseException(String mensaje){
        this.mensaje = mensaje;
    }
}
