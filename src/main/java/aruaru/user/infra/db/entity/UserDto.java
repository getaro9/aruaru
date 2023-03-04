package aruaru.user.infra.db.entity;

import org.seasar.doma.Entity;

@Entity
public class UserDto extends UserM {

    String userRoleName;

    /**
     * Returns the userRoleName.
     *
     * @return the userRoleName
     */
    public String getUserRoleName() {
        return userRoleName;
    }

    /**
     * Sets the userRoleName.
     *
     * @param userRoleName the userRoleName
     */
    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }
}
