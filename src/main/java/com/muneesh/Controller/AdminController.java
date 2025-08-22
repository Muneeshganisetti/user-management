package com.muneesh.Controller;

import com.muneesh.DAO.UserRepository;
import com.muneesh.Entity.User;
import com.muneesh.Services.AdminService;
import java.util.List;
import com.muneesh.enu.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @Autowired
    private AdminService service;
    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("admin")
    public ResponseEntity<?> getAllUser() {
        List<User> users = service.getAllUser();
        return ResponseEntity.ok(Map.of(
                "count", users.size(),
                "users", users
        ));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
             @RequestParam Role role){
        service.updateUser(id,role);
        return ResponseEntity.ok("UserRole is Updated Successfully");

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteUser(Long id){
        service.deleteUser(id);
        return ResponseEntity.ok("UserId Deleted Successfully");
    }
}
