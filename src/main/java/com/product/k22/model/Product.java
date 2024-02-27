package com.product.k22.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    @Size(max = 30 , min = 10, message ="Tên sp không được ít hơn 10 từ và không được lớn hơn 30 từ" )
//    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b",message = "sai dinh dang")
    private String pname;
    private int sid;
    private int cid;
    @Max(value = 999999,message = "Gia khong duoc qua 999999")
    @Min(value = 1,message = "So tien khong duoc nho hon 1")
    private float price;

}
