package com.github.alvinscrp.androidcodetemplate.generator.mvvm


import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.alvinscrp.androidcodetemplate.generator.util.activityLayoutName
import com.github.alvinscrp.androidcodetemplate.generator.mvvm.temp.activityLayoutTemp
import com.github.alvinscrp.androidcodetemplate.generator.util.fragmentLayoutName
import com.github.alvinscrp.androidcodetemplate.generator.mvvm.temp.fragmentLayoutTemp
import com.github.alvinscrp.androidcodetemplate.generator.util.itemFirstLayoutName
import com.github.alvinscrp.androidcodetemplate.generator.mvvm.temp.itemFirstLayoutTemp
import com.github.alvinscrp.androidcodetemplate.generator.mvvm.temp.mvvmActivityTemp
import com.github.alvinscrp.androidcodetemplate.generator.mvvm.temp.mvvmAdapterTemp
import com.github.alvinscrp.androidcodetemplate.generator.mvvm.temp.mvvmApiTemp
import com.github.alvinscrp.androidcodetemplate.generator.mvvm.temp.mvvmBeanTemp
import com.github.alvinscrp.androidcodetemplate.generator.mvvm.temp.mvvmCovertTemp
import com.github.alvinscrp.androidcodetemplate.generator.mvvm.temp.mvvmFirstModelTemp
import com.github.alvinscrp.androidcodetemplate.generator.mvvm.temp.mvvmFragmentTemp
import com.github.alvinscrp.androidcodetemplate.generator.mvvm.temp.mvvmRepositoryTemp
import com.github.alvinscrp.androidcodetemplate.generator.mvvm.temp.mvvmViewModelTemp
import com.github.alvinscrp.androidcodetemplate.generator.util.AppType
import com.github.alvinscrp.androidcodetemplate.generator.util.firstUppercase
import org.jetbrains.kotlin.util.capitalizeDecapitalize.toLowerCaseAsciiOnly

/**
 * 模板代码文件的创建与保存
 *
 * 这里有几个变量需要注意下：
 * ```
 * //当前批量生成类文件所在目录 com.example.x.y
 * classPackageName：
 *
 * //模块名，例如 user
 * val moduleName = moduleData.rootDir.name.toLowerCaseAsciiOnly()
 *
 * //模块包名，例如com.example.user , 在模块AndroidManifest.xml中配置的那个，一定要注意
 * val modulePackageName = projectData.applicationPackage
 * ```
 */
fun RecipeExecutor.mvvmRecipe(
    moduleData: ModuleTemplateData,
    bizName: String,
    classPackageName: String,
    appType: AppType,
    isCreateActivity: Boolean
) {
    val (projectData, srcOut, resOut) = moduleData
    val moduleName = moduleData.rootDir.name.toLowerCaseAsciiOnly()
    val modulePackageName = projectData.applicationPackage ?: ""

//    println("---->${projectData.rootDir},${projectData.applicationPackage},${moduleData.rootDir.name},${moduleData.packageName}")

    if(isCreateActivity) {
        save(
            mvvmActivityTemp(appType, modulePackageName, classPackageName, moduleName, bizName),
            srcOut.resolve("${bizName}/ui/${firstUppercase(bizName)}Activity.kt")
        )
        //插入Manifest，这个代码运行报错，反正我也用不到，就不管了
//        generateManifest(
//            moduleData = moduleData,
//            activityClass = "${firstUppercase(bizName)}Activity",
//            packageName = "${classPackageName}.${bizName}.ui",
//            isLauncher = false,
//            hasNoActionBar = false,
//            isNewModule = false,
//            isLibrary = false,
//            generateActivityTitle = false
//        )
    }
    save(
        mvvmFragmentTemp(appType, modulePackageName, classPackageName,moduleName, bizName),
        srcOut.resolve("${bizName}/ui/${firstUppercase(bizName)}Fragment.kt")
    )
    save(
        mvvmViewModelTemp(appType, classPackageName, bizName),
        srcOut.resolve("${bizName}/viewmodel/${firstUppercase(bizName)}ViewModel.kt")
    )
    save(
        mvvmRepositoryTemp(appType, classPackageName, bizName),
        srcOut.resolve("${bizName}/repository/${firstUppercase(bizName)}Repository.kt")
    )
    save(
        mvvmApiTemp(appType, classPackageName, bizName),
        srcOut.resolve("${bizName}/api/${firstUppercase(bizName)}Api.kt")
    )
    save(
        mvvmBeanTemp(classPackageName, bizName),
        srcOut.resolve("${bizName}/bean/${firstUppercase(bizName)}Bean.kt")
    )
    save(
        mvvmAdapterTemp(appType, modulePackageName, classPackageName, bizName),
        srcOut.resolve("${bizName}/adapter/${firstUppercase(bizName)}Adapter.kt")
    )
    save(
        mvvmCovertTemp(appType, classPackageName, bizName),
        srcOut.resolve("${bizName}/convert/${firstUppercase(bizName)}ConvertUtil.kt")
    )
    save(
        mvvmFirstModelTemp(appType, modulePackageName,classPackageName, moduleName, bizName),
        srcOut.resolve("${bizName}/model/${firstUppercase(bizName)}FirstModel.kt")
    )

    save(activityLayoutTemp(), resOut.resolve("layout/${activityLayoutName(moduleName, bizName)}.xml"))
    save(
        fragmentLayoutTemp(appType,classPackageName, bizName),
        resOut.resolve("layout/${fragmentLayoutName(moduleName, bizName)}.xml")
    )
    save(
        itemFirstLayoutTemp(classPackageName, bizName),
        resOut.resolve("layout/${itemFirstLayoutName(moduleName, bizName)}.xml")
    )

}