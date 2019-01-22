package api.model;
/**
 * @author Danilo
 * Modela os dados de usuário no payload do token
 */


public class UsuarioToken {

	
	private Long id;
	private String login;
	private String senha;	
	
	public UsuarioToken() {}

	public UsuarioToken(Long id, String login, String senha) {
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
