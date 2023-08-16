package org.example;

import org.example.models.User;
import org.example.utils.HibernateUtils;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.transaction.internal.TransactionImpl;


import java.util.List;



// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        AddUser();
        SelectUsers();
    }

    public static void AddUser() {
// Get the SessionFactory
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        // Open a session
        try (Session session = sessionFactory.openSession()) {
            // Perform database operations using the session
            // For example, save or retrieve entities
            Transaction tx = session.beginTransaction();
            User user = new User();
            user.setEmail("ivan@gmail.com");
            user.setFirstname("Іван");
            user.setLastname("Крот");
            user.setPhone("+38976 93 78 442");
            user.setPassword("123456");
            session.save(user);
            tx.commit();
        }

    }
    public static void SelectUsers() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        // Open a session

        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM User");
            List<User> users = query.list();
            for (User user : users) {
                System.out.println(user);
            }
        }
    }


}
