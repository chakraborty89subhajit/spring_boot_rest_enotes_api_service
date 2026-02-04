package com.example.enotes_api_service.repo;

import com.example.enotes_api_service.entity.FileDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileDetails,Integer> {
}
