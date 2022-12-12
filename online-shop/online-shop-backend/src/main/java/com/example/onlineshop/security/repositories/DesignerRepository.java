package com.example.onlineshop.security.repositories;

import com.example.onlineshop.security.models.Designer;
import com.example.onlineshop.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignerRepository extends JpaRepository<Designer, Long> {
    Designer findByEmail(String email);
    boolean existsByEmail(String cnp);

}
