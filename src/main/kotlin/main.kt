fun printMessage(m: String): Unit { //Unit wilt zeggen dat er geen return is
    println(m)
}

fun printprefix(m: String, prefix: String) { //als je geen return type hebt moet je unit niet persé gbruiken
    println("[$prefix] $m")
}

fun sum(x: Int, y: Int): Int { //telt twee ints op
    return x + y
}

fun mult(x: Double, y: Double) = x * y //returned X*Y als double

infix fun Int.times(str: String) = str.repeat(this)//functie de int keer een string str herhaald

fun listAll(vararg messages: String) {  //print alle strings die je in de functie hebt opgegeven.
    for (m in messages) println(m)
}

fun printAllPrefix(vararg messages: String, prefix: String) { //print alle strings die je in de functie  .
    for (m in messages) println("$prefix $m")                 //hebt opgegeven met de prefix ervoor
}


fun main(args: Array<String>) {
    printMessage("hallo")
    printprefix("hallo", "jood")
    println(sum(3, 2))
    println(mult(1.23, 3.56))
    println(4 times "a \n")
    val pair = "Ferrari" to "Katrina" //to joint de twee strings samen
    println(pair)
    listAll("a", "b", "c")
    printAllPrefix("a", "b", "c", prefix = "hallo")
    var a: String = "test" // veranderbare variable
    val b: Int = 3 // onveranderlijke variable met type
    val c = 4// onveranderlijke type ordt automatisch herkent


}

