package exam.coffeshop.model.helper;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Component
@SessionScope
public class LoggedUser {

    private Long id;

    private String username;

    public boolean isLogged() {
        return id != null && username != null;
    }
}
