import kotlin.math.pow

fun expo(n:Double, gf:Double, t:Int, stijgt:Boolean):Int{
    var q:Double = 0.0
    when(stijgt){
        true -> {
            for( i in 1..t){
                q = (n*(gf.pow(i)))
                println(q)
            }
        }
        false -> {
            for( i in 1..t){
                q = (n/(gf.pow(i)))
                println(q)
            }
        }
    }

    return q.toInt()
}


fun main() {
    expo(4851.939283014644,1.075,10, true)
}