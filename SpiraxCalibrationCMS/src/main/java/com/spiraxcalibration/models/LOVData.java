package com.spiraxcalibration.models;

public class LOVData {
	private Integer lId;
	private String  lovProcess;
	private String lovName;
	private String description;
	private String lovComment;
	private String lovValue;
	private Integer lovLovId;
	
	/**
	 * @return the lovLovId
	 */
	public Integer getLovLovId() {
		return lovLovId;
	}
	/**
	 * @param lovLovId the lovLovId to set
	 */
	public void setLovLovId(Integer lovLovId) {
		this.lovLovId = lovLovId;
	}
	/**
	 * @return the lId
	 */
	public Integer getlId() {
		return lId;
	}
	/**
	 * @param lId the lId to set
	 */
	public void setlId(Integer lId) {
		this.lId = lId;
	}
	/**
	 * @return the lovProcess
	 */
	public String getLovProcess() {
		return lovProcess;
	}
	/**
	 * @param lovProcess the lovProcess to set
	 */
	public void setLovProcess(String lovProcess) {
		this.lovProcess = lovProcess;
	}
	/**
	 * @return the lovName
	 */
	public String getLovName() {
		return lovName;
	}
	/**
	 * @param lovName the lovName to set
	 */
	public void setLovName(String lovName) {
		this.lovName = lovName;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the lovComment
	 */
	public String getLovComment() {
		return lovComment;
	}
	/**
	 * @param lovComment the lovComment to set
	 */
	public void setLovComment(String lovComment) {
		this.lovComment = lovComment;
	}
	/**
	 * @return the lovValue
	 */
	public String getLovValue() {
		return lovValue;
	}
	/**
	 * @param lovValue the lovValue to set
	 */
	public void setLovValue(String lovValue) {
		this.lovValue = lovValue;
	}
	
	/*
	 * Lid
LOVProcess
LOVNAME
LOVVALUE
LOVDESCRIPTION
LOVCOMMENT
	 * */
}
