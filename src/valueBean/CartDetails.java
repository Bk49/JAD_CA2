package valueBean;

public class CartDetails {
	private int quantity;
	private int productId;
	
	public CartDetails(int productId, int quantity){
		super();
		this.productId = productId;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	
}
