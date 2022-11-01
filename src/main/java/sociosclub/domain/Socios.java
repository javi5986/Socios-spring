package sociosclub.domain;


import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "socios")
@Data
@AllArgsConstructor 
@NoArgsConstructor
public class Socios {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;	
	
	@Column(nullable = false,length = 50,name="nombre")
	@NotEmpty
	private String nombre;
	
	@Column(nullable = false,length = 50, name="apellido")
	@NotEmpty
	private String apellido;
	
	@Column(nullable = true,length = 12, unique = true, name ="numerodocumento")
	@NotEmpty
	private String numerodocumento;
	
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@Column(nullable = true,  name ="fechanacimiento")
	private Date fechanacimiento;
	
	@Column(nullable = false, length = 1,  name ="sexo")
	@NotEmpty
	private String sexo;
	
	@Column(nullable = true, length = 15,  name ="telefono")
	private String telefono;
	
	@Column(nullable = true, length = 60,  name ="direccion")
	private String direccion;
	
	@Column(nullable = true, length = 60,  name ="correoelectronico")
	private String correoelectronico;
	
	@Column(nullable = true, length = 50,  name ="localidad")
	private String localidad;
	
	@Column(nullable = true, length = 10, name = "codigopostal")
	private String codigopostal;
	
	@Column(nullable = true, length = 50,  name ="provincia")
	private String provincia;
		
	@Column(nullable = false, length = 1,  name ="habilitado")
	@NotNull
	private Long habilitado;
	
	
	@Column(nullable = false, length = 1,  name ="titular")
	@NotNull
	private Long titular;
	
	@Column(nullable = true, length = 10,  name ="idtitular")
	private Long idtitular;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="socios_categorias",
		joinColumns = @JoinColumn(name="id_socios"),
		inverseJoinColumns = @JoinColumn(name="id_categorias"))
	private Set<Categorias> categorias;
	

}