
import java.lang.reflect.Method;
import java.math.BigDecimal;

import calculoMatematico.OperadorNotacaoPolonesaEnum;

public class NotacaoPolonesaReversa {
	
	private BigDecimal valor;
	
	private OperadorNotacaoPolonesaEnum operador;
	
	private Method method;
	
	public NotacaoPolonesaReversa() {
		
	}
	
	public NotacaoPolonesaReversa(OperadorNotacaoPolonesaEnum operador) {
		this.operador = operador;
	}

	public NotacaoPolonesaReversa(BigDecimal valor) {
		this.valor = valor;
	}
	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public OperadorNotacaoPolonesaEnum getOperador() {
		return operador;
	}

	public void setOperador(OperadorNotacaoPolonesaEnum operador) {
		this.operador = operador;
	}
	
	public Method getMethod() {
		return method;
	}
	
	@Override
	public String toString() {
		return valor != null ? valor.toString() : operador.getValor();
	}
}

