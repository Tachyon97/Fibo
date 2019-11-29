package se.tachyon97.stuff;

public class ItemizedOrder {
    private String drop;
    private String pick;
    private int price;
    private String email;
    private String number;
    private String name;
    private String message;

    public ItemizedOrder() {

    }

    public ItemizedOrder(String drop, String email, String message, String name, String number, String pick, int price) {
        this.drop = drop;
        this.pick = pick;
        this.price = price;
        this.email = email;
        this.number = number;
        this.name = name;
        this.message = message;
    }

    public String getdrop() {
        return drop;
    }

    public void setdrop(String drop) {
        this.drop = drop;
    }

    public String getpick() {
        return pick;
    }

    public void setpick(String pick) {
        this.pick = pick;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getnumber() {
        return number;
    }

    public void setnumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RequestObject{" +
                " drop='" + drop + '\'' +
                ", pick='" + pick + '\'' +
                ", price=" + price +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
