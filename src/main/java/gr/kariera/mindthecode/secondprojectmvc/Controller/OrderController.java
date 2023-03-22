package gr.kariera.mindthecode.secondprojectmvc.Controller;
import org.springframework.ui.Model;

import gr.kariera.mindthecode.secondprojectmvc.Services.OrderService;
import gr.kariera.mindthecode.secondprojectmvc.DTO.NewOrderDto;
import gr.kariera.mindthecode.secondprojectmvc.Entities.Order;

import org.springframework.data.domain.Page;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;


import java.util.List;
@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public String getOrderById(@PathVariable Integer id, Model model) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "order-details";
    }

    @GetMapping("/new")
    public String showNewOrderForm(Model model) {
        NewOrderDto newOrder = new NewOrderDto();
        model.addAttribute("newOrder", newOrder);
        return "orders";
    }

    @PostMapping("/new")
    public String createNewOrder(@ModelAttribute("newOrder") NewOrderDto newOrder) {
        orderService.createNewOrder(newOrder);
        return "orders";
    }

    @GetMapping("/index")
    public String getAllOrders(
            @RequestParam(required = false) String address,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "ASC", required = false) String sort,
            Model model) {

        Page<Order> orders = orderService.getAllOrders(address, page, size, sort);
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/{id}/edit")
    public String showEditOrderForm(@PathVariable Integer id, Model model) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "orders";
    }

    @PostMapping("/{id}/edit")
    public String updateOrder(@PathVariable Integer id, @ModelAttribute("order") Order order) {
        orderService.updateOrder(id, order);
        return "redirect:/orders";
    }

    @PostMapping("/{id}/delete")
    public String deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }
}
