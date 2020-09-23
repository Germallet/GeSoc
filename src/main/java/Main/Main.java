package Main;

public class Main {
    public static void main(String[] args) {
        System.out.println("Generando compras pendientes");
        RepoOrganizaciones.repositorio().obtenerOrganizaciones().forEach(organizacion -> organizacion.validarEgresos());
    }
}