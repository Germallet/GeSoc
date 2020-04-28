package Egresos;

// quizas nos conviene que MedioDePago sea una clase para poder settear el numero identificador
//se descarta utilizar herencia porque supongo que puede haber mas de un metodo d pago
interface MedioDePago {

}

class TarjetaCredito implements MedioDePago{

}

class TarjetaDebito implements MedioDePago{

}

class Efectivo implements MedioDePago{

}

class CajeroAutomatico implements MedioDePago{

}

class DineroEnCuenta implements MedioDePago{

}


