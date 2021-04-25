
import java.util.Date;

/*
 * Author: Edward Hanlon
 * Date: 
 */

/**
 *
 * @author edhan
 */
public class User {
    
    private int id;
    private String name;
    private String password;
    private String email;
    private String cardNumber;
    private String cardExpiry;
    private String securityCode;
    
    public User(int Id,String name,String password, String email, 
            String cardNumber ,String cardExpiry,String securityCode)
    {
        this.id = Id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.cardNumber = cardNumber;
        this.cardExpiry = cardExpiry;
        this.securityCode = securityCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", cardNumber=" + cardNumber + ", cardExpiry=" + cardExpiry + ", securityCode=" + securityCode + '}';
    }
    
    
}
