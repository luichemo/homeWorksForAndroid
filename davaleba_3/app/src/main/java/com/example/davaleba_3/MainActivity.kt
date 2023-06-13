import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.davaleba_3.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var personalNumberEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var submitButton: Button

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstNameEditText = findViewById(R.id.firstNameEditText)
        lastNameEditText = findViewById(R.id.lastNameEditText)
        personalNumberEditText = findViewById(R.id.personalNumberEditText)
        emailEditText = findViewById(R.id.emailEditText)
        submitButton = findViewById(R.id.submitButton)

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().reference.child("school")

        submitButton.setOnClickListener {
            val firstName = firstNameEditText.text.toString().trim()
            val lastName = lastNameEditText.text.toString().trim()
            val personalNumber = personalNumberEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()

            if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) ||
                TextUtils.isEmpty(personalNumber) || TextUtils.isEmpty(email)
            ) {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            } else if (personalNumber.length != 13) {
                Toast.makeText(this, "Personal number should be 13 digits long", Toast.LENGTH_SHORT).show()
            } else if (!email.contains("@")) {
                Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show()
            } else {
                val student = Student(firstName, lastName, personalNumber, "", email)
                saveStudentData(student)
            }
        }
    }

    private fun saveStudentData(student: Student) {
        val studentId = databaseReference.push().key
        if (studentId != null) {
            databaseReference.child(studentId).setValue(student)
                .addOnSuccessListener {
                    Toast.makeText(this, "Student data saved successfully", Toast.LENGTH_SHORT).show()
                    clearFields()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to save student data", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "Failed to generate student ID", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearFields() {
        firstNameEditText.text.clear()
        lastNameEditText.text.clear()
        personalNumberEditText.text.clear()
        emailEditText.text.clear()
    }
}
