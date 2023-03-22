package gr.kariera.mindthecode.secondprojectmvc.Services;

import gr.kariera.mindthecode.secondprojectmvc.DTO.NewOrderDto;
import gr.kariera.mindthecode.secondprojectmvc.Entities.Order;
import gr.kariera.mindthecode.secondprojectmvc.Entities.OrderProduct;
import gr.kariera.mindthecode.secondprojectmvc.Entities.OrderProductPK;
import gr.kariera.mindthecode.secondprojectmvc.Entities.Product;
import gr.kariera.mindthecode.secondprojectmvc.Repositories.OrderRepository;
import gr.kariera.mindthecode.secondprojectmvc.Repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID: " + id));
    }

    @Override
    public Order createNewOrder(NewOrderDto newOrder) {
        Order order = new Order();
        order.setAddress(newOrder.getAddress());
        order.setDiscountPercentage(newOrder.getDiscountPercentage());
        order = orderRepository.save(order);

        final Order finalOrder = order;
        newOrder.getProducts().forEach(nop -> {
            Product product = productRepository.findById(nop.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id " + nop.getProductId()));
            OrderProduct orderProduct = new OrderProduct();
            OrderProductPK orderProductPK = new OrderProductPK();
            orderProductPK.setOrderId(finalOrder.getId());
            orderProductPK.setProductId(product.getId());
            orderProduct.setId(orderProductPK);
            orderProduct.setOrder(finalOrder);
            orderProduct.setProduct(product);
            orderProduct.setQuantity(nop.getQuantity());
            finalOrder.getOrderProducts().add(orderProduct);
            finalOrder.setOrderProducts(finalOrder.getOrderProducts());
        });

        Order result = orderRepository.save(finalOrder);
        return orderRepository.findById(result.getId())
                .orElseThrow(() -> new RuntimeException("Order not found with id " + result.getId()));

    }

    @Override
    public Page<Order> getAllOrders(String address, int page, int size, String sort) {
        return null;
    }

    @Override
    public void updateOrder(Integer id, Order order) {

    }

    @Override
    public void deleteOrder(Integer id) {

    }


}
