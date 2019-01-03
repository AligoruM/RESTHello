package com.netcracker.crud;

import com.netcracker.crud.config.SpringConfig;
import com.netcracker.crud.dao.ICustomerDAO;
import com.netcracker.crud.model.Customer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


/*
Т.к. юзаю sqlite, что бы запустить(просто не хочется искать способ как получить нужный контекст)
надо в properties указать полный путь до базы sqlite.db(русские символы чет не робят...),
а так, все норм работает)
*/

@Path("/data")
public class CRUDService {
    private static AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    private ICustomerDAO customerDAO = (ICustomerDAO) context.getBean("customerDAO");

    @GET
    @Path("/customer")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List result = customerDAO.findAllCustomers();
        if(result.isEmpty())
            return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
        return  new ResponseEntity<List<Customer>>(result, HttpStatus.OK);
    }

    @GET
    @Path("/customer/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Customer> getCustomerById(@PathParam("id") int id){
        Customer result = customerDAO.findCustomerById(id);
        if(result!=null){
            return new ResponseEntity<Customer>(result, HttpStatus.FOUND);
        }
        return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
    }

    @POST
    @Path("/customer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<CustomStatus> addCustomer(@RequestBody Customer customer){
        customerDAO.saveCustomer(customer);
        return new ResponseEntity<CustomStatus>(new CustomStatus("Created"), HttpStatus.CREATED);
    }

    @DELETE
    @Path("/customer/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<CustomStatus> deleteCustomerById(@PathParam("id") int id){
        int result = customerDAO.deleteCustomerById(id);
        if (result==1)
            return new ResponseEntity<CustomStatus>(new CustomStatus("Deleted"), HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<CustomStatus>(new CustomStatus("Not found to delete"), HttpStatus.NOT_FOUND);

    }
    @DELETE
    @Path("/customer")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<CustomStatus> deleteAllCustomers(){
        int result = customerDAO.deleteAll();
        if(result > 0)
            return new ResponseEntity<CustomStatus>(new CustomStatus("Deleted"), HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<CustomStatus>(new CustomStatus("Nothing to delete"), HttpStatus.NO_CONTENT);
    }


    class CustomStatus{

        private String status;

        public CustomStatus() {
        }

        public CustomStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return status;
        }
    }
}
