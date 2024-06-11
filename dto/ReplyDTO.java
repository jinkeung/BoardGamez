package zz_Project_Gamez.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReplyDTO {
	
	private String id, text, s_num;
	private Timestamp date_now;
	private int reply_num;
}

