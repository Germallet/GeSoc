package Seguridad;

import Seguridad.ValidadorDeContrasenia.*;
import org.junit.*;
import java.util.*;

public class ContraseniaTest {
    private Contrasenia contrasenia;

    @Before
    public void inicializarTest() {
        contrasenia = new Contrasenia("ContraseniaValida", new ArrayList<>());
    }

    @Test
    public void contraseniasDeMismoTextoSonIguales() {
        Assert.assertTrue(contrasenia.esIgualA("ContraseniaValida"));
    }

    @Test
    public void contaseniasDistintasNoSonIguales() {
        Assert.assertFalse(contrasenia.esIgualA("ContraseniaInvalida"));
    }

    @Test
    public void contraseniaCumpleMultiplesVerifidores() {
        Collection<ValidadorDeContrasenia> validadores = Arrays.asList(new ValidadorDeContrasenia_Longitud(5), new ValidadorDeContrasenia_TieneCaracterEspecial());
        new Contrasenia("ContraseniaValida$", validadores);
    }

    @Test
    public void contraseniaNoCumpleMultiplesVerifidores() {
        Collection<ValidadorDeContrasenia> validadores = Arrays.asList(new ValidadorDeContrasenia_Longitud(5), new ValidadorDeContrasenia_TieneCaracterEspecial());
        Assert.assertThrows(PasswordException.class, () ->
                new Contrasenia("ContraseniaInvalida", validadores)
        );
    }
}
