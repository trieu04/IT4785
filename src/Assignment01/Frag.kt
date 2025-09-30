package Assignment01

// class Phân số (Fraction)
class Frag {
    var n: Int = 0 // tử số
    var d: Int = 1 // mẫu số (đặt mặc định là 1 cho an toàn, tránh chia 0)

    // nhập phân số từ bàn phím
    fun input() {
        var inN: Int? = null
        var inD: Int? = null
        while (inN == null) { // lặp cho đến khi nhập được tử số
            print("Nhập tử số: ")
            inN = readLine()?.toInt()
        }
        n = inN

        while (inD == null || inD == 0) { // mẫu số phải khác 0
            print("Nhập mẫu số (khác 0): ")
            inD = readLine()?.toInt()
        }
        d = inD
    }

    // in phân số ra màn hình
    fun print() {
        println("$n/$d")
    }

    // rút gọn phân số
    fun reduce() {
        val g = gcd(Math.abs(n), Math.abs(d)) // tìm ước chung lớn nhất
        n /= g
        d /= g
        // nếu mẫu số âm thì đổi dấu cả tử và mẫu
        if (d < 0) {
            n = -n
            d = -d
        }
    }

    // so sánh 2 phân số
    fun compare(other: Frag): Int {
        // dùng cross-multiply để tránh lỗi chia nguyên
        val left = n * other.d
        val right = other.n * d
        return when {
            left > right -> 1
            left < right -> -1
            else -> 0
        }
    }

    // cộng phân số khác vào phân số hiện tại
    fun add(other: Frag) {
        n = n * other.d + other.n * d // quy đồng tử số
        d = d * other.d               // nhân mẫu số
        reduce()                      // rút gọn kết quả
    }

    // hàm tìm ước chung lớn nhất (Euclid)
    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a else gcd(b, a % b)
    }
}
