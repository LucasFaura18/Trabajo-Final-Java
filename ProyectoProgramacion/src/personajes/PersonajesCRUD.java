package personajes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import CRUD.CRUD;
import dataset.DataSetInterface;

public class PersonajesCRUD implements CRUD<Personaje>, DataSetInterface {
    Connection conn;
    public PersonajesCRUD(Connection conn){
        this.conn = conn;
    }
    @Override
    public ArrayList<Personaje> requestAll() throws SQLException {
        Statement statement = null;
        ArrayList<Personaje> result = new ArrayList<Personaje>();
        statement = this.conn.createStatement();
        String sql = "SELECT * FROM personajes";
        ResultSet querySet = statement.executeQuery(sql);
        while (querySet.next()) {
            int CodPer = querySet.getInt("CodPer");
            String NomPer = querySet.getString("NomPer");
            String GenPer = querySet.getString("GenPer");
            Long CodAct = querySet.getLong("CodAct")==0?null:querySet.getLong("CodAct");
            result.add(new Personaje(CodPer, NomPer, GenPer, CodAct));
        }
        statement.close();
        return result;
    }

    @Override
    public Personaje requestById(long id) throws SQLException {
        Statement statement = null;
        Personaje result = null;
        statement = this.conn.createStatement();
        String sql = String.format("SELECT * FROM personajes WHERE CodPer=%d", id);
        ResultSet querySet = statement.executeQuery(sql);
        if (querySet.next()) {
            String NomPer = querySet.getString("NomPer");
            String GenPer = querySet.getString("GenPer");
            Long CodAct = querySet.getLong("CodAct")==0?null:querySet.getLong("CodAct");
            result = new Personaje(id, NomPer, GenPer, CodAct);
        }
        statement.close();
        return result;
    }

    @Override
    public long create(Personaje object) throws SQLException {
        String sql = String.format("INSERT INTO personajes (Nomper, GenPer, CodAct) VALUES (?, ?, ?)");
        PreparedStatement prepst = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        prepst.setString(1, object.getNomPer());
        prepst.setString(2, object.getGenPer());
        if (object.getCodAct() == null) {
            prepst.setNull(3, Types.INTEGER);
        } else{
            prepst.setLong(3, object.getCodAct());
        }
        prepst.execute();
        ResultSet keys = prepst.getGeneratedKeys();
        if (keys.next()) {
            long CodPer = keys.getLong(1);
            prepst.close();
            return CodPer;
        }
        else{
            throw new SQLException("Fallo al crear el campo, ninguna fila afectada");
        }
    }

    @Override
    public int update(Personaje object) throws SQLException {
        long CodPer = object.getCodPer();
        String NomPer = object.getNomPer();
        String GenPer = object.getGenPer();
        Long CodAct = object.getCodAct();
        Statement statement = null;
        statement = this.conn.createStatement();
        String sql = String.format("UPDATE personajes SET NomPer='%s', GenPer='%s', CodAct=%d WHERE CodPer=%d", NomPer, GenPer, CodAct, CodPer);
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
        String sql = String.format("DELETE FROM personajes WHERE CodPer=%d", id);
        int result = statement.executeUpdate(sql);
        statement.close();
        return result == 1;
    }

    @Override
    public void exportToCSV(String file) throws Exception {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8));
            ArrayList<Personaje> personajes = this.requestAll();
            for (Personaje personaje : personajes) {
                bw.write(personaje.serialize()+"\n");
            }
            bw.close();
        } catch(IOException e){
            throw new Exception("Ocurrio un error de entrada/salida"+e.toString());
        }catch (SQLException e) {
            throw new Exception("Ocurrio un error al acceder a la base de datos"+e.toString());
        }catch (Exception e) {
            throw new Exception("Ocurrio un error"+e.toString());
        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }
    @Override
    public void importFromCSV(String file) throws Exception {
        // No he utilizado esta función
        throw new UnsupportedOperationException("Unimplemented method 'importFromCSV'");
    }
    
}
