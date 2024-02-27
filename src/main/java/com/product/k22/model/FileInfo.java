package com.product.k22.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fid;
    private int pid;
    private String fileName;
    private String contentType;
    @Lob
    @Column(length = 20971520)
    private byte[] content;

}
