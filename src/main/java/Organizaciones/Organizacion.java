package Organizaciones;
import Egresos.Egreso;
import java.util.List;

public class Organizacion {
    List<Entidad> entidades;
    List<Egreso> egresos;

    public void validarEgresos() {
        egresos.forEach(egreso -> egreso.validar());
    }
}
