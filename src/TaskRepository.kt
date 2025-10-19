/*
 *  TaskRepository.kt
 *
 *  Part of the Model in MVC design pattern.
 *  Manages the collection of Task objects in memory.
 *  Provides CRUD operations (Create, Read, Update, Delete) for tasks.
 *  Ensures encapsulation and data integrity.
 *  ===================================================================
 *  ===================================================================
 */

class TaskRepository {

    //  List that stores all tasks.
    //  Private - can only be accessed inside this class.
    //  Val - Reference to the list cannot be reassigned - contents can change, however.
    //  mutableListOf - list can be modified.
    private val _tasks = mutableListOf<Task>()

    //  Adds a new task to the main list.
    fun addTask(task: Task) {
        _tasks.add(task)
    }

    //  Gets all tasks (read-only list).
    //  .toList() returns a copy - outside code can't modify mutable list.
    fun getAllTasks(): List<Task> = _tasks.toList()

    //  Safely get a single task by index - returns null if index is invalid.
    fun getTask(index: Int): Task? = _tasks.getOrNull(index)

    //  Delete a task by index.
    //  Returns true when deletion succeeded, false when index was invalid.
    //  The controller calls this and loops/re-prompts the user until it returns true.
    fun deleteTask(index: Int): Boolean {
        return if (index in _tasks.indices) {
            _tasks.removeAt(index)
            true
        } else false
    }

    //  Get the number of tasks.
    fun taskCount(): Int = _tasks.size
}
