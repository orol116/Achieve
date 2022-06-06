package edu.kh.Achieve.member.model.vo;


public class Member {
	
	private int memberNo;
	private String memberEmail;
	private String memberPw;
	private String memberName;
	private String memberNickname;
	private String memberBirthday;
	private String memberTel;
	private String secessionFlag;
	private String suspensionFlag;
	private String profileImage;
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public String getMemberBirthday() {
		return memberBirthday;
	}
	public void setMemberBirthday(String memberBirthday) {
		this.memberBirthday = memberBirthday;
	}
	public String getMemberTel() {
		return memberTel;
	}
	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}
	public String getSecessionFlag() {
		return secessionFlag;
	}
	public void setSecessionFlag(String secessionFlag) {
		this.secessionFlag = secessionFlag;
	}
	public String getSuspensionFlag() {
		return suspensionFlag;
	}
	public void setSuspensionFlag(String suspensionFlag) {
		this.suspensionFlag = suspensionFlag;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberEmail=" + memberEmail + ", memberPw=" + memberPw
				+ ", memberName=" + memberName + ", memberNickname=" + memberNickname + ", memberBirthday="
				+ memberBirthday + ", memberTel=" + memberTel + ", secessionFlag=" + secessionFlag + ", suspensionFlag="
				+ suspensionFlag + ", profileImage=" + profileImage + "]";
	}
	

}

