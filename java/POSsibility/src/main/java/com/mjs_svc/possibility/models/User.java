package com.mjs_svc.possibility.models;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.security.MessageDigest;
import java.util.Random;

/**
 *
 * @author Matthew Scott
 * @version $Id$
 */
public class User {
    private long id;
    private String username, password, firstName, lastName, email;
    private boolean isActive, isStaff, isSuperuser;
    private Date lastLogin, dateJoined;

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

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isIsStaff() {
        return isStaff;
    }

    public void setIsStaff(boolean isStaff) {
        this.isStaff = isStaff;
    }

    public boolean isIsSuperuser() {
        return isSuperuser;
    }

    public void setIsSuperuser(boolean isSuperuser) {
        this.isSuperuser = isSuperuser;
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
}
