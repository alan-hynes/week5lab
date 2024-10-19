package ie.atu.week5.customerapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerOrderController {

    private final CustomerService customerService;
    private final OrderService orderService;

    public CustomerOrderController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @PostMapping("/customer-with-orders")
    public ResponseEntity<String> createCustomerWithOrders(@RequestBody CustomerOrderRequest customerOrderRequest) {
        Customer savedCustomer = customerService.createCustomer(customerOrderRequest.getCustomer());
        for (Order order : customerOrderRequest.getOrders()) {
            order.setCustomerId(savedCustomer.getId());
            orderService.createOrder(order);
        }
        return ResponseEntity.ok("Customer and orders created successfully");
    }
}
