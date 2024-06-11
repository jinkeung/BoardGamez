package zz_Project_Gamez.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import zz_Project_Gamez.dto.FreeDTO;
import zz_Project_Gamez.dto.ProductDTO;
import zz_Project_Gamez.dto.ProductDTO_builder;
import zz_Project_Gamez.dto.UserDTO;
import zz_Project_Gamez.mapper.FreeMapper;
import zz_Project_Gamez.mapper.ProductMapper;
import zz_Project_Gamez.mapper.UserMapper;
import zz_Project_Gamez.method.SessionCheck;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private SessionCheck sessionCheck;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private FreeMapper freeMapper;
	
	@GetMapping("")
	public String admin_main(HttpServletRequest request, Model model) {
		sessionCheck.SessionChecker(request, model);
		
		HttpSession session=request.getSession();
		
		if(session.getAttribute("user_data") != null) {
		    String userid = session.getAttribute("user_data").toString();
		    String userrole = userMapper.selectRole(userid);
		    if(userrole != null && userrole.equals("admin")) {
		        model.addAttribute("admin", true);
		        return "admin/admin_menu.html";
		    }
		}
		
		return "admin/admin_login.html";
	}
	
	@GetMapping("/")
	public String admin__main(HttpServletRequest request, Model model) {
		sessionCheck.SessionChecker(request, model);
		
		HttpSession session=request.getSession();
		
		if(session.getAttribute("user_data") != null) {
		    String userid = session.getAttribute("user_data").toString();
		    String userrole = userMapper.selectRole(userid);
		    if(userrole != null && userrole.equals("admin")) {
		        model.addAttribute("admin", true);
		        return "admin/admin_menu.html";
		    }
		}
		return "admin/admin_login.html";
	}
	
	@GetMapping("/admin_menu")
	public String admin_menu(HttpServletRequest request, Model model) {
		sessionCheck.SessionChecker(request, model);
		sessionCheck.AdminChecker(request, model);
		return "admin/admin_menu.html";
	}
	
	@PostMapping("/admin_menu")
	public String adminloginProc(@RequestParam String login_id, @RequestParam String login_pw,
							Model model,HttpServletRequest request,HttpServletResponse response) {
		
		//실패하면 그자리, 성공하면 홈으로, 세션, 쿠키 생성
		String inputid=userMapper.selectId(login_id);
		String inputpw=userMapper.selectPw(login_id);
		String userrole=userMapper.selectRole(inputid);
		
		if(inputid!=null&&userrole!=null) {
			if(inputid.equals(login_id)&&inputpw.equals(login_pw)&&userrole.equals("admin")) {
				HttpSession session=request.getSession();
				session.setAttribute("user_data", login_id);
				
				Cookie current_user=new Cookie("user",login_id);
				current_user.setMaxAge(10000);
				current_user.setPath("/");
				response.addCookie(current_user);
				model.addAttribute("username", session.getAttribute("user_data"));
				sessionCheck.AdminChecker(request, model);
				return "/admin/admin_menu.html";
			}
		}
		
		model.addAttribute("userlogin", false);
		return "/admin/admin_login";
	}
	
	@GetMapping("/admin_product")
	public String admin_product(HttpServletRequest request, Model model) {
		
		List<ProductDTO> products=productMapper.selectAllProducts();
		model.addAttribute("products", products);
		
		sessionCheck.SessionChecker(request, model);
		sessionCheck.AdminChecker(request, model);
		return "admin/admin_product.html";
	}
	
	@PostMapping("/addProduct")
	public String addProduct(@RequestParam String s_n, @RequestParam String name,
	                         @RequestParam int price, @RequestParam int quantity,
	                         @RequestParam String a_1, @RequestParam String a_2,
	                         @RequestParam String a_3, @RequestParam String link,
	                         HttpServletRequest request, Model model) {
		
		ProductDTO productDTO =new ProductDTO_builder().setS_n(s_n).setName(name).setPrice(price).setQuantity(quantity)
														.setA_1(a_1).setA_2(a_2).setA_3(a_3).setLink(link).build();
	    productMapper.addProduct(productDTO);
	    sessionCheck.SessionChecker(request, model);
	    sessionCheck.AdminChecker(request, model);
	    return "redirect:/admin/admin_product";
	}

	@PostMapping("/updateProduct")
	public String updateProduct(@RequestParam String s_n, @RequestParam String name,
					            @RequestParam int price, @RequestParam int quantity,
					            @RequestParam String a_1, @RequestParam String a_2,
					            @RequestParam String a_3, @RequestParam String link,
					            HttpServletRequest request, Model model) {
		ProductDTO productDTO =new ProductDTO_builder().setS_n(s_n).setName(name).setPrice(price).setQuantity(quantity)
				.setA_1(a_1).setA_2(a_2).setA_3(a_3).setLink(link).build();
	    productMapper.updateProduct(productDTO);
	    sessionCheck.SessionChecker(request, model);
	    sessionCheck.AdminChecker(request, model);
	    return "redirect:/admin/admin_product";
	}
	
	@PostMapping("/deleteProduct")
	public String deleteProduct(@RequestParam String s_n,HttpServletRequest request, Model model) {
		productMapper.deleteProduct(s_n);
		sessionCheck.SessionChecker(request, model);
		 sessionCheck.AdminChecker(request, model);
	    return "redirect:/admin/admin_product";
	}


	
	@GetMapping("/admin_user")
	public String admin_user(HttpServletRequest request, Model model) {
		
		List<UserDTO> users=userMapper.selectAllUsers();
		model.addAttribute("users", users);

		sessionCheck.SessionChecker(request, model);
		sessionCheck.AdminChecker(request, model);
		return "admin/admin_user.html";
	}
	@PostMapping("/deleteUser")
	public String deleteUser(@RequestParam String id) {
		userMapper.deleteUser(id);	    
	    return "redirect:/admin/admin_user";
	}
	
	@GetMapping("/admin_free")
	public String admin_free(HttpServletRequest request, Model model) {
		
		List<FreeDTO> frees=freeMapper.selectAllfrees();
		model.addAttribute("free_list", frees);
		
		sessionCheck.SessionChecker(request, model);
		sessionCheck.AdminChecker(request, model);
		return "admin/admin_free.html";
	}
	@PostMapping("/deleteFree")
	public String deleteFree(@RequestParam String s_num) {
		freeMapper.deleteFree(s_num);  
	    return "redirect:/admin/admin_free";
	}
	

	
}
