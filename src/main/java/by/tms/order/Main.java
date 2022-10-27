package by.tms.order;

public class Main {

    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        Product computer = new Product(1,"HP X552M",500);
        Product smartphone = new Product(2,"iphone 14 pro",1000);

        Order newOrder = orderService.createOrder();

        orderService.addItem(newOrder.getId(),computer,2);
        orderService.addItem(newOrder.getId(),smartphone,1);

        orderService.finishOrder(newOrder.getId());

        Order order = orderService.getOrder(newOrder.getId());
        System.out.println("Order: " + order);

    }
}
