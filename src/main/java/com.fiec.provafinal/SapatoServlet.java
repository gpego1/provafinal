package com.fiec.provafinal;
import com.fiec.provafinal.models.Sapato;
import com.fiec.provafinal.models.SapatoRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/sapatos/*")
public class SapatoServlet extends HttpServlet {

    private SapatoRepositorio repository;
    private EntityManager em;

    public SapatoServlet(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("minhaUnidadeDePersistencia");
        this.em = entityManagerFactory.createEntityManager();
    }

    private static String getId(HttpServletRequest req){
        String path = req.getPathInfo();
        String[] paths = path.split("/");
        String id = paths[paths.length - 1];
        return id;
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Sapato> entities = em.createQuery("select t from " + Sapato.class.getSimpleName() + " t").getResultList();
        System.out.println(entities);
        resp.setContentType("text/html");
        String content = "";
        if(entities != null){
            content = entities.stream().map(p -> p.toString()).collect(Collectors.joining("<br/>"));
        }
        resp.getWriter().println(content);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        double preco = Double.parseDouble(req.getParameter("preco"));
        String imagem = req.getParameter("imagem");
        int tamanho = Integer.parseInt(req.getParameter("tamanho"));
        String marca = req.getParameter("marca");
        Sapato p = Sapato.builder()
                .nome(nome)
                .preco(preco)
                .imagem(imagem)
                .tamanho(tamanho)
                .marca(marca)
                .build();
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
        resp.setContentType("text/html");
        resp.getWriter().println("<h1>Hello from POST do SAPATO!</h1>");
    }

    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = getId(req);
        Sapato entity = em.find(Sapato.class, id);
        String nome = req.getParameter("nome");
        double preco = Double.parseDouble(req.getParameter("preco"));
        String imagem = req.getParameter("imagem");
        int tamanho = Integer.parseInt(req.getParameter("tamanho"));
        String marca = req.getParameter("marca");
        em.getTransaction().begin();
        if(entity != null){
            entity.setNome(nome);
            entity.setPreco(preco);
            entity.setImagem(imagem);
            entity.setTamanho(tamanho);
            entity.setMarca(marca);
            em.merge(entity);
        }
        em.getTransaction().commit();
        resp.setContentType("text/html");
        resp.getWriter().println("<h1>Hello from PUT do SAPATO!</h1>");
    }

    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = getId(req);
        System.out.println(id);
        em.getTransaction().begin();
        Sapato entity = em.find(Sapato.class, id);
        em.remove(entity);
        em.getTransaction().commit();
    }

}