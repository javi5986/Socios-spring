package sociosclub.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocioCategoriaPK implements Serializable{

	private static final long serialVersionUID = 2L;

	private Long idSocio;	
	
	private Long idCategoria;
	
}