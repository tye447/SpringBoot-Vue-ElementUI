package com.example.service;

import com.example.auth.Role;
import com.example.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.util.Util.getNullPropertyNames;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public List<Role> listRole(){
        return roleRepository.findAll();
    }
    public Role getRole(String id){
        Role authRole = roleRepository.findById(id).orElse(null);
        return authRole;
    }
    public Role addRole(Role role){
        return roleRepository.save(role);
    }
    public void deleteRole(String id){
        roleRepository.deleteById(id);
    }
    public Role updateRole(String id, Role role){
        Role currentInstance=roleRepository.findById(id).orElse(null);
        String[] nullPropertyNames = getNullPropertyNames(role);
        BeanUtils.copyProperties(role, currentInstance, nullPropertyNames);
        return roleRepository.save(currentInstance);
    }
    public Role findByName(String name){
        return roleRepository.findByName(name);
    }
}
