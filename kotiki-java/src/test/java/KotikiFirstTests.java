public class KotikiFirstTests {

   /* private CatServices catServices;
    private OwnerServices ownerServices;
    private CatDAO catsDAO;
    private OwnerDAO ownerDAO;

    @BeforeEach
    void Setup() throws Exception {
        catsDAO = Mockito.mock(CatDAO.class);
        ownerDAO = Mockito.mock(OwnerDAO.class);
        catServices = Mockito.mock(CatServices.class);
        ownerServices = Mockito.mock(OwnerServices.class);


    }

    @Test
    public void AddOwners() throws Exception {
        ArrayList<OwnersEntity> ownersEntities = new ArrayList<>();
        OwnersEntity owners = new OwnersEntity("David", new Timestamp(2));
        Mockito.when(ownerDAO.saveOwner(owners)).thenReturn(owners);
        OwnersEntity owner = ownerDAO.saveOwner(owners);
        owner.setId(1);
        ownersEntities.add(owner);
        OwnersEntity owner1 = ownerDAO.saveOwner(owners);
        owner.setId(2);
        ownersEntities.add(owner);
        Assert.assertEquals(owner1, owner);
        Assert.assertEquals(owner1.getId().intValue(), 2);
    }

    @Test
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

