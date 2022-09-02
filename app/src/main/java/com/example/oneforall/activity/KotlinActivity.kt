package com.example.oneforall.activity

import android.widget.TextView
import com.example.oneforall.R

fun printFuck(who:String ,whom :String):Boolean{
    System.out.println(who+" fuck "+ whom);
    return true;
}

class KotlinActivity : BaseActivity() {
    override fun getContentId(): Int {

        return R.layout.activity_default;
    }

    override fun initView() {
        findViewById<TextView>(R.id.tv).text = "Kotlin Language Activity";

        val i:Int = 0b001;
        println("i.and(0b100) = ${i.and(0b100)}");
        println("i.xor(0b1001) = ${i.xor(0b100)}");
        println("foo start")
        this.foo();
    }

    fun foo() {
        println("foo start2")
        var arr = arrayOf(1,2,3);
        arr.forEach (fun(value){
            if (value == 0) return@forEach
            println(value)
        })
        arr.forEach{
            if(it ==0) return@forEach
            println(it)
        }
    }

    override fun initData() {

        println("initData 开始 ")
        var r = Runoob("Cindy",1);
        r.printTest();
    }
}
class Runoob  constructor(name: String) {  // 类名为 Runoob
    // 大括号内是类体构成
    var url: String = "http://www.runoob.com"
    var country: String = "CN"
    var siteName = name

    init {
        println("初始化网站名: ${name}")
    }
    // 次构造函数
    constructor (name: String, alexa: Int) : this(name) {
        println("Alexa 排名 $alexa")
    }

    fun printTest() {
        println("我是类的函数")
    }
}