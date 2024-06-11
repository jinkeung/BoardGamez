package zz_Project_Gamez.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import zz_Project_Gamez.dto.ProductDTO;
import zz_Project_Gamez.mapper.ProductMapper;
import zz_Project_Gamez.method.SessionCheck;

@Controller
public class HomeController {
	
	@Autowired
	private SessionCheck sessionCheck;
	@Autowired
	private ProductMapper productMapper;
	
	@GetMapping("/")
	public String home(Model model,HttpServletRequest request) {
		
		List<ProductDTO> productDTO=productMapper.getRandom();
		model.addAttribute("random_product",productDTO);
		
		sessionCheck.SessionChecker(request, model);
		
		return "home.html";
	}
	
	@PostMapping("/")
	public String home_post(Model model,HttpServletRequest request) {
		
		List<ProductDTO> productDTO=productMapper.getRandom();
		model.addAttribute("radndom_product",productDTO);
		
		sessionCheck.SessionChecker(request, model);
		
		return "home.html";
	}
}
