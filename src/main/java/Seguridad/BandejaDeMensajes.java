package Seguridad;

import Main.IDGenerator;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BandejaDeMensajes extends IDGenerator {
    @OneToMany
    private List<Mensaje> mensajes = new ArrayList<>();

    public void recibirMensaje(Mensaje nuevoMensaje) {
        mensajes.add(nuevoMensaje);
    }
}
