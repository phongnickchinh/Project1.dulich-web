package com.pweb.dulich.service.impl;

import com.pweb.dulich.model.Admin;
import com.pweb.dulich.repository.AdminRepository;
import com.pweb.dulich.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl  implements AdminService{

    @Autowired
    private AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin getAdminLogin(String username, String password) {
        return adminRepository.findByUsernameAndPassword(username, password);
    }
    
    
}
