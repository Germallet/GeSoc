package Seguridad.ValidadorDeContrasenia;

import com.google.common.base.Preconditions;

import java.util.regex.Pattern;

public class ValidadorDeContrasenia_TieneCaracterEspecial implements ValidadorDeContrasenia {
    @Override
    public void validar(String contrasenia) {
        Preconditions.checkArgument(Pattern.matches("^(?=.*[@#$%^&+=]).*$", contrasenia), new IllegalArgumentException("La contraseña no tiene un caracter especial"));
    }
}
