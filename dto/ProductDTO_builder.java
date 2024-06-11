package zz_Project_Gamez.dto;

import lombok.Data;


public class ProductDTO_builder {
	
	private String s_n,name,a_1,a_2,a_3,link;
	private int price, quantity;
	
	public ProductDTO_builder setS_n(String s_n) {this.s_n=s_n;return this;}
	public ProductDTO_builder setName(String name) {this.name=name;return this;}
	public ProductDTO_builder setPrice(int price) {this.price=price;return this;}
	public ProductDTO_builder setQuantity(int quantity) {this.quantity=quantity;return this;}
	public ProductDTO_builder setA_1(String a_1) {this.a_1=a_1;return this;}
	public ProductDTO_builder setA_2(String a_2) {this.a_2=a_2;return this;}
	public ProductDTO_builder setA_3(String a_3) {this.a_3=a_3;return this;}
	public ProductDTO_builder setLink(String link) {this.link=link;return this;}

	public ProductDTO build() {
	return new ProductDTO(s_n,name,price,quantity,a_1,a_2,a_3,link);
	}
}
