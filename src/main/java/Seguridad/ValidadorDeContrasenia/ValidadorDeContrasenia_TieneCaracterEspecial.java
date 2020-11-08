package Seguridad.ValidadorDeContrasenia;

import com.google.common.base.Preconditions;

import java.util.regex.Pattern;

public class ValidadorDeContrasenia_TieneCaracterEspecial implements ValidadorDeContrasenia {
    @Override
    public void validar(String contrasenia) {
        if(!Pattern.matches("^(?=.*[@#$%^&+=]).*$", contrasenia))
            throw new PasswordException("La contraseña no tiene un caracter especial");
    }
}
