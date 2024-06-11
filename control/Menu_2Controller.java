package zz_Project_Gamez.control;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import zz_Project_Gamez.method.SessionCheck;

@Controller
@RequestMapping("/menu/menu_2")
public class Menu_2Controller {
	
	@Autowired
	private SessionCheck sessionCheck;
	
	
	
	@GetMapping("/rules")
	public String rules(HttpServletRequest request, Model model) {
		sessionCheck.SessionChecker(request, model);
		return "/menu/menu_2/rules.html";
	}
	
	@GetMapping("/list/{subcategory}")
	public String getlistSubcategory(@PathVariable String subcategory, HttpServletRequest request, Model model) {
		sessionCheck.SessionChecker(request, model);
		return "/menu/menu_2/list/" + subcategory + ".html";
	}
	
	//링크페이지
	@GetMapping("/links")
	public String links(HttpServletRequest request, Model model) {
		sessionCheck.SessionChecker(request, model);
		return "/menu/menu_2/links.html";
	}
	
}
