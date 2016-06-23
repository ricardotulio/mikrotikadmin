package br.com.ricardotulio.mikrotikadmin.controller;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class FaturaControllerTest {
	
	private Long numeroA;
	private Long numeroB;
	private Long resultadoEsperado;
	
	public FaturaControllerTest(Long numeroA, Long numeroB, Long resultadoEsperado) {
		this.numeroA = numeroA;
		this.numeroB = numeroB;
		this.resultadoEsperado = resultadoEsperado;
	}

	@Parameters(name = "{index}: testaSeGeraFatura({0} + {1}) = {2}")
	public static Collection<Object[]> parametros() {
		return Arrays.asList(new Object[][] {
			{1L, 1L, 2L},
			{2L, 2L, 4L},
			{3L, 3L, 6L},
		});
	}
	
	@Test
	public void testaSeGeraFatura() {
		assertEquals((this.numeroA + this.numeroB), this.resultadoEsperado, 0.01);
	}
	
}
