package com.product.k22.repository;

import com.product.k22.model.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FileRepository extends JpaRepository<FileInfo, Integer> {
    FileInfo findByPid(int pid);
    @Query(value = "select fid from file_info where pid = ?",nativeQuery = true)
    String findFid(int pid);
}
