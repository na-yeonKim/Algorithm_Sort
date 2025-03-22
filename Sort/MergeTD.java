package Sort;

public class MergeTD extends AbstractSort{
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for(int k = lo; k <= hi; k++)
            aux[k] = a[k];
        int i = lo, j = mid+1;
        for(int k = lo; k <= hi; k++) {
            if(i > mid) a[k] = aux[j++];
            else if(j > hi) a[k] = aux[i++];
            else if(less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);
    }

    // sort에서 a랑 aux바꿀 수도 있음
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        /* if(hi <= lo) return; 대신에
        if(hi <= lo+CUTOFF-1) {
            insertion.sort(a, lo, hi);
            return;
        }
        */
        if(hi <= lo) return;
        int mid = lo + (hi-lo)/2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        // if(less(a[mid], a[mid+1])) return;
        merge(a, aux, lo, mid, hi);
    }

    public static void main(String[] args) {
        Integer[] a = {10, 4, 5, 8, 1, 8, 3, 6};
        MergeTD.sort(a);
        for(int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }
}
