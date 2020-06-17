package Seguridad;

import org.junit.*;
import java.util.ArrayList;

import static Seguridad.TipoDeUsuario.ADMINISTRADOR;

public class UsuarioTest {
    @Test
    public void usuarioDebeTenerNombre() {
        Contrasenia contrasenia = new Contrasenia("Contrasenia", new ArrayList());
        Assert.assertThrows(IllegalArgumentException.class, () -> new Usuario(ADMINISTRADOR, "", contrasenia));
    }
}
