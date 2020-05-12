package Seguridad;

import org.junit.*;

import static Seguridad.TipoDeUsuario.*;

public class UsuarioTest {
    @Test
    public void UsuarioDebeTenerNombre() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new Usuario(ADMINISTRADOR, "", "ContraseniaVálida$"));
    }
}
