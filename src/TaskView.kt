/*
 *  TaskView.kt
 *
 *  The View component in MVC design pattern.
 *  Responsible for all user interaction and display logic.
 *  Shows menus, prompts user input, and displays messages or task lists.
 *  Contains no business logic or data storage - pure User Interface.
 *  ========================================================================
 *  ========================================================================
 */

class TaskView {

    //  This is the main menu
    fun showMenu() {
        println("\n=== Task Manager ===\n")
        println("1. Create a Task")
        println("2. Edit a Task")
        println("3. Delete a Task")
        println("4. List Tasks")
        println("5. End Session")
    }

    //  Loop that returns Int value.
    //  Runs until valid input is given.
    fun getUserChoice(): Int {
        while (true) {
            print("Enter your choice: ")
            val input = readln()

            // Converts readln() from String to Int.
            val choice = input.toIntOrNull()

            // Checks to see if input is valid
            if (choice != null && choice in 1..5) {
                return choice
            } else {
                println("Sorry, invalid input. Please choose a number between 1 and 5.")
            }
        }
    }

    fun showMessage(message: String) {
        println(message)
    }

    /**
     *  Prompts user to enter input for a specific field.
     *
     *  fieldName - the name of the field to display in the prompt.
     * Returns the user’s input as a String.
     */
    fun promptInput(fieldName: String): String {
        //  Displays the prompt, showing the field name
        print("Enter $fieldName: ")

        // Read a line from the user and return it
        return readln()
    }

    /**
     *  Prints all tasks in a numbered list.
     *
     *  tasks - A list of Task objects to display.
     */
    fun listTasks(tasks: List<Task>) {
        //  Checks if the list is empty
        if (tasks.isEmpty()) {
            println("No tasks available.")
        } else {
            //  Loops through the list with an index
            //  Prints each task with a number starting from 1
            //  $task - calls task.toString() method from 'Any' Kotlin class.
            //  task.toString() was overridden in Task.kt class -
            tasks.forEachIndexed { index, task ->
                println("${index + 1}: $task")
            }
        }
    }


    /**
     *  Prompts the user to select a task index from 1 to maxIndex.
     *  Keeps asking until a valid number is entered.
     *
     *  maxIndex - the maximum valid task number (1-based for the user).
     *  returns - the selected task index as 0-based (for list access).
     */
    fun promptTaskIndex(maxIndex: Int): Int {
        //  Infinite loop — will only exit when a valid input is received.
        while (true) {
            //  Asks user to enter a task number between 1 and maxIndex
            print("Enter task number (1-$maxIndex): ")

            //  Reads the input line and attempt to convert it to an Int.
            //  If conversion fails (user enters non-number), input will be null.
            val input = readln().toIntOrNull()

            //  Validate input: must not be null AND must be in the valid range!
            if (input != null && input in 1..maxIndex) {
                //  Subtract 1 to convert 1-based user input into 0-based index.
                return input - 1
            }

            //  If input is invalid, notifies the user and repeats the loop
            println("Invalid input.")
        }
    }
}