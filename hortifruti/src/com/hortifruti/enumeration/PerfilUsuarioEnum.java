package com.hortifruti.enumeration;

public enum PerfilUsuarioEnum {
	
	ADMINISTRADOR ("Administrador"),
	CONSUMIDOR ("Consumidor");
	
	private String label;  
	  
	PerfilUsuarioEnum(String label){  
        this.label = label;  
    }  
  
    public String getLabel(){  
        return label;  
    }
    
    
    public static PerfilUsuarioEnum getPerfilUsuario(String label) {	
		
    	PerfilUsuarioEnum enumEncontrado = null;
		
		for (PerfilUsuarioEnum e : PerfilUsuarioEnum.values()) {
			if (e.getLabel().equalsIgnoreCase(label)) {
				enumEncontrado = e;
				break;
			}
		}
		
		return enumEncontrado;
	}

}
