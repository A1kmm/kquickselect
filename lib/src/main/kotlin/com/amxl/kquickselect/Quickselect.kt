package com.amxl.kquickselect

import kotlin.random.Random
import kotlin.random.nextInt

object Quickselect {
    internal fun <T> MutableList<T>.swap(a: Int, b: Int) {
        val tmp = this[a]
        this[a] = this[b]
        this[b] = tmp
    }

    /** Partitions a list by a selected pivot, using a provided comparator.
     * Uses Hoare partition scheme.
     * @return The final index of the pivot
     * @author Andrew Miller
     */
    fun <T> MutableList<T>.partitionListBy(left: Int, right: Int, pivotIndex: Int, comparator: Comparator<T>): Int {
        val pivot = this[pivotIndex]
        swap(pivotIndex, right)
        var storeIdx = left
        for (i in left..<right) {
            if (comparator.compare(this[i], pivot) < 0) {
                swap(storeIdx, i)
                storeIdx++
            }
        }
        swap(right, storeIdx)
        return storeIdx
    }

    /**
     * Finds the kth highest value, by a provided comparator, using the
     * quickselect algorithm - O(this.size) average time.
     * Swaps elements in the array in the process.
     * @author Andrew Miller
     * @return The value of the nth highest element.
     */
    fun <T> MutableList<T>.findKthBy(
        k: Int,
        comparator: Comparator<T>,
        left: Int = 0, right: Int = this.size - 1,
    ): T? {
        if (left >= this.size || right >= this.size || left < 0 || right < left || k < 0 ||
            k > (right - left)) {
            return null
        }
        var leftIdx = left
        var rightIdx = right
        while (true) {
            if (leftIdx == rightIdx) {
                return this[leftIdx]
            }
            var pivotIndex = Random.nextInt(leftIdx..rightIdx)
            pivotIndex = partitionListBy(leftIdx, rightIdx, pivotIndex, comparator)
            if (pivotIndex == k)
                return this[k]
            else if (pivotIndex > k)
                rightIdx = pivotIndex - 1
            else
                leftIdx = pivotIndex + 1
        }
    }
}