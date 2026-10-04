package com.example.demo.repository;


import com.example.demo.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataPublisherRepository  extends JpaRepository<Publisher, Long> {

    Optional<Publisher> findByName(String name);


}
