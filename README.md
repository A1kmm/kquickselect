# kquickselect

kquickselect provides a library for use with Kotlin.

## Usage

```
import com.amxl.kquickselect.Quickselect.findKthBy

fun main() {
  println(mutableListOf(5, 3, 2, 4, 9).findKthBy(3, compareBy { it }))
}
```
