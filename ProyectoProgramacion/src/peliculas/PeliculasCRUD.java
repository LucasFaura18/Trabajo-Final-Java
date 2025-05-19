package peliculas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import CRUD.CRUD;

public class PeliculasCRUD implements CRUD<Pelicula>{
    Connection conn;
    public PeliculasCRUD(Connection conn){
        this.conn = conn;
    }
    @Override
    public ArrayList<Pelicula> requestAll() throws SQLException {
        Statement statement = null;
        ArrayList<Pelicula> result = new ArrayList<Pelicula>();
        statement = this.conn.createStatement();
        String sql = "SELECT * FROM peliculas";
        ResultSet querySet = statement.executeQuery(sql);
        while (querySet.next()) {
            int CodPel = querySet.getInt("CodPel");
            String TitPel = querySet.getString("TitPel");
            String GenPel = querySet.getString("GenPel");
            int duracion = querySet.getInt("duracion");
            result.add(new Pelicula(CodPel, TitPel, GenPel, duracion));
        }
        statement.close();
        return result;
    }

    @Override
    public Pelicula requestById(long id) throws SQLException {
        Statement statement = null;
        Pelicula result = null;
        statement = this.conn.createStatement();
        String sql = String.format("SELECT * FROM peliculas WHERE CodPel=%d", id);
        ResultSet querySet = statement.executeQuery(sql);
        if (querySet.next()) {
            String TitPel = querySet.getString("TitPel");
            String GenPel = querySet.getString("GenPel");
            int duracion = querySet.getInt("duracion");
            result = new Pelicula(id, TitPel, GenPel, duracion);
        }
        statement.close();
        return result;
    }

    @Override
    public long create(Pelicula object) throws SQLException {
        String sql = String.format("INSERT INTO peliculas (TitPel, GenPel, duracion) VALUES (?, ?, ?)");
        PreparedStatement prepst = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        prepst.setString(1, object.getTitPel());
        prepst.setString(2, object.getGenPel());
        prepst.setLong(3, object.getDuracion());
        prepst.execute();
        ResultSet keys = prepst.getGeneratedKeys();
        if (keys.next()) {
            long CodPel = keys.getLong(1);
            prepst.close();
            return CodPel;
        }
        else{
            throw new SQLException("Fallo al crear el campo, ninguna fila afectada");
        }
    }

    @Override
    public int update(Pelicula object) throws SQLException {
        long CodPel = object.getCodPel();
        String TitPel = object.getTitPel();
        String GenPel = object.getGenPel();
        long duracion = object.getDuracion();
        Statement statement = null;
        statement = this.conn.createStatement();
        String sql = String.format("UPDATE peliculas SET TitPel='%s', GenPel='%s', duracion=%d WHERE CodPel=%d", TitPel, GenPel, duracion, CodPel);
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
        String sql = String.format("DELETE FROM peliculas WHERE CodPel=%d", id);
        int result = statement.executeUpdate(sql);
        statement.close();
        return result == 1;
    }
    
}
