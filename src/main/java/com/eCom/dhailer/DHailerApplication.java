package com.eCom.dhailer;


import com.eCom.dhailer.service.RoleService;
import com.eCom.dhailer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DHailerApplication implements CommandLineRunner {

	private final UserService userService;
	private final RoleService roleService;

	@Autowired
	public DHailerApplication(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    public static void main(String[] args) {
		SpringApplication.run(DHailerApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		roleService.initializeUserRoles();
		userService.initializeAdmin();

	}
}
