package Controller;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Repositories.UserService;
import com.example.model.User;


@RestController
@RequestMapping("/api")
public class UserRestApiController {
public static Logger logger = LoggerFactory.getLogger(UserRestApiController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUser(){
		List<User> listUser= userService.findAll();
		if(listUser.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(listUser, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public User findUser(@PathVariable("id") long id) {
		@SuppressWarnings("deprecation")
		User user= userService.getOne(id);
		if(user == null) {
			ResponseEntity.notFound().build();
		}
		return user;
	}
	
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public User saveUser(@Valid @RequestBody User user) {
		return userService.save(user);
	}
	
	@RequestMapping(value = "/user/", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, 
	                                       @Valid @RequestBody User userForm) {
		@SuppressWarnings("deprecation")
		User user = userService.getOne(userId);
	    if(user == null) {
	        return ResponseEntity.notFound().build();
	    }
	    user.setName(userForm.getName());
	    user.setCondominumId(userForm.getCondominumId());
	    user.setPhone(userForm.getPhone());
	    user.setCccd(userForm.getCccd());
	    user.setService(userForm.getService());

	    User updatedUser = userService.save(user);
	    return ResponseEntity.ok(updatedUser);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long id) {
		@SuppressWarnings("deprecation")
		User user = userService.getOne(id);
	    if(user == null) {
	        return ResponseEntity.notFound().build();
	    }

	    userService.delete(user);
	    return ResponseEntity.ok().build();
	}
}
