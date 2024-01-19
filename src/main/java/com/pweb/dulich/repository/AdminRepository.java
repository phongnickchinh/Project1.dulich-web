package com.pweb.dulich.repository;

import org.springframework.stereotype.Repository;
import com.pweb.dulich.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;





@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {

    public Admin findByUsernameAndPassword(String username, String password);
}
