package zz_Project_Gamez.method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import zz_Project_Gamez.mapper.UserMapper;

@Component
public class SessionCheck {
		

	//세션의 "user_data" 값 == 쿠키 name 같으면 username에 아이디 저장
	public static void SessionChecker(HttpServletRequest request, Model model) {
		HttpSession session=request.getSession();
		Cookie[] cookies = request.getCookies();
		if(cookies!=null&&session.getAttribute("user_data")!=null) {
			for(Cookie i:cookies) {
				if(session.getAttribute("user_data").equals(i.getValue())) {
					model.addAttribute("username", session.getAttribute("user_data"));
				}
			}
		}
	}
	
	
	
	@Autowired
	private UserMapper userMapper;
	
	public void AdminChecker(HttpServletRequest request, Model model) {
		
		//현재 세션 데이터의 id 에 해당하는 user의 role 이 admin인지
		
		HttpSession session=request.getSession();
		
		if(session.getAttribute("user_data") != null) {
		    String userid = session.getAttribute("user_data").toString();
		    String userrole = userMapper.selectRole(userid);
		    if(userrole != null && userrole.equals("admin")) {
		        model.addAttribute("admin", true);
		    }
		}
	
	}
}
