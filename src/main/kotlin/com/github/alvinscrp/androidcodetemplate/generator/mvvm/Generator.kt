package com.github.alvinscrp.androidcodetemplate.generator.mvvm


import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import com.github.alvinscrp.androidcodetemplate.generator.util.AppType


/**
 * 模板配置需要的参数，根据你的需要，在这里添加
 */
fun jlMvvmGenerator(appType: AppType): Template {

    return template {
        name = "DataBinding Mvvm Temp Code - ${appType.key}"
        description =
            "生成一套基于DataBinding的MVVM代码，包括：Activity、Fragment、ViewModel、ListAdapter、 ListItemModel、BeanModelConvert、Bean、 Retrofit Api、 Repository"
        minApi = MIN_API

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.ActivityGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        //业务名称
        val bizNameParameter = stringParameter {
            name = "Business Name：英文，小写开头，camel命名，可以多单词"
            default = "template"
            help = "业务名称：英文，可以多单词，camel命名，用来作为生成的各种文件的前缀"
            constraints = listOf(Constraint.NONEMPTY)
        }


        val classPackageNameParameter = stringParameter {
            name = "Class Package Name: 这个不要改它"
            help = "文件名称：生成文件的存放位置，不是APP包名"
            default = "com.github.alvinscrp"
            constraints = listOf(Constraint.PACKAGE)
            suggest = { packageName }
        }

        //baseActivity，baseFragment依赖的base文件路径
//        val appTypeParameter = enumParameter<AppType> {
//            name = "应用区分，决定各种底层库的依赖路径"
//            help = "baseActivity，baseFragment,retrofit 依赖的base文件路径"
//            default = AppType.FXJ
//        }

        val isCreateActivityParameter = booleanParameter {
            name = "生成Activity,需手动加入清单文件"
            help = ""
            default = false
        }


        widgets(
            TextFieldWidget(bizNameParameter),
            TextFieldWidget(classPackageNameParameter),
//            EnumWidget(appTypeParameter),
            CheckBoxWidget(isCreateActivityParameter)
        )

        recipe = {
            mvvmRecipe(
                it as ModuleTemplateData,
                bizNameParameter.value,
                classPackageNameParameter.value,
//                appTypeParameter.value,
                appType,
                isCreateActivityParameter.value
            )
        }
    }
}