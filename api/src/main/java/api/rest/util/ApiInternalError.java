package api.rest.util;

import org.omg.CORBA.Any;

public class ApiInternalError {
    String campo;
    Object valorRejeitado;
    String mensagem;

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public Object getValorRejeitado() {
        return valorRejeitado;
    }

    public void setValorRejeitado(Object valorRejeitado) {
        this.valorRejeitado = valorRejeitado;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
