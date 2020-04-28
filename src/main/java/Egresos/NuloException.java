package Egresos;

public class NuloException extends RuntimeException {
    String mensaje;

    NuloException(String mensaje){
        this.mensaje = mensaje;
    }
}
