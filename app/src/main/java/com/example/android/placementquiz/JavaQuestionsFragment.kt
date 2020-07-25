package com.example.android.placementquiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController

import com.example.android.placementquiz.databinding.FragmentJavaQuestionsBinding


data class javaQuestion(val question : String, val options: List<String>)

class JavaQuestionsFragment : Fragment() {

    val javaQuestionList : MutableList<javaQuestion> = mutableListOf<javaQuestion>(
        javaQuestion(
            "Who is called the father of java",
            listOf<String>("Daniess Richi", "Benjen", "Daniess", "James Gosling")
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
                "D"

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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding : FragmentJavaQuestionsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_java_questions ,container,false)
        generateRandomArray()
        binding.game = this
        var currentQuestionNum :Int   = 0
        setQuestion(currentQuestionNum++)
        var score:Int =0
          binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
       {

            view:View ->

           val checkedID = binding.questionRadioGroup.checkedRadioButtonId
           var answerIndex :Int

           when(checkedID){
               R.id.firstAnswerRadioButton -> answerIndex = 0
               R.id.secondAnswerRadioButton -> answerIndex = 1
               R.id.thirdAnswerRadioButton -> answerIndex = 2
               R.id.fourthAnswerRadioButton -> answerIndex = 3
               else -> answerIndex =-1

           }

           
           if(answerIndex != -1) {

               if(currentQuestion.options[0] == answers[answerIndex]){
                   score++
               }

               if (currentQuestionNum < 5) {
                   //currentQuestion = assignQuestion[randomNumList[i]]
                   binding.questionRadioGroup.clearCheck()
                   setQuestion(currentQuestionNum++)
                   binding.invalidateAll()

               } else {

                   view.findNavController().navigate(JavaQuestionsFragmentDirections.actionJavaQuestionsFragmentToScoreFragment(score))
                  /* Navigation.findNavController(view)*/
                  /*     .navigate(R.id.action_javaQuestionsFragment_to_scoreFragment)*/
               }

               Log.i("score","$score")

           }

           else{
               Toast.makeText(context,"Select one of the options to Continue",Toast.LENGTH_LONG).show()
           }

        }





        return binding.root
    }

    private fun setQuestion(i:Int) {
        currentQuestion = javaQuestionList[randomNumList[i]]
        answers = currentQuestion.options.toMutableList()
        answers.shuffle()
        
    }

    private fun generateRandomArray() {
        var hashSet : HashSet<Int> = HashSet<Int>()
        randomNumList = ArrayList<Int>()

        while(randomNumList.size!= 5){
        var curr = (0..7).random()
            if( hashSet.contains(curr)==false )  {
                randomNumList.add(curr)
                hashSet.add(curr)
            }
    }

        for( a in randomNumList){
                 Log.i("$a","I am goo")
        }

    }


}
