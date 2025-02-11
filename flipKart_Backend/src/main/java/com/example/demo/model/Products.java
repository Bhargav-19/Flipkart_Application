package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Products {

	
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String productName;
    private String description;
    private double price;
    private String category;

    @Lob
    private byte[] image;
		
		
		public void Product() {
	    }

	    public void Product(Long Id, String productName, String description, double price, String category, byte[] image) {
	        this.Id = Id;
	        this.productName = productName;
	        this.description = description;
	        this.price = price;
	        this.category = category;
	        this.image = image;
	    }

	

		public Long getId() {
			return Id;
		}

		public void setId(Long Id) {
			this.Id = Id;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public byte[] getImage() {
	        return image;
	    }

	    public void setImage(byte[] image) {
	        this.image = image;
	    }

		@Override
		public String toString() {
			return "Register [Id=" + Id + ", productName=" + productName + ", description=" + description + ", price="
					+ price + ", category=" + category + ", image=" + image + "]";
		}
		
	    
	    
		
	}
		
		
