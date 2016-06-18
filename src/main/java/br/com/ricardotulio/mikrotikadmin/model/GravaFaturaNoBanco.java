package br.com.ricardotulio.mikrotikadmin.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ricardotulio.mikrotikadmin.dao.FaturaDao;

@Component
public class GravaFaturaNoBanco implements AcaoAposGerarFaturas {

	private FaturaDao faturaDao;

	@Autowired
	public GravaFaturaNoBanco(FaturaDao faturaDao) {
		this.faturaDao = faturaDao;
	}

	public void executa(List<Fatura> faturas) {
		for (Fatura fatura : faturas) {
			this.faturaDao.persiste(fatura);
		}
	}

}
