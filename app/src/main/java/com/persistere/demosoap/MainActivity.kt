package com.persistere.demosoap

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.PropertyInfo
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE

class MainActivity : AppCompatActivity() {

    //NO PROGRAMA SOAP UI CRIAMOS O SOAP http://pa02micro42:8080/CalculadoraWSService/CalculadoraWS
    //No build.gradle DemoSOAP cahmamos o repositorio maven { url 'https://oss.sonatype.org/content/repositories/ksoap2-android-releases/' }
    // e no buil.gradle app chamamos o implementation 'com.google.code.ksoap2-android:ksoap2-android:3.6.2'

    private val url = "http://10.3.2.42:8080/CalculadoraWSService/CalculadoraWS?wsdl"
    private val nameSpace = "http://heiderlopes.com.br/"
    private val methodName = "calcular"
    private val soapAction = nameSpace + methodName
    private val paramentro1 = "num1"
    private val paramentro2 = "num2"
    private val paramentro3 = "op"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btSomar.setOnClickListener {
            CallWebService()
                    .execute(etNumero1.text.toString(),
                             etNumero2.text.toString(),
                             "+")
        }
    }

    inner class CallWebService : AsyncTask<String, Void, String> () {

        override fun onPostExecute(result: String?) {
            tvResultado.text = result
        }

        override fun doInBackground(vararg params: String?): String {
            var result = ""
            val soapObject = SoapObject(nameSpace, methodName)
            val numberInfo = PropertyInfo()

            numberInfo.name = paramentro1
            numberInfo.value = params[0]
            numberInfo.type = Integer::class.java

            soapObject.addProperty(numberInfo)


            val number2Info = PropertyInfo()
            number2Info.name = paramentro2
            number2Info.value = params[1]
            number2Info.type = Integer::class.java

            soapObject.addProperty(number2Info)

            val opInfo = PropertyInfo()
            opInfo.name = paramentro3
            opInfo.value = params[2]
            opInfo.type = String::class.java

            soapObject.addProperty(opInfo)

            val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
            envelope.setOutputSoapObject(soapObject)

            val httpTransportSE = HttpTransportSE(url)

            try {
                httpTransportSE.call(soapAction, envelope)
                val soapPrimitive = envelope.response
                result = soapPrimitive.toString()

            } catch (e: Exception) {
                e.printStackTrace()
            }

            return result
        }

    }
}
