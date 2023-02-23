package com.example.a2splus
import android.app.AlertDialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    var arr = arrayListOf<String>()
    var type = arrayListOf<String>()
    var con = arrayListOf<String>()
    var phon = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        readJson()
        //createButtonDin()
        ///////////////////////////////////////////////////////////////////////////////
        //val btn = findViewById<Button>(R.id.btn)
        //btn.setOnClickListener {
            //createSimpleDialog()
        //}
    }
    private fun readJson()
    {
        val json_text = findViewById<ListView>(R.id.json_text)
        var json : String? = null

        try {
            val inputStream: InputStream = assets.open("First.json")
            json = inputStream.bufferedReader().use { it.readText() }
            //json_text.text = json

            var jsonarr = JSONArray(json)

            for (i in 0..jsonarr.length() - 1)
            {
                var jsonobj = jsonarr.getJSONObject(i)
                arr.add(jsonobj.getString("_ko"))
                type.add(jsonobj.getString("_th"))
                con.add(jsonobj.getString("_cn"))
                phon.add(jsonobj.getString("_cp"))
            }
            var adpt = ArrayAdapter(this, android.R.layout.simple_list_item_1, arr)
            json_text.adapter = adpt

            json_text.onItemClickListener = AdapterView.OnItemClickListener() { parent, view, position, id ->
                //Toast.makeText(applicationContext, type[position]+con[position]+phon[position], Toast.LENGTH_LONG).show()
                val _ko = arr[position]
                val _th = type[position]
                val _cn = con[position]
                val _cp = phon[position]
                createSimpleDialog(_ko, _th, _cn, _cp)
            }
        }
        catch (e : IOException)
        {

        }
    }
    private fun createButtonDin()
    {
        for (i in 0..10) {
            val ll_main = findViewById(R.id.linearLayout) as LinearLayout
            val button_dynamic = Button(this)
            button_dynamic.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,

            )
            button_dynamic.text = i.toString()
            button_dynamic.setOnClickListener{createSimpleDialogAuto(i.toString())}
            ll_main.addView(button_dynamic)
        }

    }
    private fun createSimpleDialogAuto(i: String)
    {
        Toast.makeText(getApplicationContext(), i, Toast.LENGTH_LONG).show()
    }

    private fun createSimpleDialog(_ko : String, _th : String, _cn : String, _cp : String)
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(_ko)
        builder.setMessage(_th+"\n\n\n"+_cp+"\n"+_cn)
        builder.setNeutralButton("Позвонить",){ dialogInterface, i ->

        }
        builder.setNegativeButton("Выйти"){ dialogInterface, i ->

        }
        builder.setPositiveButton("Выполнено"){ dialogInterface, i ->

        }
        builder.show()
    }
}