package com.booking.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="refId")
public class ReferenceId {
	
	@Id
	private String referenceID;
	private boolean checkdin;

	public ReferenceId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getReferenceID() {
		return referenceID;
	}

	public void setReferenceID(String referenceID) {
		this.referenceID = referenceID;
	}

	public boolean isCheckdin() {
		return checkdin;
	}

	public void setCheckdin(boolean checkdin) {
		this.checkdin = checkdin;
	}

	public ReferenceId(String referenceID, boolean checkdin) {
		super();
		this.referenceID = referenceID;
		this.checkdin = checkdin;
	}

	@Override
	public int hashCode() {
		return Objects.hash(checkdin, referenceID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReferenceId other = (ReferenceId) obj;
		return checkdin == other.checkdin && Objects.equals(referenceID, other.referenceID);
	}

	@Override
	public String toString() {
		return "ReferenceId [referenceID=" + referenceID + ", checkdin=" + checkdin + "]";
	}

	
}
