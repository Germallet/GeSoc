package Egresos;

public interface MedioDePago {
}

class TarjetaCredito implements MedioDePago {
    int id;

    TarjetaCredito(int id){
        this.id = id;
    }
}

class TarjetaDebito implements MedioDePago {
    int id;

    TarjetaDebito(int id){
        this.id = id;
    }
}

class Efectivo implements MedioDePago {
}

class CajeroAutomatico implements MedioDePago {
}

class DineroEnCuenta implements MedioDePago {
}
