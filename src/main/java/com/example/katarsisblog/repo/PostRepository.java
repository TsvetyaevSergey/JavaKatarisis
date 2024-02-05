package com.example.katarsisblog.repo;

import com.example.katarsisblog.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

}
