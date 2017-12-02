package com.topcoder.timobile.story

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import com.topcoder.timobile.R
import kotlinx.android.synthetic.main.custom_card_adapter.view.*

internal class CustomCardAdapter(context: Context, layoutId: Int, list: List<CardDetailsModel>) : ArrayAdapter<CardDetailsModel>(context, layoutId, list) {

    override fun getView(Position: Int, convertview: View?, parent: ViewGroup): View {
        val view: View
        if (convertview == null)
            view = LayoutInflater.from(context).inflate(R.layout.custom_card_adapter, null)
        else
            view = convertview

        val mObject = getItem(Position)
        if (mObject != null) {
            view.es.text = mObject.es
            view.matter.text = mObject.matter
            view.card_view_title.text = mObject.cardviewtitle
            view.ChapterInfo.text = (mObject.chapterCount).toString() + " Chapters"
            view.CardInfo.text = (mObject.cardCount).toString() + " Cards"

            // TODO: Read images from SD Card and add to card view
            when (mObject.id) {
                1 -> view.image.setImageResource(R.drawable.story1)
                2 -> view.image.setImageResource(R.drawable.story2)
                3 -> view.image.setImageResource(R.drawable.story3)
                else -> view.image.setImageResource(R.mipmap.ic_launcher)
            }
        }
        return view
    }
}

