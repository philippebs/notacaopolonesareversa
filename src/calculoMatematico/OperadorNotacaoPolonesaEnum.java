package calculoMatematico;

public enum OperadorNotacaoPolonesaEnum {
	
	SOMA("+"), SUBTRACAO("-"), MULTIPLICACAO("*"), DIVISAO("/"), ABRIR_PARENTESES("("), FECHAR_PARENTESES(")");
	
	private String valor;
	
	private OperadorNotacaoPolonesaEnum(String valor) {
		this.valor = valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}
	
	public Operador getOperador() {
		return Operador.buscaValor(getValor());
	}
	
	public static OperadorNotacaoPolonesaEnum buscaValor(String operador){
		for (OperadorNotacaoPolonesaEnum op : OperadorNotacaoPolonesaEnum.values()) {
			if(op.getValor().equals(operador)){
				return op;
			}
		}
		return null;
	}
	
}

