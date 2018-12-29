package org.labsse.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.labsse.demo.dto.User;
import org.labsse.demo.dto.UserQueryCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lijiechu
 * @create on 2018/12/21
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation("用户查询服务")
    public List<User> query(UserQueryCondition condition, @PageableDefault(page = 1, size = 15, sort = "username,asc") Pageable pageable) {
        List<User> users = new ArrayList<>();
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @GetMapping(value = "/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@ApiParam("用户id") @PathVariable("id") String id) {
        System.out.println("进入getInfo服务");
        User user = new User();
        user.setUsername("tom");
        return user;
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {

//        if(errors.hasErrors()) {
//            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
//        }



        user.setId("1");
        System.out.println(user.getBirthday());
        return user;
    }

    @PutMapping(value = "/{id:\\d+}")
    public User updateUser(@Valid @RequestBody User user, BindingResult errors) {

        if(errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> {
                FieldError fieldError = (FieldError)error;
                String errorMessage = fieldError.getField() + " " +fieldError.getDefaultMessage();
                System.out.println(errorMessage);
            });
        }

        user.setId("1");
        return user;
    }


    @DeleteMapping(value = "/{id:\\d+}")
    public void deleteUser(@PathVariable String id) {
        System.out.println(id);
    }

}
