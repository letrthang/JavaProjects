package restful.services;

/**
 * @author Thang Le. 
 * www.letrungthang.blogspot.com
 * 
 * */

import org.springframework.stereotype.Service;

@Service("userService")
public class UserControllerImpl implements IUserController {

	@Override
	public User createUser(User usr) {
		if (usr == null)
			return null;
		User user = new User();
		user.setId(usr.getId());
		user.setName(usr.getName());

		System.out.println("server: create user. name: " + user.getName() + ". id: " + user.getId());
		return user;
	}

	@Override
	public User readUser(String id) {
		User user = new User();
		try {
			if (id == null)
				return null;

			user.setId(id);
			user.setName("Thang Le");
			System.out.println("server: read user. id: " + id + ". name = " + user.getName());
		} catch (Exception e) {
			System.out.println("wrong path.........");
			return user;
		}
		return user;
	}

}
