package entity;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class RegisterUserDto {

    private String userName;

    private String userPassword;

    private String[] authorities;

    private Boolean enable;

    private Boolean notLocked;

}
