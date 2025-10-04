/*
 *  Main.kt
 *
 *  Entry point of Task Manager application.
 *  Initializes the View and Controller components -
 *  and starts the program loop.
 *  =================================================
 *  =================================================
 */

fun main() {
    // Instantiates the view and controller objects
    val view = TaskView()
    val controller = TaskController(view)

    // Begins the main menu loop - program begins.
    controller.run()
}