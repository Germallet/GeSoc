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
    public boolean esValido() {
        return presupuestoElegido != null && presupuestosSuficiente() && criterioDelMenor();
    }

    public void validar() {
        if (esValido())
            revisores.forEach(revisor ->  revisor.getBandejaDeMensajes().recibirMensaje(new Mensaje("Egreso generado")));
    }

    public int valorTotal(){
       return presupuestoElegido.valorTotal();
    }

    public boolean esDelUltimoMes() {
        return this.fecha.getYear() == LocalDate.now().getYear() && this.fecha.getMonth().equals(LocalDate.now().getMonth());
    }

    public void agregarEtiqueta(Etiqueta nuevaEtiqueta){
        this.etiquetas.add(nuevaEtiqueta);
    }
    public boolean tieneEtiqueta(Etiqueta unaEtiqueta){
        return this.etiquetas.contains(unaEtiqueta);
    }

    public String getUrl(){
        return "egresos/" + getId();
    }

    public int getValorTotal(){
        return this.valorTotal();
    }

    public boolean getEsDelUltimoMes(){
        return this.esDelUltimoMes();
    }

    public boolean getEsValido(){
        return this.esValido();
    }
}
