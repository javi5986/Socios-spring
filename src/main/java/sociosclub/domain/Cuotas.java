package sociosclub.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "cuotas")
@Data
@AllArgsConstructor 
@NoArgsConstructor
public class Cuotas {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@Column(nullable = false,length = 10,name="id_socio")
	@NotNull
	private Long idSocio;
	
	@Column(nullable = false,length = 10,name="id_categoria")
	@NotNull
	private Long idCategoria;
	
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@Column(nullable = false,  name ="periodo")
	private Date periodo;
	
	@Column(nullable = false,length = 10,name="monto")
	@NotNull
	private Long monto;
	
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@Column(nullable = true,  name ="fecha_cobro")
	private Date fechaCobro;
}