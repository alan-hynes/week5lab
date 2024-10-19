package ie.atu.week5.customerapp;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    @Digits(message = "Order code must be a 4-digit number", integer = 4, fraction = 0)
    private int orderCode;

    @NotBlank(message = "Order details cannot be empty")
    private String orderDetails;

    private String orderDate;

    private String customerId; // Reference to the associated customer
}
