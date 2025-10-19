/*
 *  TaskController.kt
 *
 *  The Controller component in MVC design pattern.
 *  Acts as the intermediary between the View and the Model.
 *  Interprets user actions from View, manipulates data via Model,
 *  and updates the View accordingly.
 *
 *  Contains the main program loop and business logic for the Task Manager.
 *  =========================================================================
 *  =========================================================================
 */

//  Header defines primary TaskController constructor; requires TaskView object.
//  Private TaskView object - only accessible by TaskController.

//  The TaskController class handles user input and orchestrates the flow between
//  the view (TaskView) and the model/repository (TaskRepository).
class TaskController(private val _view: TaskView) {

    //  Private repository field stores all tasks.
    private val _repository = TaskRepository()

    //  Main loop that runs the application until the user selects "End Session".
    fun run() {
        var running = true
        while (running) {
            _view.showMenu()                   // Display main menu
            when (_view.getUserChoice()) {     // Get valid user choice
                1 -> createTask()             // Option 1: Create a new task
                2 -> editTask()               // Option 2: Edit an existing task
                3 -> deleteTask()             // Option 3: Delete a task
                4 -> listTasks()              // Option 4: List all tasks
                5 -> {                        // Option 5: End the session
                    _view.showMessage("Ending session.. Goodbye!")
                    running = false
                }
            }
        }
    }

    //  Creates a new task by prompting the user for details and storing it in the repository.
    private fun createTask() {
        val title = _view.promptInput("Title")
        val status = _view.promptInput("Status (Pending, Completed, etc)")
        val importance = _view.promptInput("Importance (Low, Med, High)")
        val description = _view.promptInput("Description")

        //  Populates the Task constructor.
        val task = Task(title, status, importance, description)

        //  Now adds new task to the private repository.
        _repository.addTask(task)

        //  Displays a success message.
        _view.showMessage("Task created successfully!")
    }

    //  Allows the user to edit an existing task.
    private fun editTask() {
        //  Checks to see if there are any tasks to edit.
        if (_repository.taskCount() == 0) {
            _view.showMessage("No tasks to edit.")
            return
        }

        //  If so, first lists tasks with indices.
        listTasks()
        //  Converts user index to 0-base for use in a list.
        val index = _view.promptTaskIndex(_repository.taskCount())
        //  Selects specified task - readies Task object for update.
        val task = _repository.getTask(index) ?: return

        //  Summons setters from Task object.
        //  Prompts user for new Task values using TaskView object function.
        task.editTitle(_view.promptInput("New Title"))
        task.editStatus(_view.promptInput("New Status (Pending, Completed, etc)"))
        task.editImportance(_view.promptInput("New Importance (Low, Med, High)"))
        task.editDescription(_view.promptInput("New Description"))

        _view.showMessage("Task updated successfully!")
    }

    //  Deletes a task selected by the user
    private fun deleteTask() {
        //  Checks to see if there are any tasks to delete.
        if (_repository.taskCount() == 0) {
            _view.showMessage("No tasks to delete.")
            return
        }

        //  If so, first lists current tasks.
        listTasks()
        //  Converts user index to 0-base for use in a list.
        val index = _view.promptTaskIndex(_repository.taskCount())

        //  Uses 0-base index to delete task - gives feedback.
        if (_repository.deleteTask(index)) {
            _view.showMessage("Task deleted successfully!")
        } else {
            _view.showMessage("Failed to delete task.")
        }
    }

    //  Lists all tasks currently stored in the repository
    private fun listTasks() {
        //  Gets a read-only copy of all tasks.
        val tasks = _repository.getAllTasks()
        //  Assigns view to print.
        //  Separation of concerns.
        _view.listTasks(tasks)
    }
}
