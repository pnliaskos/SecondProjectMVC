package gr.kariera.mindthecode.secondprojectmvc.Repositories;

import gr.kariera.mindthecode.secondprojectmvc.Entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Page<Order> findByAddressContainingIgnoreCase(String address, Pageable pageable);
}
