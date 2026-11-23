import java.util.ArrayList;
import java.util.List;

class ProductOfNumbers {
    private List<Integer> prefixProducts;

    public ProductOfNumbers() {
        prefixProducts = new ArrayList<>();
        // Initialize with 1 as a base dummy value to simplify multiplication and division
        prefixProducts.add(1);
    }

    public void add(int num) {
        if (num == 0) {
            // Any product including this 0 becomes 0, so reset the prefix list
            prefixProducts.clear();
            prefixProducts.add(1);
        } else {
            int lastProduct = prefixProducts.get(prefixProducts.size() - 1);
            prefixProducts.add(lastProduct * num);
        }
    }

    public int getProduct(int k) {
        int n = prefixProducts.size();
        // If k is greater than or equal to the current list size, 
        // a 0 was encountered within the last k elements
        if (k >= n) {
            return 0;
        }
        return prefixProducts.get(n - 1) / prefixProducts.get(n - 1 - k);
    }
}
