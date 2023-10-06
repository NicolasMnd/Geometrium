package sorting;

import util.helpers.Printer;
import util.math.Segment;

public class DoorlooplijnQuicksort {

    private Printer printer = new Printer();
    private FieldGetter fieldSelector;

    public DoorlooplijnQuicksort() {
        printer.stopPrints();
    }

    public Segment[] sort(Segment[] array) {
        return hoare(array, 0, array.length-1);
    }

    public void setFieldSelector(FieldGetter geter) {
        this.fieldSelector = geter;
    }

    private Segment[] hoare(Segment[] array, int low, int high) {
        printer.print("low: ", low + ", high: ", Integer.toString(high), ".");
        if(high <= low) {
            printer.print("low: ", low + ", high: ", high + ", STOPPED");
            return array;
        }
        int pivot = partitionHoare(array, low, high);
        //System.out.println("Sorteren van " + low + ", tot " + (pivot-1));
        //System.out.println("Sorteren van " + (pivot+1) + ", tot " + (high));
        hoare(array, low, pivot-1);
        hoare(array, pivot+1, high);
        return array;
    }

    public float getValue(Segment seg) {
        return fieldSelector.getFloat(seg);
    }

    private int partitionHoare(Segment[] array, int low, int high) {
        float pivot = getValue(array[low]);
        Segment pivotSegment = array[low];

        // i + 1, since low is the pivot!
        int i = low+1, j = high;

        printer.print("---------- from ", low + " to ", high + " ------------");
        //printer.print(printer.floatString(array), "\n");

        while(i < j) {

            // Search for an element bigger than the pivot starting from low+1
            while (getValue(array[i]) <= pivot && i < high) {
                printer.print("Checking at index ", i + ": element ", array[i] + " <= " + pivot);
                i++;
            }
            if(i >= high) printer.print("Check for i stopped at index " + i + " -> " + array[i] + " > " + pivot);
            else printer.print("i (" + i + ") not < " + high);

            // Search for an element smaller than pivot to switch with i
            while (getValue(array[j]) > pivot && j > low) {
                printer.print("Checking at index " + j + ": element " + array[j] + " > " + pivot);
                j--;
            }
            printer.print("Check for j stopped at index " + j + " -> " + array[j] + " <= " + pivot);

            // Switch both elements. If i >= j, we break from this loop
            //printer.print("Previous: ", printer.floatString(array));

            if(i < j) {
                Segment temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }

            //printer.print("After:    ", printer.floatString(array));

        } // Repeat this process until i >= j

        // If i == j, then the algorithm stopped immediately. Pivot should stay in place
        if(getValue(array[j]) < pivot) {
            // Switch pivot & j or i element
            //printer.print("Pivot change before: ", printer.floatString(array));
            array[low] = array[j];
            array[j] = pivotSegment;
            //printer.print("Pivot change after: ", printer.floatString(array));
        }
        printer.print("\n");

        if(j == low) return j++;
        else return j;

    }

}
