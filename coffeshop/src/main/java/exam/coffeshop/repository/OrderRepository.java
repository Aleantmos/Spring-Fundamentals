package exam.coffeshop.repository;

import exam.coffeshop.model.dtos.EmployeeWithOrdersDTO;
import exam.coffeshop.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order as o order by o.price desc")
    List<Order> getAllOrderByPriceDesc();


    List<Order> getOrderByEmployee_Username(String username);
}
