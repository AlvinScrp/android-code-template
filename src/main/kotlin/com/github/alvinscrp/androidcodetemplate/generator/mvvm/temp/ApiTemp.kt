package com.github.alvinscrp.androidcodetemplate.generator.mvvm.temp

import com.github.alvinscrp.androidcodetemplate.generator.util.AppType
import com.github.alvinscrp.androidcodetemplate.generator.util.firstUppercase


fun mvvmApiTemp(
    appType: AppType,
    classPackageName: String,
    bizName: String

): String {
    return """
    package ${classPackageName}.${bizName}.api

    import ${appType.fullHttpResponse()}
    import ${classPackageName}.${bizName}.bean.${firstUppercase(bizName)}Bean
    import retrofit2.http.GET
    import retrofit2.http.QueryMap

    interface ${firstUppercase(bizName)}Api {

        @GET("/xx/xx/${bizName}")
        suspend fun getTemplateData(@QueryMap params: HashMap<String, Any>): ${appType.simpleHttpResponse()}<${firstUppercase(bizName)}Bean>

    }
""".trimIndent()
}



