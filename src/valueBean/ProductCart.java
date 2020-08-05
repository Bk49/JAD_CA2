package valueBean;

import java.util.ArrayList;

public class ProductCart {
	private ArrayList<ProductDetails> products = new ArrayList<ProductDetails>();
	private ArrayList<Integer> quantity = new ArrayList<Integer>();
	
	public ProductCart() {
		super();
	}

	// Getters & setters
	public ArrayList<ProductDetails> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<ProductDetails> products) {
		this.products = products;
	}

	public ArrayList<Integer> getQuantity() {
		return quantity;
	}

	public void setQuantity(ArrayList<Integer> quantity) {
		this.quantity = quantity;
	}
	
	// Self-made function
	public void pushProduct(ProductDetails product, int quantity) {
		if(product == null) {
			System.out.println("The product seems to be null!");
		}
		System.out.println("From valueBean.ProductCart, name: "+product.getProductName());
		
		this.products.add(product);
		this.quantity.add(quantity);
	}
	
	
}
