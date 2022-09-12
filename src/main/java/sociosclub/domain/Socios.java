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


@Entity
@Table(name = "socios")
//@Data
//@AllArgsConstructor

public class Socios {

	public Socios() {
		// TODO Auto-generated constructor stub
	}
	//la clave en la db: es AUTOINCREMENT
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@NotEmpty
	@Column(nullable = false,length = 50,name="nombre")
	private String nombre;
	
	@NotEmpty
	@Column(nullable = false,length = 50, name="apellido")
	private String apellido;
	
	@NotEmpty
	@Column(nullable = true,length = 12, unique = true, name ="numerodocumento")
	private String numerodocumento;
	
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@Column(nullable = true,  name ="fechanacimiento")
	private Date fechanacimiento;
	
	@NotEmpty
	@Column(nullable = false, length = 1,  name ="sexo")
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
	
	
	@NotNull
	@Column(nullable = false, length = 1,  name ="habilitado")
	private Long habilitado;
	
	
	@NotNull
	@Column(nullable = false, length = 1,  name ="titular")
	private Long titular;
	
	@Column(nullable = true, length = 10,  name ="idtitular")
	private Long idtitular;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNumerodocumento() {
		return numerodocumento;
	}

	public void setNumerodocumento(String numerodocumento) {
		this.numerodocumento = numerodocumento;
	}

	public Date getFechanacimiento() {
		return fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreoelectronico() {
		return correoelectronico;
	}

	public void setCorreoelectronico(String correoelectronico) {
		this.correoelectronico = correoelectronico;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getCodigopostal() {
		return codigopostal;
	}

	public void setCodigopostal(String codigopostal) {
		this.codigopostal = codigopostal;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public Long getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Long habilitado) {
		this.habilitado = habilitado;
	}

	public Long getTitular() {
		return titular;
	}

	public void setTitular(Long titular) {
		this.titular = titular;
	}

	public Long getIdtitular() {
		return idtitular;
	}

	public void setIdtitular(Long idtitular) {
		this.idtitular = idtitular;
	}

	
	public Socios(Long id, String nombre, String apellido, String numerodocumento, Date fechanacimiento, String sexo,
			String telefono, String direccion, String correoelectronico, String localidad, String codigopostal,
			String provincia, Long habilitado, Long titular, Long idtitular) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.numerodocumento = numerodocumento;
		this.fechanacimiento = fechanacimiento;
		this.sexo = sexo;
		this.telefono = telefono;
		this.direccion = direccion;
		this.correoelectronico = correoelectronico;
		this.localidad = localidad;
		this.codigopostal = codigopostal;
		this.provincia = provincia;
		this.habilitado = habilitado;
		this.titular = titular;
		this.idtitular = idtitular;
	}

	public Socios(String nombre, String apellido, String numerodocumento, Date fechanacimiento, String sexo,
			String telefono, String direccion, String correoelectronico, String localidad, String codigopostal,
			String provincia, Long habilitado, Long titular, Long idtitular) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.numerodocumento = numerodocumento;
		this.fechanacimiento = fechanacimiento;
		this.sexo = sexo;
		this.telefono = telefono;
		this.direccion = direccion;
		this.correoelectronico = correoelectronico;
		this.localidad = localidad;
		this.codigopostal = codigopostal;
		this.provincia = provincia;
		this.habilitado = habilitado;
		this.titular = titular;
		this.idtitular = idtitular;
	}

}