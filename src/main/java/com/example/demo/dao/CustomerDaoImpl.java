
@Repository
public class CustomerDaoImpl implements CustomerDao{


    private EntityManager entityManager;

    @Autowired
    public CustomerDaoImpl (EntityManager entityManager){
        this.entityManager = entityManager;
    }

    
    @Override
    public void save(Customer customer){
       return entityManager.persist(customer);
    }

    @Override
    public Customer findById(long id){
        return entityManager.find(Customer.class, id);
    }


    @Override
    public List<Customer> findAll(){
        TypedQuery<Customer> query = entityManager.createQuery("FROM Customer ", Customer.class);
        
        return query.getResultList();
    }

    @Override
    public void deleteById(long id){
        Customer customer = entityManager.find(Customer.class, id);

        if(customer != null){
            entityManager.remove(customer);
        }
    }

    @Override
    public Customer update(Customer customer){
        return entityManager.merge(customer);
    }
}