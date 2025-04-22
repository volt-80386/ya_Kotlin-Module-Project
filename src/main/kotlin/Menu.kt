import java.util.Scanner

abstract class Menu<T>(private val title: String, private val isMainMenu: Boolean = false) {
    private val items = mutableListOf<Pair<String, () -> Unit>>()
    protected val scanner = Scanner(System.`in`)
    protected val data = mutableListOf<T>()

    protected fun addItem(description: String, action: () -> Unit) {
        items.add(description to action)
    }

    private fun showMenu() {
        println("\n$title:")
        items.forEachIndexed { index, pair ->
            println("${index + 1}. ${pair.first}")
        }
        println("0. ${if (isMainMenu) "Выход" else "Назад"}")
    }

    protected fun getUserChoice(): Int? {
        print("Выберите пункт меню: ")
        return try {
            val input = scanner.nextLine()
            if (input.isEmpty()) {
                println("Введите число от 0 до ${items.size}")
                null
            } else {
                val choice = input.toInt()
                if (choice !in 0..items.size) {
                    println("Число должно быть от 0 до ${items.size}")
                    null
                } else {
                    choice
                }
            }
        } catch (e: NumberFormatException) {
            println("Некорректный ввод. Введите число от 0 до ${items.size}")
            null
        }
    }

    open fun show() {
        while (true) {
            showMenu()
            when (val choice = getUserChoice()) {
                0 -> return
                null -> continue
                else -> items[choice - 1].second.invoke()
            }
        }
    }

    protected fun promptForNonEmptyInput(prompt: String): String {
        while (true) {
            print(prompt)
            val input = scanner.nextLine().trim()
            if (input.isNotEmpty()) {
                return input
            }
            println("Поле не может быть пустым. Попробуйте снова")
        }
    }
}