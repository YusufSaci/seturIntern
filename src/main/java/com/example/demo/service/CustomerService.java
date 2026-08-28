

public interface CustomerService{

    void save(Customer customer);

    Customer findById(long id);

    List<Customer> findAll();

    void deleteById(long id);

    Customer update(Customer customer);
}