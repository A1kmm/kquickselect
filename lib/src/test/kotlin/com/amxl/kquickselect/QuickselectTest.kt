package com.amxl.kquickselect

import com.amxl.kquickselect.Quickselect.findKthBy
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.pair
import io.kotest.property.checkAll

internal fun <T> List<T>.slowReferenceSelectBy(n: Int, comparator: Comparator<T>): T? =
    if (n < 0 || n >= size)
        null
    else
        sortedWith(comparator)[n]

class QuickselectTest : FunSpec({
    test("generates the same results as the reference implementation") {
        // A list of ints, and an index in or just outside it (or a negative of it).
        val arbCase: Arb<Pair<List<Int>, Int>> =
            Arb.pair(Arb.list(Arb.int()), Arb.int()).map {
                Pair(it.first, it.second % (it.first.size + 2))
            }
        checkAll(arbCase) {
            it.first.toMutableList().findKthBy(it.second, compareBy { it -> it }) shouldBe
                    it.first.slowReferenceSelectBy(it.second, compareBy { it -> it })

        }
    }
    test("swaps but doesn't otherwise change list") {
        // A list of ints, and an index in or just outside it (or a negative of it).
        val arbCase: Arb<Pair<List<Int>, Int>> =
            Arb.pair(Arb.list(Arb.int()), Arb.int()).map {
                Pair(it.first, it.second % (it.first.size + 2))
            }
        checkAll(arbCase) {
            val mutableList = it.first.toMutableList()
            mutableList.findKthBy(it.second, compareBy { it -> it })
            mutableList.sort()
            mutableList.toList() shouldBe it.first.sorted()
        }
    }
})