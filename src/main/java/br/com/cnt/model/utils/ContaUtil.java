package br.com.cnt.model.utils;

import java.math.BigDecimal;

import br.com.cnt.model.entity.balanco.Conta;
import br.com.cnt.model.entity.balanco.ContaOrigem;

public class ContaUtil {
	
	public static Integer retornarNivel(Conta conta){
		String estrutura = conta.getEstrutura();
		String[] split = estrutura.split("\\.");
		for (int i = 0; i < split.length; i++) {
			if(Integer.parseInt(split[i]) == 0)
				return i;
		}
		return split.length;
	}
	
	public static String estruturaSemZeros(Conta conta) {
		return conta.getEstrutura().replace(".00", "").replace(".0", "");
	}
	
	/**
	 * Todo valor a débito será positivo e crédito negativo.
	 * Caso o valor não esteja correto, será a origem será invertida e o valor absoluto será exibido.
	 * @param valor
	 * @param origem
	 * @return
	 */
	public static String getValorContabil(BigDecimal valor, ContaOrigem origem){
		float vlr = valor.floatValue();
		ContaOrigem co = null;
		if(ContaOrigem.DEVEDORA == origem && vlr < 0f ){
			co = ContaOrigem.CREDORA;
		}
		if(ContaOrigem.CREDORA == origem && vlr > 0f ){
			co = ContaOrigem.DEVEDORA;
		}
		if(co == null){
			co = origem;
		}
		String format = NumberUtil.format(Math.abs(valor.doubleValue()));
		return format + " " + co.codigo;
	}
	
	public static boolean isValorContabilPositivo(float valor, ContaOrigem origem){
		if(ContaOrigem.DEVEDORA == origem && valor < 0f ){
			return false;
		}
		if(ContaOrigem.CREDORA == origem && valor > 0f ){
			return false;
		}
		return true;
	}

	public static int compararNivel(Conta conta1, Conta conta2) {
		String[] split1 = conta1.getEstrutura().split("\\.");
		String[] split2 = conta2.getEstrutura().split("\\.");
		if(split1.length != split2.length){
			throw new RuntimeException("As estruturas das contas não podem ser comparadas.");
		}
		for (int i = 0; i < split2.length; i++) {
			int compareTo = new Integer(split1[i]).compareTo(new Integer(split2[i]));
			if(compareTo != 0){
				return compareTo;
			}
		}
		return 0;
	}

	public static boolean isFilho(Conta conta1, Conta conta2) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean isPai(Conta conta, Conta conta2) {
		// TODO Auto-generated method stub
		return false;
	}

}
