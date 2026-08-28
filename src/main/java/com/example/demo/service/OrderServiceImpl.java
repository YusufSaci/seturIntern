

@Service
public class OrderServiceImpl implements OrderService{

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public Order save( Order order){
        return  orderRepository.save(order);
    }

    @Override
    public  Order findById(long id){
         Optional<Order> order =  orderRepository.findById(id);

         if(order.isPresent()){
            return order.get();
         }

         throw new RunTimeException("order not found.")
    }

    @Override
    public List<order> findAll(){
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public  void deleteById(long id){
        Order order = findById(id);
        
        orderRepository.delete(order);
    }
}