package Organizaciones;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EntidadesTest {

    Base entidadBase;
    Juridica entidadJuridica;
    Juridica entidadJuridicaDistinta;

    @Before
    public void inicializarTest() {
        entidadJuridica = new Juridica("Mc Donalds S.A", "Mc Donalds", 2010133789, 1431, new OSC());
        entidadJuridicaDistinta = new Juridica("Burger King S.A", "Burger King", 2055133789, 1481, new OSC());
        entidadBase = new Base("Base", "Es una entidad base");

    }

    @Test
    public void unaEntidadBaseNoPuedePertenecerAMasDeUnaJuridica() {
        entidadJuridica.setEntidadBase(entidadBase);
        entidadBase.setEntidadJuridica(entidadJuridica);
        Assert.assertThrows(EntidadBaseException.class, () -> entidadJuridicaDistinta.setEntidadBase(entidadBase));
    }
}

