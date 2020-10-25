package io.github.mat3e.todo;

import io.github.mat3e.HibernateUtil;
import io.github.mat3e.lang.Lang;

import java.util.List;
import java.util.Optional;

class TodoRepository {

    List<TODO> findAll() {
            var session = HibernateUtil.getSessionFactory().openSession();
            var transaction = session.beginTransaction();

            var result = session.createQuery("from TODO", TODO.class).list();

            transaction.commit();
            session.close();
            return result;
    }

    TODO toggleTodo(Integer id){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var result = session.get(TODO.class, id);

        result.setDone(!result.isDone());
        transaction.commit();
        session.close();
        return result;
    }

    TODO addTodo(TODO newTodo){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        session.persist(newTodo);

        transaction.commit();
        session.close();
        return newTodo;

    }

    Optional<TODO> findById(Integer id){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.get(TODO.class, id);
            transaction.commit();
            session.close();
            return Optional.ofNullable(result);
    }
}
