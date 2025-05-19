package actores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import CRUD.CRUD;

public class ActorCRUD implements CRUD<Actor> {
    Connection conn;
    public ActorCRUD(Connection conn){
        this.conn = conn;
    }
    @Override
    public ArrayList<Actor> requestAll() throws SQLException {
        Statement statement = null;
        ArrayList<Actor> result = new ArrayList<Actor>();
        statement = this.conn.createStatement();
        String sql = "SELECT * FROM actores";
        ResultSet querySet = statement.executeQuery(sql);
        while (querySet.next()) {
            int CodAct = querySet.getInt("CodAct");
            String NomAct = querySet.getString("NomAct");
            String ApeAct = querySet.getString("ApeAct");
            result.add(new Actor(CodAct, NomAct, ApeAct));
        }
        statement.close();
        return result;
    }

    @Override
    public Actor requestById(long id) throws SQLException {
        Statement statement = null;
        Actor result = null;
        statement = this.conn.createStatement();
        String sql = String.format("SELECT * FROM actores WHERE CodAct=%d", id);
        ResultSet querySet = statement.executeQuery(sql);
        if (querySet.next()) {
            String NomAct = querySet.getString("NomAct");
            String ApeAct = querySet.getString("ApeAct");
            result = new Actor(id, NomAct, ApeAct);
        }
        statement.close();
        return result;
    }

    @Override
    public long create(Actor object) throws SQLException {
        String sql = String.format("INSERT INTO actores (NomAct, ApeAct) VALUES (?, ?)");
        PreparedStatement prepst = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        prepst.setString(1, object.getNomAct());
        prepst.setString(2, object.getApeAct());
        prepst.execute();
        ResultSet keys = prepst.getGeneratedKeys();
        if (keys.next()) {
            long CodAct = keys.getLong(1);
            prepst.close();
            return CodAct;
        }
        else{
            throw new SQLException("Fallo al crear el campo, ninguna fila afectada");
        }
    }

    @Override
    public int update(Actor object) throws SQLException {
        long CodAct = object.getCodAct();
        String NomAct = object.getNomAct();
        String ApeAct = object.getApeAct();
        Statement statement = null;
        statement = this.conn.createStatement();
        String sql = String.format("UPDATE actores SET NomAct='%s', ApeAct='%s' WHERE CodAct=%d", NomAct, ApeAct, CodAct);
        int result = statement.executeUpdate(sql);
        statement.close();
        if (result == 1) {
            return 1;
        } else {
            throw new SQLException("Fallo al actualizar el campo, ninguna fila afectada");
        }
    }

    @Override
    public boolean delete(long id) throws SQLException {
        Statement statement = null;
        statement = this.conn.createStatement();
        String sql = String.format("DELETE FROM actores WHERE CodAct=%d", id);
        int result = statement.executeUpdate(sql);
        statement.close();
        return result == 1;
    }
    
}
