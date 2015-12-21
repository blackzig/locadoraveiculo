/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.locadoraveiculo.recurso;

import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.service.CadastroFabricanteService;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Michel A. Medeiros
 */
@Path("fabricante")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FabricateRecurso {
    
    @Inject
    private CadastroFabricanteService cadastroFabricanteService;
    
    private Fabricante fabricante;
    
    @Path("inserirFabricante")
    @POST
    public Response salvar(String conteudo) {
        System.out.println("conteudo " + conteudo);
        Fabricante fabricante = new Gson().fromJson(conteudo, Fabricante.class);
        System.out.println("fabricante " + fabricante.getNome());
        try {
            cadastroFabricanteService.salvar(fabricante);
            return Response.ok()
                    .status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers",
                            "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods",
                            "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .header("Access-Control-Max-Age", "1209600")
                    .build();
        } catch (NegocioException ex) {
            //return Response.notModified(ex.getMessage());
            System.out.println("erro salvar Fabricante "+ex.getMessage());
        }

        return null;
    }
    
}
