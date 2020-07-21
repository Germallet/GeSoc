package Egresos;

import Seguridad.Usuario;
import com.google.common.base.Preconditions;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Egreso {
    private DocumentoComercial documento;
    private LocalDate fecha;
    private MedioDePago medioDePago;
    private List<Presupuesto> presupuestos = new ArrayList<>();
    private Presupuesto presupuestoElegido;
    private List<Usuario> revisores = new ArrayList<>();
    private int presupuestosRequeridos;
    private boolean escogerMenor;
    //Etiqueta etiqueta;

    Egreso(DocumentoComercial documento, LocalDate fecha, MedioDePago unPago, int presupuestosRequeridos, boolean escogerMenor) {
        this.documento = documento;
        this.fecha = fecha;
        this.medioDePago = Preconditions.checkNotNull(unPago, "No se ingreso un medio de pago");
        this.presupuestosRequeridos = presupuestosRequeridos;
        this.escogerMenor = escogerMenor;
    }

    public void agregarPresupuesto(Presupuesto nuevoPresupuesto) {
        presupuestos.add(nuevoPresupuesto);
    }

    public void elegirPresupuesto(Presupuesto presupuestoElegido) {
        Preconditions.checkArgument(presupuestos.contains(presupuestoElegido));
        this.presupuestoElegido = presupuestoElegido;
    }

    private boolean presupuestosSuficiente() {
        return presupuestos.size() >= presupuestosRequeridos;
    }
    private boolean criterioDelMenor() {
        if (escogerMenor)
            return presupuestos.stream().allMatch(presupuesto -> presupuesto.valorTotal() >= presupuestoElegido.valorTotal());
        return true;
    }
    public boolean esValido() {
        return presupuestoElegido != null && presupuestosSuficiente() && criterioDelMenor();
    }

    public void validar() {
        if (esValido())
            revisores.forEach(revisor ->  revisor.getBandejaDeMensajes().recibirMensaje("Egreso generado"));
    }
};
