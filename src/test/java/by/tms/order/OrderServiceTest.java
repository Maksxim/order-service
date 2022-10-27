package by.tms.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    public void setup() {
        orderService = new OrderService();
    }

    @Test
    public void createOrderTest() {
        Order order = orderService.createOrder();

        assertNotNull(order);
        assertEquals(1, order.getId());
    }

    @Test
    public void addItemTest() {
        Product computer = new Product(1,"HP X552M",500);
        Product smartphone = new Product(2,"iphone 14 pro",1000);
        Order newOrder = orderService.createOrder();

        orderService.addItem(newOrder.getId(),computer,2);
        orderService.addItem(newOrder.getId(),smartphone,1);

        assertNotNull(orderService.getOrders());
        assertEquals(1, orderService.getOrders().size());
        Order createdOrder = orderService.getOrder(newOrder.getId());
        assertNotNull(createdOrder.getItems());
        assertEquals(2, createdOrder.getItems().size());
        Item computerItem = createdOrder.getItems().get(0);
        Item smartphoneItem = createdOrder.getItems().get(1);

        assertEquals(1, computerItem.getProductId());
        assertEquals(500, computerItem.getPrice());
        assertEquals(2, computerItem.getCount());

        assertEquals(2, smartphoneItem.getProductId());
        assertEquals(1000, smartphoneItem.getPrice());
        assertEquals(1, smartphoneItem.getCount());
    }

    @Test
    public void finishOrderTest(){
        Order newOrder = orderService.createOrder();
        orderService.finishOrder(newOrder.getId());

        Order createdOrder = orderService.getOrder(newOrder.getId());

        assertTrue(createdOrder.isFinished());
        assertNotNull(createdOrder.getDateTime());
    }

    @Test
    public void getOrderTest(){
        Order newOrder = orderService.createOrder();

        Order order = orderService.getOrder(newOrder.getId());

        assertEquals(order.getId(), newOrder.getId());
    }
}
