package com.example.demo.repositories;

import com.example.demo.entities.FbPage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jerry on 2017/7/22.
 */
public interface FbPageRepository extends JpaRepository<FbPage, String> {
}
