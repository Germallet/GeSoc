package Main;

public class Main {
    public static void main(String[] args) {
        System.out.println("Generando compras pendientes");
        RepoOrganizaciones.repositorio().obtener().forEach(organizacion -> organizacion.validarEgresos());
    }
}