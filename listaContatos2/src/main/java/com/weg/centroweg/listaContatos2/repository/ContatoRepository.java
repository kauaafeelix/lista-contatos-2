package com.weg.centroweg.listaContatos2.repository;


import com.weg.centroweg.listaContatos2.model.Contato;
import com.weg.centroweg.listaContatos2.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContatoRepository {

    public Contato save (Contato contato) throws SQLException {

        String sql = """
                INSERT INTO Contato (
                nome,
                telefone,
                email )
                VALUES (?,?,?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getTelefone());
            ps.setString(3, contato.getEmail());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                int idGerado = rs.getInt(1);
                contato.setId(idGerado);
                return contato;
            }
        }
        throw new RuntimeException("Erro ao cadastrar o Contato");
    }


    public List<Contato> findAll() throws SQLException{

        List<Contato>contatos = new ArrayList<>();

        String sql = """
                SELECT 
                id,
                nome,
                telefone,
                email
                FROM Contato
                """;

        try(Connection conn = Conexao.conectar();
        PreparedStatement ps = conn.prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Contato contato = new Contato(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("email")
                );
                contatos.add(contato);
            }
        }
        return contatos;
    }

    public Contato findById(int id) throws SQLException{
        String sql = """
                SELECT 
                id, 
                nome,
                telefone,
                email
                FROM Contato
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                int idGerado = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                Contato contato = new Contato( idGerado, nome, telefone, email);
                return contato;
            }
        }
        throw new RuntimeException("Ocorreu um erro ao buscar um Contato por ID.");
    }

    public void updateContato(Contato contato) throws SQLException{

        String sql = """
                
               UPDATE Contato
               SET nome = ?, telefone = ?, email = ?
               WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getTelefone());
            ps.setString(3, contato.getEmail());
            ps.setInt(4, contato.getId());
            ps.executeUpdate();
        }
    }

    public void deleteContato (int id) throws SQLException{

        String sql = """
                DELETE FROM Contato
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
        PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
