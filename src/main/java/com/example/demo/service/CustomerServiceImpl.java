

@Service
public class CustomerServiceImpl implements CustomerService{

    private CustomerDao customerDao;

    public CustomerServiceImpl(CustomerDao customerDao){
        this.customerDao = customerDao;
    }

    @Override
    @Transactional
    public void save(Customer customer){
        customerDao.save(customer);
    }

    @Override
    public Customer findById(long id){
        return customerDao.findById(id);
    }

    @Override
    public List<Customer> findAll(){
        return customerDao.findAll();
    }

    @Override
    @Transactional
    public  void deleteById(long id){
        customerDao.deleteById(id);
    }

    @Override
    @Transactional
    public Customer update(Customer customer){
        return customerDao.update(customer);
    }
}