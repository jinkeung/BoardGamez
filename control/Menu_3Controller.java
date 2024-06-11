package zz_Project_Gamez.control;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import zz_Project_Gamez.dto.FreeDTO;
import zz_Project_Gamez.dto.ReplyDTO;
import zz_Project_Gamez.mapper.FreeMapper;
import zz_Project_Gamez.mapper.ReplyMapper;
import zz_Project_Gamez.method.SessionCheck;

@Controller
@RequestMapping("/menu/menu_3")
public class Menu_3Controller {
	
	@Autowired
	private SessionCheck sessionCheck;
	@Autowired
	private FreeMapper freeMapper;
	@Autowired
	private ReplyMapper replyMapper;
	

	
	//자유게시판
	@GetMapping("/free")
	public String free(HttpServletRequest request, Model model) {
		
		//free 테이블에서 현재 게시글 가져와서 넘김
		List<FreeDTO> freeDTO=freeMapper.selectAllfrees();
		model.addAttribute("free_list", freeDTO);
		
		sessionCheck.SessionChecker(request, model);
		return "/menu/menu_3/free.html";
	}
	
	//자유게시판 게시글 작성 페이지 이동
	@GetMapping("/free_write")
	public String free_w(HttpServletRequest request, Model model) {
		sessionCheck.SessionChecker(request, model);
		return "/menu/menu_3/free_wirte.html";
	}
	
	//post=> 게시글 저장 => free 메인으로 다시 
	@PostMapping("/free_write_end")
	public String free_writing(HttpServletRequest request, Model model,
							   @RequestParam String title, @RequestParam String text) {
		
		//현재 id, 시간, 입력받은 title, text 
		HttpSession session=request.getSession();
		String login_id=session.getAttribute("user_data").toString();
		Timestamp date_now=new Timestamp(System.currentTimeMillis());
		
		//=> free 테이블에 insert
		freeMapper.insertFree(login_id, title, text, date_now);
		
		// select로 다시 free테이블 정보 가져와서 보내기
		List<FreeDTO> free=freeMapper.selectFree();
		model.addAttribute("free_list", free);
		
		sessionCheck.SessionChecker(request, model);
		return "redirect:/menu/menu_3/free";
	}
	
	//게시글 상세페이지
    @PostMapping("/freedetail")
    public String showfreeDetails(@RequestParam String s_num, HttpServletRequest request, Model model) {
    	//게시글(free) 테이블에서 select해서 freedetail페이지로 넘김
    	FreeDTO freedetail=freeMapper.select_snumFree(s_num);
    	model.addAttribute("freedetail",freedetail);
    	
    	//게시글 테이블의 s_num과 일치하는 댓글 select 해서 matchreply로 넘김
    	List<ReplyDTO> matchreply=replyMapper.selectmatchReply(s_num);
    	if (matchreply.isEmpty()) {model.addAttribute("matchreply", null);}
    	else{model.addAttribute("matchreply", matchreply);}

    	
    	
    	//현재user 와 작성자가 일치하는지 확인 => write true,false 넘김 =>(게시글수정,삭제권한)
    	HttpSession session=request.getSession();
    	String user=session.getAttribute("user_data").toString();
    	String writer=freeMapper.selectWriter(s_num);
    	if(user.equals(writer)) {
    		model.addAttribute("writer", true);
    	}else model.addAttribute("writer",false);
    	
       	//현재 user와 댓글 작성자가 일치하는지 확인=> reply_writer true, false 넘김 => (댓글수정,삭제권한)
    	boolean reply_writer=false;
    	
    	for(ReplyDTO i: matchreply) {
    		if(i.getId().equals(user)) {
    			reply_writer=true;
    			break;
    		}
    	}
    	model.addAttribute("reply_writer", reply_writer);
    	
    	sessionCheck.SessionChecker(request, model);
        return "/menu/menu_3/free_detail.html"; 
    }
    
	
    //게시글 수정
    @PostMapping("/freemodify")
    public String modifyfreeDetails(@RequestParam String s_num, HttpServletRequest request, Model model) {
    	
    	//게시글 s_num에 해당하는 게시글 select해서 modify 페이지에서 열기
       	FreeDTO freedetail=freeMapper.select_snumFree(s_num);
    	model.addAttribute("freedetail",freedetail);
    	
    	sessionCheck.SessionChecker(request, model);
        return "/menu/menu_3/free_modify";
    }
    
    //게시글 수정 완료
    @PostMapping("/freemodify_end")
    public String modify_end(@RequestParam String s_num, @RequestParam String title, @RequestParam String text,
    						HttpServletRequest request, Model model) {
    	//free 테이블에 update
    	freeMapper.updateFree(s_num, title, text);
    	
    	sessionCheck.SessionChecker(request, model);
        return "/menu/menu_3/free_modify_end"; 
    }
    
    
    //게시글 삭제
    @PostMapping("/freedelete")
    public String deletefree(@RequestParam String s_num, HttpServletRequest request, Model model) {
    	
    	// 현재 게시글의 s_num 에 해당하는 게시글 delete
    	freeMapper.deleteFree(s_num);
    	sessionCheck.SessionChecker(request, model);
        return "/menu/menu_3/free_delete"; 
    }
    
    
	//댓글 insert
	@PostMapping("/replywrite")
	public String replywriter(@RequestParam String replytext,@RequestParam String s_num,
							  HttpServletRequest request, Model model) {
		
		//현재 id, 시간, 입력받은 replytext, s_num => reply테이블로 insert
		HttpSession session=request.getSession();
		String id=session.getAttribute("user_data").toString();
		Timestamp date_now=new Timestamp(System.currentTimeMillis());
	
		replyMapper.insertFree(id, replytext, date_now, s_num);
		
		
    	//게시글(free) 테이블에서 select해서 freedetail에서 표시
		FreeDTO freedetail=freeMapper.select_snumFree(s_num);
    	model.addAttribute("freedetail",freedetail);
    	
    	
    	//게시글 테이블의 s_num과 일치하는 댓글 select 해서 matchreply로 표시
    	List<ReplyDTO> matchreply=replyMapper.selectmatchReply(s_num);
    	model.addAttribute("matchreply", matchreply);

    	
    	//현재user 와 작성자가 일치하는지 확인 => write true,false 넘김 =>(게시글수정,삭제권한)
    	String user=session.getAttribute("user_data").toString();
    	String writer=freeMapper.selectWriter(s_num);
    	if(user.equals(writer)) {
    		model.addAttribute("writer", true);
    	}else model.addAttribute("writer",false);
    	
    	//현재 user와 댓글 작성자가 일치하는지 확인=> reply_writer true, false 넘김 => (댓글관리 권한)
    	boolean reply_writer=false;
    	
    	for(ReplyDTO i: matchreply) {
    		if(i.getId().equals(user)) {
    			reply_writer=true;
    			break;
    		}
    	}
    	model.addAttribute("reply_writer", reply_writer);
    	
		sessionCheck.SessionChecker(request, model);
		return "/menu/menu_3/free_detail.html";
	}
    
    
}
