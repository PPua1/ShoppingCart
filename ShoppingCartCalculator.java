import java.util.ArrayList;

public class ShoppingCartCalculator {

    /**
     * ตะกร้าสินค้า E commerce เพื่อคำนวนราคาสินค้า 
     * รหัสสินค้า NORMAL , BOKO , BULK เพื่อตั้งค่าเงื่อนไข
     *         - NORMAL คือการซื้อธรรมดา ไม่มีโปรโมชั่น
     *         - BOKO  เป็นรหัสสินค้าที่มีโปร 1 แถม 1 
     *         - BULK  เมื่อซื้่อ 6 ชิ้นขึ้นไปจะได้รับส่วนลด 10 % จากราคารวม
     * @param  items เป็นสินค้าที่ถูกเลือกลงตะกร้า ซึ่งจะมีข้อมูล คือ รหัสสินค้า ชื่อสินค้า ราคาต่อชิ้น  และ จำนวนชิ้นที่ซื้อ
     * @return Total สรุปยอดราคาที่ถูกคิดคำนวณผ่านแต่ละโปรโมชั่นแล้ว 
     */
    public static double calculateTotalPrice(ArrayList<CartItem> items) {
        // TODO: เขียนโค้ด Implementation ที่นี่
        double Total = 0;
        double SubTotal = 0;

        if(items == null){  // ตรวจตะกร้า null
            return Total = 0.0;
        }

        if(items.isEmpty()){ // ตรวจตะกร้าว่าง
            return Total = 0.0;
        }

        for (CartItem item : items){  // ลูปที่เอาArray มาวนเก็บค่าแต่ละชนิดมา
            String SKU = item.sku();
            // String Name = item.name(); ไม่ได้ใช้ตอนนี้ ไม่จำเป็นต้องตครวจสอบว่าเป็นสินค้าอะไร
            double price = item.price();
            int quantity = item.quantity();

            // ต้องเขียนให้บวกราคาของย่อย (Subtotal) ในแต่ละประเภทสินค้าไว้ก่อน แล้วค่อยคืนค่า Total รวมทั้งหมด
            // อันนี้แยกประเภทที่มีโปรโมชั่น เพื่แคำนวณ ราคา ให้บวกทุกอย่างในค่า Subtotal ก่อน แล้วค่อยเอาเข้าไปบวกรวมราคาทั้งหมด
            switch (SKU) {
                case "BOKO" : if((quantity%2) == 0){  // กรณีซื้อโปร 1 แถม 1 
                    SubTotal = (price * quantity ) /2;
                }else {
                    SubTotal = (price * ((quantity/2)+1));
                }
                    break;
                case "BULK" :   if(quantity >= 6 ){ // กรณี ซื้อ 6 ชิ้นขึ้นไป 
                                double cost = price * quantity ;
                                SubTotal = cost - (0.1*cost);
                                }else {
                                    SubTotal =  price * quantity ;
                                }
                    break;
                default: SubTotal =  price * quantity ;
                    break;
        }
            Total += SubTotal;
        }
        return Total ;



    }

}