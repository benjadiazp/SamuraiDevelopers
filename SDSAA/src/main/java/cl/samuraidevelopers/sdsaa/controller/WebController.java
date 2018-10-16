package cl.samuraidevelopers.sdsaa.controller;

import cl.samuraidevelopers.sdsaa.domain.*;

import javax.validation.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Usuario usuario, Model model)
	{
		System.out.println();
		model.addAttribute("usuario", new Usuario());
		return "InicioSesion";
	}
	
	@RequestMapping(value="/iniciarSesion", method=RequestMethod.POST)
	public String iniciarSesion(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model)
	{
		//if (result.hasErrors()) return "Error";
		return "Notificaciones";
	}

}
