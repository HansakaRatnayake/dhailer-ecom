package com.eCom.dhailer.api;

import com.eCom.dhailer.dto.request.RequestUserDto;
import com.eCom.dhailer.service.UserService;
import com.eCom.dhailer.util.StandardResponce;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/v1/users")
public class AdminUserController {

    @Autowired
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<StandardResponce> create(@RequestBody RequestUserDto dto) {
        userService.create(dto);
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(201, "User successfully created", null),
                HttpStatus.CREATED
        );
    }
    
}
