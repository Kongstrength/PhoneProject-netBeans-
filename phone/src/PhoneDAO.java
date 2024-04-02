import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import org.hibernate.query.Query;

public class PhoneDAO {

    private static SessionFactory factory;
    
    public PhoneDAO(){
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            factory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void addPhone(Phone phone) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(phone);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
     public List<Phone> listProducts() {
        return listPhone(Phone.class);
    }
     
      public <T> List<T> listPhone(Class<T> returnType) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        List<T> phones = null;
        try {
            tx = session.beginTransaction();
            String hql = "SELECT id, model, publisher, price, warranty FROM Phone";

            Query<T> query =   session.createQuery(hql,returnType);
            phones = query.list();
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return phones;
    }
    

    public Phone getPhoneById(int id) {
        Session session = factory.openSession();
        Phone phone = null;
        try {
            phone = session.get(Phone.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return phone;
    }

    public <T> List<T> getPhonesByPublisher(String publisher, Class<T> returnType)  {
    Session session = factory.openSession();
    Transaction tx = null;
    List<T> phones = null;
    try {
        tx = session.beginTransaction();
        String hql = "SELECT id, model"
                    + ", publisher, price, warranty FROM Phone p  "
                    + " WHERE p.publisher = :publisher ";

        Query<T> query = session.createQuery(hql, returnType);
        query.setParameter("publisher", publisher);
        phones = query.list();
        tx.commit();

    } catch (Exception e) {
        if (tx != null) {
            tx.rollback();
        }
        e.printStackTrace();
    } finally {
        session.close();
    }
    return phones;
}

    
    

    public void updatePhone(Phone phone) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(phone);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deletePhone(int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Phone phone = session.get(Phone.class, id);
            if (phone != null) {
                session.delete(phone);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
