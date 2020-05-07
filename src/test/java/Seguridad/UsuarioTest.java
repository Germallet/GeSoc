package Seguridad;

import org.junit.*;

public class UsuarioTest {
    @Test
    public void UsuarioDebeTenerNombre() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new Usuario(new Administrador(), "", "ContraseniaVálida$"));
    }
}
