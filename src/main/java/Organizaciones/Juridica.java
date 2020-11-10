package Organizaciones;

import com.google.common.base.Preconditions;

import javax.persistence.Entity;

@Entity
public class Juridica extends Entidad {
    String razonSocial;
    int CUIT;
    int direccionPostal;
    int codigoDeInscripcion;

    public Juridica(String razonSocial, String nombreFicticio, int CUIT, int direccionPostal, Categoria categoria) {
        this.razonSocial = Preconditions.checkNotNull(razonSocial, "No se ingresó razón social");
        this.nombreFicticio = Preconditions.checkNotNull(nombreFicticio, "No se ingresó nombre ficticio");
        this.CUIT = CUIT;
        this.direccionPostal = direccionPostal;
        this.categoria = categoria; //Preconditions.checkNotNull(categoria, "No se ingresó categoría");
    }

    public Juridica() {
        super();
    }

    public void setCodigoDeInscripcion(int unCodigo) {
        codigoDeInscripcion = unCodigo;
    }

    public boolean permiteEntidadBase() {
        return categoria == null || categoria.permiteEntidadBase();
    }

    public String getTipo() { return "Jurídica"; }

    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial)  { this.razonSocial = razonSocial; }
    public int getCUIT() { return CUIT; }
    public void setCUIT(int CUIT)  { this.CUIT = CUIT; }
    public int getDireccionPostal() { return direccionPostal; }
    public void setDireccionPostal(int direccionPostal)  { this.direccionPostal = direccionPostal; }
}
