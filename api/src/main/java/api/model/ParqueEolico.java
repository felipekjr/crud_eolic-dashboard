package api.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "parque_eolico")
@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
//        allowGetters = true)
public class ParqueEolico {
	@Id
	@GenericGenerator(name="incrementador" , strategy="increment")
	@GeneratedValue(generator="incrementador")
	@Column(name = "id")
	private Long id;

	@NotNull
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "latitude")
	private int latitude;
	
	@Column(name = "longitude")
	private int longitude;

	@NotNull
	@Column(name = "potencia_instalada")
	private BigDecimal potenciaInstalada;
	
	@ManyToOne	
	@JoinColumn(name = "complexo_eolico_id")
	private ComplexoEolico complexoEolico;

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

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getPotenciaInstalada() {
		return potenciaInstalada;
	}

	public void setPotenciaInstalada(BigDecimal potenciaInstalada) {
		this.potenciaInstalada = potenciaInstalada;
	}

	public ComplexoEolico getComplexoEolico() {
		return complexoEolico;
	}

	public void setComplexoEolico(ComplexoEolico complexoEolico) {
		this.complexoEolico = complexoEolico;
	}

	@Override
	public String toString() {
		return "ParqueEolico{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", latitude=" + latitude +
				", longitude=" + longitude +
				", potencia_instalada=" + potenciaInstalada +
				", complexoEolico=" + complexoEolico +
				'}';
	}
}
