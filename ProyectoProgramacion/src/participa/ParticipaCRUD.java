package participa;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import CRUD.CRUDParticipa;

public class ParticipaCRUD implements CRUDParticipa<Participa>{
    Connection conn;
    public ParticipaCRUD(Connection conn){
        this.conn = conn;
    }   
    @Override
    public ArrayList<Participa> requestAll() throws SQLException {
        Statement statement = null;
        ArrayList<Participa> result = new ArrayList<Participa>();
        statement = this.conn.createStatement();
        String sql = "SELECT * FROM participa";
        ResultSet querySet = statement.executeQuery(sql);
        while (querySet.next()) {
            int CodPel = querySet.getInt("CodPel");
            int CodPer = querySet.getInt("CodPer");
            String Rol = querySet.getString("Rol");
            result.add(new Participa(CodPel, CodPer, Rol));
        }
        statement.close();
        return result;
    }
    // En vez de hacer un buscar por id, se hace un buscar por CodPel
    @Override
    public Participa requestById(long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'requestById'");
    }

    public Participa requestByCodPel(long id) throws SQLException {
        Statement statement = null;
        Participa result = null;
        statement = this.conn.createStatement();
        String sql = String.format("SELECT * FROM participa WHERE CodPel=%d", id);
        ResultSet querySet = statement.executeQuery(sql);
        if (querySet.next()) {
            int CodPer = querySet.getInt("CodPer");
            String Rol = querySet.getString("Rol");
            result = new Participa(id, CodPer, Rol);
        }
        statement.close();
        return result;
    }

    @Override
    public long create(Participa object) throws SQLException {
        String sql = "INSERT INTO participa (CodPel, CodPer, Rol) VALUES (?, ?, ?)";
        PreparedStatement prepst = this.conn.prepareStatement(sql);
        prepst.setLong(1, object.getCodPel());
        prepst.setLong(2, object.getCodPer());
        prepst.setString(3, object.getRol());

        int affectedRows = prepst.executeUpdate();
        prepst.close();

        if (affectedRows == 0) {
            throw new SQLException("Fallo al crear la participación, ninguna fila fue afectada");
        } else {
            return object.getCodPel(); 
        }
    }

    @Override
    public String update(Participa object) throws SQLException {
        String sql = "UPDATE participa SET CodPer = ?, Rol = ? WHERE CodPel = ?";
        PreparedStatement prepst = this.conn.prepareStatement(sql);
        prepst.setLong(1, object.getCodPer());
        prepst.setString(2, object.getRol());
        prepst.setLong(3, object.getCodPel());

        int result = prepst.executeUpdate();
        prepst.close();

        if (result == 1) {
            return "La operación se realizó correctamente"; 
        } else {
            return "No se encontró ninguna fila para actualizar"; 
        }
    }

    @Override
    public String delete(long id) throws SQLException {
        Statement statement = null;
        statement = this.conn.createStatement();
        String sql = String.format("DELETE FROM participa WHERE CodPel=%d", id);
    
        int result = statement.executeUpdate(sql);
        statement.close();
    
        if (result == 1) {
            return "La participación fue eliminada correctamente";  
        } else {
            return "No se encontró ninguna participación con ese CodPel"; 
        }
    }

}
