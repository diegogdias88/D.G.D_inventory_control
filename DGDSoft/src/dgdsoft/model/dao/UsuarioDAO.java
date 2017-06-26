package dgdsoft.model.dao;

import dgdsoft.model.domain.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean buscar(String user, String senha){
        String sql = "select * from usuario where usuario = ? and senha = ?";
        Usuario retorno = new Usuario();
        boolean existe = false;
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,user);
            stmt.setString(2,senha );
            ResultSet resultado = stmt.executeQuery();
            if(resultado.next()){
                existe = true;
            }
        }catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null,ex);
        }
        return existe;
    }
    
}
