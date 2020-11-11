package Egresos;

import Main.IDGenerator;
import Seguridad.Mensaje;
import Seguridad.Usuario;
import com.google.common.base.Preconditions;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import Organizaciones.*;

@Entity
public class Egreso extends IDGenerator {
    @OneToOne
    private DocumentoComercial documento;
    private LocalDate fecha;

    @Enumerated(EnumType.ORDINAL)
    private MedioDePago medioDePago;
    @OneToMany
    @JoinColumn(name="egreso_id")
    private List<Presupuesto> presupuestos = new ArrayList<>();
    @OneToOne
    private Presupuesto presupuestoElegido;
    @ManyToMany
    private List<Usuario> revisores = new ArrayList<>();
    private int presupuestosRequeridos;
    private boolean escogerMenor;
    @ManyToMany
    private List<Etiqueta> etiquetas = new ArrayList<>();


    public Egreso(DocumentoComercial documento, LocalDate fecha, MedioDePago unPago, int presupuestosRequeridos, boolean escogerMenor) {
        this.documento = documento;
        this.fecha = fecha;
        this.medioDePago = Preconditions.checkNotNull(unPago, "No se ingreso un medio de pago");
        this.presupuestosRequeridos = presupuestosRequeridos;
        this.escogerMenor = escogerMenor;
    }

    public Egreso() {
        super();
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
    public boolean getEsValido() { return presupuestoElegido != null && presupuestosSuficiente() && criterioDelMenor(); }

    public void validar() {
        if (getEsValido())
            revisores.forEach(revisor ->  revisor.getBandejaDeMensajes().recibirMensaje(new Mensaje("Egreso generado")));
    }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public MedioDePago getMedioDePago() { return medioDePago; }
    public void setMedioDePago(MedioDePago medioDePago) { this.medioDePago = medioDePago; }
    public int getPresupuestosRequeridos() { return presupuestosRequeridos; }
    public void setPresupuestosRequeridos(int presupuestosRequeridos) { this.presupuestosRequeridos = presupuestosRequeridos; }
    public Boolean getEscogerMenor(){ return escogerMenor; }
    public void setEscogerMenor(boolean escogerMenor) { this.escogerMenor = escogerMenor; }

    public int getValorTotal() { return presupuestoElegido.valorTotal(); }

    public boolean esDelUltimoMes() {
        return this.fecha.getYear() == LocalDate.now().getYear() && this.fecha.getMonth().equals(LocalDate.now().getMonth());
    }

    public void agregarEtiqueta(Etiqueta nuevaEtiqueta){
        this.etiquetas.add(nuevaEtiqueta);
    }
    public boolean tieneEtiqueta(Etiqueta unaEtiqueta){
        return this.etiquetas.contains(unaEtiqueta);
    }
}
