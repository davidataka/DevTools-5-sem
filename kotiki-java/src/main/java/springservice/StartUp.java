package springservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class StartUp {




    public static void main(String args[]) throws IOException {
        SpringApplication.run(StartUp.class, args);
        //SpringApplication.run(Main.class, args);
        // CatsDAO catsDAO = new CatsDAO();
        //OwnerServices ownerServices = new OwnerServices();
        //CatsEntity cat = catServices.addCat("May", new Timestamp(2), "cobaka", 1);
        //CatsEntity cat2 = catServices.addCat("May", new Timestamp(2), "cobaka", 2);
        //OwnersEntity owner = ownerServices.addOwner("David", new Timestamp(2));
        //ownerServices.assignOwner(7, 2);
        //CatsEntity cats= catsDAO.getCat(2);
        //CatsEntity cats2= catsDAO.getCat(15);
        //cat.addFriend(cat2);
        //CatsDAO.updateCat(cat);
        //catServices.makeFriend(2,16);
        //catServices.makeFriend(11,12);
        //CatServices.makeFriend(4, 5);
        //catServices.deleteCat(10);
        //ArrayList<CatsEntity> friends=CatServices.catFriends(2);
        //CatsDAO.deleteCat(cat);
        //HibernateSession.shutDown();
    }
}
