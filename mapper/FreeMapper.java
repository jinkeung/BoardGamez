package zz_Project_Gamez.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import zz_Project_Gamez.dto.FreeDTO;
import zz_Project_Gamez.dto.UserDTO;

@Mapper
public interface FreeMapper {
	
	@Insert("INSERT INTO free (id, title, text, date_now) "
			+ "VALUES (#{id}, #{title}, #{text}, #{date_now})")
	public void insertFree(String id, String title, String text, Timestamp date_now);
	
	@Select("select s_num,id,title,text,date_now from free")
	public List<FreeDTO> selectFree();
	
	@Select("select * from free where s_num=#{s_num}")
	public FreeDTO select_snumFree(String s_num);
	
    @Select("select * from free order by s_num desc")
    public List<FreeDTO> selectAllfrees();
    
    @Delete("delete from free where s_num=#{s_num} ")
    public void deleteFree(String s_num);
    
	@Select("select id from free where s_num=#{s_num}")
	public String selectWriter(String s_num);

	@Update("update free set title=#{title},text=#{text} where s_num=#{s_num}")
	public void updateFree(String s_num, String title, String text);
	
	@Select("select * from free where id=#{username} order by s_num desc")
	public List<FreeDTO> selectuserFree(String username);
}	
