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
import zz_Project_Gamez.mapper.FreeMapper;
import zz_Project_Gamez.mapper.ProductMapper;
import zz_Project_Gamez.mapper.ReplyMapper;
import zz_Project_Gamez.mapper.UserMapper;
import zz_Project_Gamez.method.SessionCheck;
import zz_Project_Gamez.dto.*;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	
	@Autowired
	private SessionCheck sessionCheck;
	@Autowired
	private FreeMapper freeMapper;
	@Autowired
	private ReplyMapper replyMapper;
	@Autowired
	private UserMapper userMapper;

	@GetMapping("/my_main")
	public String my_main(HttpServletRequest request,Model model) {
		
		sessionCheck.SessionChecker(request, model);
		return "/mypage/my_main.html";
	}
	
	
	@PostMapping("/my_main")
	public String my_main_post(HttpServletRequest request, Model model) {
		
		
		sessionCheck.SessionChecker(request, model);
		return "/mypage/my_main.html";
	}


	//자유게시판
	@GetMapping("/my_free")
	public String free(HttpServletRequest request, Model model) {
		
		HttpSession session=request.getSession();
		String username=session.getAttribute("user_data").toString();
		
		//free 테이블에서 현재 게시글 가져와서 넘김
		List<FreeDTO> freeDTO=freeMapper.selectuserFree(username);
		model.addAttribute("free_list", freeDTO);
		
		sessionCheck.SessionChecker(request, model);
		return "/mypage/my_free.html";
	}
	
	//내정보
	@GetMapping("/my_info")
	public String my_info(HttpServletRequest request,Model model) {
		
		HttpSession session=request.getSession();
		String username=session.getAttribute("user_data").toString();
		List<UserDTO> userDTO=userMapper.selectNowUser(username);
		
		
		model.addAttribute("user_info", userDTO.get(0));
		
		sessionCheck.SessionChecker(request, model);
		return "/mypage/my_info.html";
	}
	
	//내정보
	@PostMapping("/my_info_modify")
	public String my_info(@RequestParam(name="pw") String pw,
			  			  @RequestParam(name="name") String name, @RequestParam(name="postcode") String postcode,
						  @RequestParam(name="addr_m") String addr_m, @RequestParam(name="addr_s") String addr_s,
						  Model model,HttpServletRequest request) {
		HttpSession session=request.getSession();
		String username=session.getAttribute("user_data").toString();
		
		userMapper.updateUser(pw, name, postcode, addr_m, addr_s, username);
		
		List<UserDTO> userDTO=userMapper.selectNowUser(username);
		model.addAttribute("user_info", userDTO.get(0));
		sessionCheck.SessionChecker(request, model);
		return "/mypage/my_info.html";	
	}
	

	
	
	@GetMapping("/my_reply")
	public String my_reply(HttpServletRequest request,Model model) {
		
		HttpSession session=request.getSession();
		String username=session.getAttribute("user_data").toString();
		List<ReplyDTO> replyDTO=replyMapper.selectuserReply(username);
		
		model.addAttribute("user_reply", replyDTO);

		sessionCheck.SessionChecker(request, model);
		return "/mypage/my_reply.html";
	}
	
	@PostMapping("/my_reply")
	public String my_reply_post(HttpServletRequest request,Model model) {
		
		HttpSession session=request.getSession();
		String username=session.getAttribute("user_data").toString();
		List<ReplyDTO> replyDTO=replyMapper.selectuserReply(username);
		
		model.addAttribute("user_reply", replyDTO);
		
		sessionCheck.SessionChecker(request, model);
		return "/mypage/my_reply.html";
	}
	
	@PostMapping("/deletereply")
	public String deleteUser(@RequestParam List<String> delete_reply_num) {
		
		for(String i:delete_reply_num) {
			replyMapper.deleteReply(Integer.valueOf(i));
		}
		
	    return "redirect:/mypage/my_reply";
	}
	
}
