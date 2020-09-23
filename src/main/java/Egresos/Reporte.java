package Egresos;

import Organizaciones.Etiqueta;
import java.util.List;
import java.util.stream.Collectors;

public class Reporte {
    List<Egreso> egresos;

    public Reporte(List<Egreso> egresos){
        this.egresos = egresos.stream().filter(egreso -> egreso.esDelUltimoMes()).collect(Collectors.toList());
    }

    public List<Egreso> egresos() {
        return egresos;
    }

    public List<Egreso> generarEgresosPorEtiqueta(Etiqueta etiqueta) {
        return egresos.stream().filter(egreso -> egreso.tieneEtiqueta(etiqueta)).collect(Collectors.toList());
    }
}
