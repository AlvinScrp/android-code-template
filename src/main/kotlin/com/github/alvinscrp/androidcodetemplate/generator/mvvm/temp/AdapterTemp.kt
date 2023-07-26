package com.github.alvinscrp.androidcodetemplate.generator.mvvm.temp

import com.github.alvinscrp.androidcodetemplate.generator.util.AppType
import com.github.alvinscrp.androidcodetemplate.generator.util.firstUppercase


fun mvvmAdapterTemp(
    appType: AppType,
    modulePackageName: String,
    classPackageName:String,
    bizName: String
): String {
    return """
    package ${classPackageName}.${bizName}.adapter

    import androidx.databinding.ViewDataBinding
    import ${appType.fullCBaseDiffListAdapter()}
    import ${appType.fullIDiffVhModelType()}
    import ${appType.fullViewTypeDelegateManager()}
    import ${modulePackageName}.BR
    import ${classPackageName}.${bizName}.model.${firstUppercase(bizName)}FirstModel


    class ${firstUppercase(bizName)}Adapter(private val listener: OnAdapterEventListener) : ${appType.simpleCBaseDiffListAdapter()}() {

        override fun onCreateVHForAll(binding: ViewDataBinding) {
            binding.setVariable(BR.listener, listener)
        }

        override fun onBindVHForAll(binding: ViewDataBinding, m: ${appType.simpleIDiffVhModelType()}) {
            binding.setVariable(BR.item, m)
        }


        override fun addDelegate(manager: ViewTypeDelegateManager<${appType.simpleIDiffVhModelType()}>) {
            super.addDelegate(manager)
    //        manager.add(XxVTD(listener))
        }

        interface OnAdapterEventListener : ${firstUppercase(bizName)}FirstModel.OnItemEventListener
    }
""".trimIndent()
}
