class ArchiveMenu : Menu<NoteMenu>("Список архивов", true) {
    init {
        addItem("Создать архив") { createArchive() }
        addItem("Открыть архив") { openArchive() }
    }

    private fun createArchive() {
        val name = promptForNonEmptyInput("Введите название архива: ")
        data.add(NoteMenu(name))
        println("Архив '$name' создан")
    }

    private fun openArchive() {
        if (data.isEmpty()) {
            println("Нет доступных архивов")
            return
        }

        println("\nВыберите архив:")
        data.forEachIndexed { index, archive -> println("${index + 1}. ${archive.title}") }
        println("0. Назад")

        when (val choice = getUserChoice()) {
            0 -> return
            null -> openArchive()
            else -> data[choice - 1].show()
        }
    }
}