import cn.com.scooper.commonG.extend.copyBean
import cn.com.scooper.commonG.extend.copyBeanBatch
import cn.com.scooper.commonG.extend.copyBeanBatchCallback
import cn.com.scooper.commonG.extend.copyNotNullFieldTo
import org.junit.Test

class TestCopyBean {
    @Test
    fun base() {
        val b = A("v1", "v2").copyBean(B::class.java)
        assert(b.v1 == "v1")
        assert(b.v2 == "v2")
        assert(b.v3 == null)
    }


    @Test
    fun list() {
        val Bs = listOf(
                A("1", "2"),
                A("2", "3"),
                A("4", "2")
        ).copyBeanBatch(B::class.java)
        assert(Bs[0].takeIf { it.v1 == "1" && it.v2 == "2" && it.v3 == null } != null)
        assert(Bs[1].takeIf { it.v1 == "2" && it.v2 == "3" && it.v3 == null } != null)
        assert(Bs[2].takeIf { it.v1 == "4" && it.v2 == "2" && it.v3 == null } != null)
    }


    @Test
    fun callback() {
        val Bs = listOf(
                A("1", "2"),
                A("2", "3"),
                A("4", "2")
        ).copyBeanBatchCallback(B::class.java) { a, b ->
            b.v3 = a.v1
        }
        assert(Bs[0].takeIf { it.v1 == "1" && it.v2 == "2" && it.v3 == "1" } != null)
        assert(Bs[1].takeIf { it.v1 == "2" && it.v2 == "3" && it.v3 == "2" } != null)
        assert(Bs[2].takeIf { it.v1 == "4" && it.v2 == "2" && it.v3 == "4" } != null)
    }

    @Test
    fun copyLink() {
        val b = B("v1", null, "v3")
        val a = A("v1-rep", "v2-rel")
        b.copyNotNullFieldTo(a)
        assert(a.v1 == "v1")
        assert(a.v2 == "v2-rel")
    }
}


class A(
        var v1: String? = null,
        var v2: String? = null
)

class B(
        var v1: String? = null,
        var v2: String? = null,
        var v3: String? = null
)
