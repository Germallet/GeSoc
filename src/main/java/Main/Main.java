package Main;

import Egresos.DocumentoComercial;
import Egresos.Egreso;
import Egresos.MedioDePago;
import Egresos.TipoDeDocumentoComercial;
import Localizacion.Ciudad;
import Localizacion.DireccionPostal;
import Organizaciones.Categoria;
import Organizaciones.Entidad;
import Organizaciones.Etiqueta;
import Organizaciones.Organizacion;
import Proveedor.*;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Generando compras pendientes");
        //RepoOrganizaciones.repositorio().obtenerOrganizaciones().forEach(organizacion -> organizacion.validarEgresos());

        DocumentoComercial documento = new DocumentoComercial(TipoDeDocumentoComercial.FACTURA);
        Egreso egreso1 = new Egreso(documento, LocalDate.now(), MedioDePago.TarjetaCredito ,3,false);

        Ciudad ciudad = new Ciudad();
        DireccionPostal dir = new DireccionPostal("Belgrano", "8av0",3120, ciudad);
        Identificador id = new Identificador(12, TipoDeID.CUIT);
        Proveedor prov = new Proveedor("Coca cola", id, dir);

        RepoOrganizaciones.repositorio().agregarProveedor(prov);

    }
}