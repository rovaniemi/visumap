package visumap.Structures;
import java.util.Comparator;
import java.util.Objects;

/**
 * @author Rovaniemi.
 * @param <T>
 */


public class MinHeap<T> {

    private Comparator c;
    private T[] heap;

    /**
     * Annetaan comparator
     * @param c tietyn luokan comparator.
     */

    public MinHeap(Comparator c) {
        this.heap = (T[])new Object[1];
        this.c = c;
    }

    /**
     * Olion lisääminen kekoon.
     * @param o olio joka halutaan lisätä.
     */

    public boolean add(T o) {
        int last = findLast();
        if (last + 1 > heap.length - 1) {
            increaseSize();
        }
        heap[last+1] = o;
        upHeapify();
        return true;
    }

    /**
     * Poistaa ja palauttaa viimeisen (pienimmän) olion keosta.
     * @return palauttaa pienimmän olion keosta.
     */

    public T poll() {
        if (findLast() == -1) {
            return null;
        } else if(findLast() < (this.heap.length - 3) / 2){
            reduceSize();
        }
        T out = heap[0];
        heap[0] = heap[findLast()];
        heap[findLast()] = null;
        downHeapify();
        return out;
    }

    /**
     * Keon alaspäin heapify operaatio. Aikavaativuus on O(height) = O(log n)
     */

    private void downHeapify() {
        int i = 0;
        while (true) {

            if (heap[left(i)] == null && heap[right(i)] == null) {
                return;
            }

            if (heap[left(i)] == null) {
                if (c.compare(heap[i], heap[right(i)]) > 0) {
                    swap(right(i), i);
                    i = right(i);
                    continue;
                } else {
                    return;
                }
            }

            if (heap[right(i)] == null) {
                if (c.compare(heap[i], heap[left(i)]) > 0) {
                    swap(left(i), i);
                    i = left(i);
                    continue;
                } else {
                    return;
                }
            }

            int minChild = c.compare(heap[left(i)], heap[right(i)]) < 0 ? left(i): right(i);

            if (c.compare(heap[i], heap[minChild]) > 0) {
                swap(minChild, i);
                i = minChild;
                continue;
            } else {
                return;
            }
        }
    }

    /**
     * Keon ylöspäin heapify operaatio. Aikavaativuus on O(height) = O(log n)
     */

    private void upHeapify() {
        int i = findLast();
        while (true) {
            if (i == 0) {
                return;
            }
            if (c.compare(heap[i], heap[parent(i)]) < 0) {
                swap(parent(i), i);
                i = parent(i);
            } else {
                return;
            }
        }
    }

    /**
     * Apuoperaatio joka etsii keon viimeisimmän olion indeksin.
     * @return viimeisimmän olion indeksi.
     */

    private int findLast() {
        for (int i = 0; i < heap.length; i++) {
            if (heap[i] == null) {
                return i - 1;
            }
        }
        return heap.length - 1;
    }

    /**
     * Apuoperaatio solmujen paikan vaihtamiseen keskenään.
     * @param i solmu i
     * @param j solmu j
     */

    private void swap(int i, int j) {
        T tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    /**
     * Apuoperaatio
     * @param i solmu
     * @return solmun vanhempi
     */

    private int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * Apuoperaatio
     * @param i solmu
     * @return solmun i vasen lapsi.
     */

    private int left(int i) {
        return 2 * i + 1;
    }

    /**
     * Apuoperaatio
     * @param i solmu
     * @return solmun i oikea lapsi.
     */

    private int right(int i) {
        return 2 * i + 2;
    }

    private void increaseSize(){
        T[] newHeap = (T[])new Object[this.heap.length * 2];
        for (int i = 0; i < this.heap.length; i++) {
            newHeap[i] = this.heap[i];
        }
        this.heap = newHeap;
    }

    private void reduceSize(){
        T[] newHeap = (T[])new Object[this.heap.length / 2];
        for (int i = 0; i < newHeap.length; i++) {
            newHeap[i] = this.heap[i];
        }
        this.heap = newHeap;
    }
}