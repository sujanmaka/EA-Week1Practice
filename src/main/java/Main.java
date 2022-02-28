import miu.edu.cs544.sujan.practice.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("practicePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        /*
        * save dummy data into DB
        * */
//        saveDataToDB(em);

        String query = "select f from Factory as f where f.year > 1999 and f.year=:year";
        TypedQuery<Factory> query1 = em.createQuery(query, Factory.class);
        query1.setParameter("year", 2000);
        System.out.println(query1.getResultList());

        String query2= "select c.person from Car as c where c.millage < 100000 and c.factory.insurance.address.state='CA'";
        TypedQuery<Person> query3 = em.createQuery(query2, Person.class);
        System.out.println(query3.getResultList());

        TypedQuery<Vehicle> query4 = em.createNamedQuery("findVehiclesOwnByAPerson", Vehicle.class);
        query4.setParameter("name", "Jack");
        System.out.println(query4.getResultList());

        TypedQuery<Vehicle> query5 = em.createNamedQuery("findAllCars", Vehicle.class);
        System.out.println(query5.getResultList());

        CriteriaBuilder criBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Car> criQuery = criBuilder.createQuery(Car.class);
        Root<Car> rootCar = criQuery.from(Car.class);
        criQuery.select(rootCar);
        Predicate millagePredicate = criBuilder.greaterThan(rootCar.get("millage"), 100000);
        Predicate factoryPredicate = criBuilder.equal(rootCar.get("factory").get("insurance")
                .get("address")
                .get("state"), "CA");
        Predicate andPredicate = criBuilder.and(millagePredicate, factoryPredicate);
        criQuery.where(andPredicate);
        TypedQuery<Car> vehicleTypedQuery = em.createQuery(criQuery);
        System.out.println(vehicleTypedQuery.getResultList());

        //solution with Ale
        CriteriaBuilder criBuilder1 = em.getCriteriaBuilder();
        CriteriaQuery<Vehicle> criQuery1 = criBuilder1.createQuery(Vehicle.class);
        Root<Vehicle> rootVehicle = criQuery1.from(Vehicle.class);
        criQuery.select(rootVehicle.get("person"));
        Root<Car> car = criBuilder1.treat(rootVehicle, Car.class);
        Predicate millagePredicate1 = criBuilder1.greaterThan(car.get("millage"), 100000);
        Predicate factoryPredicate1 = criBuilder1.equal(rootVehicle.get("factory").get("insurance")
                .get("address")
                .get("state"), "CA");
        Predicate andPredicate1 = criBuilder.and(millagePredicate1, factoryPredicate1);
        criQuery.where(andPredicate1);
        TypedQuery<Vehicle> personTypedQuery = em.createQuery(criQuery1);
        System.out.println(personTypedQuery.getResultList());



        tx.begin();
        tx.commit();
        em.close();
        emf.close();
    }

    private static void saveDataToDB(EntityManager em) {
        Person jack = new Person("Jack", 32);
        Person john = new Person("John", 33);
        Person jill = new Person("Jill", 28);

        Car car1 = new Car(2005, 18900);
        Car car2 = new Car(2020, 10020);
        Car car3 = new Car(1999, 120398);
        Car car4 = new Car(2000, 123452);
        car1.setPerson(jack);
        car2.setPerson(jack);
        car3.setPerson(john);
        car4.setPerson(jill);

        Van van1 = new Van(VehicleColor.RED);
        Van van2 = new Van(VehicleColor.BLUE);
        van1.setPerson(jill);
        van2.setPerson(jack);

        jack.setVehicles(Arrays.asList(car1,car2, van2));
        john.setVehicles(Arrays.asList(car3));
        jill.setVehicles(Arrays.asList(car4,van1));

        em.persist(jack);
        em.persist(john);
        em.persist(jill);


        Insurance insurance1 = new Insurance("StateFarm", new Address(" 1102 E Chapman Ave", "CA", "Fullerton"));
        Insurance insurance2 = new Insurance("GoodOnes", new Address("425 N New Ballas Rd UNIT 215", "MO", "Creve Coeur"));

        Factory factory1 = new Factory(1990, 12300, Arrays.asList(car1, car4));
        Factory factory2 = new Factory(1980, 800, Arrays.asList(van1, car3));
        Factory factory3 = new Factory(2000, 20034, Arrays.asList(car2, van2));

        factory1.setInsurance(insurance1);
        factory2.setInsurance(insurance1);
        factory3.setInsurance(insurance2);

        em.persist(factory1);
        em.persist(factory2);
        em.persist(factory3);

        car1.setFactory(factory1);
        car2.setFactory(factory3);
        car3.setFactory(factory2);
        car4.setFactory(factory1);
        van1.setFactory(factory2);
        van2.setFactory(factory3);
    }
}
