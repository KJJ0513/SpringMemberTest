package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
public class MemberVO {
	
	private  String userid;
	private String userpw;
	private String username;
	private String useremail;
	private Timestamp regdate;
	private Timestamp updatedate;
	


}
