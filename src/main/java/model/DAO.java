package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbuser?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "1807Chopin@";

	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	/** CREATE **/
	public void insertUser(JavaBeans user) {
		String create = "insert into user(nome, login, password) values (?, ?, ?)";
		try {

			Connection con = conectar();

			PreparedStatement pst = con.prepareStatement(create);

			pst.setString(1, user.getNome());
			pst.setString(2, user.getLogin());
			pst.setString(3, user.getPassword());

			pst.executeUpdate();

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/** READ **/
	public ArrayList<JavaBeans> listarUsers() {

		ArrayList<JavaBeans> users = new ArrayList<>();
		String read = "select * from user order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				//
				String id = rs.getString(1);
				String nome = rs.getString(2);
				String login = rs.getString(3);
				String password = rs.getString(4);

				users.add(new JavaBeans(id, nome, login, password));
			}
			con.close();
			return users;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
