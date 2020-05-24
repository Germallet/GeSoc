package Seguridad;

import org.junit.*;

public class ContraseniaTest {
    private Contrasenia contrasenia;

    @Before
    public void inicializarTest() {
        contrasenia = new Contrasenia("ContraseniaValida$");
    }

    @Test
    public void contraseniasDeMismoTextoSonIguales() {
        Assert.assertTrue(contrasenia.esIgualA("ContraseniaValida$"));
    }

    @Test
    public void contaseniasDistintasNoSonIguales() {
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
