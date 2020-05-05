package Seguridad;

import org.junit.*;

public class DiccionarioTest {
    private Diccionario diccionarioTest;

    @Before
    public void InicializarTest() {
        diccionarioTest = new Diccionario("DiccionarioTest.txt");
    }

    @Test
    public void DiccionarioTestContieneContraseniaComún() {
        Assert.assertTrue(diccionarioTest.Contiene("123"));
    }

    @Test
    public void DiccionarioTestNoContieneContraseniaSegura() {
        Assert.assertFalse(diccionarioTest.Contiene("bqQxxUUGMPazGu39ycr7"));
    }
}
