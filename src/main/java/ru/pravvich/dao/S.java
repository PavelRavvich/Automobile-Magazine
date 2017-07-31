package ru.pravvich.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class S {
    public static void main(String[] args) {
        SessionFactory f = new Configuration().configure().buildSessionFactory();

        AtomicReference<SessionFactory> factory = new AtomicReference<>(f);


        final List<String> marks = new DAOImpl(factory).getModelsByMark("audi");

        f.close();

        marks.forEach(System.out::println);
    }
}
