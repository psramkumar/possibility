package com.mjs_svc.possibility.models;

import com.mjs_svc.possibility.util.HibernateUtil;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.security.MessageDigest;
import org.hibernate.Session;

/**
 * User ORM matching Django's User model
 * @author Matthew Scott
 * @version $Id$
 */
public class User {
    private long id;
    private String username, password, firstName, lastName, email;
    private boolean active, staff, superuser;
    private Date lastLogin, dateJoined;
    private Creator creator;
    private Customer customer;
    private Employee employee;
    private Set permissions = new HashSet(), groups = new HashSet();

    public Set getGroups() {
        return groups;
    }

    public void setGroups(Set groups) {
        this.groups = groups;
    }

    public Set getPermissions() {
        return permissions;
    }

    public void setPermissions(Set permissions) {
        this.permissions = permissions;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isStaff() {
        return staff;
    }

    public void setStaff(boolean staff) {
        this.staff = staff;
    }

    public boolean isSuperuser() {
        return superuser;
    }

    public void setSuperuser(boolean superuser) {
        this.superuser = superuser;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //------------------------------------------------------------------------//
    private MessageDigest md;
    private boolean isAuthenticated = false;

    /**
     * Convert a byte array into a hex string
     * @param data The byte array to convert
     * @return A hex string of the array
     */
    private String toHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while(two_halfs++ < 1);
        }
        return buf.toString();
    }

    /**
     *
     * @param plaintext
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String generateNewPasswordHash(String plaintext) {
        StringBuffer pwhash = new StringBuffer("sha1$");
        Random r = new Random();
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            //
        }
        byte[] salt = new byte[5];

        // Generate a new salt
        r.nextBytes(salt);

        // Convert it to a hex string and append it to the password string
        pwhash.append(toHex(salt) + "$");

        // Update the digest with the new salt
        md.update(salt);

        // Append the resulting hash of the plaintext to the password string
        pwhash.append(toHex(md.digest(plaintext.getBytes())));
        return pwhash.toString();
    }

    /**
     * Authenticate a user with a given password against the password stored in
     * the database.  This goes through a SHA-1 hashing digest and the hashes
     * are compared for equality.  This sets the isAuthenticated flag on the
     * user object, which can be checked with getIsAuthenticated()
     * @param plaintext The plaintext password
     * @return True if the hashes match, false otherwise
     * @throws NoSuchAlgorithmException if SHA-1 isn't installed (it should be)
     */
    public boolean authenticate(String plaintext) throws NoSuchAlgorithmException {
        String[] passwordParts = this.password.split("\\$");
        md = MessageDigest.getInstance("SHA-1");

        // Salt the digest with the given salt
        md.update(passwordParts[1].getBytes());

        // Set isAuthenticated based on whether the hashes are the same
        isAuthenticated = toHex(md.digest(plaintext.getBytes())).equalsIgnoreCase(passwordParts[2]);
        return isAuthenticated;
    }

    /**
     * Log a user out by setting the isAuthenticated flag to false
     * @return False
     */
    public boolean deauthenticate() {
        isAuthenticated = false;
        return isAuthenticated;
    }

    /**
     * Returns whether or not a user is currently authenticated
     * @return True if the user is authenticated, false otherwise
     */
    public boolean getIsAuthenticated() {
        return isAuthenticated;
    }

    /**
     * Returns whether or not the user has the given permission
     * @param perm The permission's codename to check
     * @return True if the user or the user's groups have that permission, false otherwise
     */
    public boolean hasPermission(String perm) {
        // Superusers have all permissions
        if (isSuperuser()) {
            return true;
        }

        // Unauthenticated users (hibernate guesses id = 0) have no permissions
        if (id == 0) {
            return false;
        }

        // Otherwise, really go out and check our permissions.
        Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
        sess.beginTransaction();

        // Use a current instance of our user
        User u = (User) sess.load(User.class, getId());

        // Check user permissions
        for (Object p : u.getPermissions()) {
            if (((Permission) p).getCodeName().equals(perm)) {
                sess.getTransaction().commit();
                return true;
            }
        }

        // Check group permissions
        for (Object g : u.getGroups()) {
            for (Object p : ((Group) g).getPermissions()) {
                if (((Permission) p).getCodeName().equals(perm)) {
                    sess.getTransaction().commit();
                    return true;
                }
            }
        }

        // None found
        sess.getTransaction().commit();
        return false;
    }
}
