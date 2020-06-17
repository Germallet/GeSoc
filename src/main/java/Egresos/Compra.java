package Egresos;

import com.google.common.base.Preconditions;
import java.util.List;
import java.util.ArrayList;

public class Compra {

    boolean requierePresupuestos;
    int presupuestosRequeridos;
    List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();

    Compra(int presupuestosRequeridos,boolean requierePresupuestos,List<Presupuesto> presupuestos){
        this.presupuestosRequeridos = Preconditions.checkNotNull(presupuestosRequeridos, "No son los presupuestos requeridos");
        this.requierePresupuestos = Preconditions.checkNotNull(requierePresupuestos,"No se definio si requiere o no presupuestos");
        this.presupuestos = presupuestos;
    }

    private boolean requierePresupuestos(){
        return requierePresupuestos;
    }
}
