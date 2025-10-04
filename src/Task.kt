/*
 *  Task.kt
 *
 *  Part of the Model in MVC architecture.
 *  Represents a single task entity with its properties and behaviors.
 *  Emphasises encapsulation of task data: title, status, importance, description.
 *  Provides controlled access - getter & setters.
 *  ===============================================================================
 *  ===============================================================================
 */

//  Header also defines primary constructor (Kotlin).

class Task(
    private var _title: String,
    private var _status: String,
    private var _importance: String,
    private var _description: String
) {


    //  Read-only, val is immutable, not updated from outside - easy public accessors.
    val title: String
        get() = _title

    val status: String
        get() = _status

    val importance: String
        get() = _importance

    val description: String
        get() = _description


    //  Setters for mutable fields.
    //  Only way to change fields from outside class.
    fun editTitle(newTitle: String) {
        _title = newTitle
    }

    fun editStatus(newStatus: String) {
        _status = newStatus
    }

    fun editImportance(newImportance: String) {
        _importance = newImportance
    }

    fun editDescription(newDescription: String) {
        _description = newDescription
    }


    //  Overrides the default toString() method inherited from Any - Kotlin superclass.
    //  Customizes how a Task object is represented as a String.
    //  Builds a readable string with all properties of the Task.
    //  Kotlin string templates ($variable) - inserts the property values directly.
    override fun toString(): String {
        return "Title: $title || Status: $status || Importance: $importance || Description: $description"
    }

}
