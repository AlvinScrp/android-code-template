package com.github.alvinscrp.androidcodetemplate.generator.mvvm.temp

import com.github.alvinscrp.androidcodetemplate.generator.util.AppType
import com.github.alvinscrp.androidcodetemplate.generator.util.firstUppercase
import com.github.alvinscrp.androidcodetemplate.generator.util.fragmentDataBindingName


fun mvvmFragmentTemp(
    appType: AppType,
    modulePackageName: String,
    classPackageName: String,
    moduleName: String,
    bizName: String
): String {
    return """
    package ${classPackageName}.${bizName}.ui

    import android.os.Bundle
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.fragment.app.viewModels
    import ${appType.fullCBaseFragment()}
    import ${modulePackageName}.databinding.${fragmentDataBindingName(moduleName, bizName)}
    import ${classPackageName}.${bizName}.adapter.${firstUppercase(bizName)}Adapter
    import ${classPackageName}.${bizName}.model.${firstUppercase(bizName)}FirstModel
    import ${classPackageName}.${bizName}.viewmodel.${firstUppercase(bizName)}ViewModel
    import com.scwang.smartrefresh.layout.api.RefreshLayout
    import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener


    class ${firstUppercase(bizName)}Fragment : ${appType.simpleCBaseFragment()}() {

        companion object {
            private const val KEY_Template_ID = "key_template_id"

            fun newInstance(templateId: Long) = ${firstUppercase(bizName)}Fragment().apply {
                arguments = Bundle().apply {
                    putLong(KEY_Template_ID, templateId)
                }
            }
        }

        interface OnEventListener : OnRefreshLoadMoreListener,
            ${firstUppercase(bizName)}Adapter.OnAdapterEventListener {
            fun onBackClick()
        }

        private val binding by lazy { ${fragmentDataBindingName(moduleName, bizName)}.inflate(layoutInflater) }

        private val vm by viewModels<${firstUppercase(bizName)}ViewModel>()

        private val adapter by lazy { ${firstUppercase(bizName)}Adapter(eventListener) }


        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            initViewModel()
            initView()
            subscribeUI()
        }

        private fun initViewModel() {
            var templateId = arguments?.getLong(KEY_Template_ID) ?: 0L
            vm.init(templateId)
        }

        private fun initView() {
            binding.lifecycleOwner = this
            binding.vm = vm
            binding.listener = eventListener
            binding.rv.adapter = adapter
        }

        private fun subscribeUI() {

        }

        /**
         * 页面的事件。
         */
        private val eventListener = object : OnEventListener {

            override fun onBackClick() {
                activity?.onBackPressed()
            }

            override fun onLoadMore(refreshLayout: RefreshLayout?) {
            }

            override fun on${firstUppercase(bizName)}FirstClick(item: ${firstUppercase(bizName)}FirstModel) {

            }

            override fun onRefresh(refreshLayout: RefreshLayout?) {
                vm.refresh()
            }
        }
    }
""".trimIndent()
}

