package com.github.alvinscrp.androidcodetemplate.generator.mvvm.temp

import com.github.alvinscrp.androidcodetemplate.generator.util.AppType
import com.github.alvinscrp.androidcodetemplate.generator.util.firstUppercase


fun mvvmRepositoryTemp(
    appType: AppType,
    classPackageName: String,
    bizName: String

): String {
    return """
    package ${classPackageName}.${bizName}.repository

    import ${appType.fullHttpResponse()}
    import ${classPackageName}.${bizName}.api.${firstUppercase(bizName)}Api
    import ${classPackageName}.${bizName}.bean.${firstUppercase(bizName)}Bean

    class ${firstUppercase(bizName)}Repository(val api: ${firstUppercase(bizName)}Api) {

        suspend fun getTemplateData(templateId: Long): ${appType.simpleHttpResponse()}<${firstUppercase(bizName)}Bean> {
            var params = hashMapOf<String, Any>(
                "templateId" to templateId
            )
            return api.getTemplateData(params)
        }

    }
""".trimIndent()
}



