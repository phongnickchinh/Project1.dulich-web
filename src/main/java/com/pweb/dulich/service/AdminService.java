package com.pweb.dulich.service;

import com.pweb.dulich.model.Admin;

public interface AdminService {
    public Admin getAdminLogin(String username, String password);
    
}
