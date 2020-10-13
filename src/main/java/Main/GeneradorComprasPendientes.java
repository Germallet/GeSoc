package Main;

public class GeneradorComprasPendientes {
    public static void main(String[] args) {
        System.out.println("Generando compras pendientes");
        RepoOrganizaciones.repositorio().obtenerOrganizaciones().forEach(organizacion -> organizacion.validarEgresos());
    }
}