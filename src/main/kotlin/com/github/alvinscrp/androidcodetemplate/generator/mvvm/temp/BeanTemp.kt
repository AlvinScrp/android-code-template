package com.github.alvinscrp.androidcodetemplate.generator.mvvm.temp

import com.github.alvinscrp.androidcodetemplate.generator.util.firstUppercase


fun mvvmBeanTemp(
    classPackageName: String,
    bizName:String

): String {
    return """
    package ${classPackageName}.${bizName}.bean

    import androidx.annotation.Keep

    @Keep
    data class ${firstUppercase(bizName)}Bean(
        val title: String? = null,
        val summary: String? = null,
        val imageUrl: String? = null
    )
""".trimIndent()
}



