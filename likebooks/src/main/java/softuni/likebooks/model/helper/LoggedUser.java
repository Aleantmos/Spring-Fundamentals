package softuni.likebooks.model.helper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
@Getter
@Setter
@NoArgsConstructor

@Component
@SessionScope
public class LoggedUser {

    private Long id;

    private String username;

    public boolean isLogged() {
        return id != null && username != null;
    }
}
