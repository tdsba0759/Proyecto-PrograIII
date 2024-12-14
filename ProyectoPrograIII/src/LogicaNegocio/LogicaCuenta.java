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
        public void crearNuevaCuenta(String numeroCuenta, String Nombre, double saldo, String pin) throws IOException, IllegalArgumentException, Exception {
            if (existeCuenta(numeroCuenta)) {
                throw new IllegalArgumentException("El número de cuenta ya existe.");
            }

            LogicaEncriptacion encriptacion = new LogicaEncriptacion();
            String pinEncriptado = encriptacion.encriptarPin(pin);
            String nuevaCuenta = numeroCuenta + "," + Nombre + "," + saldo + "," + pinEncriptado;
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
    public boolean validarCredenciales(String usuario, String contrasena) throws IOException, Exception {
        ArrayList<String[]> registros = accesoDatos.leerRegistros();
        LogicaEncriptacion encriptacion = new LogicaEncriptacion();

        for (String[] datosUsuario : registros) {
            // Verificar que el arreglo tenga al menos 4 elementos
            if (datosUsuario.length >= 4) {
                String usuarioGuardado = datosUsuario[0].trim();
                String contrasenaGuardada = datosUsuario[3].trim(); // Acceder al PIN encriptado

                if (usuario.equals(usuarioGuardado)) {
                    // Verificar si el PIN ingresado coincide con el PIN encriptado
                    if (encriptacion.verificarPin(contrasena, contrasenaGuardada)) {
                        return true;
                    }
                }
            } else {
                System.out.println("Error: el registro de usuario no tiene el formato correcto.");
            }
        }
        return false;
    }

    }
