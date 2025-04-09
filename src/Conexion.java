/**
 *
 * @author Sebastián G.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    private Connection conn;
    
    public Connection getConexion() throws SQLException {
        if (conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/storage", "root", "");
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, 
                    "Error: No se encontró el driver JDBC", 
                    "Error de conexión", JOptionPane.ERROR_MESSAGE);
                conn = null;
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, 
                    "Error al conectar con la base de datos: " + ex.getMessage(), 
                    "Error de conexión", JOptionPane.ERROR_MESSAGE);
                conn = null;
            }
        }
        return conn;
    }
    
    public void cerrarConexion() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                "Error al cerrar la conexión: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
