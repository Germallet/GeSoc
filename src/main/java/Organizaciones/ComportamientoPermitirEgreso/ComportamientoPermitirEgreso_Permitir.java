package Organizaciones.ComportamientoPermitirEgreso;

import Egresos.Egreso;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Permitir")
public class ComportamientoPermitirEgreso_Permitir extends ComportamientoPermitirEgreso {

    public ComportamientoPermitirEgreso_Permitir() {
        super();
    }

    public boolean permiteEgreso(List<Egreso> egresos, Egreso nuevoEgreso) {
        return true;
    }
}
