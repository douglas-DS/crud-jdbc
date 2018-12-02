/*
 * Created on : 17/11/2018, 12:12:02
 * @author : Douglas Souza 
 */
package br.com.douglas.model.dao;

import br.com.douglas.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import br.com.douglas.model.bean.Pessoa;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Douglas Souza
 */
public class PessoaDAO {
    
    public void create(Pessoa pessoa) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;  
        
        try {
            stmt = con.prepareStatement("INSERT INTO pessoa (nome,email) VALUES(?,?)");
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getEmail());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Pessoa> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        List<Pessoa> listaPessoa = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM pessoa");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setId(rs.getInt("id"));
                    pessoa.setNome(rs.getString("nome"));
                    pessoa.setEmail(rs.getString("email"));
                    listaPessoa.add(pessoa);
            }
                          
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return listaPessoa;
    }
    
       public void update(Pessoa pessoa) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;  
        
        try {
            stmt = con.prepareStatement("UPDATE pessoa SET nome = ?, email = ? WHERE id = ?");
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getEmail());
            stmt.setInt(3, pessoa.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
       
       public void delete(Pessoa pessoa) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;  
        
        try {
            stmt = con.prepareStatement("DELETE FROM pessoa WHERE id = ?");
            stmt.setInt(1, pessoa.getId());           
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
