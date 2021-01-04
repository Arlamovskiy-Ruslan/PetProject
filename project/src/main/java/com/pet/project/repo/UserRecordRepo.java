package com.pet.project.repo;

import com.pet.project.models.UserRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRecordRepo extends CrudRepository<UserRecord,Long> {
}
