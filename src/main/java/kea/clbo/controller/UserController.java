package kea.clbo.controller;

import kea.clbo.model.User;
import kea.clbo.model.UserInfo;
import kea.clbo.repositories.IUserInfoRepository;
import kea.clbo.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    IUserRepository userRepository;
    @Autowired
    IUserInfoRepository userInfoRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email, @RequestParam String address) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request


        User n = new User();
        UserInfo ui = new UserInfo();

        n.setName(name);
        n.setEmail(email);

        ui.setAddress(address);
        ui.setUser(n);

        userRepository.save(n);
        userInfoRepository.save(ui);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody List<UserInfo> getAllUsers() {
        // This returns a JSON or XML with the users

        // Iterable<UserInfo> users = userInfoRepository.findAll();

        List<UserInfo> userInfos = new ArrayList<>();

        for (UserInfo ui: userInfoRepository.findAll()) {
            userInfos.add(new UserInfo(ui.getAddress(), ui.getUser()));
        }

        return userInfos;



        //return userInfoRepository.findAll();

    }

    @DeleteMapping(path="/delete")
    public @ResponseBody String deleteUser (@RequestParam Integer id) {
/*
        Iterable<User> users = userRepository.findAll();
        for (User u : users) {
            if(u.getName().equals(name)){
                userRepository.deleteById(u.getId());
            }
        }*/

        userRepository.deleteById(id);
       return "Done!";
    }




}
