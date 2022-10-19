package sociosclub.enums;

public enum EnumCategorias {

	INDEX("/categorias/index"),
	REDIRECT_INDEX("redirect:/categorias/index"),
	ALTA("/categorias/alta"),
	EDITAR("/categorias/editar"); 

	private String view;
	
	private EnumCategorias(String view) {
		this.view = view;			
	}

	public String getView() {
		return this.view;
	}
	
}
