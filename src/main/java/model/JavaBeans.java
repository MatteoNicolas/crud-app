package model;

public class JavaBeans {
	private String id;
	private String nome;
	private String login;
	private String password;

	public JavaBeans() {
		super();
	}

	public JavaBeans(String id, String nome, String login, String password) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
