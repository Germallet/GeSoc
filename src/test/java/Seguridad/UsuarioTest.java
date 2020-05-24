package Seguridad;

import org.junit.*;
import java.util.ArrayList;

import static Seguridad.TipoDeUsuario.ADMINISTRADOR;

public class UsuarioTest {
    @Test
    public void UsuarioDebeTenerNombre() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new Usuario(ADMINISTRADOR, "", "ContraseniaVálida$", new ArrayList()));
    }
}
