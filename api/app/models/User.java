package models;

import java.util.*;
import javax.persistence.*;

import com.feth.play.module.pa.user.AuthUser;
import com.feth.play.module.pa.user.AuthUserIdentity;
import org.mindrot.jbcrypt.BCrypt;
import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class User extends Model {
    @Id
    @Constraints.Min(10)
    public String id;

    public String user_id;
    public String provider_id;

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

    public static User create(AuthUser authUser) {
        User u = new User();
        u.id = authUser.getId();
        u.provider_id = authUser.getProvider();
        u.save();
        return u;
    }

    public static boolean existsByAuthUserIdentity(AuthUser authUser) {
        List<User> lu = find.where()
                .ilike("user_id", "%" + authUser.getId() +"%")
                .findPagingList(25)
                .setFetchAhead(false)
                .getAsList();
        if (lu.size() >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public static User findByAuthUserIdentity(AuthUserIdentity identity) {
        List<User> lu = find.where()
                .ilike("user_id", "%" + identity.getId() +"%")
                .findPagingList(25)
                .setFetchAhead(false)
                .getAsList();
        return lu.get(0);
    }

    public static void merge(AuthUser newUser, AuthUser oldUser) {

    }

    public static void addLinkedAccount(final AuthUser oldUser, final AuthUser newUser) {

    }

    public static Finder<Long,User> find = new Finder<Long,User>(
            Long.class, User.class
    );

//    public void hashPassword() {
//        this.password = BCrypt.hashpw(this.password, BCrypt.gensalt());
//        this.save();
//    }
//
//    public static User authenticate(String userName, String password) {
//        User user = User.find.where().eq("email", userName).findUnique();
//        if (user != null && BCrypt.checkpw(password, user.password)) {
//            return user;
//        } else {
//            return null;
//        }
//    }
}
