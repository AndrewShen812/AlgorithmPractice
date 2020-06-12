package com.sy.generics

/**
 *
 * @author ShenYong
 * @date 2020/4/22
 */
class InnerFunc {

    fun outFunc() {
        fun innerFunc(x: Int): String {
            val a = x + 1
            return a.toString()
        }
    }
}