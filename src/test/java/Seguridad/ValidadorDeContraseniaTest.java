package Seguridad;

import Seguridad.ValidadorDeContrasenia.*;
import org.junit.*;

public class ValidadorDeContraseniaTest {

    @Test
    public void validadorLongitud_CorrectoSiEsMayor() {
        ValidadorDeContrasenia_Longitud validador = new ValidadorDeContrasenia_Longitud(5);
        validador.validar("ContraseniaValida");
    }

    @Test
    public void validadorLongitud_IncorrectoSiEsMenor() {
        ValidadorDeContrasenia_Longitud validador = new ValidadorDeContrasenia_Longitud(5);
        validador.validar("ContraseniaValida");
    }

    @Test
    public void validadorNoEnDiccinoario_CorrectoSiNoContiene() {
        ValidadorDeContrasenia_NoEnDiccionario validador = new ValidadorDeContrasenia_NoEnDiccionario("DiccionarioTest.txt");
        validador.validar("bqQxxUUGMPazGu39ycr7");
    }

    @Test
    public void validadorNoEnDiccinoario_InorrectoSiContiene() {
        ValidadorDeContrasenia_NoEnDiccionario validador = new ValidadorDeContrasenia_NoEnDiccionario("DiccionarioTest.txt");
        Assert.assertThrows(IllegalArgumentException.class, () -> validador.validar("123"));
    }

    @Test
    public void validadorTieneCaracterEspecial_CorrectoSiTiene() {
        ValidadorDeContrasenia_TieneCaracterEspecial validador = new ValidadorDeContrasenia_TieneCaracterEspecial();
        validador.validar("Contrasenia$");
    }

    @Test
    public void validadorTieneCaracterEspecial_InorrectoSiNoTiene() {
        ValidadorDeContrasenia_TieneCaracterEspecial validador = new ValidadorDeContrasenia_TieneCaracterEspecial();
        Assert.assertThrows(IllegalArgumentException.class, () -> validador.validar("SinCaracterEspecial"));
    }
}
