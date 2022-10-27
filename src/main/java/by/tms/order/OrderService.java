package by.tms.order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private static Logger log = LogManager.getLogger(OrderService.class);

    private List<Order> orders = new ArrayList<>();

    public Order createOrder(){
        log.info("Method createOrder was called");
        Order order = new Order();
        orders.add(order);
        return order;
    }

    public void addItem(int orderId, Product product, int count){
        log.info("Method addItem was called with orderId={}, product={}, count={}", orderId, product, count);
        Item item = new Item();
        item.setCount(count);
        item.setProductId(product.getId());
        item.setPrice(product.getPrice());

        Order order = getOrder(orderId);
        if(order == null) {
            log.error("Order not found.");
            return;
        }
        order.getItems().add(item);
    }

    public void finishOrder(int orderId) {
        log.info("Method finishOrder was called with orderId={}", orderId);
       Order order = getOrder(orderId);
       if (order == null) {
           log.error("Order not found.");
         return;
       }
        order.setFinished(true);
        order.setDateTime(LocalDateTime.now());
    }

    public Order getOrder(int orderId) {
        log.info("Method getOrder was called with orderId={}", orderId);
        Order order = null;
        for (int i = 0; i < orders.size();i++) {
            if (orderId == orders.get(i).getId()) {
                order = orders.get(i);
            }
        }
        return order;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
