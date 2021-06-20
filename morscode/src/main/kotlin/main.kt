package solution

object StockList {
    fun stockSummary(lstOfArt: Array<String>, lstOfCat: Array<String>): String {
        val stock = mutableListOf<String>()
        var tempString: String
        var quan: Int
        var total: Int = 0
        for (l in lstOfCat) {
            var q: Int = 0
            for (art in lstOfArt) {
                val cat: String = art[0].toString()
                if (cat == l) {
                    quan = art.substringAfter(" ").toInt()
                    q += quan
                    total += quan
                }
            }
            tempString = "($l : $q)"
            if (lstOfCat.indexOf(l) != lstOfCat.size && lstOfCat.indexOf(l) != 0) stock.add("-")
            stock.add(tempString)

        }
        var ts: String = stock.toString()
        ts = ts.substring(1, ts.length - 1)
        ts = ts.replace(",", "")
        if (lstOfArt.isEmpty() || lstOfCat.isEmpty()) return "" else return ts

    }
}


fun main() {
    println(
        StockList.stockSummary(
            arrayOf("BBAR 150", "CDXE 515", "BKWR 250", "BTSQ 890", "DRTY 600"),
            arrayOf("A", "B", "C", "D")
        )
    )
}