package utilityBean;

import java.sql.*;
import java.util.ArrayList;

import valueBean.ProductDetails;

public class ProductDetailsDB {
	
	// Get Product Details
	public ProductDetails getProductDetails(int productId) {
		ProductDetails product = new ProductDetails();
		try {
	           Class.forName("com.mysql.jdbc.Driver");
	          String connURL = "jdbc:mysql://localhost/jad?user=root&password=Devious1211&serverTimezone=UTC";
	          // String connURL = "jdbc:mysql://localhost:3306/jad?user=root&password=khyelerk12KL&serverTimezone=UTC";

	          Connection conn = DriverManager.getConnection(connURL); 
	          String sqlStr = "SELECT * FROM product WHERE productId = ?";

	    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
	    		pstmt.setInt(1, productId);
	    		ResultSet rs = pstmt.executeQuery();	          
	          if (rs.next()) {
	        	  System.out.print(rs.getString("productId")+" this is from ProductDetailsDB");
	              product.setProductName(rs.getString("productName"));
	              product.setProductId(rs.getInt("productId"));
	              product.setBriefDescription(rs.getString("briefDescription"));
	              product.setDetailDescription(rs.getString("detailDescription"));
	              product.setCostPrice(rs.getDouble("costPrice"));
	              product.setRetailPrice(rs.getDouble("retailPrice"));
	              product.setStockQuantity(rs.getInt("stockQuantity"));
	              product.setProductCategory(rs.getString("productCategory"));
	              product.setImageLocation(rs.getString("imageLocation"));
	          }	        
	          conn.close();
	     } catch (Exception e) {
	        System.err.println("Error :" + e);
	     }
		return product;
	}
	
	// Insert new Product using ProductDetails.java
	public int insertProduct(ProductDetails product) {
		int count = 0;
		try {
	           Class.forName("com.mysql.jdbc.Driver");
	           String connURL = "jdbc:mysql://localhost/jad?user=root&password=Devious1211&serverTimezone=UTC";
	         Connection conn = DriverManager.getConnection(connURL); 
	         
	         String insertStr = "INSERT INTO product(productName, briefDescription, detailDescription, costPrice, retailPrice, stockQuantity, productCategory, imageLocation) VALUES (?,?,?,?,?,?,?,?) ";
		 	PreparedStatement pstmt = conn.prepareStatement(insertStr);
	         pstmt.setString(1, product.getProductName());
	         pstmt.setString(2, product.getBriefDescription());
	         pstmt.setString(3, product.getDetailDescription());
	         pstmt.setDouble(4, product.getCostPrice());
	         pstmt.setDouble(5, product.getRetailPrice());
	         pstmt.setInt(6, product.getStockQuantity());
	         pstmt.setString(7, product.getProductCategory());
	         pstmt.setString(8, product.getImageLocation());

	         count = pstmt.executeUpdate();
	         conn.close(); 

	    } catch (Exception e) {
	       System.out.print(e);
	    }
		return count;
	}
	
	// Delete Product using productId
	public int deleteProduct(int productId) {
		int count = 0;
		try {
	         String connURL = "jdbc:mysql://localhost/jad?user=root&password=Devious1211&serverTimezone=UTC";
	         Connection conn = DriverManager.getConnection(connURL); 
	         
	         String deleteStr = "DELETE FROM product WHERE productId = ?";
	         PreparedStatement pstmt = conn.prepareStatement(deleteStr);
	         pstmt.setInt(1, productId);
	         count = pstmt.executeUpdate();
	         conn.close(); 

	    } catch (Exception e) {
	       System.out.print("error");
	    }
		return count;
	}
	
	// Update Product using ProductDetails.java
	public int updateProduct(ProductDetails product) {
		int count = 0;
		try {
	         String connURL = "jdbc:mysql://localhost/jad?user=root&password=Devious1211&serverTimezone=UTC";
	         Connection conn = DriverManager.getConnection(connURL); 
	         
	         String insertStr = "UPDATE product SET productName = ?, briefDescription = ?, detailDescription = ?, costPrice = ?, retailPrice = ?, stockQuantity = ?, productCategory = ?, imageLocation = ? WHERE productId = ?";
		 	PreparedStatement pstmt = conn.prepareStatement(insertStr);
	         pstmt.setString(1, product.getProductName());
	         pstmt.setString(2, product.getBriefDescription());
	         pstmt.setString(3, product.getDetailDescription());
	         pstmt.setDouble(4, product.getCostPrice());
	         pstmt.setDouble(5, product.getRetailPrice());
	         pstmt.setInt(6, product.getStockQuantity());
	         pstmt.setString(7, product.getProductCategory());
	         pstmt.setString(8, product.getImageLocation());
	         pstmt.setInt(9, product.getProductId());

	         count = pstmt.executeUpdate();
	         conn.close(); 

	    } catch (Exception e) {
	       System.out.print("error");
	    }
		return count;
	}
	
	// Select all available product categories
	public ArrayList<String> getProductCategories() {
		ArrayList<String> categories = new ArrayList<String>();
		try {
	           Class.forName("com.mysql.jdbc.Driver");
	          String connURL = "jdbc:mysql://localhost/jad?user=root&password=Devious1211&serverTimezone=UTC";
	          // String connURL = "jdbc:mysql://localhost:3306/jad?user=root&password=khyelerk12KL&serverTimezone=UTC";

	          Connection conn = DriverManager.getConnection(connURL); 
	          String sqlStr = "SELECT DISTINCT productCategory FROM product";

	    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
	    		ResultSet rs = pstmt.executeQuery();	          
	          while (rs.next()) {
	        	  categories.add(rs.getString("productCategory"));
	          }	        
	          conn.close();
	     } catch (Exception e) {
	        System.err.println("Error :" + e);
	     }
		System.out.println("Length in product DetailsDB "+categories.size());
		return categories;
	}
	
	// Select random products
	public ArrayList<ProductDetails> getRandomProducts() {
		ArrayList<ProductDetails> products = new ArrayList<ProductDetails>();
		ProductDetails product;
		try {
	           Class.forName("com.mysql.jdbc.Driver");
	          String connURL = "jdbc:mysql://localhost/jad?user=root&password=Devious1211&serverTimezone=UTC";
	          // String connURL = "jdbc:mysql://localhost:3306/jad?user=root&password=khyelerk12KL&serverTimezone=UTC";

	          Connection conn = DriverManager.getConnection(connURL); 
	          String sqlStr = "SELECT productId,productName, costPrice, imageLocation FROM jad.product ORDER BY RAND() LIMIT 10";

	    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
	    		ResultSet rs = pstmt.executeQuery();	          
	          while (rs.next()) {
	      		  product = new ProductDetails();
	      		  product.setProductName(rs.getString("productName"));
	              product.setProductId(rs.getInt("productId"));
	              product.setCostPrice(rs.getDouble("costPrice"));
	              product.setImageLocation(rs.getString("imageLocation"));
	              products.add(product);
	          }	        
	          conn.close();
	     } catch (Exception e) {
	        System.err.println("Error :" + e);
	     }
		return products;
	}
	
	// Get 10 products according to its page
			public ArrayList<ProductDetails> getProductsLimit(int pg) {
				ProductDetails product;
				ArrayList<ProductDetails> products = new ArrayList<ProductDetails>();
				
				int startRow = pg*10-10;
				try {
			           Class.forName("com.mysql.jdbc.Driver");
			          String connURL = "jdbc:mysql://localhost/jad?user=root&password=Devious1211&serverTimezone=UTC";
			          // String connURL = "jdbc:mysql://localhost:3306/jad?user=root&password=khyelerk12KL&serverTimezone=UTC";

			          Connection conn = DriverManager.getConnection(connURL); 
			          String sqlStr = "SELECT productId, productName, costPrice, retailPrice, stockQuantity, productCategory FROM product LIMIT ?,10";

			    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			    		pstmt.setInt(1, startRow);
			    		ResultSet rs = pstmt.executeQuery();	          
			          while (rs.next()) {
			        	  product = new ProductDetails();
			        	  product.setProductId(rs.getInt("productId"));
			        	  product.setProductName(rs.getString("productName"));
			        	  product.setCostPrice(rs.getDouble("costPrice"));
			        	  product.setRetailPrice(rs.getDouble("retailPrice"));
			        	  product.setStockQuantity(rs.getInt("stockQuantity"));
			        	  product.setProductCategory(rs.getString("productCategory"));
			              products.add(product);
			          }	        
			          conn.close();
			     } catch (Exception e) {
			        System.err.println("Error :" + e);
			     }
				System.out.println("Size of products in ProductDetailsDB "+products.size());
				return products;
			}
			
			// Get count of discounts
			public double getProductCount() {
				double count = 0;
				try {
			           Class.forName("com.mysql.jdbc.Driver");
			          String connURL = "jdbc:mysql://localhost/jad?user=root&password=Devious1211&serverTimezone=UTC";
			          // String connURL = "jdbc:mysql://localhost:3306/jad?user=root&password=khyelerk12KL&serverTimezone=UTC";

			          Connection conn = DriverManager.getConnection(connURL); 
			          String sqlStr = "SELECT COUNT(*) count FROM product";

			    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			    		ResultSet rs = pstmt.executeQuery();	          
			          if (rs.next()) {
			        	  count = (double)rs.getInt("count");
			          }	        
			          conn.close();
			     } catch (Exception e) {
			        System.err.println("Error :" + e);
			     }
				return count;
			}
			
			// Get products based on category
			public ArrayList<ProductDetails> getProductsOnCats(String categoryName){
				ArrayList<ProductDetails> products = new ArrayList<ProductDetails>();
				ProductDetails product;
				try {
			           Class.forName("com.mysql.jdbc.Driver");
			          String connURL = "jdbc:mysql://localhost/jad?user=root&password=Devious1211&serverTimezone=UTC";
			          // String connURL = "jdbc:mysql://localhost:3306/jad?user=root&password=khyelerk12KL&serverTimezone=UTC";

			          Connection conn = DriverManager.getConnection(connURL); 
			          String sqlStr = "SELECT productId, productName, briefDescription, costPrice, retailPrice, imageLocation FROM product WHERE productCategory LIKE ?";

			    		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			    		pstmt.setString(1, categoryName);
			    		ResultSet rs = pstmt.executeQuery();	          
			          while (rs.next()) {
			      		  product = new ProductDetails();
			      		  product.setProductName(rs.getString("productName"));
			              product.setProductId(rs.getInt("productId"));
			              product.setCostPrice(rs.getDouble("costPrice"));
			              product.setImageLocation(rs.getString("imageLocation"));
			              product.setBriefDescription(rs.getString("briefDescription"));
			              product.setRetailPrice(rs.getDouble("retailPrice"));
			              products.add(product);
			          }	        
			          conn.close();
			     } catch (Exception e) {
			        System.err.println("Error :" + e);
			     }
				System.out.println(products.size());
				return products;
			}
	
}
