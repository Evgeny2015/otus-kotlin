import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class `test-first` {
    @Test
    fun firstTest() {
        assertEquals(5, 2 + 3)
    }

    @Test
    fun secondTest() {
        assertNotEquals(5, 2 + 2)
    }
}