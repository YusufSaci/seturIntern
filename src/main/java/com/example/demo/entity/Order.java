
@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;


    @Column(name = "amount")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // constructor
    public Order() {}

    public Order( Product product, int amount){
        this.product = product;
        this.amount = amount;

    }

    // getter ve setter
     
    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }


    public Product getProduct(){
        return product;
    }

    public void setProduct(Product product){
        this.product = product;
    }

    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount){
        this.amount=amount;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }



}