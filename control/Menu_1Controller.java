package zz_Project_Gamez.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import zz_Project_Gamez.dto.ProductDTO;
import zz_Project_Gamez.mapper.ProductMapper;
import zz_Project_Gamez.method.SessionCheck;

@Controller
@RequestMapping("/menu/menu_1")
public class Menu_1Controller {
	
	@Autowired
	private SessionCheck sessionCheck;
	@Autowired
	private ProductMapper productMapper;
	
	
	//코보 메인
	@GetMapping("/kobo")
	public String kobo(HttpServletRequest request, Model model) {	
		//코보만 뽑아오기
		List<ProductDTO> productDTO = productMapper.getAllKobo();
		model.addAttribute("product", productDTO);
		
		sessionCheck.SessionChecker(request, model);
		return "/menu/menu_1/kobo.html";}

	//아스모 메인
	@GetMapping("/asmodee")
	public String asmo(HttpServletRequest request, Model model) {
		
		List<ProductDTO> productDTO = productMapper.getAllAsmo();
		model.addAttribute("product", productDTO);

		sessionCheck.SessionChecker(request, model);
		return "/menu/menu_1/asmodee.html";}
	
	//보드엠 메인
	@GetMapping("/boardm")
	public String boardm(HttpServletRequest request, Model model) {
		
		List<ProductDTO> productDTO = productMapper.getAllM();
		model.addAttribute("product", productDTO);
		
		sessionCheck.SessionChecker(request, model);
		return "/menu/menu_1/boardm.html";}

	
	//상세페이지
	@PostMapping("/detail")
	public String getKoboSubcategory(@RequestParam String s_n, HttpServletRequest request, Model model) {
		
		//누른페이지 s_n 따라서 가져오기
		List<ProductDTO> productDTO=productMapper.getS_nProduct(s_n);
		//그중의 0번째 (아마 데이터 한줄임)
		model.addAttribute("product_detail", productDTO.get(0));
		
		sessionCheck.SessionChecker(request, model);
		return "/menu/menu_1/detail.html";
	}
	
}
