package sociosclub.enums;

public enum EnumSociosCategorias {

	BUSCARUSUARIO("/sociosCategorias/buscarUsuario"),
	SELECCIONARCATEGORIA("/sociosCategorias/seleccionarCategoria"),
	REDIRECT_SELECCIONARCATEGORIA("redirect:/sociosCategorias/seleccionarCategoria");

	private String view;
	
	private EnumSociosCategorias(String view) {
		this.view = view;			
	}

	public String getView() {
		return this.view;
	}
	
}
