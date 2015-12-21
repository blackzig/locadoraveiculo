/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.curso.jpa2.service;

import com.algaworks.curso.jpa2.dao.FabricanteDAO;
import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.util.jpa.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author Michel A. Medeiros
 */
public class CadastroFabricanteService implements Serializable{
    
    @Inject
    private FabricanteDAO fabricanteDAO;
    
    @Transactional
    public void salvar(Fabricante fabricante) throws NegocioException{

        if(fabricante.getNome()==null || fabricante.getNome().trim().equals("")){
            throw new NegocioException("O nome do fabricante é obrigatório.");
        }
        this.fabricanteDAO.salvar(fabricante);
        
    }
    
}
