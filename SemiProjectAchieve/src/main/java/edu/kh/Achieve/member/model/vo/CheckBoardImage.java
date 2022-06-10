package edu.kh.Achieve.member.model.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CheckBoardImage {

	private int imageNo;
	private String imageReName;
	private String imageOriginal;
	private int imageLevel;
	private int boardNo;

}
