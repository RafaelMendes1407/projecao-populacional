package br.com.tt.projecaopopulacional.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tt.projecaopopulacional.domain.dto.LogConsultaDTO;
import br.com.tt.projecaopopulacional.domain.dto.ProjecaoPopulacionalDTO;
import br.com.tt.projecaopopulacional.domain.vo.ProjecaoVO;
import br.com.tt.projecaopopulacional.service.LogService;
import br.com.tt.projecaopopulacional.service.ProjecaoService;

@RestController
@RequestMapping(path = "/projecoes")
public class ProjecaoController {

	@Autowired
	private ProjecaoService projecaoService;

	@Autowired
	private LogService logService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ProjecaoPopulacionalDTO getProjecao(@Valid final ProjecaoVO projecaoVo) {
		return projecaoService.getProjecao(projecaoVo.getDataProjecao());
	}

	@GetMapping("/log")
	@ResponseStatus(HttpStatus.OK)
	public List<LogConsultaDTO> getLogAcesso() {
		return logService.getLogAcesso();
	}
}
