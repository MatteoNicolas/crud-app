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

	public ArrayList<JavaBeans> listarUsers() {

		ArrayList<JavaBeans> users = new ArrayList<>();
		String read = "select * from user order by id";
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

	public void selectUser(JavaBeans user) {
		String readSelect = "select * from user where id = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(readSelect);
			pst.setString(1, user.getId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				user.setId(rs.getString(1));
				user.setNome(rs.getString(2));
				user.setLogin(rs.getString(3));
				user.setPassword(rs.getString(4));
				con.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void alterUser(JavaBeans user) {
		String create = "update user set nome = ?, login = ?, password = ? where id = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, user.getNome());
			pst.setString(2, user.getLogin());
			pst.setString(3, user.getPassword());
			pst.setString(4, user.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deleteUser(JavaBeans user) {
		String delete = "delete from user where id = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, user.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
