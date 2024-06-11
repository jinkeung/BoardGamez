package zz_Project_Gamez.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import zz_Project_Gamez.dto.ProductDTO;


@Mapper
public interface ProductMapper {
	
	@Insert("INSERT INTO products VALUES (#{s_n},#{name},#{price},#{quantity},"
											+ "#{a_1},#{a_2},#{a_3},#{link})")
	void insertProduct(ProductDTO productDTO);
	
    @Select("select * from products order by s_n asc")
    List<ProductDTO> selectAllProducts();
	
    @Insert("INSERT INTO products VALUES (#{s_n},#{name}, #{price}, #{quantity},"
    											+ "#{a_1},#{a_2},#{a_3},#{link})")
    void addProduct(ProductDTO product);

    @Update("UPDATE products SET name = #{name}, price = #{price}, quantity = #{quantity} WHERE s_n = #{s_n}")
    void updateProduct(ProductDTO product);
    
    @Delete("delete from products where s_n=#{s_n} ")
    void deleteProduct(String s_n);
    
    @Select("SELECT * FROM products WHERE s_n LIKE 'kobo_%'")
    List<ProductDTO> getAllKobo();
    
    @Select("SELECT * FROM products WHERE s_n LIKE 'asmo_%'")
    List<ProductDTO> getAllAsmo();
    
    @Select("SELECT * FROM products WHERE s_n LIKE 'm_%'")
    List<ProductDTO> getAllM();
    
    @Select("SELECT * FROM products WHERE s_n=#{s_n}")
    List<ProductDTO> getS_nProduct(String s_n);
    
    @Select("select * from (select * from products order by dbms_random.value) where rownum<=3")
    List<ProductDTO> getRandom();
}
