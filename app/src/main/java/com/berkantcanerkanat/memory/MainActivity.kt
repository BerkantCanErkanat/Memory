package com.berkantcanerkanat.memory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import com.berkantcanerkanat.memory.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var myImageArray = arrayOfNulls<MyImage>(16)
    private var ran_index = HashSet<Int>()
    var streak = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initializeVars()
        random()
        changeImages()
        binding.alertText.text = "hafizaya at!!"
        myImageArray.forEach {
            it!!.image.isClickable = false
        }
        object : CountDownTimer(6000,1000){
            override fun onTick(p0: Long) {
                binding.timeText.text = "Left : ${p0/1000}"
            }

            override fun onFinish() {
                default()
                binding.alertText.text = "solve!!"
                myImageArray.forEach {
                    it!!.image.isClickable = true
                }
            }
        }.start()

        binding.doneButton.setOnClickListener {
            val check = check()
            if(check){
                streak++
                binding.streakText.text = "Streak : ${streak}"
                binding.alertText.text = "Well played!!"
            }else{
                binding.alertText.text = "LOST!!"
                streak = 0
                binding.streakText.text = "Streak : ${streak}"
            }
            default()
            random()
            changeImages()
            myImageArray.forEach {
                it!!.image.isClickable = false
            }
            object : CountDownTimer(5000,1000){
                override fun onTick(p0: Long) {
                    binding.timeText.text = "Left : ${p0/1000}"
                    binding.alertText.text = "hafizaya at!!"
                }
                override fun onFinish() {
                    default()
                    myImageArray.forEach {
                        it!!.image.isClickable = true
                    }
                    binding.alertText.text = "solve!!"
                }

            }.start()
        }
    }
    fun initializeVars(){
        myImageArray[0] = MyImage(true,binding.i1,R.drawable.empty)
        myImageArray[1] = MyImage(true,binding.i2,R.drawable.empty)
        myImageArray[2] = MyImage(true,binding.i3,R.drawable.empty)
        myImageArray[3] = MyImage(true,binding.i4,R.drawable.empty)
        myImageArray[4] = MyImage(true,binding.i5,R.drawable.empty)
        myImageArray[5] = MyImage(true,binding.i6,R.drawable.empty)
        myImageArray[6] = MyImage(true,binding.i7,R.drawable.empty)
        myImageArray[7] = MyImage(true,binding.i8,R.drawable.empty)
        myImageArray[8] = MyImage(true,binding.i9,R.drawable.empty)
        myImageArray[9] = MyImage(true,binding.i10,R.drawable.empty)
        myImageArray[10] = MyImage(true,binding.i11,R.drawable.empty)
        myImageArray[11] = MyImage(true,binding.i12,R.drawable.empty)
        myImageArray[12] = MyImage(true,binding.i13,R.drawable.empty)
        myImageArray[13] = MyImage(true,binding.i14,R.drawable.empty)
        myImageArray[14] = MyImage(true,binding.i15,R.drawable.empty)
        myImageArray[15] = MyImage(true,binding.i16,R.drawable.empty)
    }
    fun bastik(view: View){
        for (i in myImageArray.indices){
            if(view.id == myImageArray[i]!!.image.id){
                if(myImageArray[i]!!.ImageInt == R.drawable.empty){
                    myImageArray[i]!!.ImageInt = R.drawable.full
                    myImageArray[i]!!.image.setImageResource(R.drawable.full)
                }else if(myImageArray[i]!!.ImageInt == R.drawable.full){
                    myImageArray[i]!!.ImageInt = R.drawable.empty
                    myImageArray[i]!!.image.setImageResource(R.drawable.empty)
                }
            }
        }
    }
    fun random(){
        ran_index = HashSet<Int>()
        for(i in 1..6){
            ran_index.add(Random.nextInt(16))
        }
    }

    fun changeImages(){
        for(i in ran_index){
            myImageArray[i]!!.image.setImageResource(R.drawable.full)
            myImageArray[i]!!.ImageInt = R.drawable.full
        }
    }
    fun default(){
        myImageArray.forEach {
            it!!.image.setImageResource(R.drawable.empty)
            it!!.ImageInt = R.drawable.empty
        }
    }
    fun check():Boolean{
        for(value in ran_index){
            if(myImageArray[value!!]!!.ImageInt != R.drawable.full){
                return false
            }
        }
        var count = 0
        myImageArray.forEach {
            if(it!!.ImageInt == R.drawable.full) count++
        }
        if(count != ran_index.size){
            return false
        }
        return true
    }

}