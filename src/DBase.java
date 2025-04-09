/**
 *
 * @author Sebastián G.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBase {
    public void maxId(Connection conn, Clientes ct) throws SQLException {
        String sql = "SELECT COALESCE(MAX(id), 0) + 1 AS new_id FROM clientes";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                ct.setId(rs.getInt("new_id"));
            }
        }
    }

    public void insert(Connection conn, Clientes ct) throws SQLException {
        String sql = "INSERT INTO clientes (id, nombre, direction, telefono) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ct.getId());
            stmt.setString(2, ct.getNombre());
            stmt.setString(3, ct.getDirection());
            stmt.setString(4, ct.getTelefono());
            stmt.executeUpdate();
        }
    }

    public boolean select(Connection conn, Clientes ct, int id) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ct.setId(rs.getInt("id"));
                    ct.setNombre(rs.getString("nombre"));
                    ct.setDirection(rs.getString("direction"));
                    ct.setTelefono(rs.getString("telefono"));
                    return true;
                }
                return false;
            }
        }
    }

    public boolean update(Connection conn, Clientes ct) throws SQLException {
        String sql = "UPDATE clientes SET nombre = ?, direction = ?, telefono = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ct.getNombre());
            stmt.setString(2, ct.getDirection());
            stmt.setString(3, ct.getTelefono());
            stmt.setInt(4, ct.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean delete(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
}
