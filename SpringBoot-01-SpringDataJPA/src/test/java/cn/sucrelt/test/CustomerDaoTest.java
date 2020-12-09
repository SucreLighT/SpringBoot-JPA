package cn.sucrelt.test;

import cn.sucrelt.dao.CustomerDao;
import cn.sucrelt.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class) //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器的配置信息
public class CustomerDaoTest {
    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindOne() {
        Customer customer = customerDao.findOne(4l);
        System.out.println(customer);
    }

    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setCustName("zhangsan");
        customer.setCustIndustry("it");
        customerDao.save(customer);
    }

    @Test
    public void testUpdate() {
        Customer customer = new Customer();
        customer.setCustId(4l);
        customer.setCustName("lisi");
        customerDao.save(customer);
    }

    @Test
    public void testDelete() {
        customerDao.delete(3l);
    }

    @Test
    public void testFindAll() {
        List<Customer> list = customerDao.findAll();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    @Test
    @Transactional
    public void  testGetOne() {
        Customer customer = customerDao.getOne(4l);
        System.out.println(customer);
    }

}
