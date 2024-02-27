package com.product.k22.repository;

import com.product.k22.model.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileInfo, Integer> {
    FileInfo findByPid(int pid);
}
