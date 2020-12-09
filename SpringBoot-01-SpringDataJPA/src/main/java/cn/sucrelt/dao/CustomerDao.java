package cn.sucrelt.dao;

import cn.sucrelt.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author sucre
 * 符合SpringDataJpa的dao层接口规范
 * JpaRepository<实体类类型，主键类型>：用来完成基本CRUD操作
 * JpaSpecificationExecutor<实体类类型>：用于复杂查询（分页等查询操作）
 */
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
}
