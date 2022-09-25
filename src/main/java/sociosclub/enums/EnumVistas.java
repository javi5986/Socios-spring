package sociosclub.enums;

public enum EnumVistas {

	
	ABM("/socios/abm"),
	ALTA("/socios/alta"),
	EDIT("/socios/edit"), 
	LIST("/socios/list"),
	REDIRECT("redirect:/socios/abm"),
	LISTADOS("/socios/listados"),
	REDIRECT_LISTADOS("redirect:/socio/listados"),;


	private String view;
	
	private EnumVistas(String view) {
		this.view = view;			
	}

	public String getView() {
		return this.view;
	}
	
}
