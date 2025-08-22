package com.muneesh.Controller;

import com.muneesh.Entity.User;
import com.muneesh.Services.CustomUserDetails;
import com.muneesh.enu.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Admin")
public class UserController {
    @Autowired
    private CustomUserDetails userservice;


     public ResponseEntity<List<User>>getAllUsers(){
         return ResponseEntity.ok(userservice.getAllUsers());

     }
     @GetMapping
     @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userservice.getUserById(id));
    }
    @PutMapping
    @PreAuthorize("hasRole('Admin')")
      public ResponseEntity<User> updateUserRole(@PathVariable Long id, @RequestParam Role role){
         User update =userservice.updateUserRole(id, role);
         return ResponseEntity.ok(update);
      }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userservice.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
