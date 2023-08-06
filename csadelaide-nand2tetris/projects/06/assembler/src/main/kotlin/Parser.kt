import java.io.File
import java.io.InputStream

class Parser(filePath: String) {
    enum class COMMAND_TYPE {
        A_COMMAND,
        C_COMMAND,
        L_COMMAND,
        NONE
    }

    var inputLines = mutableListOf<String>()

    var commandIndex: Int = -1

    var binaryIndex: Int = -1

    init {
        val inputStream: InputStream = File(filePath).inputStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        lineList.forEach {
            // コメント除去
            val formattedLine = it.split("//")[0]

            if (formattedLine.isEmpty()) {
                return@forEach
            }
            this.inputLines.add(formattedLine)
        }
    }

    // 入力にまだコマンドが存在するか
    fun hasMoreCommands(): Boolean {
        val nextLine = inputLines.getOrNull(this.commandIndex + 1)
        return nextLine !== null
    }

    // 次のコマンドの読み込み
    fun advance() {
        this.commandIndex++
    }

    // 現コマンドの種類を表す
    fun commandType(): COMMAND_TYPE {
        val command = getCurrentCommand()
        return when {
            command.startsWith("@") -> COMMAND_TYPE.A_COMMAND
            command.startsWith("(") && command.endsWith(")") -> COMMAND_TYPE.L_COMMAND
            else -> COMMAND_TYPE.C_COMMAND
        }
    }

    fun symbol(): String {
        val command = this.getCurrentCommand()
        val commandType = this.commandType()
        if (commandType !== COMMAND_TYPE.L_COMMAND && commandType !== COMMAND_TYPE.A_COMMAND) {
            return ""
        }
        if (commandType === COMMAND_TYPE.L_COMMAND) {
            return command.replace("(", "").replace(")", "")
        }
        return command.replace("@", "").trim()
    }

    fun dest(): String {
        val command = this.getCurrentCommand()
        if (!command.contains("=")) {
            return ""
        }
        return command.split("=")[0].trim()
    }

    fun comp(): String {
        val command = this.getCurrentCommand()
        var comp = command
        if (command.contains(";")) {
            comp = command.split(";")[0].trim()
        }

        if (command.contains("=")) {
            comp = command.split("=")[1].trim()
        }

        return comp
    }

    fun jump(): String {
        val command = this.getCurrentCommand()
        if (!command.contains(";")) {
            return ""
        }
        return command.split(";")[1].trim()
    }

    private final fun getCurrentCommand(): String {
        return inputLines.get(this.commandIndex).trim()
    }

    fun reset() {
        commandIndex = -1
        binaryIndex = -1
    }
}
