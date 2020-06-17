package Main;

import Egresos.CompraPendiente;

import java.util.ArrayList;
import java.util.List;

public class RepoCompraPendiente {
    private static List<CompraPendiente> comprasPendientes = new ArrayList<>();

    public static void agregarCompraPendiente(CompraPendiente nuevaCompraPendiente) {
        comprasPendientes.add(nuevaCompraPendiente);
    }

    public static void quitarCompraPendiente(CompraPendiente compraPendiente) {
        comprasPendientes.remove(compraPendiente);
    }

    public static void generarComprasPendientes() {
        comprasPendientes.forEach(compraPendiente -> compraPendiente.generarEgreso());
    }
}
