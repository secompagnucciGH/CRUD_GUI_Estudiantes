package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Estudiantes extends JFrame {
    // Componentes de la interfaz gráfica de usuario
    private JPanel panel; // Panel principal de la GUI
    private JTextField txtCel; // Campo de texto para el número de celular
    private JTextField txtCarrera; // Campo de texto para la carrera
    private JTextField txtId; // Campo de texto para el ID
    private JTextField txtNombre; // Campo de texto para el nombre
    private JTextField txtApellido; // Campo de texto para el apellido
    private JTextField txtEdad; // Campo de texto para la edad
    private JButton ingresarButton; // Botón para ingresar datos
    private JButton consultarButton; // Botón para consultar datos
    private JList list1; // Lista para mostrar datos

    // Declaración de PreparedStatement para consultas SQL
    PreparedStatement ps;
    // Declaración de la conexión
    Connection con;
    DefaultListModel mod = new DefaultListModel();
    Statement st;
    ResultSet r;
    // Constructor de la clase
    public Estudiantes() {

        // Añadiendo un ActionListener al botón de consultar
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    listar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    insertar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }



    // Método para listar (insertar) datos en la base de datos
    public void listar() throws SQLException {
        conectar(); // Conectar a la base de datos
        list1.setModel(mod);
        st = con.createStatement();
        r = st.executeQuery("SELECT id,nombre,apellido FROM estudiante");
        mod.removeAllElements();
        while (r.next()) {
            mod.addElement(r.getString(1) + " " + r.getString(2) + " " + r.getString(3));
        }
    }

    // Método para insertar datos (aún sin implementar)
    public void insertar() throws SQLException {
        conectar(); // Conectar a la base de datos
        // Preparar la consulta SQL para insertar datos en la tabla estudiante
        ps  = con.prepareStatement("INSERT INTO estudiante VALUES(?,?,?,?,?,?)");
        // Establecer los parámetros de la consulta SQL
        ps.setInt(1, Integer.parseInt(txtId.getText())); // ID
        ps.setString(2, txtNombre.getText()); // Nombre
        ps.setString(3, txtApellido.getText()); // Apellido
        ps.setInt(4, Integer.parseInt(txtEdad.getText())); // Edad
        ps.setString(5, txtCel.getText()); // Celular
        ps.setString(6, txtCarrera.getText()); // Carrera
//         Ejecutar la consulta SQL ps.executeUpdate();
        if ( ps.executeUpdate()>0) {
            list1.setModel(mod);
            mod.removeAllElements();
            mod.addElement(" Inserción exitosa ");

            txtId.setText("");
            txtNombre.setText("");
            txtApellido.setText("");
            txtEdad.setText("");
            txtCel.setText("");
            txtCarrera.setText("");
        }
    }

    // Método principal para mostrar la GUI
    public static void main(String[] args) {
        Estudiantes f = new Estudiantes(); // Crear instancia de la clase Estudiantes
        f.setContentPane(new Estudiantes().panel); // Establecer el panel principal
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana
        f.setVisible(true); // Hacer visible la ventana
        f.pack(); // Ajustar el tamaño de la ventana
    }


    // Método para conectar a la base de datos MySQL
    public void conectar() {
        try {
            // Establecer la conexión con la base de datos
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/learning", "root", "mysql");
            System.out.println("CONECCIÓN EXITOSA."); // Mensaje de éxito
        } catch (SQLException e) {
            throw new RuntimeException(e); // Manejo de la excepción en caso de error
        }
    }
}
