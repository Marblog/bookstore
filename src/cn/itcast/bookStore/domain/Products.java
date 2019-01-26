package cn.itcast.bookStore.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Products entity. @author MyEclipse Persistence Tools
 */

public class Products implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private Double price;
	private String category;
	private Integer pnum;
	private String imgurl;
	private String description;
	private Set orderitems = new HashSet(0);

	// Constructors

	/** default constructor */
	public Products() {
	}

	/** full constructor */
	public Products(String name, Double price, String category, Integer pnum, String imgurl, String description,
			Set orderitems) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.pnum = pnum;
		this.imgurl = imgurl;
		this.description = description;
		this.orderitems = orderitems;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getPnum() {
		return this.pnum;
	}

	public void setPnum(Integer pnum) {
		this.pnum = pnum;
	}

	public String getImgurl() {
		return this.imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getOrderitems() {
		return this.orderitems;
	}

	public void setOrderitems(Set orderitems) {
		this.orderitems = orderitems;
	}

}