package Seguridad;

import org.junit.*;

public class DiccionarioTest {
    private Diccionario diccionarioTest;

    @Before
    public void inicializarTest() {
        diccionarioTest = new Diccionario("DiccionarioTest.txt");
    }

    @Test
    public void diccionarioTestContieneContraseniaComún() {
        Assert.assertTrue(diccionarioTest.contiene("123"));
    }

    @Test
    public void diccionarioTestNoContieneContraseniaSegura() {
        Assert.assertFalse(diccionarioTest.contiene("bqQxxUUGMPazGu39ycr7"));
    }
}
