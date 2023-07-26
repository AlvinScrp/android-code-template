package com.github.alvinscrp.androidcodetemplate.generator.mvvm.temp

import com.github.alvinscrp.androidcodetemplate.generator.util.AppType
import com.github.alvinscrp.androidcodetemplate.generator.util.firstUppercase


fun mvvmCovertTemp(
    appType: AppType,
    classPackageName: String,
    bizName: String

): String {
    return """
    package ${classPackageName}.${bizName}.convert

    import ${appType.fullToLoadUrl()}
    import ${classPackageName}.${bizName}.bean.${firstUppercase(bizName)}Bean
    import ${classPackageName}.${bizName}.model.${firstUppercase(bizName)}FirstModel

    object ${firstUppercase(bizName)}ConvertUtil {

        fun convertFirstModel(${bizName}Bean: ${firstUppercase(bizName)}Bean?): ${firstUppercase(bizName)}FirstModel? {
            val bean = ${bizName}Bean ?: return null

            return ${firstUppercase(bizName)}FirstModel(
                title = bean.title.orEmpty(),
                summary = bean.summary.orEmpty(),
                imageUrl = bean.imageUrl?.toLoadUrl().orEmpty()
            )
        }
    }
""".trimIndent()
}



