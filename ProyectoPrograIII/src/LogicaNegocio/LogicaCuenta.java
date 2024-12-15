    package LogicaNegocio;

    import AccesoDatos.AccesoDatos;
    import Seguridad.LogicaEncriptacion;
    import java.io.IOException;
    import java.util.ArrayList;

    /**
     * La clase LogicaCuenta gestiona tanto el proceso de inicio de sesión
     * como la creación y validación de cuentas de usuario.
     * 
     * @author dmsda
     */
    public class LogicaCuenta implements Servicios.ServicioLogicaCuenta {

        private final AccesoDatos accesoDatos;

        /**
         * Constructor de la clase LogicaCuenta.
         * Inicializa el objeto de acceso a datos y establece el archivo de usuarios.
         */
        public LogicaCuenta() {
            this.accesoDatos = new AccesoDatos();
            this.accesoDatos.setNombreArchivo("usuarios.txt");
        }

        /**
         * Verifica si la cuenta existe en el archivo de datos.
         *
         * @param numeroCuenta Número de la cuenta a verificar.
         * @return {@code true} si la cuenta existe, {@code false} en caso contrario.
         * @throws IOException Si ocurre un error al acceder a los datos.
         */
        @Override
        public boolean existeCuenta(String numeroCuenta) throws IOException {
            ArrayList<String[]> registros = accesoDatos.leerRegistros();
            for (String[] registro : registros) {
                if (registro[0].equals(numeroCuenta)) {
                    return true;
                }
            }
            return false;
        }
      
        
         
        

        /**
         * Crea una nueva cuenta verificando previamente si ya existe y encriptando la información.
         *
         * @param numeroCuenta Número de la cuenta a crear.
         * @param Nombre Nombre del titular de la cuenta.
         * @param saldo Saldo inicial de la cuenta.
         * @param pin PIN asociado a la cuenta.
         * @throws IOException Si ocurre un error al acceder a los datos.
         * @throws IllegalArgumentException Si el número de cuenta ya existe.
         * @throws Exception Si ocurre cualquier otro error.
         */
        @Override
        public void crearNuevaCuenta(String numeroCuenta, String nombreUsuario, String pin) throws IOException, IllegalArgumentException, Exception {
            if (existeCuenta(numeroCuenta)) {
                throw new IllegalArgumentException("El número de cuenta ya existe.");
            }

            LogicaEncriptacion encriptacion = new LogicaEncriptacion();
            String pinEncriptado = encriptacion.encriptarPin(pin);

            // Crear el registro
            String nuevaCuenta = numeroCuenta + "," + nombreUsuario + "," + pinEncriptado;

            // Guardar el registro en el archivo
            accesoDatos.agregarRegistro(nuevaCuenta);
        }




        /**
         * Valida las credenciales de usuario contra un archivo de texto.
         *
         * @param usuario El nombre de usuario ingresado.
         * @param contrasena La contraseña ingresada.
         * @return true si las credenciales son válidas, false en caso contrario.
         * @throws IOException Si ocurre un error al acceder a los datos.
         * @throws Exception Si ocurre un error durante la verificación.
         */
    @Override
    public boolean validarCredenciales(String cedula, String pin) throws IOException, Exception {
        AccesoDatos accesoDatos = new AccesoDatos();

        // Encriptar el PIN ingresado por el usuario utilizando LogicaEncriptacion
        LogicaEncriptacion logicaEncriptacion = new LogicaEncriptacion();
        String hashPin = logicaEncriptacion.encriptarPin(pin);

        // Leer usuarios y verificar credenciales
        ArrayList<String[]> usuarios = accesoDatos.leerRegistros();
        for (String[] usuario : usuarios) {
            if (usuario[0].equals(cedula) && usuario[2].equals(hashPin)) {
                return true; // Autenticación exitosa
            }
        }
        return false; // Credenciales incorrectas
    }


    }
