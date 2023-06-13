data class Student(
    val firstName: String = "",
    val lastName: String = "",
    val personalNumber: String = "",
    val profilePicture: String = "",
    val email: String = ""
) {
    fun isPersonalNumberValid(): Boolean {
        return personalNumber.length == 13
    }

    fun isEmailValid(): Boolean {
        return email.contains("@")
    }
}
