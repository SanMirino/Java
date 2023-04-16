import com.example.ToDoList.Models.User;
import com.example.ToDoList.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public void save(@RequestBody User user){
        userService.saveUser(user);
    }

    @GetMapping("")
    public List<User> getAll(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        }
        catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer id){
        try{
            User new_user = userService.getUserById(id);
            new_user.updateUser(user);
            userService.saveUser(new_user);
            return ResponseEntity.ok().build();
        }
        catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Integer id) {
        userService.deleteUserById(id);
    }
}