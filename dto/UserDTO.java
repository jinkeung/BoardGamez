package zz_Project_Gamez.dto;



public class UserDTO {
	
	private String role,id,pw,name,postcode,addr_m,addr_s;
	
	UserDTO(String role, String id,String pw,String name,
			String postcode,String addr_m,String addr_s){
		this.role=role;
		this.id=id; this.pw=pw; this.name=name; 
		this.postcode=postcode; this.addr_m=addr_m; this.addr_s=addr_s;
	}
	
	public String getRole() {return role;}
	public String getId() {return id;}
	public String getPw() {return pw;}
	public String getName() {return name;}
	public String getPostcode() {return postcode;}
	public String getAddr_m() {return addr_m;}
	public String getAddr_s() {return addr_s;}
	
}

