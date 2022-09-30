package vttp2022.assessment.csf.orderbackend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.assessment.csf.orderbackend.models.OrderSummary;

@Repository
public class OrderRepository {

    public static final String SQL_INSERT_ORDER = 
        "insert into orders(order_id, name, email, pizza_size, thick_crust, toppings, sauce, comments) values (?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String SQL_GET_ORDERS =
        "select * from orders where email = ?";

    @Autowired
    private JdbcTemplate template;

    public boolean postOrder(Integer orderid, String name, String email,
            Integer size, Boolean thickCrust, String toppings, String sauce, String comments) {
        int count = template.update((SQL_INSERT_ORDER), orderid, name, email, size, thickCrust, toppings, sauce, comments);
        return 1 == count;
    }

    public Optional<OrderSummary> getOrdersByEmail(String email) {
        SqlRowSet rs = template.queryForRowSet(SQL_GET_ORDERS, email);
        if (rs.next())
            return Optional.of(OrderSummary.create(rs));
        return Optional.empty();
    }
}
