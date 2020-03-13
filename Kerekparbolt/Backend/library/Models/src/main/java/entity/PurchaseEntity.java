package entity;

import enums.Payment;
import org.joda.time.DateTime;

public class PurchaseEntity {
    public int Id;
    public String CustomerUniqId;
    public int ItemId;
    public DateTime Date;
    public Payment PaymentMethod;

    public PurchaseEntity() {
    }

    public PurchaseEntity(int id, String customerUniqId, int itemId, DateTime date, Payment paymentMethod) {
        Id = id;
        CustomerUniqId = customerUniqId;
        ItemId = itemId;
        Date = date;
        PaymentMethod = paymentMethod;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCustomerUniqId() {
        return CustomerUniqId;
    }

    public void setCustomerUniqId(String customerUniqId) {
        CustomerUniqId = customerUniqId;
    }

    public int getItemId() {
        return ItemId;
    }

    public void setItemId(int itemId) {
        ItemId = itemId;
    }

    public DateTime getDate() {
        return Date;
    }

    public void setDate(DateTime date) {
        Date = date;
    }

    public Payment getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(Payment paymentMethod) {
        PaymentMethod = paymentMethod;
    }
}
