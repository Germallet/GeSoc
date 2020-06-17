package Seguridad;

import java.util.ArrayList;
import java.util.List;

public class BandejaDeMensajes {
    private List<String> mensajes = new ArrayList<>();

    public void recibirMensaje(String nuevoMensaje) {
        mensajes.add(nuevoMensaje);
    }
}
