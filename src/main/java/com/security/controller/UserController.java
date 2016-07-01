package com.security.controller;

import com.security.model.User;
import com.security.model.UserDao;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;


@RestController
@RequestMapping("/user")
@Configurable
public class UserController {

    @Resource
    private UserDao userDao;

    @RequestMapping(method = RequestMethod.POST)
    public void post(@RequestBody User user) {
        try {
            user.getAuthorities().forEach(p->p.setUser(user));
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userDao.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public User get(String name) {
        try {
            return userDao.findByUsername(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
