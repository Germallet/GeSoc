package Seguridad;

import org.junit.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class ContraseniaTest {
    private Contrasenia contrasenia;

    @Before
    public void inicializarTest() throws InvalidKeySpecException, NoSuchAlgorithmException {
        contrasenia = new Contrasenia("ContraseniaValida$");
    }

    @Test
    public void contraseniasDeMismoTextoSonIguales() throws InvalidKeySpecException, NoSuchAlgorithmException {
        Assert.assertTrue(contrasenia.esIgualA("ContraseniaValida$"));
    }

    @Test
    public void contaseniasDistintasNoSonIguales() throws InvalidKeySpecException, NoSuchAlgorithmException {
        Assert.assertFalse(contrasenia.esIgualA("ContraseniaIncorrecta"));
    }

    @Test
    public void noPuedeMedirMenosQue8() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new Contrasenia("Corta"));
    }

    @Test
    public void noPuedeSerComun() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new Contrasenia("12345678"));
    }
}
