package model;

public class Cuenta {
    private String nombreBanco;
    private TipoCuenta tipo;
    private double saldo;

    /**
     * Descripción: Crea una nueva cuenta bancaria
     * @param nombreBanco nombre del banco, no puede ser null ni vacío
     * @param tipo tipo de cuenta, no puede ser null
     * @param saldo saldo inicial, debe ser mayor o igual a 0
     * @pre nombreBanco != null && !nombreBanco.trim().isEmpty()
     * @pre tipo != null
     * @pre saldo >= 0
     */
    public Cuenta(String nombreBanco, TipoCuenta tipo, double saldo) {
        this.nombreBanco = nombreBanco;
        this.tipo = tipo;

        if (saldo >= 0) {
            this.saldo = saldo;
        } else {
            this.saldo = 0; // validación mínima
        }
    }

    /**
     * Descripción: Obtiene el nombre del banco
     * @return nombre del banco
     */
    public String getNombreBanco() {
        return nombreBanco;
    }

    /**
     * Descripción: Establece el nombre del banco
     * @param nombreBanco nuevo nombre del banco
     */
    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    /**
     * Descripción: Obtiene el tipo de cuenta
     * @return tipo de cuenta
     */
    public TipoCuenta getTipo() {
        return tipo;
    }

    /**
     * Descripción: Establece el tipo de cuenta
     * @param tipo nuevo tipo de cuenta
     */
    public void setTipo(TipoCuenta tipo) {
        this.tipo = tipo;
    }

    /**
     * Descripción: Obtiene el saldo actual
     * @return saldo actual de la cuenta
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Descripción: Deposita dinero en la cuenta
     * @param monto cantidad a depositar, debe ser mayor que 0
     * @pre monto > 0
     * @pos this.saldo >= old(this.saldo)
     */
    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
        }
    }

    /**
     * Descripción: Retira dinero de la cuenta si hay fondos suficientes
     * @param monto cantidad a retirar, debe ser mayor que 0
     * @return true si el retiro fue exitoso, false en caso contrario
     * @pre monto > 0
     * @pos this.saldo >= 0
     */
    public boolean retirar(double monto) {
        if (monto > 0 && saldo >= monto) {
            saldo -= monto;
            return true;
        }
        return false;
    }

    /**
     * Descripción: Genera representación en cadena de la cuenta
     * @return cadena con información de la cuenta
     */
    public String toString() {
        return "Banco: " + nombreBanco + " | Tipo: " + tipo + " | Saldo: " + saldo;
    }
}