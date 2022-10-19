package sociosclub.enums;

public enum EnumSocios {

	
	ALTA("/socios/alta"),
	EDITAR("/socios/editar"), 
	REDIRECT("redirect:/socios/abm"),
	LISTADOS("/socios/listados"),
	REDIRECT_LISTADOS("redirect:/socio/listados"),
	BUSCARSOCIO("/socios/buscar");


	private String view;
	
	private EnumSocios(String view) {
		this.view = view;			
	}

	public String getView() {
		return this.view;
	}
	
}
