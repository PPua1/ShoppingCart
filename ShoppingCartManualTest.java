import java.lang.reflect.Array;
import java.util.ArrayList;

public class ShoppingCartManualTest {

    public static void run() {
        System.out.println("--- Starting Shopping Cart Calculator Tests ---");
        System.out.println(); // for spacing

        int passedCount = 0;
        int failedCount = 0;

        // Test 1: ตะกร้าเป็น null
        try {
            double total1 = ShoppingCartCalculator.calculateTotalPrice(null);
            if (total1 == 0.0) {
                System.out.println("PASSED: Null cart should return 0.0");
                passedCount++;
            } else {
                System.out.println("FAILED: Null cart expected 0.0 but got " + total1);
                failedCount++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: Null cart caused an exception: " + e.getMessage());
            failedCount++;
        }

        // Test 2: ตะกร้าว่าง
        ArrayList<CartItem> emptyCart = new ArrayList<>();
        double total2 = ShoppingCartCalculator.calculateTotalPrice(emptyCart);
        if (total2 == 0.0) {
            System.out.println("PASSED: Empty cart should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: Empty cart expected 0.0 but got " + total2);
            failedCount++;
        }

        // Test 3: คำนวณปกติ ไม่มีส่วนลด
        ArrayList<CartItem> simpleCart = new ArrayList<>();
        simpleCart.add(new CartItem("NORMAL", "Bread", 25.0, 2)); // 50
        simpleCart.add(new CartItem("NORMAL", "Milk", 15.0, 1));      // 15
        double total3 = ShoppingCartCalculator.calculateTotalPrice(simpleCart);
        if (total3 == 65.0) {
            System.out.println("PASSED: Simple cart total is correct (65.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 65.0 but got " + total3);
            failedCount++;
        }

        // Test 4: คำนวณแบบ ซื้อ1 แถม 1 หรือ ซื้อ 2 แถม 2 หรือ  ซื้อ3 จ่าย 2 หรือ ซื้อ 4 จ่าย 2
        ArrayList<CartItem> BokoCart = new ArrayList<>();
        BokoCart.add(new CartItem("BOKO", "Sandwich", 15, 2)); // 15
        BokoCart.add(new CartItem("BOKO", "Milk", 30, 3)); //  60
        BokoCart.add(new CartItem("BOKO", "Pen", 10, 5)); //  30
        double total4 = ShoppingCartCalculator.calculateTotalPrice(BokoCart);
        if (total4 == 105) {
            System.out.println("PASSED: Boko Cart total is correct (105.0)");
            passedCount++;
        }else {
            System.out.println("FAILED: Boko Cart total expected 105.0 but got " + total4);
            failedCount++;
        }

        // Test 5 : เมื่อซื้อครบ 6 ชิ้นขึ้นไป จะลดราคา 10%
        ArrayList<CartItem> BulkCart = new ArrayList<>();
        BulkCart.add(new CartItem("BULK","Candy", 10, 6)); // 60 - 6 = 54
        BulkCart.add(new CartItem("BULK","Water", 20, 2)); // 40
        double total5 = ShoppingCartCalculator.calculateTotalPrice(BulkCart);
        if (total5 == 94.0) {
            System.out.println("PASSED: Bulk Cart total is correct (94.0)");
            passedCount++;
        }else {
            System.out.println("FAILED: Bulk Cart total expected 94.0 but got " + total5);
            failedCount++;
        }

        // Test 6: มีทั้ง สามแบบ 
        ArrayList<CartItem> MixCart = new ArrayList<>();
        MixCart.add(new CartItem("NORMAL", "Milk", 15, 1)); //15
        MixCart.add(new CartItem("BOKO", "Sandwich", 15, 2)); // 15
        MixCart.add(new CartItem("BULK","Candy", 10, 6)); //54     all = 84
        double total6 = ShoppingCartCalculator.calculateTotalPrice(MixCart);
        if (total6 == 84.0) {
            System.out.println("PASSED: Mix Cart total is correct (84.0)");
            passedCount++;
        }else {
            System.out.println("FAILED: Mix Cart total expected 84.0 but got " + total6);
            failedCount++;
        }

        // Test 7:  ซื้อของที่เข้าร่วมโปรโมชั่น แต่ไม่ถึงเงื่อนไขที่กำหนด
        ArrayList<CartItem> LessCart = new ArrayList<>();
        LessCart.add(new CartItem("BOKO", "Sandwich", 15, 1)); // 15
        LessCart.add(new CartItem("BULK","Candy", 10, 5)); // 50
        double total7 = ShoppingCartCalculator.calculateTotalPrice(LessCart);
        if (total7 == 65.0) {
            System.out.println("PASSED: LessCart total is correct (65.0)");
            passedCount++;
        }else {
            System.out.println("FAILED: LessCart total expected 84.0 but got " + total7);
            failedCount++;
        }



        // --- Test Summary ---
        System.out.println("\n--------------------");
        System.out.println("--- Test Summary ---");
        System.out.println("Passed: " + passedCount + ", Failed: " + failedCount);
        if (failedCount == 0) {
            System.out.println("Excellent! All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}
