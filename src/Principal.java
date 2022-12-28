import java.util.Stack;

public class Principal {

	
	public static void main(String args[]) {
		Stack<NotacaoPolonesaReversa> notacao = new Stack<NotacaoPolonesaReversa>();
		ExpressaoCalculo expressaoCalculo = new ExpressaoCalculo();
		Calculo calculo = new Calculo();
		
		String soma = "4 / (2 + 1) - (5 + (2 + 2)) * 3";
		notacao = expressaoCalculo.tratarExpressao(soma);
		System.out.println(notacao);
		
		for (NotacaoPolonesaReversa notacaoPolonesaReversa : notacao) {
			
			if (notacaoPolonesaReversa.getValor() != null)  {
				calculo.setValor(notacaoPolonesaReversa.getValor());
			} else if (notacaoPolonesaReversa.getOperador() != null) {
				calculo.setOperador(notacaoPolonesaReversa.getOperador());;
			}
		}
		
		System.out.println(calculo.resultado());
	}
}
