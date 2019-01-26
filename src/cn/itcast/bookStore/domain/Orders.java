package cn.itcast.bookStore.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Orders entity. @author MyEclipse Persistence Tools
 */

public class Orders implements java.io.Serializable {

	// Fields

	private String id;
	private User user;
	private Double money;
	private String receiverAddress;
	private String receiverName;
	private String receiverPhone;
	private Integer paystate;
	private Timestamp ordertime;
	private Set orderitems = new HashSet(0);

	// Constructors

	/** default constructor */
	public Orders() {
	}

	/** full constructor */
	public Orders(User user, Double money, String receiverAddress, String receiverName, String receiverPhone,
			Integer paystate, Timestamp ordertime, Set orderitems) {
		this.user = user;
		this.money = money;
		this.receiverAddress = receiverAddress;
		this.receiverName = receiverName;
		this.receiverPhone = receiverPhone;
		this.paystate = paystate;
		this.ordertime = ordertime;
		this.orderitems = orderitems;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getReceiverAddress() {
		return this.receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverName() {
		return this.receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhone() {
		return this.receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public Integer getPaystate() {
		return this.paystate;
	}

	public void setPaystate(Integer paystate) {
		this.paystate = paystate;
	}

	public Timestamp getOrdertime() {
		return this.ordertime;
	}

	public void setOrdertime(Timestamp ordertime) {
		this.ordertime = ordertime;
	}

	public Set getOrderitems() {
		return this.orderitems;
	}

	public void setOrderitems(Set orderitems) {
		this.orderitems = orderitems;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", user=" + user + ", money=" + money + ", receiverAddress=" + receiverAddress
				+ ", receiverName=" + receiverName + ", receiverPhone=" + receiverPhone + ", paystate=" + paystate
				+ ", ordertime=" + ordertime + ", orderitems=" + orderitems + "]";
	}

}