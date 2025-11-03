package Assignment01

fun main(args: Array<String>) {
    var n: Int? = null
    while (n == null) {
        println("Nhập số phân số: ")
        n = readln().toInt()
    }
    val list: List<Frag> = List(n) { Frag() };

    for (i in 0..n - 1) {
        list[i].input();
    }

    println("In phân số")
    for (i in 0..n - 1) {
        list[i].print();
    }

    println("Tối giản phân số")
    for (i in 0..n - 1) {
        list[i].reduce();
    }
    for (i in 0..n - 1) {
        list[i].print();
    }

    println("Tổng các phân số")
    val sum = Frag();
    sum.n = list[0].n;
    sum.d = list[0].d;
    for (i in 1..n - 1) {
        sum.add(list[i]);
    }
    sum.print();

    println("Phân số lớn nhất")
    var maxFrag = list[0];
    for (i in 0..n - 1) {
        if (list[i].compare(maxFrag) == 1) {
            maxFrag = list[i];
        }
    }
    maxFrag.print();

    println("Sắp xếp giảm dần")
    val sortedList = list.sortedWith { o1, o2 ->
        -o1.compare(o2) // thêm dấu - để đảo ngược
    }

    for (i in 0.. n-1) {
        sortedList[i].print()
    }
}