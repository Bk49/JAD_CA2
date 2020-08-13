package valueBean;

public class PaymentDetails {
	private String ccType;
	private String ccName;
	private String ccExpDate;
	private String ccNum;
	private int ccCvv;
	private int customerId;
	
	public PaymentDetails() {
		super();
	}

	public String getCcType() {
		return ccType;
	}

	public void setCcType(String ccType) {
		this.ccType = ccType;
	}

	public String getCcName() {
		return ccName;
	}

	public void setCcName(String ccName) {
		this.ccName = ccName;
	}

	public String getCcExpDate() {
		return ccExpDate;
	}

	public void setCcExpDate(String ccExpDate) {
		this.ccExpDate = ccExpDate;
	}

	public String getCcNum() {
		return ccNum;
	}

	public void setCcNum(String ccNum) {
		this.ccNum = ccNum;
	}

	public int getCcCvv() {
		return ccCvv;
	}

	public void setCcCvv(int ccCvv) {
		this.ccCvv = ccCvv;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	
}
