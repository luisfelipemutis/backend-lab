package modelo;

/**
 *
 * @author Luis Mutis
 */
public class Cliente {

    private int idCliente;
    private String nombre;
    private String apellido;
    private String imei;
    private String telefono;
    private double saldo;

    public Cliente() {
    }

    public Cliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(String nombre, String apellido, String imei, String telefono, double saldo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.imei = imei;
        this.telefono = telefono;
        this.saldo = saldo;
    }

    public Cliente(int idCliente, String nombre, String apellido, String imei, String telefono, double saldo) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.imei = imei;
        this.telefono = telefono;
        this.saldo = saldo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nombre=" + nombre + ", apellido=" + apellido + ", imei=" + imei + ", telefono=" + telefono + ", saldo=" + saldo + '}';
    }

}
