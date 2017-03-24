package cn.edu.nju.model;

import java.sql.Timestamp;

public class BbsReplies {
	private Integer repliesId;
	private String repliesTitle;
	private Timestamp repliesCreateTime;
	private BbsUser bbsUser;
	private BbsTopic bbsTopic;
	private String repliesComment;
	public Integer getRepliesId() {
		return repliesId;
	}
	public void setRepliesId(Integer repliesId) {
		this.repliesId = repliesId;
	}
	public String getRepliesTitle() {
		return repliesTitle;
	}
	public void setRepliesTitle(String repliesTitle) {
		this.repliesTitle = repliesTitle;
	}
	public Timestamp getRepliesCreateTime() {
		return repliesCreateTime;
	}
	public void setRepliesCreateTime(Timestamp repliesCreateTime) {
		this.repliesCreateTime = repliesCreateTime;
	}
	public BbsUser getBbsUser() {
		return bbsUser;
	}
	public void setBbsUser(BbsUser bbsUser) {
		this.bbsUser = bbsUser;
	}
	public BbsTopic getBbsTopic() {
		return bbsTopic;
	}
	public void setBbsTopic(BbsTopic bbsTopic) {
		this.bbsTopic = bbsTopic;
	}
	public String getRepliesComment() {
		return repliesComment;
	}
	public void setRepliesComment(String repliesComment) {
		this.repliesComment = repliesComment;
	}
	
	
}
