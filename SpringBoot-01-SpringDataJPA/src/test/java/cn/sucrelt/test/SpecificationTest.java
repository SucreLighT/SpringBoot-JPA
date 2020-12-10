package cn.sucrelt.test;

import cn.sucrelt.dao.CustomerDao;
import cn.sucrelt.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecificationTest {
    @Autowired
    private CustomerDao customerDao;

    /**
     * 根据条件，查询单个对象
     */
    @Test
    public void testSpec() {
        //匿名内部类
        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //1.获取比较的属性
                Path<Object> custName = root.get("custName");
                //2.设置匹配方式
                Predicate predicate = cb.equal(custName, "张三");
                return predicate;
            }
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    /**
     * 多条件查询
     * 案例：根据客户名和客户所属行业查询
     */
    @Test
    public void testSpec1() {
        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //1.构造查询条件
                //客户名,模糊查询
                Path<Object> custName = root.get("custName");
                Predicate p1 = cb.like(custName.as(String.class), "张");
                //所属行业
                Path<Object> custIndustry = root.get("custIndustry");
                Predicate p2 = cb.equal(custIndustry, "it");

                //2.联合查询条件
                Predicate predicates = cb.and(p1, p2);
                return predicates;
            }
        };

        //创建排序对象,需要调用构造方法实例化sort对象
        Sort sort = new Sort(Sort.Direction.DESC, "custId");
        List<Customer> customers = customerDao.findAll(spec, sort);
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }


    /**
     * 分页查询
     */
    @Test
    public void testSpec4() {
        Specification spec = null;
        Pageable pageable = new PageRequest(0, 2);
        //分页查询
        Page<Customer> page = customerDao.findAll(null, pageable);
        System.out.println(page.getContent()); //得到数据集合列表
        System.out.println(page.getTotalElements());//得到总条数
        System.out.println(page.getTotalPages());//得到总页数
    }
}
