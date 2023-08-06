class SymbolTable() {
    var table = mutableMapOf<String, Int>()

    init {
        table.putAll(mapOf(
            "R0" to 0,
            "R1" to 1,
            "R2" to 2,
            "R3" to 3,
            "R4" to 4,
            "R5" to 5,
            "R6" to 6,
            "R7" to 7,
            "R8" to 8,
            "R9" to 9,
            "R10" to 10,
            "R11" to 11,
            "R12" to 12,
            "R13" to 13,
            "R14" to 14,
            "R15" to 15,
            "SP" to 0,
            "LCL" to 1,
            "ARG" to 2,
            "THIS" to 3,
            "THAT" to 4,
            "SCREEN" to 16384,
            "KBD" to 24576
        ))
    }

    // 保存開始アドレス
    var index = 16

    fun addEntry(symbol: String, address: Int) {
        table[symbol] = address
    }

    fun addEntry(symbol: String) {
        table[symbol] = this.index++
    }

    fun contains(symbol: String): Boolean {
        return table.contains(symbol)
    }

    fun getAddress(symbol: String): Int {
        return table.get(symbol)!!
    }
}