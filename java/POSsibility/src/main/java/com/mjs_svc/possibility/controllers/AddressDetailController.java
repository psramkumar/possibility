package com.mjs_svc.possibility.controllers;

import com.mjs_svc.possibility.models.Address;
import com.mjs_svc.possibility.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author mscott
 */
public class AddressDetailController {
    public static Address createAddress(String name, String phone,
            String address1, String city, String state, String country) {

        Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
        sess.beginTransaction();

        Address a = new Address();
        a.setName(name);
        a.setPhone(phone);
        a.setAddress1(address1);
        a.setCity(city);
        a.setState(state);
        a.setCountry(country);

        sess.save(a);
        sess.getTransaction().commit();

        return a;
    }

    public static Address updateAddress(int id, String name, String organization,
            String phone, String address1, String address2, String city,
            String state, String zipCode, String Country) {

        Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
        sess.beginTransaction();

        Address a = (Address) sess.load(Address.class, id);

        a.setName(name);
        a.setOrganization(organization);
        a.setPhone(phone);
        a.setAddress1(address1);
        a.setAddress2(address2);
        a.setCity(city);
        a.setState(state);
        a.setZipCode(zipCode);
        a.setCountry(Country);

        sess.update(a);

        return a;
    }
}
