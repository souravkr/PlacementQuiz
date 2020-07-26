package com.example.android.placementquiz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.placementquiz.databinding.FragmentJavaQuestionsBinding


data class javaQuestion(val question : String, val options: List<String>)

class JavaQuestionsFragment : Fragment() {

    val javaQuestionList : MutableList<javaQuestion> = mutableListOf<javaQuestion>(
        javaQuestion(
            "Who is called the father of java",
            listOf<String>("Daniess Richi", "Benjen", "Steve Goryi", "James Gosling")
        ),

        javaQuestion(
            "Who is the first Name of Java",
            listOf<String>("The Oak", "Hans", "Oomd", "The Noov")
        ),

        javaQuestion(
            "What is JVM",
            listOf<String>(   "Java Virtual Machine" ,
                "Java VIP Machince",
                "Java Virtual Maintance",
                "Java Version Manupulation"

            )
        ),
        javaQuestion(
            "Which of the following option leads to the portability and security of Java?",
            listOf<String>(
                "Bytecode is executed by JVM",
                "The applet makes the Java code secure and portable",
                "Use of exception handling",
                "Dynamic binding between objects"
            )
        ),
        javaQuestion(
            "The \\u0021 article referred to as a",
            listOf<String>(
                "Unicode escape sequence",
                "Octal escape",
                "Hexadecimal",
                "Line feed"
            )
        ),
        javaQuestion(
            "Which of the following is not a Java features?",
            listOf<String>(
                "Use of pointers",
                "Dynamic",
                "Architecture Neutral",
                "Object-oriented"
            )
        ),
        javaQuestion(
            "_____ is used to find and fix bugs in the Java programs.",
            listOf<String>(
                "JDK",
                "JVM",
                "JRE",
                "JDB"
            )
        ),
        javaQuestion(
            "Which of the following is a valid declaration of a char?",
            listOf<String>(
                "char cr = \\u0223;",
                "char ch = '\\utea';",
                "char ca = 'tea';",
                "char cc = '\\itea';"
            )
        )
    )

    lateinit var randomNumList : ArrayList<Int>
    lateinit var currentQuestion : javaQuestion
    lateinit var answers : MutableList<String>
    val NUMERS_OF_QUESTION = 5
    val QUESTION_IN_DATABASE = 7

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : FragmentJavaQuestionsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_java_questions ,container,false)
        binding.game = this

        //Generate a list of Unique Random numbers (Display those question only)
        generateRandomArray()

        var currentQuestionNum :Int   = 0

        //Set the first Question
        setQuestion(currentQuestionNum++)

        var score: Int = 0

          binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
       {

            view:View ->

           //Get the ID of the checked Radio butoon and if nothing is checked update to -1
           val checkedID = binding.questionRadioGroup.checkedRadioButtonId
           var answerIndex :Int

           //Convert ID value to index value
           when(checkedID){
               R.id.firstAnswerRadioButton -> answerIndex = 0
               R.id.secondAnswerRadioButton -> answerIndex = 1
               R.id.thirdAnswerRadioButton -> answerIndex = 2
               R.id.fourthAnswerRadioButton -> answerIndex = 3
               else -> answerIndex =-1

           }

           //Check if atleast one of them are selected (if not display the toast to select one)
           if(answerIndex != -1) {

               //Check if the option value matches the value to the value in one index which is the correct
               // and if yes update the score
               if(currentQuestion.options[0] == answers[answerIndex]){
                   score++
               }

               //Update the question and before that check if weather 5 of them are displayed
               if (currentQuestionNum < NUMERS_OF_QUESTION) {
                   //Clear the checked button
                   binding.questionRadioGroup.clearCheck()
                   setQuestion(currentQuestionNum++)

                   //Invalidate all to reload the fragment to the new value
                   binding.invalidateAll()

               } else {

                   //Navigate to the score Fragment with the score value
                   view.findNavController().navigate(JavaQuestionsFragmentDirections.actionJavaQuestionsFragmentToScoreFragment(score))

               }

               Log.i("score","$score")

           } else {
               Toast.makeText(context,"Select one of the options to Continue",Toast.LENGTH_LONG).show()
           }

        }

        return binding.root
    }

    private fun setQuestion(i:Int) {
        currentQuestion = javaQuestionList[randomNumList[i]]

        //make a copy of the options in the answer value and then suffle it
        answers = currentQuestion.options.toMutableList()
        answers.shuffle()
        
    }

    private fun generateRandomArray() {
        var hashSet : HashSet<Int> = HashSet<Int>()
        randomNumList = ArrayList<Int>()

        while (randomNumList.size != NUMERS_OF_QUESTION) {
            var curr = (0 until javaQuestionList.size).random()
            if( hashSet.contains(curr)==false )  {
                randomNumList.add(curr)
                hashSet.add(curr)
            }
    }
    }


}
