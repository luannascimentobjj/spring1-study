package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Controller
@RequestMapping("produtos")
public class ProdutosController {

	@Autowired
	private ProdutoDAO produtodDao;
	
	
	@RequestMapping("/form")
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView("produtos/form");
		mv.addObject("tipos", TipoPreco.values());
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView grava(Produto produto, RedirectAttributes redirectAttributes) {
		System.out.println(produto);
		produtodDao.gravar(produto);
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");
		return new ModelAndView("redirect:produtos");
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> produtos = produtodDao.listar();
		ModelAndView mv = new ModelAndView("produtos/lista");
		mv.addObject("produtos", produtos);
		return mv;
		
	}

}
