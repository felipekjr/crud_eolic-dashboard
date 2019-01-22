package api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "aerogerador")
@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
//        allowGetters = true)
public class Aerogerador {
	@Id
	@GenericGenerator(name="incrementador" , strategy="increment")
	@GeneratedValue(generator="incrementador")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "latitude")
	private BigDecimal latitude;
	
	@Column(name = "longitude")
	private BigDecimal longitude;
	
	@Column(name = "altura_torre")
	private BigDecimal altura_torre;
	
	@Column(name = "diametro_varredura")
	private BigDecimal diametro_varredura;
	
	@Column(name = "modelo")
	private String modelo;		
	
	@ManyToOne
	@JoinColumn(name = "parque_eolico_id")
	private ParqueEolico parqueEolico;

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

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getAltura_torre() {
		return altura_torre;
	}

	public void setAltura_torre(BigDecimal altura_torre) {
		this.altura_torre = altura_torre;
	}

	public BigDecimal getDiametro_varredura() {
		return diametro_varredura;
	}

	public void setDiametro_varredura(BigDecimal diametro_varredura) {
		this.diametro_varredura = diametro_varredura;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public ParqueEolico getParqueEolico() {
		return parqueEolico;
	}

	public void setParqueEolico(ParqueEolico parqueEolico) {
		this.parqueEolico = parqueEolico;
	}
	
	
}
