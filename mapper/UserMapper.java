package zz_Project_Gamez.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import zz_Project_Gamez.dto.UserDTO;

@Mapper
public interface UserMapper {
	
    @Insert("INSERT INTO users VALUES (#{role}, #{id}, #{pw}, #{name}, "
    		+ "#{postcode}, #{addr_m}, #{addr_s})")
    void insertUser(UserDTO userDTO);
    
    @Select("select * from users order by id")
    List<UserDTO> selectAllUsers();
	
    @Select("select id from users where id=#{id}")
    String selectId(String id);
    
    @Select("select pw from users where id=#{id}")
    String selectPw(String id);
    
    @Select("select role from users where id=#{id}")
    String selectRole(String id);
    
    @Delete("delete from users where id=#{id} ")
    void deleteUser(String id);
    
    @Select("select * from users where id=#{username}")
    List<UserDTO> selectNowUser(String username);
    
    @Update("UPDATE users SET pw = #{pw}, name= #{name}, "
    		+ "postcode=#{postcode}, addr_m=#{addr_m}, addr_s=#{addr_s} WHERE id = #{id}")
    void updateUser(String pw, String name, String postcode, String addr_m, String addr_s, String id);
}
