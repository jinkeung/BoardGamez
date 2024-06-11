package zz_Project_Gamez.dto;

import lombok.Data;

@Data
public class ProductDTO {
	
	private String s_n,name,a_1,a_2,a_3,link;
	private int price, quantity;
	
	ProductDTO(String s_n, String name,int price, int quantity,
			String a_1,String a_2,String a_3,String link){
		this.s_n=s_n;
		this.name=name;
		this.a_1=a_1;
		this.a_2=a_2;
		this.a_3=a_3;
		this.link=link;
		this.price=price;
		this.quantity=quantity;
	}
}
