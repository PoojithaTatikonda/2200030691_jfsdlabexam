package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // Configure Hibernate
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        config.addAnnotatedClass(Client.class);

        SessionFactory sessionFactory = config.buildSessionFactory();

        // Insert records
        insertClient(sessionFactory, "John Doe", "Male", 30, "New York", "john.doe@example.com", "1234567890");
        insertClient(sessionFactory, "Jane Smith", "Female", 25, "California", "jane.smith@example.com", "0987654321");

        // Print all records
        printAllClients(sessionFactory);

        // Close the SessionFactory
        sessionFactory.close();
    }

    // Method to insert a client record
    public static void insertClient(SessionFactory sessionFactory, String name, String gender, int age, String location, String email, String mobile) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Client client = new Client();
        client.setName(name);
        client.setGender(gender);
        client.setAge(age);
        client.setLocation(location);
        client.setEmail(email);
        client.setMobile(mobile);

        session.save(client);
        transaction.commit();
        session.close();

        System.out.println("Client inserted: " + name);
    }

    // Method to print all client records
    public static void printAllClients(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();

        List<Client> clients = session.createQuery("from Client", Client.class).list();

        System.out.println("All Clients:");
        for (Client client : clients) {
            System.out.println(client);
        }

        session.close();
    }
}
