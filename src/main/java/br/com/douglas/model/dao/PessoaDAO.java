package br.com.douglas.model.dao;

import br.com.douglas.connection.ConnectionFactory;
import br.com.douglas.model.pojo.Pessoa;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

public class PessoaDAO {

    public static final Logger LOGGER = Logger.getLogger(PessoaDAO.class.getName());

    public void create(Pessoa pessoa) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO pessoa (nome,email) VALUES(?,?)");
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getEmail());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException var8) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + var8);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Pessoa> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pessoa> listaPessoa = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM pessoa");
            rs = stmt.executeQuery();

            while(rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
                listaPessoa.add(pessoa);
            }
        } catch (SQLException ex) {
            LOGGER.log(SEVERE, null, ex);
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
        } catch (SQLException var8) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + var8);
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
        } catch (SQLException var8) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + var8);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
}
