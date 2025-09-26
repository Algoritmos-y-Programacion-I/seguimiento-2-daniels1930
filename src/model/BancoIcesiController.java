package model;

public class BancoIcesiController {

    private Cliente[] clientes;

    public BancoIcesiController(int size) {
        clientes = new Cliente[size];
    }

    public Cliente[] getClientes() {
        return clientes;
    }

    public void setClientes(Cliente[] clientes) {
        this.clientes = clientes;
    }

    public void registrarCliente(String nombre, int edad, String cedula) {
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] == null) {
                clientes[i] = new Cliente(nombre, edad, cedula);
                break;
            }
        }
    }

    public void agregarCuentaCliente(String cedula, String banco, TipoCuenta tipo, double saldo) {
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null) {
                if (clientes[i].getCedula().equals(cedula)) {
                    clientes[i].agregarCuenta(new Cuenta(banco, tipo, saldo));
                    break;
                }
            }
        }
    }

    public void depositarDineroCuenta(String cedula, int index, double monto) {
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null) {
                if (clientes[i].getCedula().equals(cedula)) {
                    clientes[i].getCuentasList()[index].depositar(monto);
                    break;
                }
            }
        }
    }

    public void retirarDineroCuenta(String cedula, int index, double monto) {
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null) {
                if (clientes[i].getCedula().equals(cedula)) {
                    clientes[i].getCuentasList()[index].retirar(monto);
                    break;
                }
            }
        }
    }

    public double calcularSaldoTotal() {
        double total = 0;
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null) {
                Cuenta[] cuentas = clientes[i].getCuentasList();
                for (int j = 0; j < cuentas.length; j++) {
                    if (cuentas[j] != null) {
                        total += cuentas[j].getSaldo();
                    }
                }
            }
        }
        return total;
    }
}
