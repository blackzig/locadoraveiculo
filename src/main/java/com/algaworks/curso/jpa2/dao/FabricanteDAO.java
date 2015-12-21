/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.curso.jpa2.dao;

import com.algaworks.curso.jpa2.modelo.Fabricante;
import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Michel A. Medeiros
 */
public class FabricanteDAO implements Serializable{

    @Inject
    private EntityManager em;
    
    public void salvar(Fabricante fabricante){
        em.persist(fabricante);
    }
    
}
