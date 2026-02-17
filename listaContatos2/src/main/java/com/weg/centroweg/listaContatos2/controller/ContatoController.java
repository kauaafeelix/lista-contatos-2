package com.weg.centroweg.listaContatos2.controller;


import com.weg.centroweg.listaContatos2.model.Contato;
import com.weg.centroweg.listaContatos2.service.ContatoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/contato")
public class ContatoController {

    private ContatoService service;


    public ContatoController (ContatoService service){
        this.service = service;
    }

    @PostMapping
    public Contato save(@RequestBody Contato contato){
        try{
            return service.save(contato);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<Contato> findAll(){
        try{
            List<Contato> contatos = new ArrayList<>();
            contatos = service.findAll();
            return contatos;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Contato findById (@PathVariable int id){
        try{
            Contato contato = service.findById(id);
            return contato;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public Contato updateContato(@PathVariable int id, @RequestBody Contato contato){
        try{
            return contato = service.updateContato(contato, id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o usuário com ID: "+id+ " || "+e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteContato(@PathVariable int id){
        try {
            service.deleteContato(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar usuário com ID: "+id +" || "+e.getMessage());
        }
    }
}
