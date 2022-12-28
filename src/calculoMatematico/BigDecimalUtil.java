package calculoMatematico;
import java.math.BigDecimal;
import java.math.MathContext;

public class BigDecimalUtil {
	
	public static BigDecimal calcular(BigDecimal valor1, Operador operador, BigDecimal valor2) {
		BigDecimal retorno = null;
		
		if (operador == Operador.SOMA) {
			retorno = valor1.add(valor2);
		} else if (operador == Operador.SUBTRACAO) {
			retorno = valor1.subtract(valor2);
		} else if (operador == Operador.DIVISAO) {
			retorno = valor1.divide(valor2, MathContext.DECIMAL128);
		} else if (operador == Operador.MULTIPLICACAO) {
			retorno	= valor1.multiply(valor2, MathContext.DECIMAL128);
		}
		
		return retorno;
	}

}
