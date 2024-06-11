package zz_Project_Gamez.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class FreeDTO {
	
	private String id, title, text;
	private Timestamp date_now;
	private int s_num;
}

