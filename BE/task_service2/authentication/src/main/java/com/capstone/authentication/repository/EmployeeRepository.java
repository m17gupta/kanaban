package com.capstone.authentication.repository;

import com.capstone.authentication.domain.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeDetails,String> {
}
