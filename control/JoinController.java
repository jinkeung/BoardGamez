package zz_Project_Gamez.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import zz_Project_Gamez.dto.UserDTO_builder;
import zz_Project_Gamez.mapper.UserMapper;
import zz_Project_Gamez.dto.*;

@Controller
@RequestMapping("/join")
public class JoinController {
	
	@Autowired
	private UserMapper usermapper;
	
	@GetMapping("/join_main")
	public String joinmain() {
		return "join/join_main.html";
	}
	

	
	@PostMapping("/join_process")
	public String joinend(@RequestParam String id, @RequestParam String pw,
			  @RequestParam String name, @RequestParam String postcode,
			  @RequestParam String addr_m, @RequestParam String addr_s,
			  Model model) {
		
		   try {
		        UserDTO userdto = new UserDTO_builder().setId(id).setPw(pw).setName(name)
		        				.setPostcode(postcode).setAddr_m(addr_m).setAddr_s(addr_s).build();
		        usermapper.insertUser(userdto);
		        return "join/join_end.html";
		        
		    } catch (Exception e) { // 예외 처리: 데이터베이스 삽입 작업 실패 시
		        e.printStackTrace(); 
		        model.addAttribute("test", false);
		        return "join/join_main.html";}
		  
	}
	
}
