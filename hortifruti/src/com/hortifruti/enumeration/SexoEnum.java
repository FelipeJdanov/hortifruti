package com.hortifruti.enumeration;


public enum SexoEnum {
	
	MASCULINO ("Masculino"),
	FEMININO ("Feminino");
	
	
	private String sexo;
	
	SexoEnum(String sexo){
		this.sexo = sexo;
	}

	public String getSexo() {
		return sexo;
	}

	public static SexoEnum getSexoByNome(String sexo) {
		for(SexoEnum sexoEnum: SexoEnum.values()){
			if(null != sexo && sexo.equals(sexoEnum.getSexo())){
				return sexoEnum;
			}	
		}
		return null;
	}
	
	
}
