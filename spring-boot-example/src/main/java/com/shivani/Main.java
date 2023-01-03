package com.shivani;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    static record NewCustomerRequest(
        String name,
        String email,
        Integer age
    ){}

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());

        customerRepository.save(customer);
    }

    @DeleteMapping(path = "{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

    @PutMapping("{customerId}")
    public void updateCustomer(){
    }
    //@GetMapping("/greet")
//    public GreetResponse greet(){
//        GreetResponse response = new GreetResponse("Hello",
//                List.of("Java", "Golang", "JavaScript"),
//                new Person("Alex",28,30_000));
//        return  response;
//    }

//    record Person(String name,int age, double savings) {
//
//    }
//    record GreetResponse(String greet,
//                         List<String> favProgrammingLanguages,
//                         Person person){}

//This is the equivalent code of record statement above
//    class GreetResponse{
//        private final String greet;
//        public String getGreet() {
//            return greet;
//        }
//        GreetResponse(String greet){
//            this.greet = greet;
//        }
//
//        @Override
//        public String toString() {
//            return super.toString();
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            GreetResponse that = (GreetResponse) o;
//            return Objects.equals(greet, that.greet);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(greet);
//        }
//    }
}
