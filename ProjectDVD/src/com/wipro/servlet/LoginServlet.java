package com.wipro.servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Dados da requisição
		String email = req.getParameter("email");
		String senha = req.getParameter("senha");
		
		//validar o usuario e a senha
		if(email.equals("email@teste.com") && (senha.equals("123"))){
			//criando uma sessão 
			req.getSession().setAttribute("emailSessao", email);;
			
			//Chama o novo servlet - encaminhamento 
			//INFORMANDO A URL DO SERVLET QUE IRÁ GERAR A NOVA TELA
			RequestDispatcher rd = req.getRequestDispatcher("/mostraTela.html");
			
			//acionar a servlet
			rd.forward(req,resp);
		}else{
			//Chama a tela de erro - redirecionamento
			resp.sendRedirect("erro.html");
		}
	}
}
