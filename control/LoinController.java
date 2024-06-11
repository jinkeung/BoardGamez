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
import zz_Project_Gamez.mapper.ProductMapper;
import zz_Project_Gamez.mapper.UserMapper;
import zz_Project_Gamez.dto.*;

@Controller
@RequestMapping("/login")
public class LoinController {
	
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private UserMapper userMapper;

	
	@GetMapping("/login_main")
	public String loginmain(Model model) {
		model.addAttribute("userlogin", true);
		
		return "login/login_main.html";
	}
	
	@PostMapping("/login_process")
	public String loginProc(@RequestParam String login_id, @RequestParam String login_pw,
							Model model,HttpServletRequest request,HttpServletResponse response) {


		//로그인 실패하면 login_main, 성공하면 홈으로, 세션, 쿠키 생성
		String dataid=userMapper.selectId(login_id);
		String datapw=userMapper.selectPw(login_id);

		if(dataid!=null) {
			if(dataid.equals(login_id)&&datapw.equals(login_pw)) {
				HttpSession session=request.getSession();
				session.setAttribute("user_data", login_id);

				Cookie current_user=new Cookie("user",login_id);
				current_user.setMaxAge(60*60*24);
				current_user.setPath("/");
				response.addCookie(current_user);
				model.addAttribute("username", session.getAttribute("user_data"));
				return "redirect:/";
			}
		}
		
		model.addAttribute("userlogin", false);
		return "login/login_main.html";
	}
	
	
	@PostMapping("/logout")
	public String logout(HttpServletRequest request,Model model) {
	    HttpSession session = request.getSession(false);
	    if (session != null) {session.invalidate();}

		return "redirect:/";
	}

	
	
}
