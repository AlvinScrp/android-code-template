package com.github.alvinscrp.androidcodetemplate.generator.mvvm.temp

import com.github.alvinscrp.androidcodetemplate.generator.util.AppType
import com.github.alvinscrp.androidcodetemplate.generator.util.activityLayoutName
import com.github.alvinscrp.androidcodetemplate.generator.util.firstUppercase
import com.github.alvinscrp.androidcodetemplate.generator.util.fragmentClassName


/**
 * 生成XxActivity文件的内容，你的项目里是啥，就是啥，不要用我这个模板
 */
fun mvvmActivityTemp(
    appType: AppType,
    modulePackageName: String,
    classPackageName: String,
    moduleName: String,
    bizName: String
): String {
    return """
    package ${classPackageName}.${bizName}.ui

    import android.os.Bundle
    import ${appType.fullBaseActivity()}
    import ${modulePackageName}.R

    class ${firstUppercase(bizName)}Activity : ${appType.simpleBaseActivity()}() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.${activityLayoutName(moduleName, bizName)})
            replaceFragment(R.id.fragment_container, ${fragmentClassName(bizName)}.newInstance(123), false)
        }

    }
""".trimIndent()
}



