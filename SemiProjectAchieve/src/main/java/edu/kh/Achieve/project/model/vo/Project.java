package edu.kh.Achieve.project.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Project {
	
	private int projectNo;
	private String projectName;
	private int projectManager;
	private String projectQuota;
	private String openStatus;
	private String projectIntro;
	
}
