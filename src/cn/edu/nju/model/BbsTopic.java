package cn.edu.nju.model;

import java.sql.Timestamp;

public class BbsTopic {
	// Ù–‘
	private Integer topicId;
	private String topicTitle;
	private Timestamp topicCreateTime;
	private BbsUser bbsUser;
	private BbsChannel bbsChannel;
	private String topicComment;
	private Boolean topicTop;
	private Boolean topicEssence;
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public String getTopicTitle() {
		return topicTitle;
	}
	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}
	public Timestamp getTopicCreateTime() {
		return topicCreateTime;
	}
	public void setTopicCreateTime(Timestamp topicCreateTime) {
		this.topicCreateTime = topicCreateTime;
	}
	public BbsUser getBbsUser() {
		return bbsUser;
	}
	public void setBbsUser(BbsUser bbsUser) {
		this.bbsUser = bbsUser;
	}
	public BbsChannel getBbsChannel() {
		return bbsChannel;
	}
	public void setBbsChannel(BbsChannel bbsChannel) {
		this.bbsChannel = bbsChannel;
	}
	public String getTopicComment() {
		return topicComment;
	}
	public void setTopicComment(String topicComment) {
		this.topicComment = topicComment;
	}
	public Boolean getTopicTop() {
		return topicTop;
	}
	public void setTopicTop(Boolean topicTop) {
		this.topicTop = topicTop;
	}
	public Boolean getTopicEssence() {
		return topicEssence;
	}
	public void setTopicEssence(Boolean topicEssence) {
		this.topicEssence = topicEssence;
	}
	
	
	
}
