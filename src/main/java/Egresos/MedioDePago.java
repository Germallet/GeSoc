package Egresos;

public enum MedioDePago {
    TarjetaCredito, TarjetaDebito, Efectivo, CajeroAutomatico, DineroEnCuenta;

    @Override
    public String toString() {
        switch(this) {
            case TarjetaCredito: return "Tarjeta de Crédito";
            case TarjetaDebito: return "Tarjeta de Débito";
            case Efectivo: return "Efectivo";
            case CajeroAutomatico: return "Cajero Automático";
            case DineroEnCuenta: return "Dinero en Cuenta";
            default: throw new IllegalArgumentException();
        }
    }
}
