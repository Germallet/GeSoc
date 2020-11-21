package Server;


import java.time.LocalDate;
import java.util.ArrayList;

import Persistence.WithGlobalEntityManagerEnv;
import org.apache.commons.lang3.ObjectUtils;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import Localizacion.*;
import Proveedor.*;
import java.util.*;
import java.time.LocalDate;
import Egresos.*;


public class Bootstrap implements WithGlobalEntityManagerEnv, EntityManagerOps, TransactionalOps{

    public static void main(String[] args) {
        new Bootstrap().init();
    }

    public void init(){
        withTransaction(() ->{

            DocumentoComercial documento;
            Egreso egreso;
            Presupuesto presupuestoA;
            Presupuesto presupuestoB;
            Ciudad ciudad = new Ciudad("1","la plata");
            DireccionPostal direccion = new DireccionPostal("mitre","6",3,ciudad);

            documento = new DocumentoComercial(TipoDeDocumentoComercial.FACTURA);
            Proveedor proveedor = new Proveedor("juan", new Identificador(0, TipoDeID.DNI), direccion);

            Item itemA = new Item("itemA", 100, TipoDeItem.PRODUCTO);
            Item itemB = new Item("itemB", 200, TipoDeItem.SERVICIO);
            Item itemC = new Item("itemC", 300, TipoDeItem.PRODUCTO);

            List<Item> itemsA = new ArrayList<>();
            itemsA.add(itemA);
            List<Item> itemsB = new ArrayList<>();
            itemsB.add(itemB);
            itemsB.add(itemC);

            presupuestoA = new Presupuesto(proveedor, itemsA, documento);
            presupuestoB = new Presupuesto(proveedor, itemsB, documento);

            egreso = new Egreso(documento, LocalDate.now(), MedioDePago.TarjetaCredito, 2, false);
            persist(egreso);

        });
    }

}