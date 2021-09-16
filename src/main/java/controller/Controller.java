package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans user = new JavaBeans();

	public Controller() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			users(request, response);
		} else if (action.equals("/insert")) {
			newUser(request, response);
		} else if (action.equals("/select")) {
			listarUser(request, response);
		} else if (action.equals("/update")) {
			editarUser(request, response);
		} else if (action.equals("/delete")) {
			deletarUser(request, response);
		} else if (action.equals("/report")) {
			generatedRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		}

		/**
		 * Teste de conexão do banco dao.testeConexao();
		 **/
	}

	protected void users(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<JavaBeans> lista = dao.listarUsers();
		request.setAttribute("users", lista);
		RequestDispatcher rd = request.getRequestDispatcher("user.jsp");
		rd.forward(request, response);
	}

	protected void newUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * System.out.println(request.getParameter("nome"));
		 * System.out.println(request.getParameter("login"));
		 * System.out.println(request.getParameter("password"));
		 **/

		user.setNome(request.getParameter("nome"));
		user.setLogin(request.getParameter("login"));
		user.setPassword(request.getParameter("password"));

		dao.insertUser(user);

		response.sendRedirect("main");
	}

	protected void listarUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		user.setId(id);

		dao.selectUser(user);

		request.setAttribute("id", user.getId());
		request.setAttribute("nome", user.getNome());
		request.setAttribute("login", user.getLogin());
		request.setAttribute("password", user.getPassword());
		RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");
		rd.forward(request, response);
	}

	protected void editarUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		user.setId(request.getParameter("id"));
		user.setNome(request.getParameter("nome"));
		user.setLogin(request.getParameter("login"));
		user.setPassword(request.getParameter("password"));

		dao.alterUser(user);
		response.sendRedirect("main");
	}

	protected void deletarUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		user.setId(id);

		dao.deleteUser(user);
		response.sendRedirect("main");
	}

	protected void generatedRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document document = new Document();
		try {
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "inline; filename=" + "users.pdf");
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();
			document.add(new Paragraph("Relatório de gerenciamento de usuários:"));
			document.add(new Paragraph(" "));
			PdfPTable table = new PdfPTable(3);
			PdfPCell collumn1 = new PdfPCell(new Paragraph("Nome:"));
			PdfPCell collumn2 = new PdfPCell(new Paragraph("Usuário:"));
			PdfPCell collumn3 = new PdfPCell(new Paragraph("Senha:"));
			table.addCell(collumn1);
			table.addCell(collumn2);
			table.addCell(collumn3);
			ArrayList<JavaBeans> lista = dao.listarUsers();
			for (int i = 0; i < lista.size(); i++) {
				table.addCell(lista.get(i).getNome());
				table.addCell(lista.get(i).getLogin());
				table.addCell(lista.get(i).getPassword());
			}
			document.add(table);
			document.close();
		} catch (Exception e) {
			System.out.println(e);
			document.close();
		}
	}
}
