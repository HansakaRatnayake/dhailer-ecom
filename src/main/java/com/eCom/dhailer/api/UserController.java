package com.eCom.dhailer.api;

import com.eCom.dhailer.dto.request.RequestUserDto;
import com.eCom.dhailer.service.UserService;
import com.eCom.dhailer.util.StandardResponce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

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
