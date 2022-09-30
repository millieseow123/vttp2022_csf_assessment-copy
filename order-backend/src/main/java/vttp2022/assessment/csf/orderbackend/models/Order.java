package vttp2022.assessment.csf.orderbackend.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


// IMPORTANT: You can add to this class, but you cannot delete its original content

public class Order {

	private Integer orderId;
	private String name;
	private String email;
	private Integer size;
	private String sauce;
	private Boolean thickCrust;
	private List<String> toppings = new LinkedList<>();
	private String comments;

	public void setOrderId(Integer orderId) { this.orderId = orderId; }
	public Integer getOrderId() { return this.orderId; }

	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }

	public void setEmail(String email) { this.email = email; }
	public String getEmail() { return this.email; }

	public void setSize(Integer size) { this.size = size; }
	public Integer getSize() { return this.size; }

	public void setSauce(String sauce) { this.sauce = sauce; }
	public String getSauce() { return this.sauce; }

	public void setThickCrust(Boolean thickCrust) { this.thickCrust = thickCrust; }
	public Boolean isThickCrust() { return this.thickCrust; }

	public void setToppings(List<String> toppings) { this.toppings = toppings; }
	public List<String> getToppings() { return this.toppings; }
	public void addTopping(String topping) { this.toppings.add(topping); }

	public void setComments(String comments) { this.comments = comments; }
	public String getComments() { return this.comments; }

	public static Order create(ResultSet rs) throws SQLException {

		Order order = new Order();
		order.setOrderId(rs.getInt("order_id"));
		order.setName(rs.getString("name"));
		order.setEmail(rs.getString("email"));
		order.setSize(rs.getInt("pizza_size"));
		order.setSauce(rs.getString("sauce"));
		order.setThickCrust(rs.getBoolean("thick_crust"));

		List<String> l = new LinkedList<>();
		l.add(rs.getString("toppings"));
		order.setToppings(l);

		order.setComments(rs.getString("comments"));

		return order;




	}
}