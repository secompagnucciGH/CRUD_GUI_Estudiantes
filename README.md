**
Constructor de la clase:
consultarButton.addActionListener: Añade un ActionListener al botón de consultar que llama al método conectar() cuando se hace clic en el botón.

Método listar:
Conecta a la base de datos y prepara una consulta SQL para insertar datos en la tabla estudiante.
Establece los parámetros de la consulta con los valores ingresados en los campos de texto y ejecuta la consulta con ps.executeUpdate().

Método insertar:
lógica para insertar datos,llamando al método insertar().

Método main:
Crea una instancia de la clase Estudiantes, establece el panel principal, define la operación de cierre de la ventana, hace visible la ventana y ajusta su tamaño.

Método conectar:
Intenta establecer una conexión con la base de datos MySQL utilizando DriverManager.getConnection().
Imprime un mensaje de éxito si la conexión se establece correctamente o lanza una excepción en caso de error.
**
