import java.math.BigDecimal;
import java.util.Stack;

import calculoMatematico.BigDecimalUtil;
import calculoMatematico.OperadorNotacaoPolonesaEnum;

public class Calculo {
	
	private Stack<BigDecimal> stackValores;
	
	private OperadorNotacaoPolonesaEnum operador;
	
	public Calculo() {
		stackValores = new Stack<BigDecimal>();
	}
	
	public void setValor(BigDecimal valor){
		stackValores.push(valor);
	}
	
	public OperadorNotacaoPolonesaEnum getOperador() {
		return operador;
	}
	
	public void setOperador(OperadorNotacaoPolonesaEnum operador) {
		calcular(operador);
	}
	
	private void calcular(OperadorNotacaoPolonesaEnum operador){
		BigDecimal valor1 = stackValores.pop();
		BigDecimal valor2 = stackValores.pop();
		try {
			stackValores.push(BigDecimalUtil.calcular(valor2, operador.getOperador(), valor1));
			//System.out.println(valor2 + operador.getValor() + valor1 +  " = " + stackValores.get(stackValores.size()-1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public BigDecimal resultado() {
		return !stackValores.empty() ? stackValores.pop() : null;
	}
}

