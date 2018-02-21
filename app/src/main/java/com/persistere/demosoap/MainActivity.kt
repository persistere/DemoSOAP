package com.persistere.demosoap

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private val url = "http://pa02micro42:8080/CalculadoraWSService/CalculadoraWS?wsdl"
    private val nameSpace = "http://heiderlopes.com.br"
    private val methodName = "calcular"
    private val soapAction = nameSpace + methodName
    private val paramentro1 = "num1"
    private val paramentro2 = "num2"
    private val parametro3 = "op"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    inner class CallWebService : AsyncTask<String, Void, String> () {
        override fun doInBackground(vararg params: String?): String {

        }

    }
}
