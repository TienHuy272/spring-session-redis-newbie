package com.example.redisspringboot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RedisController {
    @PostMapping("/addNote")
    public ResponseEntity addNote(@RequestBody UserDto userDto, HttpServletRequest request) {
        //get the notes from request session
        List<UserDto> users = (List<UserDto>) request.getSession().getAttribute("USER_SESSION");
        //check if notes is present in session or not
        if (users == null) {
            users = new ArrayList<>();
            // if notes object is not present in session, set notes in the request session
            request.getSession().setAttribute("USER_SESSION", users);
        }
        users.add(userDto);
        request.getSession().setAttribute("USER_SESSION", users);
        return new ResponseEntity(userDto, HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity home(HttpSession session) {
        List<UserDto> notes = (List<UserDto>) session.getAttribute("USER_SESSION");
        return new ResponseEntity(notes, HttpStatus.OK);
    }
}
