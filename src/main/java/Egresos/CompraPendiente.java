package Egresos;

import Organizaciones.Organizacion;
import Proveedor.Proveedor;
import Seguridad.Usuario;
import com.google.common.base.Preconditions;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class CompraPendiente {

    private Organizacion organizacion;
    private Presupuesto presupuestoElegido;
    private MedioDePago medioDePago;
    private List<Presupuesto> presupuestos = new ArrayList<>();
    private List<Usuario> revisores = new ArrayList<>();
    private int presupuestosRequeridos;
    private boolean escogerMenor;

    public CompraPendiente(Organizacion organizacion, int presupuestosRequeridos, boolean escogerMenor) {
        this.organizacion = organizacion;
        this.presupuestosRequeridos = presupuestosRequeridos;
        this.escogerMenor = escogerMenor;
    }

    public void agregarPresupuesto(Presupuesto nuevoPresupuesto) {
        presupuestos.add(nuevoPresupuesto);
    }

    public void seleccionarPresupuesto(Presupuesto presupuestoElegido) {
        this.presupuestoElegido = presupuestoElegido;
    }

    public void seleccionarMedioDePago(Presupuesto medioDePagoElegido) {
        this.medioDePago = medioDePago;
    }

    public void agregarRevisor(Usuario revisor) {
        revisores.add(revisor);
    }

    public Egreso generarEgreso() {
        Preconditions.checkArgument(presupuestos.size() >= presupuestosRequeridos);
        if (escogerMenor) Preconditions.checkArgument(presupuestos.stream().allMatch(presupuesto -> presupuesto.valorTotal() <= presupuestoElegido.valorTotal()));

        revisores.forEach(revisor ->  revisor.getBandejaDeMensajes().recibirMensaje("Egreso generado"));
        return new Egreso(organizacion, presupuestoElegido.getProveedor(), LocalDate.now(), medioDePago, presupuestoElegido.getItems());
    }
}
