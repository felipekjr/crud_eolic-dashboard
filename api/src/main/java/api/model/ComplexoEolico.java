package api.model;
import api.arq.modelo.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "complexo_eolico")
@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
//        allowGetters = true)
public class ComplexoEolico extends AbstractEntity {
	@Id
	@GenericGenerator(name="incrementador" , strategy="increment")
	@GeneratedValue(generator="incrementador")
	@Column(name = "id")
	private Long id;

	@NotNull
	@Column(name = "nome")
	private String nome;

	@NotNull
	@Column(name = "uf")
	private String uf;

	@NotNull
	@Column(name = "identificador")
	private String identificador;

    @JsonIgnore
    @OneToMany(mappedBy = "complexoEolico", fetch = FetchType.LAZY)
    private List<ParqueEolico> parquesEolicos;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

    public List<ParqueEolico> getParquesEolicos() {
        return parquesEolicos;
    }

    public void setParquesEolicos(List<ParqueEolico> parquesEolicos) {
        this.parquesEolicos = parquesEolicos;
    }

    @Override
	public String toString() {
		return "ComplexoEolico{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", uf='" + uf + '\'' +
				", identificador='" + identificador + '\'' +
				'}';
	}
}
