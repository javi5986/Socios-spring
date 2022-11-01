package sociosclub.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "socios_categorias")
@Data
@AllArgsConstructor 
@NoArgsConstructor
@IdClass(SocioCategoriaPK.class)
public class SocioCategoria {

	@Id
	@Column(name ="id_socios")
	private Long idSocio;
	
	@Id
	@Column(name ="id_categorias")
	private Long idCategoria;
}