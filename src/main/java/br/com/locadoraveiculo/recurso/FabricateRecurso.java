/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.locadoraveiculo.recurso;

import com.algaworks.curso.jpa2.dao.FabricanteDAO;
import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.service.CadastroFabricanteService;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

    @Inject
    private FabricanteDAO fabricanteDAO;

    private Fabricante fabricante;

    @Path("inserirFabricante")
    @POST
    public Response salvar(String conteudo) {
        System.out.println("conteudo " + conteudo);
        Fabricante fabricante = new Gson().fromJson(conteudo, Fabricante.class);
        System.out.println("fabricante " + fabricante.getNome());
        try {
            fabricante = cadastroFabricanteService.salvar(fabricante);
            return Response.ok(fabricante)
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
            System.out.println("erro salvar Fabricante " + ex.getMessage());
        }

        return null;
    }

    @Path("todos")
    @GET
    public List<Fabricante> buscaTudo() {
        List<Fabricante> listaFabricantes = new ArrayList<>();
        listaFabricantes = fabricanteDAO.buscarTodos();
        return listaFabricantes;
    }

    @Path("todos/{palavra}")
    @GET
    public List<Fabricante> buscarEspecifico(@PathParam("palavra") String palavra) {
        System.out.println("palavra "+palavra);
        List<Fabricante> listaFabricantes = new ArrayList<>();
        listaFabricantes = fabricanteDAO.buscarEspecifico(palavra);
        return listaFabricantes;
    }

    @Path("remove/{id}")
    @DELETE
    public Response remove(@PathParam("id") Long id) {
        try {
            fabricanteDAO.excluirFabricante(id);
        } catch (Exception ex) {
            System.out.println("erro ao excluir Fabricante " + ex.getMessage());
        }
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS")
                .build();
    }

}
