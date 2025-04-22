class NoteMenu(val title: String) : Menu<Note>("Архив: $title") {
    init {
        addItem("Создать заметку") { createNote() }
        addItem("Открыть заметку") { openNote() }
    }

    private fun createNote() {
        val name = promptForNonEmptyInput("Введите название заметки: ")
        val content = promptForNonEmptyInput("Введите текст заметки: ")
        data.add(Note(name, content))
        println("Заметка '$name' создана")
    }

    private fun openNote() {
        if (data.isEmpty()) {
            println("Нет доступных заметок")
            return
        }

        println("\nВыберите заметку:")
        data.forEachIndexed { index, note -> println("${index + 1}. ${note.title}") }
        println("0. Назад")

        when (val choice = getUserChoice()) {
            0 -> return
            null -> openNote()
            else -> showNote(data[choice - 1])
        }
    }

    private fun showNote(note: Note) {
        println("\nЗаметка: ${note.title}")
        println("Текст: ${note.content}")
        println("\n0. Назад")

        while (true) {
            print("Выберите пункт меню: ")
            when (scanner.nextLine()) {
                "0" -> return
                else -> println("Некорректный ввод. Введите 0 для возврата")
            }
        }
    }
}