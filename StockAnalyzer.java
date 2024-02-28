import java.util.ArrayList;

public class StockAnalyzer {

    // Calculate the average stock price
    public static double calculateAveragePrice(float[] stockPrices) {
        double sum = 0;

        for (float price : stockPrices) {
            sum += price;
        }

        return sum / stockPrices.length;
    }

    // Find the maximum stock price
    public static float findMaximumPrice(float[] stockPrices) {
        float maxPrice = stockPrices[0];

        for (float price : stockPrices) {
            if (price > maxPrice) {
                maxPrice = price;
            }
        }

        return maxPrice;
    }

    // Determine the occurrence count of a specific price
    public static int countOccurrences(float[] stockPrices, float targetPrice) {
        int count = 0;

        for (float price : stockPrices) {
            if (price == targetPrice) {
                count++;
            }
        }

        return count;
    }

    // Compute the cumulative sum of stock prices for ArrayList
    public static ArrayList<Float> computeCumulativeSum(ArrayList<Float> stockPrices) {
        ArrayList<Float> cumulativeSum = new ArrayList<>();
        float sum = 0;

        for (float price : stockPrices) {
            sum += price;
            cumulativeSum.add(sum);
        }

        return cumulativeSum;
    }

    public static void main(String[] args) {
        // Example usage
        float[] stockPricesArray = {10.5f, 11.2f, 10.8f, 12.0f, 11.5f, 10.2f, 12.5f, 13.0f, 12.8f, 11.9f};

        ArrayList<Float> stockPricesArrayList = new ArrayList<>();
        for (float price : stockPricesArray) {
            stockPricesArrayList.add(price);
        }

        // Calculate average price
        System.out.println("Average Price: " + calculateAveragePrice(stockPricesArray));

        // Find maximum price
        System.out.println("Maximum Price: " + findMaximumPrice(stockPricesArray));

        // Count occurrences of a specific price
        float targetPrice = 11.5f;
        System.out.println("Occurrences of " + targetPrice + ": " + countOccurrences(stockPricesArray, targetPrice));

        // Compute cumulative sum for ArrayList
        ArrayList<Float> cumulativeSumList = computeCumulativeSum(stockPricesArrayList);
        System.out.println("Cumulative Sum: " + cumulativeSumList);
    }
}
