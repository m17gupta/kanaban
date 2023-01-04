package com.capstoneproject.taskdata.repository;

import com.capstoneproject.taskdata.domain.EmployeeData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamMemberRepository extends MongoRepository<EmployeeData,String> {
}
