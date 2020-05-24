package Seguridad.ValidadorDeContrasenia;

import com.google.common.base.Preconditions;

public class ValidadorDeContrasenia_Longitud implements ValidadorDeContrasenia {

    private int tamanioMinimo;

    public ValidadorDeContrasenia_Longitud(int tamanioMinimo) { this.tamanioMinimo = tamanioMinimo; }

    @Override
    public void validar(String contrasenia) {
        Preconditions.checkArgument(contrasenia.length() >= tamanioMinimo, new IllegalArgumentException("Contraseña demasiado corta"));
    }
}
