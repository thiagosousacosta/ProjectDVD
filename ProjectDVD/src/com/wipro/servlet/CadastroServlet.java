package com.wipro.servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wipro.model.DVD;
import com.wipro.persistencia.DVDDAO;

public class CadastroServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}
	
	protected void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		String diretor = req.getParameter("diretor");
		String ano = req.getParameter("ano");
		String sinopse = req.getParameter("sinopse");
		
		DVD dvd = new DVD();
		dvd.setNome(nome);
		dvd.setDiretor(diretor);
		dvd.setAnoDePublicacao(Integer.parseInt(ano));
		dvd.setSinopse(sinopse);
		
		DVDDAO.saveAllDVD(dvd);
		RequestDispatcher rd = req.getRequestDispatcher("/mostraTela.html");
		
		//acionar a servlet
		rd.forward(req,resp);
		
	}

}