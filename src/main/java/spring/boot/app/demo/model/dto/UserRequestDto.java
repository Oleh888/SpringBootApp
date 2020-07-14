package spring.boot.app.demo.model.dto;

import lombok.Data;
import spring.boot.app.demo.model.User;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UserRequestDto {
    @NotNull(message = "The userName can not be null")
    private String name;
    @NotNull(message = "The password can not be null")
    @Min(4)
    private String password;
    @NotNull
    private User.Role role;
}
