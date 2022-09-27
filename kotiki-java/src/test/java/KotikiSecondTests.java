import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import springservice.StartUp;
import springservice.dto.OwnerRequest;
import springservice.entity.OwnersEntity;
import springservice.repository.CatRepository;
import springservice.repository.OwnerRepository;
import springservice.services.CatServices;
import springservice.services.OwnerServices;

import java.sql.Timestamp;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartUp.class)
public class KotikiSecondTests {

    @MockBean
    private CatRepository catsDAO;
    @MockBean
    private OwnerRepository ownerRepository;
    @MockBean
    private CatServices catServices;
    @MockBean
    private OwnerServices ownerServices;


    @Test
    public void AddOwners() throws Exception {
        ArrayList<OwnersEntity> ownersEntities = new ArrayList<>();
        OwnersEntity owners = new OwnersEntity("David", new Timestamp(2));
        OwnerRequest ownerRequest=new OwnerRequest();
        ownerRequest.setName(owners.getName());
        ownerRequest.setDateBirth(owners.getDatebirth());
        Mockito.when(ownerServices.addOwner(ownerRequest)).thenReturn(owners);
        owners=ownerServices.addOwner(ownerRequest);
        ownersEntities.add(owners);
        Assert.notEmpty(ownersEntities);
    }

    /*@Test
    public void MakeFriend() throws Exception {
        CatsEntity cats = new CatsEntity("May", new Timestamp(2), "cobaka", 1);
        cats.setId(1);
        Mockito.when(catsDAO.saveCat(cats)).thenReturn(cats);
        CatsEntity cat = catsDAO.saveCat(cats);
        CatsEntity cat2 = new CatsEntity("May", new Timestamp(2), "cobaka", 2);
        catsDAO.saveCat(cat2);
        Mockito.when(catsDAO.makeFriend(cat, cat2)).thenReturn(true);
        catsDAO.makeFriend(cat, cat2);
        Mockito.verify(catsDAO, Mockito.times(1))
                .makeFriend(cat, cat2);
    }

    @Test
    public void DeleteCat() throws Exception {
        ArrayList<CatsEntity> catsEntityis = new ArrayList<>();
        CatsEntity cats = new CatsEntity("May", new Timestamp(2), "cobaka", 1);
        cats.setId(1);
        cats.setName("David");
        Mockito.when(catsDAO.saveCat(cats)).thenReturn(cats);
        CatsEntity cat = catsDAO.saveCat(cats);
        catsEntityis.add(cat);
        cats.setId(2);
        CatsEntity cat2 = catsDAO.saveCat(cat);
        catsEntityis.add(cat);
        Mockito.when(catsDAO.deleteCat(cat2)).thenReturn(cat);
        catsEntityis.remove(catsDAO.deleteCat(cat));
        Assert.assertEquals(catsEntityis.size(), 1);
        Assert.assertEquals(catsEntityis.get(0).getId().intValue(), 2);
    }*/
}
