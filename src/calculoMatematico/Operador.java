package calculoMatematico;

public enum Operador {

	SOMA("+"), SUBTRACAO("-"), MULTIPLICACAO("*"), DIVISAO("/");
	
	private String valor;
	
	private Operador(String valor) {
		this.valor = valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}
	
	public static Operador buscaValor(String operador){
		for (Operador op : Operador.values()) {
			if(op.getValor().equals(operador)){
				return op;
			}
		}
		return null;
	}
	
}
