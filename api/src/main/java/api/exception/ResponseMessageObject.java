package api.exception;
/*
	Utilize esta classe quando precisar retornar uma mensagem qualquer para o usuário
*/
public class ResponseMessageObject {

	public static final String DEFAULT_NOT_FOUND_MESSAGE = "Este registro não foi encontrado";
	public static final String DEFAULT_ERROR_MESSAGE = "Ocorreu um erro, tente novamente mais tarde";
	public static final String DEFAULT_INSERT_MESSAGE = "Registro inserido com sucesso";
	public static final String DEFAULT_UPDATE_MESSAGE = "Registro atualizado com sucesso";
	public static final String DEFAULT_DELETE_MESSAGE = "Registro deletado com sucesso";
	
	private String message;
	private Integer status;
	
	public ResponseMessageObject(String message, Integer status) {
		super();
		this.message = message;
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}

