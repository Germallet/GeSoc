package Seguridad.ValidadorDeContrasenia;

import Seguridad.Diccionario;
import com.google.common.base.Preconditions;

public class ValidadorDeContrasenia_NoEnDiccionario implements ValidadorDeContrasenia {

    private String nombreArchivoDiccionario;

    public ValidadorDeContrasenia_NoEnDiccionario(String nombreArchivoDiccionario) {
        this.nombreArchivoDiccionario = nombreArchivoDiccionario;
    }

    @Override
    public void validar(String contrasenia) {
        Diccionario diezMilMasComunes = new Diccionario(nombreArchivoDiccionario);
        if(diezMilMasComunes.contiene(contrasenia))
            throw new PasswordException("Contraseña demasiado común");
    }
}
