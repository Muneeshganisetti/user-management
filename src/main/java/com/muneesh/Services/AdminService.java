package com.muneesh.Services;


import com.muneesh.DAO.UserRepository;
import com.muneesh.Entity.User;
import java.util.List;
import com.muneesh.enu.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private UserRepository repository;

    public List<User> getAllUser(){
        return repository.findAll();
    }

    public void updateUser(long id, Role role){
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        user.setRole(role);
        repository.save(user);

    }
    public void deleteUser(long id){
        repository.deleteById(id);
    }
}
