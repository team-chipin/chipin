package cz.cvut.fit.sp.chipin.authentication.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginResponse {
    private Long id;
    private String name;
    private String email;
    private boolean enabled;
}