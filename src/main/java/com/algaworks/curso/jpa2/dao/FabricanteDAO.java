/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.curso.jpa2.dao;

import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.util.jpa.Transactional;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Michel A. Medeiros
 */
public class FabricanteDAO implements Serializable {

    @Inject
    private EntityManager em;

    public Fabricante salvar(Fabricante fabricante) {
        fabricante = em.merge(fabricante);
        return fabricante;
    }

    public List<Fabricante> buscarTodos() {
        return em.createQuery("Select f From Fabricante f").getResultList();
    }

    public List<Fabricante> buscarEspecifico(String palavra) {
        return em.createQuery("Select f From Fabricante f "
                + "WHERE f.nome LIKE '%"+palavra+"%' ").getResultList();
    }

    @Transactional
    public void excluirFabricante(Long id) {
        Fabricante f = em.find(Fabricante.class, id);
        em.remove(f);
        em.flush();
    }

}
