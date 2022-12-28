import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import calculoMatematico.OperadorNotacaoPolonesaEnum;

public class ExpressaoCalculo {
	
	/**
	 * Metodo responsavel por ler a expressao passada e converter a string numa lista de expressoes.
	 * @param expressao {@link String} - Expressao que sera convertida em lista Ex: 2 + (3 - 5) * 9
	 * @return {@link List}<{@link NotacaoPolonesaReversa}>
	 */
	private List<NotacaoPolonesaReversa> lerExpressao(String expressao){
		List<NotacaoPolonesaReversa> retorno = new ArrayList<NotacaoPolonesaReversa>();
		
		// Remove espacos vazios da expressao.
		expressao = expressao.replaceAll(" ", "");
		
		// StringBuilder usado para converter as strings que formam um numero em BigDecimal.
		StringBuilder valor = new StringBuilder();
		
		for (int i = 0; i < expressao.length(); i++) {
			// Pega o caracter na posicao i da expressao e converte para String.
			String codigo = String.valueOf(expressao.charAt(i));
			
			// Verifica se esse caracter eh um numero.
			if(codigo.matches("[0-9]") || codigo.equals(".")){
				
				// Adiciona o caracter no StringBuilder para ser convertido posteriormente.
				valor.append(codigo);
				
				// Verifica se a expressao chegou no seu ultimo caracter, converte esse caracter para um BigDecimal
				if(i == expressao.length() -1){
					retorno.add(new NotacaoPolonesaReversa(new BigDecimal(valor.toString())));
				}
			}else{// Se o caracter verificado nao for um numero.
				
				// Caso possua um valor para ser convertido, adiciona esse valor anter da operacao.
				if(valor.toString() != null && !valor.toString().isEmpty()){
					retorno.add(new NotacaoPolonesaReversa(new BigDecimal(valor.toString())));
					valor.delete(0, valor.length());
				}
				
				// Busca o tipo de operacao que devera fazer.
				retorno.add(new NotacaoPolonesaReversa(OperadorNotacaoPolonesaEnum.buscaValor(codigo)));
			}
		}
		
		return retorno;
	}
	
	/**
	 * Metodo responsavel por devolver a expressao para calculo numa pilha.
	 * @param expressaovariavel
	 * @return
	 */
	public Stack<NotacaoPolonesaReversa> tratarExpressao(String expressao){
		Stack<NotacaoPolonesaReversa> saida = new Stack<NotacaoPolonesaReversa>();
		Stack<NotacaoPolonesaReversa> temporaria = new Stack<NotacaoPolonesaReversa>();
		
		// Faz o processamento da expressao que foi passada.
		List<NotacaoPolonesaReversa> processar = lerExpressao(expressao);
		
		// Percorre a lista de operacoes
		for (NotacaoPolonesaReversa notacaoPolonesaReversa : processar) {
			
			// Se for um operador adiciona na pilha temporaria, verificando a precedencia de operadores.
			if(notacaoPolonesaReversa.getOperador() != null){
				verificarPrecedencia(notacaoPolonesaReversa, saida, temporaria);
			}else{// Se for um numero adiciona na pilha de saida.
				saida.push(notacaoPolonesaReversa);
			}
		}
		
		// Adicona o restante dos operadores na saida.
		while(!temporaria.empty()){
			saida.push(temporaria.pop());
		}
		
		return saida;
	}
	
	/**
	 * Verifica a precedencia de operadores na expressao. 
	 * @param notacao
	 * @param saida
	 * @param temporaria
	 */
	private void verificarPrecedencia(NotacaoPolonesaReversa notacao, Stack<NotacaoPolonesaReversa> saida, Stack<NotacaoPolonesaReversa> temporaria){
		// Se a pilha temporaria nao estiver vazia.
		if(!temporaria.empty()){
			NotacaoPolonesaReversa notacaoAnterior = null;
			
			// Verifica se eh o operador de fechar parenteses.
			if(notacao.getOperador() == OperadorNotacaoPolonesaEnum.FECHAR_PARENTESES){
				
				atualizaASaida(saida, temporaria, true);
			}else if(notacao.getOperador() == OperadorNotacaoPolonesaEnum.SUBTRACAO || notacao.getOperador() == OperadorNotacaoPolonesaEnum.SOMA){
				Boolean notacaoAnteriorPrecede = false;
				
				notacaoAnterior = temporaria.pop();
				if(notacaoAnterior.getOperador() != OperadorNotacaoPolonesaEnum.ABRIR_PARENTESES && notacaoAnterior.getOperador() != OperadorNotacaoPolonesaEnum.FECHAR_PARENTESES){
					notacaoAnteriorPrecede = true;
				}
				
				if(notacaoAnteriorPrecede){
					saida.push(notacaoAnterior);
					atualizaASaida(saida, temporaria, false);
				}else{
					temporaria.push(notacaoAnterior);
				}
				
			}else if(notacao.getOperador() == OperadorNotacaoPolonesaEnum.MULTIPLICACAO || notacao.getOperador() == OperadorNotacaoPolonesaEnum.DIVISAO){
				notacaoAnterior = temporaria.get(temporaria.size() -1);
				if(notacaoAnterior.getOperador() == OperadorNotacaoPolonesaEnum.MULTIPLICACAO || notacaoAnterior.getOperador() == OperadorNotacaoPolonesaEnum.DIVISAO){
					saida.push(temporaria.pop());
				}
			}
		}
		
		if(notacao.getOperador() != OperadorNotacaoPolonesaEnum.FECHAR_PARENTESES){
			temporaria.push(notacao);
		}
		
	}
	
	/**
	 * Adiciona na saida as operacoes que estao na pilha temporaria.
	 * @param saida 
	 * @param temporaria
	 * @param removerAbrirParenteses
	 */
	private void atualizaASaida(Stack<NotacaoPolonesaReversa> saida, Stack<NotacaoPolonesaReversa> temporaria, Boolean removerAbrirParenteses){
		while(!temporaria.empty() && temporaria.get(temporaria.size() -1).getOperador() != OperadorNotacaoPolonesaEnum.ABRIR_PARENTESES){
			saida.push(temporaria.pop());
		}
		
		if(!temporaria.empty() && removerAbrirParenteses){
			temporaria.pop();
		}
	}
	
}
