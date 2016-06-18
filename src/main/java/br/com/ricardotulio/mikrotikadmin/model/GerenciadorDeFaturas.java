package br.com.ricardotulio.mikrotikadmin.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GerenciadorDeFaturas {

	public static final int NUMERO_DIAS_ANTECEDENCIA_GERACAO_FATURA = 10;

	public void geraProximasFaturas(List<Cliente> clientes, Calendar dataFaturamentoTermino, Calendar dataVencimento, List<AcaoAposGerarFaturas> acoesAposGerarFatura) {		
		List<Fatura> faturas = new ArrayList<Fatura>();
		
		for(Cliente cliente : clientes) {
			Calendar dataFaturamentoInicio = cliente.getDataFaturamentoInicioProximaFatura();
			PeriodoFaturamento periodoFaturamento = new PeriodoFaturamento(dataFaturamentoInicio, dataFaturamentoTermino);
			Fatura fatura = new Fatura();
			fatura.setCliente(cliente);
			fatura.setPeriodoFaturamento(periodoFaturamento);
			fatura.setDataVencimento(dataVencimento);
			fatura.calculaValor();
					
			faturas.add(fatura);
		}
		
		for(AcaoAposGerarFaturas acao : acoesAposGerarFatura) {
			acao.executa(faturas);
		}
	}

}
