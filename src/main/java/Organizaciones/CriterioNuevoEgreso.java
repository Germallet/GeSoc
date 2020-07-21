/*package Organizaciones;

import Egresos.Egreso;
import java.util.List;

public interface CriterioNuevoEgreso {
    boolean permiteEgreso(List<Egreso> egresos, Egreso nuevoEgreso);
}

public class CriterioNuevoEgresoPermitir implements CriterioNuevoEgreso {
    @Override
    public boolean permiteEgreso(List<Egreso> egresos, Egreso nuevoEgreso) {
        return true;
    }
}

public class CriterioNuevoEgresoMontoMaximo implements CriterioNuevoEgreso {
    int montoMaximo;

    @Override
    public boolean permiteEgreso(List<Egreso> egresos, Egreso nuevoEgreso) {
        return egresos.stream().mapToInt(egreso -> egreso.valorTotal()).sum() + nuevoEgreso.valorTotal() <= montoMaximo;
    }
}*/