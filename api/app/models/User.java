package models;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
@Table(name="user")
public class User extends Model {
    
    /**
     * Unique version uid for serialization
     */
    private static final long serialVersionUID = -378338424543301076L;

    @GeneratedValue
    @Column(unique=true)
    @Id
    public Integer  id; 
    
    @Column(length=254, unique=true)
    @Constraints.Email()
    public String   email;
    
    @Column(length=40)
    public String   password;
    
    @Column(length=35)
    @Constraints.MinLength(2)
    public String   firstName;
    
    @Column(length=35)
    @Constraints.MinLength(2)
    public String   lastName;
    
    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date     inscriptionDate;

    @Column(length=40)
    public String   token;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date     issued_token;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date     max_token_validity;

    
    // @OneToMany(mappedBy="creator", cascade=CascadeType.ALL)
    // public List<Tag>   tags;
    
    // public Lang     lang;
    
    // @OneToOne(cascade=CascadeType.ALL)
    // @JoinColumn(name="profile_picture_id")
    // public Image       profilePicture;

    // @OneToOne(cascade=CascadeType.ALL)
    // @JoinColumn(name="cover_picture_id")
    // public Image       coverPicture;
    
    @Version
    Timestamp updateTime;
    
    public User(String email, String password) {
        this.email = email;
        this.password = utils.Hasher.hash(password);
        this.firstName = null;
        this.lastName = null;
    }
    
    public static Finder<Long, User> find = new Finder<Long, User>(Long.class, User.class);
    
    public static User create(String email, String password) {
        User newUser = new User(email, password);
        newUser.save();
        
        return newUser;
    }
    
    public String getToken() {
        this.issued_token = new Date();
        this.max_token_validity = new Date();
        this.max_token_validity.setTime(this.issued_token.getTime() + 25600);
        this.token = utils.Hasher.hash(this.issued_token.toString().concat(this.id.toString()).concat(this.email));
        this.save();
        return (this.token);
    }

    public void deleteToken() {
        this.token = "";
        this.issued_token = null;
        this.save();
    }
}