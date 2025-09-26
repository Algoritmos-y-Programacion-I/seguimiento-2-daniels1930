package model;

public class Cuenta {
    private String nombreBanco;
    private TipoCuenta tipo;
    private double saldo;

    public Cuenta(String nombreBanco, TipoCuenta tipo, double saldo) {
        this.nombreBanco = nombreBanco;
        this.tipo = tipo;

        if (saldo >= 0) {
            this.saldo = saldo;
        } else {
            this.saldo = 0; // validación mínima
        }
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public TipoCuenta getTipo() {
        return tipo;
    }

    public void setTipo(TipoCuenta tipo) {
        this.tipo = tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
        }
    }

    public boolean retirar(double monto) {
        if (monto > 0 && saldo >= monto) {
            saldo -= monto;
            return true;
        }
        return false;
    }

    public String toString() {
        return "Banco: " + nombreBanco + " | Tipo: " + tipo + " | Saldo: " + saldo;
    }
}
