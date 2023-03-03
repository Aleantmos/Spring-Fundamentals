package exam.coffeshop.model.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeWithOrdersDTO {

    private String username;

    private int countOfOrders;
}
