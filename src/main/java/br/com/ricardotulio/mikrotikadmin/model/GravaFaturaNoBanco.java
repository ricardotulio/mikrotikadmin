package br.com.ricardotulio.mikrotikadmin.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ricardotulio.mikrotikadmin.dao.FaturaDao;

@Component
public class GravaFaturaNoBanco implements AcaoAposGerarFatura {

	private FaturaDao faturaDao;

	@Autowired
	public GravaFaturaNoBanco(FaturaDao faturaDao) {
		this.faturaDao = faturaDao;
	}

	public void executa(Fatura fatura) {
		this.faturaDao.persiste(fatura);
	}

}
