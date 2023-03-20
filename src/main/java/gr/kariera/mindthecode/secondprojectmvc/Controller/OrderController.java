//package gr.kariera.mindthecode.secondprojectmvc.Controller;
//
//import gr.kariera.mindthecode.secondprojectmvc.Services.OrderService;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/orders")
//public class OrderController {
//
//    private final OrderService service;
//
//    public OrderController(OrderService service) {
//        this.service = service;
//    }
//
//    @GetMapping("/{id}")
//    public String getOrderById(@PathVariable Integer id, Model model) {
//        OrderViewModel orderViewModel = service.getOrderById(id);
//        model.addAttribute("order", orderViewModel);
//        return "orderDetails";
//    }
//
//    @GetMapping("")
//    public String getAllOrders(Model model) {
//        List<OrderViewModel> orderViewModels = service.getAllOrders();
//        model.addAttribute("orders", orderViewModels);
//        return "orderList";
//    }
//
//    // add more methods for create, update, and delete
//}
