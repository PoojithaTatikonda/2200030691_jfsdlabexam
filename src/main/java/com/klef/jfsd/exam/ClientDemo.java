package com.klef.jfsd.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Insert records
        Client client1 = new Client();
        client1.setName("John Doe");
        client1.setGender("Male");
        client1.setAge(30);
        client1.setLocation("New York");
        client1.setEmail("john.doe@example.com");
        client1.setMobileNumber("1234567890");

        Client client2 = new Client();
        client2.setName("Jane Smith");
        client2.setGender("Female");
        client2.setAge(28);
        client2.setLocation("California");
        client2.setEmail("jane.smith@example.com");
        client2.setMobileNumber("0987654321");

        session.save(client1);
        session.save(client2);

        transaction.commit();

        // Print all records
        Query query = session.createQuery("from Client");
        List<Client> clients = query.list();

        for (Client client : clients) {
            System.out.println("ID: " + client.getId());
            System.out.println("Name: " + client.getName());
            System.out.println("Gender: " + client.getGender());
            System.out.println("Age: " + client.getAge());
            System.out.println("Location: " + client.getLocation());
            System.out.println("Email: " + client.getEmail());
            System.out.println("Mobile Number: " + client.getMobileNumber());
            System.out.println("--------------------------------");
        }

        session.close();
        sessionFactory.close();
    }
}
