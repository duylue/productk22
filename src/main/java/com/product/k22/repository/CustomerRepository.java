package com.product.k22.repository;

import com.product.k22.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query(value = "select * from address",nativeQuery = true)
    List<Map<String, Object>> getListAddress();
    @Query(value = "select o.* ,e.ename, od.quantity, p.*\n" +
            "from orders o, employee e, orders_detail od, product p\n" +
            "where o.oid = od.od_id\n" +
            "and e.eid = o.eid\n" +
            "and od.pid = p.pid and o.cust_id = :cid",nativeQuery = true)
    List<Map<String, Object>> getCusDetail(@Param("cid") int cid);


}
