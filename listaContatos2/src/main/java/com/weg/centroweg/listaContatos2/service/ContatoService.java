package com.weg.centroweg.listaContatos2.service;

import com.weg.centroweg.listaContatos2.model.Contato;
import com.weg.centroweg.listaContatos2.repository.ContatoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContatoService {

    private ContatoRepository repository;

    public ContatoService (ContatoRepository repository){
        this.repository = repository;
    }

    public Contato save (Contato contato) throws SQLException {
        return repository.save(contato);
    }

    public List<Contato>findAll () throws SQLException{
        List<Contato> contatos = new ArrayList<>();
        contatos = repository.findAll();
        return contatos;
    }

    public Contato findById (int id) throws SQLException{
        return repository.findById(id);
    }

    public Contato updateContato (Contato contato, int id) throws SQLException{
        contato.setId(id);
        repository.updateContato(contato);
        return contato;
    }

    public void deleteContato(int id) throws SQLException{
        if (repository.findById(id) == null){
            throw new RuntimeException("O Contato não existe");
        }
        repository.deleteContato(id);
    }
}
