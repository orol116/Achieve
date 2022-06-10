package edu.kh.Achieve.member.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CheckReply {
	
	private int replyNo;
	private String replyContent;
	private String createDate;
	private int memberNo;
	private String memberNickName;
	private String profileImage;
	

}
