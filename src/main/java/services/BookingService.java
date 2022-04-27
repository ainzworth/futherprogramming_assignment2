package services;

import entity.Booking;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

@Transactional
public class BookingService {
    @Autowired
    private SessionFactory sessionFactory;
    public BookingService(){}
    public BookingService(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void saveCar(Booking booking){
        sessionFactory.getCurrentSession().save(booking);
    }
}
