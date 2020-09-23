package Seguridad;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class BandejaDeMensajes {
    @OneToMany
    @JoinColumn(name="id_usuario",referencedColumnName = "id")
    private List<Mensaje> mensajes = new ArrayList<>();

    public void recibirMensaje(Mensaje nuevoMensaje) {
        mensajes.add(nuevoMensaje);
    }
}
