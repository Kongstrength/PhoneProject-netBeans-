import javax.persistence.*;
@Entity
@Table(name = "phones")
  public class Phone {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "model")
    private String model;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "price")
    private int price;
    @Column(name = "warranty")
    private int warranty;

    public Phone(int id, String model, String publisher, int price, int warranty) {
        this.id = id;
        this.model = model;
        this.publisher = publisher;
        this.price = price;
        this.warranty = warranty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }
    
    

    @Override
    public String toString() {
        return "Phone{" + "id=" + id + ", model=" + model + ", publisher=" + publisher + ", price=" + price + ", warranty=" + warranty + '}';
    }
    
}
