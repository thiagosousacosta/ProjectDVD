package com.wipro.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wipro.model.DVD;
import com.wipro.persistencia.DVDDAO;

public class MostraListaServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<DVD> listaDVD = DVDDAO.getListaDVD();
 		resp.setContentType("text/html");
 		req.setAttribute("lsitaDVD", listaDVD);
 			
		PrintWriter pw = resp.getWriter();
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>Minha página</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<table>");

		pw.println("<tr>");
		pw.println("<th>NOME</td>");
		pw.println("<th>DIRETOR</td>");
		pw.println("<th>ANO DE PUBLICAÇÃO</td>");
		pw.println("<th>SINOPSE</td>");
		pw.println("</tr>");

		for (DVD dvd : listaDVD) {

			pw.println("<tr>");
			pw.println("<td>" + dvd.getNome() + "</td>");
			pw.println("<td>" + dvd.getDiretor() + "</td>");
			pw.println("<td>" + dvd.getAnoDePublicacao() + "</td>");
			pw.println("<td>" + dvd.getSinopse() + "</td>");
			pw.println("</tr>");
		}

		pw.println("</table>");
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
		
	}
}
