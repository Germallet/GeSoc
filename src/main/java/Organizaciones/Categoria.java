package Organizaciones;

// utilizamos herencia porque segun interpretacion del enunciado se puede ser una sola de las categorias
public class Categoria{
}

class Empresa extends Categoria{
    Clasificacion clasificacion;

    Empresa(Clasificacion clasificacion){
        this.clasificacion= clasificacion;

}}

class OSC extends Categoria{
    Clasificacion clasificacion;

   OSC(Clasificacion clasificacion){
        this.clasificacion= clasificacion;
}
}