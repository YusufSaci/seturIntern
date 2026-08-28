

@RestController
@RequestMapping("/api/customers")

public class CustomerRestController{

    private CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }   


    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable long id){
        Customer customer =customerService.findById(id);

        if(customer == null) {
            throw new RunTimeException("customer not found");
        }
        return customer;
    }

    @PostMapping
    public String addCustomer(@RequestBody Customer customer ){
        
        customerService.save(customer)
        return "Customer added.";
    }

    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer){
        return customerService.update(customer);
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable long id){

        Customer customer = customerService.findById(id);

        if(customer == null) {
            throw new RunTimeException("customer not found");
        }
        customerService.deleteById(id);

        return "Customer deleted.";
    }



}