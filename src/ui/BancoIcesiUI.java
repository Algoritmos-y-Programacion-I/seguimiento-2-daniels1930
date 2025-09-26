package ui;

import java.util.Scanner;
import model.BancoIcesiController;
import model.Cliente;
import model.Cuenta;
import model.TipoCuenta;

public class BancoIcesiUI {

    private Scanner input;
    private BancoIcesiController controller;

    public static void main(String[] args) {
        BancoIcesiUI ui = new BancoIcesiUI();
        ui.menu();
    }

    /**
     * Descripción: Crea una nueva interfaz de usuario para el banco
     * @pos this.input != null && this.controller != null
     */
    public BancoIcesiUI() {
        input = new Scanner(System.in);
        System.out.println("Ingrese el número de clientes que desea registrar: ");
        int size = input.nextInt();
        input.nextLine();
        controller = new BancoIcesiController(size);
    }

    /**
     * Descripción: Muestra el menú principal y procesa las opciones del usuario
     * @pos El sistema se ejecuta hasta que el usuario selecciona salir
     */
    public void menu() {
        System.out.println("Bienvenido a BancoIcesi");

        int option = 0;
        do {
            System.out.println("\nMenu Principal");
            System.out.println("------------------------------");
            System.out.println("1) Registrar cliente");
            System.out.println("2) Asignar cuenta bancaria a cliente");
            System.out.println("3) Depositar dinero en cuenta bancaria de un cliente");
            System.out.println("4) Retirar dinero de una cuenta bancaria de un cliente");
            System.out.println("5) Consultar cliente por numero de cedula");
            System.out.println("6) Consultar el saldo total de todas las cuentas");
            System.out.println("0) Salir del sistema");
            option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1:
                    registrarCliente();
                    break;
                case 2:
                    asignarCuentaBancariaCliente();
                    break;
                case 3:
                    depositarDineroCuenta();
                    break;
                case 4:
                    retirarDineroCuenta();
                    break;
                case 5:
                    consultarCliente();
                    break;
                case 6:
                    consultarSaldoTotal();
                    break;
                case 0:
                    System.out.println("Gracias por usar nuestros servicios. Adios!");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
                    break;
            }
        } while (option != 0);
    }

    /**
     * Descripción: Solicita los datos del cliente y lo registra en el sistema
     * @pos Se registra un nuevo cliente si hay espacio disponible
     */
    public void registrarCliente() {
        System.out.println("Ingrese nombre del cliente:");
        String nombre = input.nextLine();
        System.out.println("Ingrese edad:");
        int edad = input.nextInt();
        input.nextLine();
        System.out.println("Ingrese cedula:");
        String cedula = input.nextLine();

        controller.registrarCliente(nombre, edad, cedula);
    }

    /**
     * Descripción: Solicita los datos para asignar una cuenta bancaria a un cliente
     * @pos Se agrega una cuenta al cliente si existe y tiene espacio
     */
    public void asignarCuentaBancariaCliente() {
        System.out.println("Ingrese cedula del cliente:");
        String cedula = input.nextLine();
        System.out.println("Ingrese nombre del banco:");
        String banco = input.nextLine();
        System.out.println("Ingrese tipo de cuenta (AHORROS / CORRIENTE):");
        String tipoStr = input.nextLine().toUpperCase();
        TipoCuenta tipo = TipoCuenta.valueOf(tipoStr);
        System.out.println("Ingrese saldo inicial:");
        double saldo = input.nextDouble();
        input.nextLine();

        controller.agregarCuentaCliente(cedula, banco, tipo, saldo);
    }

    /**
     * Descripción: Solicita los datos para depositar dinero en una cuenta
     * @pos Se deposita el dinero si el cliente y la cuenta existen
     */
    public void depositarDineroCuenta() {
        System.out.println("Ingrese cedula del cliente:");
        String cedula = input.nextLine();
        System.out.println("Ingrese indice de la cuenta:");
        int index = input.nextInt();
        input.nextLine();
        System.out.println("Ingrese monto a depositar:");
        double monto = input.nextDouble();
        input.nextLine();

        controller.depositarDineroCuenta(cedula, index, monto);
    }

    /**
     * Descripción: Solicita los datos para retirar dinero de una cuenta
     * @pos Se retira el dinero si hay fondos suficientes
     */
    public void retirarDineroCuenta() {
        System.out.println("Ingrese cedula del cliente:");
        String cedula = input.nextLine();
        System.out.println("Ingrese indice de la cuenta:");
        int index = input.nextInt();
        input.nextLine();
        System.out.println("Ingrese monto a retirar:");
        double monto = input.nextDouble();
        input.nextLine();

        controller.retirarDineroCuenta(cedula, index, monto);
    }

    /**
     * Descripción: Busca y muestra la información de un cliente por su cédula
     * @pos Se muestra la información del cliente si existe
     */
    public void consultarCliente() {
        System.out.println("Ingrese cedula del cliente:");
        String cedula = input.nextLine();
        Cliente cliente = controller.buscarClientePorCedula(cedula);
        if (cliente != null) {
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Edad: " + cliente.getEdad());
            System.out.println("Cedula: " + cliente.getCedula());
            Cuenta[] cuentas = cliente.getCuentasList();
            for (int i = 0; i < cuentas.length; i++) {
                if (cuentas[i] != null) {
                    System.out.println("Cuenta #" + i);
                    System.out.println("Banco: " + cuentas[i].getNombreBanco());
                    System.out.println("Tipo: " + cuentas[i].getTipo());
                    System.out.println("Saldo: " + cuentas[i].getSaldo());
                }
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    /**
     * Descripción: Calcula y muestra el saldo total de todas las cuentas
     * @pos Se muestra el saldo total del banco
     */
    public void consultarSaldoTotal() {
        double total = controller.calcularSaldoTotal();
        System.out.println("El saldo total de todas las cuentas es: " + total);
    }
}