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
public class KotikiThirdTests {

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

}

