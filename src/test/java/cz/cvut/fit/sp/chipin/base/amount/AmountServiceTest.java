package cz.cvut.fit.sp.chipin.base.amount;

import cz.cvut.fit.sp.chipin.authentication.useraccount.UserAccountService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class AmountServiceTest {

    @MockBean
    private UserAccountService userAccountService;

    @InjectMocks
    private AmountService amountService;

//    @Test
//    public void testSetAmountsWithValidEqualInputs_1() throws Exception {
//        UserAccount payer = new UserAccount();
//        UserGroup group = new UserGroup();
//
//        Transaction transaction = new Transaction("Transaction1", 300f, payer, group);
//        List<MemberAbstractRequest> spenders = Arrays.asList(
//                new EqualTransactionMember("spender1"),
//                new EqualTransactionMember("spender2"),
//                new EqualTransactionMember("spender2")
//        );
//
//        try (MockedStatic<AmountSplitterProvider> mocked = mockStatic(AmountSplitterProvider.class)) {
//            when(userAccountService.getUserAccount(anyString())).thenReturn(new UserAccount());
//            mocked.when(() -> AmountSplitterProvider.getStrategy(TransactionType.EQUALLY))
//                    .thenReturn(new EqualAmountCalculator());
//
//            List<Amount> result = amountService.setAmounts(transaction, spenders, TransactionType.EQUALLY);
//
//            Assertions.assertNotNull(result);
//            Assertions.assertEquals(3, result.size());
//            for (Amount amount : result) {
//                Assertions.assertEquals(amount.getAmount(), 100f);
//            }
//        }
//    }
//
//    @Test
//    public void testSetAmountsWithValidEqualInputs_2() throws Exception {
//        UserAccount payer = new UserAccount();
//        UserGroup group = new UserGroup();
//
//        Transaction transaction = new Transaction("Transaction1", 2000f, payer, group);
//        List<MemberAbstractRequest> spenders = Arrays.asList(
//                new EqualTransactionMember("spender1"),
//                new EqualTransactionMember("spender2"),
//                new EqualTransactionMember("spender2")
//        );
//
//        try (MockedStatic<AmountSplitterProvider> mocked = mockStatic(AmountSplitterProvider.class)) {
//            when(userAccountService.getUserAccount(anyString())).thenReturn(new UserAccount());
//            mocked.when(() -> AmountSplitterProvider.getStrategy(TransactionType.EQUALLY))
//                    .thenReturn(new EqualAmountCalculator());
//
//            List<Amount> result = amountService.setAmounts(transaction, spenders, TransactionType.EQUALLY);
//
//            Assertions.assertNotNull(result);
//            Assertions.assertEquals(3, result.size());
//            float epsilon = 0.01f;
//            for (Amount amount : result) {
//                Assertions.assertTrue(Math.abs(amount.getAmount() - 666.66f) < epsilon);
//            }
//        }
//    }
//
//    @Test
//    public void testSetAmountsWithValidUnequalInputs_1() throws Exception {
//        UserAccount payer = new UserAccount();
//        UserGroup group = new UserGroup();
//
//        Transaction transaction = new Transaction("Transaction1", 300f, payer, group);
//        List<MemberAbstractRequest> spenders = Arrays.asList(
//                new UnequalTransactionMember("spender1", 100f),
//                new UnequalTransactionMember("spender2", 200f)
//        );
//
//        try (MockedStatic<AmountSplitterProvider> mocked = mockStatic(AmountSplitterProvider.class)) {
//            when(userAccountService.getUserAccount(anyString())).thenReturn(new UserAccount());
//            mocked.when(() -> AmountSplitterProvider.getStrategy(TransactionType.UNEQUALLY))
//                    .thenReturn(new UnequalAmountCalculator());
//
//            List<Amount> result = amountService.setAmounts(transaction, spenders, TransactionType.UNEQUALLY);
//
//            Assertions.assertNotNull(result);
//            Assertions.assertEquals(2, result.size());
//            Assertions.assertEquals(result.get(0).getAmount(), 100f);
//            Assertions.assertEquals(result.get(1).getAmount(), 200f);
//        }
//    }
//
//    @Test
//    public void testSetAmountsWithValidByAdjustmentInputs_1() throws Exception {
//        UserAccount payer = new UserAccount();
//        UserGroup group = new UserGroup();
//
//        Transaction transaction = new Transaction("Transaction1", 300f, payer, group);
//        List<MemberAbstractRequest> spenders = Arrays.asList(
//                new AdjustmentTransactionMember("spender1", 100f),
//                new AdjustmentTransactionMember("spender2", 0f),
//                new AdjustmentTransactionMember("spender3", 0f)
//        );
//
//        try (MockedStatic<AmountSplitterProvider> mocked = mockStatic(AmountSplitterProvider.class)) {
//            when(userAccountService.getUserAccount(anyString())).thenReturn(new UserAccount());
//            mocked.when(() -> AmountSplitterProvider.getStrategy(TransactionType.BY_ADJUSTMENT))
//                    .thenReturn(new AdjustmentBasedAmountCalculator());
//
//            List<Amount> result = amountService.setAmounts(transaction, spenders, TransactionType.BY_ADJUSTMENT);
//
//            Assertions.assertNotNull(result);
//            Assertions.assertEquals(3, result.size());
//            float epsilon = 0.01f;
//            Assertions.assertTrue(Math.abs(result.get(0).getAmount() - 166.66f) < epsilon);
//            Assertions.assertTrue(Math.abs(result.get(1).getAmount() - 66.66f) < epsilon);
//            Assertions.assertTrue(Math.abs(result.get(2).getAmount() - 66.66f) < epsilon);
//        }
//    }
//
//    @Test
//    public void testSetAmountsWithValidByAdjustmentInputs_2() throws Exception {
//        UserAccount payer = new UserAccount();
//        UserGroup group = new UserGroup();
//
//        Transaction transaction = new Transaction("Transaction1", 300f, payer, group);
//        List<MemberAbstractRequest> spenders = Arrays.asList(
//                new AdjustmentTransactionMember("spender1", 100f),
//                new AdjustmentTransactionMember("spender2", 200f),
//                new AdjustmentTransactionMember("spender3", 300f)
//        );
//
//        try (MockedStatic<AmountSplitterProvider> mocked = mockStatic(AmountSplitterProvider.class)) {
//            when(userAccountService.getUserAccount(anyString())).thenReturn(new UserAccount());
//            mocked.when(() -> AmountSplitterProvider.getStrategy(TransactionType.BY_ADJUSTMENT))
//                    .thenReturn(new AdjustmentBasedAmountCalculator());
//
//            List<Amount> result = amountService.setAmounts(transaction, spenders, TransactionType.BY_ADJUSTMENT);
//
//            Assertions.assertNotNull(result);
//            Assertions.assertEquals(3, result.size());
//            float epsilon = 0.01f;
//            Assertions.assertTrue(Math.abs(result.get(0).getAmount()) < epsilon);
//            Assertions.assertTrue(Math.abs(result.get(1).getAmount() - 100f) < epsilon);
//            Assertions.assertTrue(Math.abs(result.get(2).getAmount() - 200f) < epsilon);
//        }
//    }
//
//    @Test
//    public void testSetAmountsWithValidByAdjustmentInputs_3() throws Exception {
//        UserAccount payer = new UserAccount();
//        UserGroup group = new UserGroup();
//
//        Transaction transaction = new Transaction("Transaction1", 300f, payer, group);
//        List<MemberAbstractRequest> spenders = Arrays.asList(
//                new AdjustmentTransactionMember("spender1", 0f),
//                new AdjustmentTransactionMember("spender2", 200f),
//                new AdjustmentTransactionMember("spender3", 300f)
//        );
//
//        try (MockedStatic<AmountSplitterProvider> mocked = mockStatic(AmountSplitterProvider.class)) {
//            when(userAccountService.getUserAccount(anyString())).thenReturn(new UserAccount());
//            mocked.when(() -> AmountSplitterProvider.getStrategy(TransactionType.BY_ADJUSTMENT))
//                    .thenReturn(new AdjustmentBasedAmountCalculator());
//
//            List<Amount> result = amountService.setAmounts(transaction, spenders, TransactionType.BY_ADJUSTMENT);
//
//            Assertions.assertNotNull(result);
//            Assertions.assertEquals(3, result.size());
//            float epsilon = 0.01f;
//            Assertions.assertTrue(Math.abs(result.get(0).getAmount() + 66.66f) < epsilon);
//            Assertions.assertTrue(Math.abs(result.get(1).getAmount() - 133.33f) < epsilon);
//            Assertions.assertTrue(Math.abs(result.get(2).getAmount() - 233.33f) < epsilon);
//        }
//    }
//
//    @Test
//    public void testSetAmountsWithValidByPercentagesInputs() throws Exception {
//        UserAccount payer = new UserAccount();
//        UserGroup group = new UserGroup();
//
//        Transaction transaction = new Transaction("Transaction1", 300f, payer, group);
//        List<MemberAbstractRequest> spenders = Arrays.asList(
//                new PercentTransactionMember("spender1", 10f),
//                new PercentTransactionMember("spender2", 25f),
//                new PercentTransactionMember("spender3", 65f)
//        );
//
//        try (MockedStatic<AmountSplitterProvider> mocked = mockStatic(AmountSplitterProvider.class)) {
//            when(userAccountService.getUserAccount(anyString())).thenReturn(new UserAccount());
//            mocked.when(() -> AmountSplitterProvider.getStrategy(TransactionType.BY_PERCENTAGES))
//                    .thenReturn(new PercentBasedAmountCalculator());
//
//            List<Amount> result = amountService.setAmounts(transaction, spenders, TransactionType.BY_PERCENTAGES);
//
//            Assertions.assertNotNull(result);
//            Assertions.assertEquals(3, result.size());
//            Assertions.assertEquals(result.get(0).getAmount(), 30f);
//            Assertions.assertEquals(result.get(1).getAmount(), 75f);
//            Assertions.assertEquals(result.get(2).getAmount(), 195f);
//        }
//    }
//
//    @Test
//    public void testSetAmountsWithValidBySharesInputs() throws Exception {
//        UserAccount payer = new UserAccount();
//        UserGroup group = new UserGroup();
//
//        Transaction transaction = new Transaction("Transaction1", 300f, payer, group);
//        List<MemberAbstractRequest> spenders = Arrays.asList(
//                new ShareTransactionMember("spender1", 3f),
//                new ShareTransactionMember("spender2", 8f),
//                new ShareTransactionMember("spender3", 13f)
//        );
//
//        try (MockedStatic<AmountSplitterProvider> mocked = mockStatic(AmountSplitterProvider.class)) {
//            when(userAccountService.getUserAccount(anyString())).thenReturn(new UserAccount());
//            mocked.when(() -> AmountSplitterProvider.getStrategy(TransactionType.BY_SHARES))
//                    .thenReturn(new ShareBasedAmountCalculator());
//
//            List<Amount> result = amountService.setAmounts(transaction, spenders, TransactionType.BY_SHARES);
//
//            Assertions.assertNotNull(result);
//            Assertions.assertEquals(3, result.size());
//            Assertions.assertEquals(result.get(0).getAmount(), 37.5f);
//            Assertions.assertEquals(result.get(1).getAmount(), 100f);
//            Assertions.assertEquals(result.get(2).getAmount(), 162.5f);
//        }
//    }
}
