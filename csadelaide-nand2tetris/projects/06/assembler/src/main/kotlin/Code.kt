class Code() {
    companion object {
        fun dest(nimonic: String): String {
            var d = ""

            d += if (nimonic.contains("A")) {
                "1"
            } else {
                "0"
            }

            d += if (nimonic.contains("D")) {
                "1"
            } else {
                "0"
            }

            d += if (nimonic.contains("M")) {
                "1"
            } else {
                "0"
            }

            return d
        }

        fun comp(nimonic: String): String {
            val map = mapOf(
                "0" to "0101010",
                "1" to "0111111",
                "-1" to "0111010",
                "D" to "0001100",
                "A" to "0110000",
                "!D" to "0001101",
                "!A" to "0110001",
                "-D" to "0001111",
                "-A" to "0110011",
                "D+1" to "0011111",
                "A+1" to "0110111",
                "D-1" to "0001110",
                "A-1" to "0110010",
                "D+A" to "0000010",
                "D-A" to "0010011",
                "A-D" to "0000111",
                "D&A" to "0000000",
                "D|A" to "0010101",
                "M" to "1110000",
                "!M" to "1110001",
                "-M" to "1110011",
                "M+1" to "1110111",
                "M-1" to "1110010",
                "D+M" to "1000010",
                "D-M" to "1010011",
                "M-D" to "1000111",
                "D&M" to "1000000",
                "D|M" to "1010101"
            )

            return map.get(nimonic)!!
        }

        fun jump(nimonic: String): String {
            return when (nimonic) {
                "JGT" -> "001"
                "JEQ" -> "010"
                "JGE" -> "011"
                "JLT" -> "100"
                "JNE" -> "101"
                "JLE" -> "110"
                "JMP" -> "111"
                else -> "000"
            }
        }

    }
}