package com.pet.project.repo;

import com.pet.project.models.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRecordRepo extends JpaRepository<UserRecord,Long> {

    List<UserRecord> findByUserUsername(String username);
}