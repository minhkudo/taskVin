/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.vin.controller;

import com.example.vin.dao.UserDao;
import com.example.vin.model.User;
import java.util.ArrayList;
import static org.apache.tomcat.jni.User.username;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author MinhKudo
 */
@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/user", //
            method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ArrayList<User> getListUser(@RequestParam(value = "user", required = false) String username,
            @RequestParam(value = "email", required = false) String email) {
        ArrayList<User> list = userDao.listUser(username, email);
        return list;
    }

    @RequestMapping(value = "/user/{id}", //
            method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public User getUser(@PathVariable int id) {
        return userDao.dataUser(id);
    }

    @RequestMapping(value = "/user", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public User addUser(@RequestBody User user) {

        System.out.println("(Service Side) Creating user: " + user.getId());

        return userDao.add(user);
    }

    @RequestMapping(value = "/user", //
            method = RequestMethod.PUT, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public User updateUser(@RequestBody User emp) {

        System.out.println("(Service Side) Editing user: " + emp.getId());

        return userDao.edit(emp);
    }

    @RequestMapping(value = "/user", //
            method = RequestMethod.DELETE, //
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public User deleteUser(@RequestParam(value = "id", required = false) int id) {

        System.out.println("(Service Side) Deleting user: " + id);

        return userDao.delete(id);
    }
}
