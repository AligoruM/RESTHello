package com.netcracker.crud.dao;

import com.netcracker.crud.model.Customer;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("customerDAO")
@Transactional
public class CustomerDAOImpl extends BasicDAO implements ICustomerDAO {

    public void saveCustomer(Customer entity) {
        persist(entity);
    }

    public List<Customer> findAllCustomers() {
        Criteria criteria = getSession().createCriteria((Customer.class));
        return criteria.list();
    }

    public Customer findCustomerById(int id) {
        Criteria criteria = getSession().createCriteria(Customer.class);
        criteria.add(Restrictions.eq("id", id));
        return (Customer) criteria.uniqueResult();
    }

    public int deleteCustomerById(int id) {
        Query query = getSession().createQuery("delete from Customer where id = :id");
        query.setInteger("id", id);
        return query.executeUpdate();
    }

    public int deleteAll() {
        Query query = getSession().createQuery("delete from Customer");
        return query.executeUpdate();
    }
}
