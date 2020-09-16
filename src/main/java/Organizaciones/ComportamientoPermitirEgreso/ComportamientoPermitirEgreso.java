package Organizaciones.ComportamientoPermitirEgreso;

import Egresos.Egreso;
import Main.IDGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo", discriminatorType=DiscriminatorType.STRING)
public abstract class ComportamientoPermitirEgreso extends IDGenerator {
    public abstract boolean permiteEgreso(List<Egreso> egresos, Egreso nuevoEgreso);
}
