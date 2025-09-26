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

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getCedula() {
        return cedula;
    }

    public Cuenta[] getCuentasList() {
        return cuentas;
    }

    public boolean agregarCuenta(Cuenta cuenta) {
        for (int i = 0; i < cuentas.length; i++) {
            if (cuentas[i] == null) {
                cuentas[i] = cuenta;
                return true;
            }
        }
        return false;
    }

    public int numeroCuentas() {
        int c = 0;
        for (Cuenta cuenta : cuentas) {
            if (cuenta != null) {
                c++;
            }
        }
        return c;
    }

    public String toString() {
        return "Nombre: " + nombre + " | Edad: " + edad + " | Cédula: " + cedula;
    }
}
