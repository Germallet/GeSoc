package Organizaciones.ComportamientoPermitirEgreso;

import Egresos.Egreso;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("MontoMaximo")
public class ComportamientoPermitirEgreso_MontoMaximo extends ComportamientoPermitirEgreso {
    int montoMaximo;

    public ComportamientoPermitirEgreso_MontoMaximo(int montoMaximoEgresos) {
        this.montoMaximo = montoMaximoEgresos;
    }

    public ComportamientoPermitirEgreso_MontoMaximo() {
        super();
    }

    public boolean permiteEgreso(List<Egreso> egresos, Egreso nuevoEgreso) {
        return egresos.stream().mapToInt(egreso -> egreso.valorTotal()).sum() + nuevoEgreso.valorTotal() <= montoMaximo;
    }
}
