package Seguridad.ValidadorDeContrasenia;

import com.google.common.base.Preconditions;

public class ValidadorDeContrasenia_Longitud implements ValidadorDeContrasenia {

    private int tamanioMinimo;

    public ValidadorDeContrasenia_Longitud(int tamanioMinimo) { this.tamanioMinimo = tamanioMinimo; }

    @Override
    public void validar(String contrasenia) {
        if (contrasenia.length() < tamanioMinimo)
            throw new PasswordException("Contraseña demasiado corta");
    }
}
