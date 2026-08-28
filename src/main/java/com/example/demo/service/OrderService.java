public interface OrderService{

    void save(Order order);

    Order findById(long id);

    List<Order> findAll();

    void deleteById(long id);

   
}