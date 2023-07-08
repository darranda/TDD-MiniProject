package com.TDDMiniProject.TDD.Repository;


import com.TDDMiniProject.TDD.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
