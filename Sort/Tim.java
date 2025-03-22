package Sort;

import java.util.ArrayList;
import java.util.List;

public class Tim extends AbstractSort {
    private static final int MIN_RUN = 32;

    public static void sort(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[n]; // 병합에 사용할 보조 배열
        List<Run> runs = new ArrayList<>();

        int i = 0;
        while (i < n) {
            int runStart = i;
            i++;

            // run 방향 감지 (증가 또는 감소)
            if (i < n && less(a[i], a[i - 1])) {
                while (i < n && less(a[i], a[i - 1])) i++;
                reverse(a, runStart, i - 1);
            } else {
                while (i < n && !less(a[i], a[i - 1])) i++;
            }

            int runEnd = i - 1;
            int runLen = runEnd - runStart + 1;

            // run이 너무 짧으면 MIN_RUN까지 확장 후 삽입 정렬
            int extendTo = Math.min(runStart + MIN_RUN - 1, n - 1);
            if (runLen < MIN_RUN) {
                Insertion.sort(a, runStart, extendTo);
                runEnd = extendTo;
                i = runEnd + 1;
            }

            runs.add(new Run(runStart, runEnd));
        }

        // run들을 병합
        while (runs.size() > 1) {
            List<Run> merged = new ArrayList<>();
            for (int j = 0; j < runs.size(); j += 2) {
                if (j + 1 < runs.size()) {
                    Run r1 = runs.get(j);
                    Run r2 = runs.get(j + 1);
                    merge(a, aux, r1.start, r1.end, r2.end);
                    merged.add(new Run(r1.start, r2.end));
                } else {
                    merged.add(runs.get(j));
                }
            }
            runs = merged;
        }
    }

    // 삽입 정렬 (범위 지정)
    public static class Insertion extends AbstractSort {
        public static void sort(Comparable[] a, int lo, int hi) {
            for (int i = lo + 1; i <= hi; i++) {
                for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
                    exch(a, j, j - 1);
                }
            }
        }
    }

    // 병합 함수 (보조 배열 사용)
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    // run reverse
    private static void reverse(Comparable[] a, int lo, int hi) {
        while (lo < hi) {
            exch(a, lo++, hi--);
        }
    }

    // run 구간 클래스
    private static class Run {
        int start, end;

        Run(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

    // 테스트 메인
    public static void main(String[] args) {
        Comparable[] a = {10, 4, 5, 2, 1, 8, 3, 6, 12, 13, 11, 9, 14, 15, 7, 20};
        Tim.sort(a);
        show(a);
    }
}