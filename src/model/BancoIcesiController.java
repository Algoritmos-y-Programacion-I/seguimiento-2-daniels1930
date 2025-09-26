package model;

public class BancoIcesiController {

    private Cliente[] clientes;

    /**
     * Descripción: Crea un nuevo controlador para el banco con capacidad para un número determinado de clientes
     * @param size tamaño del arreglo de clientes, debe ser mayor que 0
     * @pre size > 0
     * @pos this.clientes != null && this.clientes.length == size
     * @pos contarClientesRegistrados() == 0
     */
    public BancoIcesiController(int size) {
        clientes = new Cliente[size];
    }

    /**
     * Descripción: Obtiene el arreglo de clientes del banco
     * @return arreglo de clientes del banco
     * @pos resultado != null && resultado.length > 0
     */
    public Cliente[] getClientes() {
        return clientes;
    }

    /**
     * Descripción: Establece un nuevo arreglo de clientes para el banco
     * @param clientes nuevo arreglo de clientes, no puede ser null
     * @pre clientes != null && clientes.length > 0
     * @pos this.clientes == clientes
     */
    public void setClientes(Cliente[] clientes) {
        this.clientes = clientes;
    }

    /**
     * Descripción: Registra un nuevo cliente en el banco
     * @param nombre nombre del cliente, no puede ser null ni vacío
     * @param edad edad del cliente, debe ser mayor que 0
     * @param cedula cédula del cliente, no puede ser null ni vacía y debe ser única
     * @pre nombre != null && !nombre.trim().isEmpty()
     * @pre edad > 0
     * @pre cedula != null && !cedula.trim().isEmpty()
     * @pre buscarClientePorCedula(cedula) == null
     * @pre contarClientesRegistrados() < clientes.length
     * @pos buscarClientePorCedula(cedula) != null
     * @pos contarClientesRegistrados() == old(contarClientesRegistrados()) + 1
     */
    public void registrarCliente(String nombre, int edad, String cedula) {
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] == null) {
                clientes[i] = new Cliente(nombre, edad, cedula);
                break;
            }
        }
    }

    /**
     * Descripción: Agrega una cuenta bancaria a un cliente existente
     * @param cedula cédula del cliente, no puede ser null ni vacía
     * @param banco nombre del banco, no puede ser null ni vacío
     * @param tipo tipo de cuenta, no puede ser null
     * @param saldo saldo inicial de la cuenta, debe ser mayor o igual a 0
     * @pre cedula != null && !cedula.trim().isEmpty()
     * @pre banco != null && !banco.trim().isEmpty()
     * @pre tipo != null
     * @pre saldo >= 0
     * @pre buscarClientePorCedula(cedula) != null
     * @pos El cliente tiene una cuenta más (si había espacio disponible)
     */
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

    /**
     * Descripción: Deposita dinero en una cuenta específica de un cliente
     * @param cedula cédula del cliente, no puede ser null ni vacía
     * @param index índice de la cuenta en el arreglo de cuentas del cliente
     * @param monto cantidad de dinero a depositar, debe ser mayor que 0
     * @pre cedula != null && !cedula.trim().isEmpty()
     * @pre index >= 0 && index < 10
     * @pre monto > 0
     * @pre buscarClientePorCedula(cedula) != null
     * @pre buscarClientePorCedula(cedula).getCuentasList()[index] != null
     * @pos La cuenta tiene el saldo aumentado en la cantidad depositada
     */
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

    /**
     * Descripción: Retira dinero de una cuenta específica de un cliente
     * @param cedula cédula del cliente, no puede ser null ni vacía
     * @param index índice de la cuenta en el arreglo de cuentas del cliente
     * @param monto cantidad de dinero a retirar, debe ser mayor que 0
     * @pre cedula != null && !cedula.trim().isEmpty()
     * @pre index >= 0 && index < 10
     * @pre monto > 0
     * @pre buscarClientePorCedula(cedula) != null
     * @pre buscarClientePorCedula(cedula).getCuentasList()[index] != null
     * @pos Si hay fondos suficientes, la cuenta tiene el saldo disminuido en la cantidad retirada
     */
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

    /**
     * Descripción: Calcula el saldo total de todas las cuentas de todos los clientes
     * @return suma de todos los saldos de todas las cuentas bancarias
     * @pos resultado >= 0
     */
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

    /**
     * Descripción: Busca un cliente por su número de cédula
     * @param cedula cédula del cliente a buscar, no puede ser null ni vacía
     * @return cliente encontrado o null si no existe
     * @pre cedula != null && !cedula.trim().isEmpty()
     * @pos (resultado != null) ==> resultado.getCedula().equals(cedula)
     */
    public Cliente buscarClientePorCedula(String cedula) {
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null) {
                if (clientes[i].getCedula().equals(cedula)) {
                    return clientes[i];
                }
            }
        }
        return null;
    }

    /**
     * Descripción: Cuenta el número de clientes registrados en el banco
     * @return número de clientes no nulos en el arreglo
     * @pos resultado >= 0 && resultado <= clientes.length
     */
    private int contarClientesRegistrados() {
        int count = 0;
        if (clientes != null) {
            for (Cliente cliente : clientes) {
                if (cliente != null) {
                    count++;
                }
            }
        }
        return count;
    }
}