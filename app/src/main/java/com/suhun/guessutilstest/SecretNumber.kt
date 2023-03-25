package com.suhun.guessutilstest

import android.content.res.Resources
import android.util.Log
import java.util.Random

class SecretNumber {
    val tag:String = SecretNumber::class.java.simpleName
    var secretRandom:Int = 0
    var guessCounter:Int = 0
    init{
        secretRandom = Random().nextInt(100) + 1
        Log.d(tag, "Secret number is $secretRandom")
    }

    fun verify(userInput:Int) = secretRandom - userInput

    fun verifyResult(r:Resources, userInput:Int):String{
        guessCounter++
        if(verify(userInput) > 0){
            return r.getString(R.string.bigger)
        }else if(verify(userInput) < 0){
            return r.getString(R.string.smaller)
        }else{
            if(guessCounter < 3){
                return r.getString(R.string.excellent) + secretRandom.toString()
            }else{
                return r.getString(R.string.you_got_it)
            }
        }
    }

    fun resetAll(){
        guessCounter = 0
        secretRandom = Random().nextInt(100) + 1
        Log.d(tag, "Reset secret number is $secretRandom")
    }
}