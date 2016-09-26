package br.com.ricardotulio.mikrotikadmin.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ricardotulio.mikrotikadmin.dao.FaturaDao;
import br.com.ricardotulio.mikrotikadmin.model.Fatura;
import br.com.ricardotulio.mikrotikadmin.model.FormaPagamento;
import br.com.ricardotulio.mikrotikadmin.model.StatusFatura;

@Controller
public class FaturasController {

	private static final String FATURA_NAO_ENCONTRADA = "Fatura não encontrada!";
	private static final String FATURA_CANCELADA_COM_SUCESSO = "Fatura cancelada com sucesso!";
	private static final String FATURA_JA_CANCELADA = "Esta fatura já está cancelada!";
	private static final String FATURA_NAO_PAGA_JA_CANCELADA = "Não é possível pagar esta fatura pois ela já foi cancelada!";
	private static final String FATURA_NAO_CANCELADA_JA_ESTA_PAGA = "Essa fatura não pode ser cancelada porque ela já está paga!";
	private static final String FATURA_JA_ESTA_PAGA = "Essa fatura não pode ser cancelada porque ela já está paga!";
	private static final String FATURA_PAGA_COM_SUCESSO = "Fatura paga com sucesso!";

	private FaturaDao faturaDao;

	@Autowired
	public FaturasController(FaturaDao faturaDao) {
		this.faturaDao = faturaDao;
	}

	@RequestMapping(value = { "/faturas", "/faturas/" }, method = RequestMethod.GET)
	public String index(Model model) {
		List<Fatura> faturas = this.faturaDao.obtemLista();
		List<StatusFatura> listaStatusFatura = Arrays.asList(StatusFatura.values());

		model.addAttribute("faturas", faturas);
		model.addAttribute("listaStatusFatura", listaStatusFatura);

		return "faturas/index";
	}

	@RequestMapping(value = "/faturas/{id}/cancelar")
	@Transactional
	public String cancelar(@PathVariable("id") Long id, final RedirectAttributes redirectAttributes) {
		Fatura fatura = this.faturaDao.obtem(id);

		if (fatura == null) {
			redirectAttributes.addFlashAttribute("error", FaturasController.FATURA_NAO_ENCONTRADA);
			return "redirect:/faturas/";
		}

		if (fatura.cancelada()) {
			redirectAttributes.addFlashAttribute("error", FaturasController.FATURA_JA_CANCELADA);
			return "redirect:/faturas/";
		}

		if (fatura.paga()) {
			redirectAttributes.addFlashAttribute("error", FaturasController.FATURA_NAO_CANCELADA_JA_ESTA_PAGA);
			return "redirect:/faturas/";
		}

		fatura.cancelar();
		this.faturaDao.persiste(fatura);

		redirectAttributes.addFlashAttribute("success", FaturasController.FATURA_CANCELADA_COM_SUCESSO);
		return "redirect:/faturas/";
	}

	@RequestMapping(value = { "/faturas/cadastrar", "/faturas/cadastrar/" }, method = RequestMethod.GET)
	public String cadastrarGet(Model model) {
		List<StatusFatura> listaStatusFatura = Arrays.asList(StatusFatura.values());
		List<FormaPagamento> listaFormasPagamento = Arrays.asList(FormaPagamento.values());

		model.addAttribute("listaStatusFatura", listaStatusFatura);
		model.addAttribute("listaFormasPagamento", listaFormasPagamento);

		return "faturas/cadastrar";
	}

	@RequestMapping(value = "/faturas/{id}/pagar")
	@Transactional
	public String pagar(@PathVariable("id") Long id, final RedirectAttributes redirectAttributes) {
		Fatura fatura = this.faturaDao.obtem(id);

		if (fatura == null) {
			redirectAttributes.addFlashAttribute("error", FaturasController.FATURA_NAO_ENCONTRADA);
			return "redirect:/faturas/";
		}

		if (fatura.cancelada()) {
			redirectAttributes.addFlashAttribute("error", FaturasController.FATURA_NAO_PAGA_JA_CANCELADA);
			return "redirect:/faturas/";
		}

		if (fatura.paga()) {
			redirectAttributes.addFlashAttribute("error", FaturasController.FATURA_JA_ESTA_PAGA);
			return "redirect:/faturas/";
		}

		fatura.pagar(FormaPagamento.DINHEIRO);
		this.faturaDao.persiste(fatura);

		redirectAttributes.addFlashAttribute("success", FaturasController.FATURA_PAGA_COM_SUCESSO);
		return "redirect:/faturas/";
	}
	
	@ResponseBody
	public String pagarPagSeguro(@PathVariable("transactionId") Long transactionId) {
		System.out.println("Entrou aqui");
		return "ok";
	}
}
