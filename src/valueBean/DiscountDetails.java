package valueBean;

public class DiscountDetails {
	private int discountId;
	private String discountCode;
	private double discountValue;
	private String discountType;
	private int usageLimit;
	private int usageCount;
	
	// Constructor
	public DiscountDetails() {
		super();
	}
	
	// Getters and Setters
	public String getDiscountCode() {
		return discountCode;
	}
	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}
	public double getDiscountValue() {
		return discountValue;
	}
	public void setDiscountValue(double discountValue) {
		this.discountValue = discountValue;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	public int getUsageLimit() {
		return usageLimit;
	}
	public void setUsageLimit(int usageLimit) {
		this.usageLimit = usageLimit;
	}
	public int getUsageCount() {
		return usageCount;
	}
	public void setUsageCount(int usageCount) {
		this.usageCount = usageCount;
	}
	public int getDiscountId() {
		return discountId;
	}
	public void setDiscountId(int discountId) {
		this.discountId = discountId;
	}
	
	
	
}
