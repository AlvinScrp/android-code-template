package com.github.alvinscrp.androidcodetemplate.generator.mvvm.temp

import com.github.alvinscrp.androidcodetemplate.generator.util.AppType
import com.github.alvinscrp.androidcodetemplate.generator.util.firstUppercase
import com.github.alvinscrp.androidcodetemplate.generator.util.layoutPrefix


fun mvvmFirstModelTemp(
    appType: AppType,
    modulePackageName: String,
    classPackageName: String,
    moduleName: String,
    bizName: String
): String {
    return """

    package ${classPackageName}.${bizName}.model

    import ${appType.fullIDiffVhModelType()}
    import ${modulePackageName}.R

    class ${firstUppercase(bizName)}FirstModel(
        var title: String = "这是标题",
        var summary: String = "这是副标题",
        var imageUrl:String=""
    ) : ${appType.simpleIDiffVhModelType()} {
        override fun getViewType(): Int = R.layout.${layoutPrefix(moduleName,bizName)}_item_first

        interface OnItemEventListener {
            fun on${firstUppercase(bizName)}FirstClick(item: ${firstUppercase(bizName)}FirstModel)
        }
    }
""".trimIndent()
}



