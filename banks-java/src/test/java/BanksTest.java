import models.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BanksTest {
    private CentralBank centralBank;
    private Bank bank1;
    private Bank bank2;
    private Client user1;
    private Client user2;
    private BankAccount a1;
    private BankAccount a2;

    @BeforeEach
    void Setup() throws Exception {
        ArrayList<double[]> interests = new ArrayList<double[]>();
        interests.add(new double[]{10000, 4});
        centralBank = new CentralBank();
        bank1 = centralBank.bankRegistration("Soft1", 2, 10000,
                interests, 2000, 100000);
        bank2 = centralBank.bankRegistration("Soft2", 2, 10000,
                interests, 2000, 100000);
        user1 = bank1.addClient("I", "A", "A", "1234567890");
        user2 = bank2.addClient("I", "A", "", "");
        a1 = bank1.openAccount(user1, TypeOfAccount.Credit,0);
        a2 = bank2.openAccount(user2, TypeOfAccount.Debit,0);
    }

    @Test
    public void Limit_For_Account_ThowExeption() throws Exception {
        Assert.assertThrows(Exception.class, ()->{
                a1.replenishment(10000, bank1);
        a1.withdrawal(5000, bank1);
        a2.replenishment(20000, bank2);
        a2.transfer(15000, a1.getId(), centralBank, bank2, bank1);}
        );
    }

    @Test
    public void Replenishment() throws Exception {
        a2.replenishment(10000, bank2);
        if (a2.getMoney() != 10000)
            Assert.fail("Incorrect replenishment");
    }

    @Test
    public void Withdrawal() throws Exception {
        a1.replenishment(10000, bank1);
        a1.withdrawal(5000, bank1);
        if (a1.getMoney() != 105000)
            Assert.fail("Incorrect withdrawal");
    }


    @Test
    public void Transfer() throws Exception {
        a1.replenishment(10000, bank1);
        a1.withdrawal(5000, bank1);
        a2.replenishment(10000, bank2);
        a1.transfer(2000, a2.getId(), centralBank, bank1, bank2);
        if (a1.getMoney() != 103000 || a2.getMoney() != 12000)
            Assert.fail("Incorrect transfer");
    }
}