package Seguridad;

import org.junit.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class ContraseniaTest {
    private Contrasenia contrasenia;

    @Before
    public void InicializarTest() throws InvalidKeySpecException, NoSuchAlgorithmException {
        contrasenia = new Contrasenia("ContraseniaVálida$");
    }

    @Test
    public void ContraseniasDeMismoTextoSonIguales() throws InvalidKeySpecException, NoSuchAlgorithmException {
        Assert.assertTrue(contrasenia.EsIgualA("ContraseniaVálida$"));
    }

    @Test
    public void ContaseniasDistintasNoSonIguales() throws InvalidKeySpecException, NoSuchAlgorithmException {
        Assert.assertFalse(contrasenia.EsIgualA("ContraseniaIncorrecta"));
    }

    @Test
    public void NoPuedeMedirMenosQue8() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new Contrasenia("Corta"));
    }

    @Test
    public void NoPuedeSerComún() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new Contrasenia("12345678"));
    }
}
