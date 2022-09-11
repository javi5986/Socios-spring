package sociosclub.enums;

public enum EnumVistas {

	
	ABM("/socios/abm"),
	ALTA("socios/alta");

	private String view;
	
	private EnumVistas(String view) {
		this.view = view;			
	}

	public String getView() {
		return view;
	}
	
}
