package zz_Project_Gamez.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import zz_Project_Gamez.dto.FreeDTO;
import zz_Project_Gamez.dto.ReplyDTO;
import zz_Project_Gamez.dto.UserDTO;

@Mapper
public interface ReplyMapper {
	
	@Insert("INSERT INTO reply (id,text,date_now,s_num)"
			+ "VALUES (#{id}, #{text}, #{date_now},#{s_num})")
	public void insertFree(String id, String text, Timestamp date_now, String s_num);
	
    @Select("select * from reply where s_num=#{s_num} order by date_now desc")
    public List<ReplyDTO> selectmatchReply(String s_num);
    
	@Select("select id from reply where reply_num=#{reply_num} order by date_now desc")
	public String selectreWriter(String reply_num);
	
	@Select("select * from reply where id=#{id} order by date_now desc")
	public List<ReplyDTO> selectuserReply(String id);
	
	
	@Delete("delete from reply where reply_num=#{reply_num}")
	public void deleteReply(int reply_num);
	
}	
