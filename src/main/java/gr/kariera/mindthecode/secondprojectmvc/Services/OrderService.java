package gr.kariera.mindthecode.secondprojectmvc.Services;

import gr.kariera.mindthecode.secondprojectmvc.DTO.NewOrderDto;
import gr.kariera.mindthecode.secondprojectmvc.Entities.Order;
import org.springframework.data.domain.Page;


public interface OrderService {
    Order getOrderById(Integer id);

    Order createNewOrder(NewOrderDto newOrder);

    Page<Order> getAllOrders(String address, int page, int size, String sort);

    void updateOrder(Integer id, Order order);

    void deleteOrder(Integer id);
}
