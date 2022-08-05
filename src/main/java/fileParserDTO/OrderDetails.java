package fileParserDTO;

import java.io.Serializable;
import java.math.BigDecimal;


public class OrderDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private BigDecimal amount;
	private String currency;
	private String comment;
	private String fileName;
	private String line;
	private String result;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "{orderId=" + orderId + ", amount=" + amount + ", currency=" + currency + ", comment="
				+ comment + ", fileName=" + fileName + ", line=" + line + ", result=" + result + "}";
	}
	
}
