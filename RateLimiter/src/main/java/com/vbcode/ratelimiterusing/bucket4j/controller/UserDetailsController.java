package com.vbcode.ratelimiterusing.bucket4j.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserDetailsController {

        public UserDetails UserDetailsController(Integer id){
                List<UserDetails> userDetails= new ArrayList<>();
                userDetails.add(new UserDetails(1,"Shubham","Shinde"));
                userDetails.add(new UserDetails(2,"Mangesh","Chaudhari"));
                userDetails.add(new UserDetails(4,"Sameer","Maheshwari"));
                for(UserDetails user: userDetails){
                    if(user.getID()==id){
                        return user;
                    }
                }
                return  null;
        }


        @GetMapping("/user/{id}")
        public ResponseEntity<UserDetails> getUserDetails(HttpServletRequest request, @PathVariable(name = "id") Integer id ){
            String ipAddress = request.getRemoteAddr();

            if (RateLimiterManager.allowRequest(ipAddress)) {
                UserDetails user=UserDetailsController(id);
                return new ResponseEntity<>(user, HttpStatus.OK);
            }else {
                // Request rejected, return an error response or block the IP address
                // ...
                return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(null);
            }
        }



}
