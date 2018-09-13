package cs544.sdi2;

public class InventoryService implements IInventoryService {
    public int getNumberInStock(int productNumber) {
        return productNumber - 200;
    }
}
