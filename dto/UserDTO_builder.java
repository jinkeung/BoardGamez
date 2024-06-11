package zz_Project_Gamez.dto;


public class UserDTO_builder {
	
	private String id,pw,name,postcode,addr_m,addr_s;
	
	private String role="user";
	
	public UserDTO_builder setId(String id) {this.id=id;return this;}
	public UserDTO_builder setPw(String pw) {this.pw=pw;return this;}	
	public UserDTO_builder setName(String name) {this.name=name;return this;}
	public UserDTO_builder setPostcode(String postcode) {this.postcode=postcode;return this;}
	public UserDTO_builder setAddr_m(String addr_m) {this.addr_m=addr_m;return this;}
	public UserDTO_builder setAddr_s(String addr_s) {this.addr_s=addr_s;return this;}
	public UserDTO build() {
		return new UserDTO(role,id, pw, name, postcode, addr_m, addr_s);
	}
}


