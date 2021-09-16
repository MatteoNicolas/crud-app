package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete" })
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
		} else {
			response.sendRedirect("index.html");
		}

		/**
		 * Teste de conexão do banco dao.testeConexao();
		 **/
	}

	// listar
	protected void users(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<JavaBeans> lista = dao.listarUsers();
		request.setAttribute("users", lista);
		RequestDispatcher rd = request.getRequestDispatcher("user.jsp");
		rd.forward(request, response);
	}

	// novo user
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

	// editar user
	protected void listarUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		// System.out.println(id);
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
}
