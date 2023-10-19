package cz.cvut.fit.sp.chipin.authentication.user;

import cz.cvut.fit.sp.chipin.base.member.MemberDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(userService.getUser(id));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e
            );
        }
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}/memberships")
    List<MemberDTO> getMemberships(@PathVariable Long id) throws Exception {
        return userService.getMemberships(id);
    }

}