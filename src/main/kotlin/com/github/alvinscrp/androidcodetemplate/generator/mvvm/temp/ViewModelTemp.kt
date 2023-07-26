package com.github.alvinscrp.androidcodetemplate.generator.mvvm.temp

import com.github.alvinscrp.androidcodetemplate.generator.util.AppType
import com.github.alvinscrp.androidcodetemplate.generator.util.firstUppercase

/**
 *
 */
fun mvvmViewModelTemp(
    appType: AppType,
    classPackageName: String,
    bizName: String
): String {
    return """
    package ${classPackageName}.${bizName}.viewmodel

    import android.app.Application
    import androidx.lifecycle.MutableLiveData
    import androidx.lifecycle.viewModelScope
    import ${appType.fullCBaseViewModel()}
    import ${appType.fullIDiffVhModelType()}
    import ${appType.fullRetrofitHelper()}
    import ${classPackageName}.${bizName}.api.${firstUppercase(bizName)}Api
    import ${classPackageName}.${bizName}.convert.${firstUppercase(bizName)}ConvertUtil
    import ${classPackageName}.${bizName}.model.${firstUppercase(bizName)}FirstModel
    import ${classPackageName}.${bizName}.repository.${firstUppercase(bizName)}Repository
    import kotlinx.coroutines.launch

    class ${firstUppercase(bizName)}ViewModel(application: Application) : ${appType.simpleCBaseViewModel()}(application) {

        private val repository by lazy {
            ${firstUppercase(bizName)}Repository(${appType.retrofitHelperInstance()}.createApiService(${firstUppercase(bizName)}Api::class.java))
        }

        val refreshFinish = MutableLiveData<Boolean>()

        //列表和单独刷新item
        val listLiveData = MutableLiveData<List<${appType.simpleIDiffVhModelType()}>>()
        val itemsLiveData = MutableLiveData<List<${appType.simpleIDiffVhModelType()}>>()

        val assemble = Assemble()

        fun init(templateId: Long) {
            assemble.templateId = templateId
            getTemplateData(showLoading = true)
        }

        fun refresh() {
            getTemplateData(showLoading = false, isRefresh = true)
        }

        private fun getTemplateData(showLoading: Boolean = false, isRefresh: Boolean = false) {

            viewModelScope.launch {
                try {
                    if (showLoading) {
                        showLoading()
                    }
                    val entry = repository.getTemplateData(assemble.templateId)
                        ?.takeIf { it.status }?.entry
                    assemble.firstModel = ${firstUppercase(bizName)}ConvertUtil.convertFirstModel(entry)
                    listLiveData.postValue(assemble.toList())

                } catch (e: Exception) {
                    ${appType.handleRequestException()}
                    //TODO 开发时候，删掉
                    assemble.firstModel = ${firstUppercase(bizName)}FirstModel(
                        title = "测试标题，开发时候删掉",
                        summary = "测试副标题，开发时候删掉",
                        imageUrl = ""
                    )
                    listLiveData.postValue(assemble.toList())
                     //end
                } finally {
                    if (showLoading) {
                        hideLoading()
                    }
                    if (isRefresh) {
                        refreshFinish.postValue(true)
                    }
                }
            }

        }


        class Assemble {

            var templateId: Long = -1L

            var firstModel: ${firstUppercase(bizName)}FirstModel? = null

            val list = ArrayList<${appType.simpleIDiffVhModelType()}>()


            @Synchronized
            fun toList(): ArrayList<${appType.simpleIDiffVhModelType()}> {
                return ArrayList<${appType.simpleIDiffVhModelType()}>().apply {
                    firstModel?.let { add(it) }
                }
            }
        }

        companion object {
            private const val PAGE_SIZE = 20
        }
    }
""".trimIndent()
}

