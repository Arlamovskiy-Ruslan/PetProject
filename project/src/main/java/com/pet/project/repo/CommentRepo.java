package com.pet.project.repo;

import com.pet.project.models.Comments;
import com.pet.project.models.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comments,Long> {
    List<Comments> findById(long id);
}
