package br.com.ricardotulio.mikrotikadmin.model;

import java.math.BigDecimal;

import br.com.uol.pagseguro.domain.checkout.Checkout;
import br.com.uol.pagseguro.enums.Currency;
import br.com.uol.pagseguro.enums.DocumentType;
import br.com.uol.pagseguro.enums.ShippingType;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;

public class GerarFaturaPagSeguro implements AcaoAposGerarFatura {

	public void executa(Fatura fatura) {
		Cliente cliente = fatura.getCliente();		
		Endereco endereco = cliente.getEnderecos().iterator().next();
		Contato contato = cliente.getContatos().iterator().next();

		String idFormatado = String.format("%05d", fatura.getId());
		String descricaoBoleto = "SERVIÇO INTERNET - " + fatura.getCliente().getPlano().getTitulo().toUpperCase();
		Integer quantidade = 1;

		BigDecimal valorBoleto = new BigDecimal(String.format("%.2f", fatura.getValor()).replace(",", "."));

		Long peso = 0L;
		BigDecimal valorFrete = new BigDecimal("0.00");

		Checkout paymentRequest = new Checkout();
		paymentRequest.addItem(idFormatado, descricaoBoleto, quantidade, valorBoleto, peso, valorFrete);

		paymentRequest.setShippingAddress("BRA", endereco.getUf(), endereco.getCidade(), endereco.getBairro(),
				endereco.getCep(), endereco.getLogradouro(), endereco.getNumero().toString(),
				endereco.getComplemento());

		paymentRequest.setSender(cliente.getNome(), contato.getEmail(), "11", contato.getTelefone(), DocumentType.CPF,
				cliente.getCpf());

		paymentRequest.setShippingType(ShippingType.NOT_SPECIFIED);
		paymentRequest.setShippingCost(new BigDecimal("0.00"));
		paymentRequest.setReference("REF1234-USER1234-ORDER37148973249");
		paymentRequest.setNotificationURL("https://www.mikrotikadmin.com");
		paymentRequest.setCurrency(Currency.BRL);

		try {
			boolean onlyCheckoutCode = false;
			String response = paymentRequest.register(PagSeguroConfig.getAccountCredentials(), onlyCheckoutCode);

			System.out.println(response);
		} catch (PagSeguroServiceException e) {
			e.printStackTrace();
		}
	}

}
