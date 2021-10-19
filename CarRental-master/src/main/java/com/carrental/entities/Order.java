package com.carrental.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstructorResult;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.carrental.models.OrderInfo;

@Entity
@SqlResultSetMapping(name = "OrderInfoMapping", classes = @ConstructorResult(targetClass = OrderInfo.class, columns = {
		@ColumnResult(name = "id", type = Long.class), @ColumnResult(name = "userId", type = Long.class),
		@ColumnResult(name = "bill", type = Long.class) }))

@Table(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 2159650216587665394L;

	public static final String ATTRIBUTE_ID = "id";
	public static final String ATTRIBUTE_MODEL = "model";
	public static final String ATTRIBUTE_USER_ID = "userId";
	public static final String ATTRIBUTE_BILL = "bill";
	public static final String ATTRIBUTE_STATUS = "status";
	public static final String ATTRIBUTE_START_DATE = "start_date";
	public static final String ATTRIBUTE_END_DATE = "end_date";

//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
//	@SequenceGenerator(name = "my_seq", allocationSize = 1)
//	//@SequenceGenerator(name = "order_seq", sequenceName = "orders_id_seq", allocationSize =10)
//	@Column(name = "id", nullable = false)	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "userId")
	private Long userId;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private Set<OrderItem> orderItems;

	@Column(name = "bill")
	private int bill;

	@Column(name = "status")
	private String status;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "start_date")
	private Date start_date;

	@Column(name = "end_date")
	private Date end_date;

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public Long getUserId() {
		return userId;
	}

	
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	
	public void addOrderItem(OrderItem orderItem) {
		if (orderItems == null) {
			orderItems = new HashSet<>();
		}
		this.orderItems.add(orderItem);

	}

	
	public int getBill() {
		return bill;
	}

	
	public void setBill(int bill) {
		this.bill = bill;
	}

	
	public String getStatus() {
		return status;
	}

	
	public void setStatus(String status) {
		this.status = status;
	}	

	
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}	

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public static String getAttributeId() {
		return ATTRIBUTE_ID;
	}


	public static String getAttributeModel() {
		return ATTRIBUTE_MODEL;
	}


	public static String getAttributeUserId() {
		return ATTRIBUTE_USER_ID;
	}


	public static String getAttributeBill() {
		return ATTRIBUTE_BILL;
	}


	public static String getAttributeStatus() {
		return ATTRIBUTE_STATUS;
	}


	public static String getAttributeStartDate() {
		return ATTRIBUTE_START_DATE;
	}


	public static String getAttributeEndDate() {
		return ATTRIBUTE_END_DATE;
	}


	public Date getStart_date() {
		return start_date;
	}


	public Date getEnd_date() {
		return end_date;
	}


	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	
	@Override
	public String toString() {
		return "Order [id=" + id + ", model=" + model + ", userId=" + userId + ", orderItems=" + orderItems + ", bill=" + bill + ", status="
				+ status + ",start_date=" + start_date + ", finish_date=" + end_date + "]";
	}

	

}
