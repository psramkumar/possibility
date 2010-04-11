package com.mjs_svc.possibility.models;

import com.mjs_svc.possibility.util.HibernateUtil;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.security.MessageDigest;
import org.hibernate.Hibernate;
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

    /**
     *
     * @return
     */
    public Set getGroups() {
        return groups;
    }

    /**
     * 
     * @param groups
     */
    public void setGroups(Set groups) {
        this.groups = groups;
    }

    /**
     *
     * @return
     */
    public Set getPermissions() {
        return permissions;
    }

    /**
     *
     * @param permissions
     */
    public void setPermissions(Set permissions) {
        this.permissions = permissions;
    }

    /**
     *
     * @return
     */
    public Creator getCreator() {
        return creator;
    }

    /**
     * 
     * @param creator
     */
    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    /**
     * 
     * @return
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * 
     * @param customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * 
     * @return
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * 
     * @param employee
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     *
     * @return
     */
    public Date getDateJoined() {
        return dateJoined;
    }

    /**
     *
     * @param dateJoined
     */
    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    /**
     *
     * @return
     */
    public Date getLastLogin() {
        return lastLogin;
    }

    /**
     *
     * @param lastLogin
     */
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public boolean isActive() {
        return active;
    }

    /**
     *
     * @param isActive
     */
    public void setIsActive(boolean active) {
        this.active = active;
    }

    /**
     *
     * @return
     */
    public boolean isStaff() {
        return staff;
    }

    /**
     *
     * @param isStaff
     */
    public void setIsStaff(boolean staff) {
        this.staff = staff;
    }

    /**
     *
     * @return
     */
    public boolean isSuperuser() {
        return superuser;
    }

    /**
     *
     * @param isSuperuser
     */
    public void setIsSuperuser(boolean superuser) {
        this.superuser = superuser;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
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
    public String generateNewPasswordHash(String plaintext) throws NoSuchAlgorithmException {
        StringBuffer pwhash = new StringBuffer("sha1$");
        Random r = new Random();
        md = MessageDigest.getInstance("SHA-1");
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
        if (isSuperuser()) {
            return true;
        }
        Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
        sess.beginTransaction();
        User u = (User) sess.load(User.class, getId());
        for (Object p : u.getPermissions()) {
            if (((Permission) p).getCodeName().equals(perm)) {
                sess.getTransaction().commit();
                return true;
            }
        }
        for (Object g : u.getGroups()) {
            for (Object p : ((Group) g).getPermissions()) {
                if (((Permission) p).getCodeName().equals(perm)) {
                    sess.getTransaction().commit();
                    return true;
                }
            }
        }
        sess.getTransaction().commit();
        return false;
    }
}
