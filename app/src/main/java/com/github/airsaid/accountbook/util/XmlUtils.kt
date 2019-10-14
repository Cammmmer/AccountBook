package com.github.airsaid.accountbook.util

import android.content.Context
import android.content.res.XmlResourceParser

import com.github.airsaid.accountbook.data.Type

import org.xmlpull.v1.XmlPullParserException

import java.io.IOException
import java.util.ArrayList

object XmlUtils {

    @Throws(XmlPullParserException::class, IOException::class)
    fun getTypeFromXML(context: Context, xmlID: Int): List<Type> {
        val parser = context.resources.getXml(xmlID)

        val types = ArrayList<Type>()
        var type: Type? = null
        //获取事件类型
        //不是文件结尾就继续循环
        // 循环解析
        var eventType = parser.eventType
        while (parser.eventType != XmlResourceParser.END_DOCUMENT) {
            when (eventType) {
                XmlResourceParser.START_TAG -> {
                    val name = parser.name //获取标签名
                    if ("type" == name) {
                        type = Type()
                        type.name = parser.getAttributeValue(null, "name")
                        type.icon = parser.getAttributeValue(null, "icon")
                        types.add(type)
                    }
                }
            }

            eventType = parser.next()

        }
        return types
    }
}
