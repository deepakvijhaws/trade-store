package com.db.trade.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "trades")
public class Trade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "version")
	private int version;
	
	@Column(name = "counterPartyid")
	private String counterPartyId;
	
	@Column(name = "bookid")
	private String bookId;
	
	@Column(name = "maturitydate")
	private LocalDate maturityDate;
	
	@Column(name = "createdate")
	private LocalDate createDate;
	
	@Column(name = "isexpired")
	private Boolean isExpired;

	public Trade() {}
	
	public Trade(int version, String counterPartyId, String bookId, LocalDate maturityDate, LocalDate createDate, boolean isExpired) {
		this.version = version;
		this.counterPartyId = counterPartyId;
		this.bookId = bookId;
		this.maturityDate = maturityDate;
		this.createDate = createDate;
		this.isExpired = isExpired;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCounterPartyId() {
		return counterPartyId;
	}

	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public LocalDate getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public boolean isExpired() {
		return isExpired;
	}

	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}

	@Override
	public String toString() {
		return "Trade [id=" + id + ", version=" + version + ", counterPartyId=" + counterPartyId + ", bookId=" + bookId
				+ ", maturityDate=" + maturityDate + ", createDate=" + createDate + ", isExpired=" + isExpired + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((counterPartyId == null) ? 0 : counterPartyId.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((isExpired == null) ? 0 : isExpired.hashCode());
		result = prime * result + ((maturityDate == null) ? 0 : maturityDate.hashCode());
		result = prime * result + version;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trade other = (Trade) obj;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (counterPartyId == null) {
			if (other.counterPartyId != null)
				return false;
		} else if (!counterPartyId.equals(other.counterPartyId))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (id != other.id)
			return false;
		if (isExpired == null) {
			if (other.isExpired != null)
				return false;
		} else if (!isExpired.equals(other.isExpired))
			return false;
		if (maturityDate == null) {
			if (other.maturityDate != null)
				return false;
		} else if (!maturityDate.equals(other.maturityDate))
			return false;
		if (version != other.version)
			return false;
		return true;
	}
	
	
	

	
}
