package model;

public class Cliente {
    private String nombre;
    private int edad;
    private String cedula;
    private Cuenta[] cuentas;

    public Cliente(String nombre, int edad, String cedula) {
        this.nombre = nombre;

        if (edad > 0) {
            this.edad = edad;
        } else {
            this.edad = 1; // validación mínima
        }

        this.cedula = cedula;
        this.cuentas = new Cuenta[10]; // máximo 10 cuentas 
    }

    /**
     * Descripción: Obtiene el nombre del cliente
     * @return nombre del cliente
     * @pos resultado != null && !resultado.trim().isEmpty()
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Descripción: Obtiene la edad del cliente
     * @return edad del cliente
     * @pos resultado >= 1
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Descripción: Obtiene la cédula del cliente
     * @return cédula del cliente
     * @pos resultado != null && !resultado.trim().isEmpty()
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Descripción: Obtiene el arreglo de cuentas del cliente
     * @return arreglo de cuentas del cliente
     * @pos resultado != null && resultado.length == 10
     */
    public Cuenta[] getCuentasList() {
        return cuentas;
    }

    /**
     * Descripción: Agrega una cuenta bancaria al cliente si hay espacio disponible
     * @param cuenta cuenta bancaria a agregar, no puede ser null
     * @return true si la cuenta fue agregada exitosamente, false si no hay espacio
     * @pre cuenta != null
     * @pos (resultado == true) ==> (numeroCuentas() == old(numeroCuentas()) + 1)
     * @pos (resultado == false) ==> (numeroCuentas() == old(numeroCuentas()))
     * @pos numeroCuentas() <= 10
     */
    public boolean agregarCuenta(Cuenta cuenta) {
        for (int i = 0; i < cuentas.length; i++) {
            if (cuentas[i] == null) {
                cuentas[i] = cuenta;
                return true;
            }
        }
        return false;
    }

    /**
     * Descripción: Calcula el número total de cuentas que tiene el cliente
     * @return número de cuentas no nulas del cliente
     * @pos resultado >= 0 && resultado <= 10
     */
    public int numeroCuentas() {
        int c = 0;
        for (Cuenta cuenta : cuentas) {
            if (cuenta != null) {
                c++;
            }
        }
        return c;
    }

    /**
     * Descripción: Genera una representación en cadena de texto del cliente
     * @return cadena con información del cliente (nombre, edad y cédula)
     * @pos resultado != null
     * @pos resultado contiene nombre, edad y cédula
     */
    public String toString() {
        return "Nombre: " + nombre + " | Edad: " + edad + " | Cédula: " + cedula;
    }
}