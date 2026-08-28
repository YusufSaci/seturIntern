
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Order> orders;

    // constructor
    public Customer() {}

    public Customer(String firstName, String lastName, int age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;

    }

    // getter ve setter
     
    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age=age;
    }

    public List<Order> getOrders(){
        return orders;
    }

    public void setOrders(List<Order> orders){
        this.orders=orders;
    }

    public void addOrder(Order order){
        if(orders == null){
            orders = new ArrayList<>();
        }
        orders.add(order);
        order.setCustomer(this);

    }



}