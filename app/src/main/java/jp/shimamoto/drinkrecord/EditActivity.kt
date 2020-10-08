package jp.shimamoto.drinkrecord

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    var count1 = 0
    var ttldrk = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)




        //// 日付を初期化・更新


        fun calcTtlDrink(cunt: Int) {

            val posi1 = spinner1.selectedItemPosition.toString().toInt()
            val ttlSpin1: Double = when (posi1) {
                0 -> 0.0 ////* count1.toDouble()
                1 -> 2.0 * cunt.toDouble()
                2 -> 1.5 * 2////* count1.toDouble()
                3 -> 1.2 ////* count1.toDouble()
                else -> 0.0
            }

            var dayTtl: Double = ttlSpin1 //// + ttlSpin2
            dayTtlAlcVol.text = dayTtl.toString()

        }


        // Spinnerの取得
        val spinner1 = findViewById<Spinner>(R.id.spinner1)

        // Adapterの生成 //// spinnerItemsからアダプターを作る？
        // val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems) //// 選択肢("X", "〇")
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.list,
            android.R.layout.simple_spinner_item
        ) //// 選択肢はstring.xmlに設定

        // 選択肢の各項目のレイアウト  //// アダプタがスピナーの選択肢の一覧を表示するのに使うレイアウトを指定
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        // AdapterをSpinnerのAdapterとして設定
        spinner1.adapter = adapter

        //// spinnerの初期値を指定する　10/6
        spinner1.setSelection(0)


        // OnItemSelectedListenerの実装
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            // 項目が選択された時に呼ばれる
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val text = parent?.selectedItem as String
                val posi1 = parent.selectedItemPosition.toString() //// 選択されたposiを取得　10/6
                //// val selid = parent.selectedItemId.toString() //// 選択されたidを取得->posi と id の違いが不明？ 10/6
                itemPosiText1.text = posi1 //// 10/6

                Toast.makeText(this@EditActivity, text, Toast.LENGTH_SHORT).show()
            }

            // 基本的には呼ばれないが、何らかの理由で選択されることなく項目が閉じられたら呼ばれる
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@EditActivity, "変化なし", Toast.LENGTH_SHORT).show()

            }
        }


        //// 個々の数量を初期化・更新
        ////fun eachCount() {
        var count1: Int = 0
        countText1.text = count1.toString()

        pBtn1.setOnClickListener {
            count1++
            countText1.text = count1.toString()
            calcTtlDrink(count1)
        }

        mBtn1.setOnClickListener {
            count1--
            countText1.text = count1.toString()
            calcTtlDrink(count1)
        }
        ////}


        //// ドリンク合計を初期化・更新
/*
        fun calcTtlDrink() {

            val posi1 = spinner1.selectedItemPosition.toString().toInt()
            val ttlSpin1: Double = when(posi1) {
                0 -> 0.0
                1 -> 1*2.0
                2 -> 1*1.5
                3 -> 1*1.2
                else -> 0.0
            }

            var dayTtl: Double = ttlSpin1
            DayTtlAlcVol.text = dayTtl.toString()

        }

 */


    }
}
