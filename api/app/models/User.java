package models;

import java.util.*;
import javax.persistence.*;

import org.mindrot.jbcrypt.BCrypt;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class User extends Model {
    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    @Column(unique=true)
    public String email;

    @Constraints.Required
    public String password;

//    @OneToMany(cascade=CascadeType.ALL)
//    public List<Channel> channels;

//    public User() {
//        channels = new ArrayList<Channel>();
//    }

//    public boolean done;

//    @Formats.DateTime(pattern="dd/MM/YY")
//    public Date dueDate;

    public static Finder<Long,User> find = new Finder<Long,User>(
            Long.class, User.class
    );

    public void hashPassword() {
        this.password = BCrypt.hashpw(this.password, BCrypt.gensalt());
        this.save();
    }

    public static User authenticate(String userName, String password) {
        User user = User.find.where().eq("email", userName).findUnique();
        if (user != null && BCrypt.checkpw(password, user.password)) {
            return user;
        } else {
            return null;
        }
    }
}
