package com.github.alvinscrp.androidcodetemplate.generator.util

enum class AppType(val key: String) {
    FXJ("fxj"), HYK("hyk"), MC("micai");


    fun fullBaseActivity(): String {
        return when (this) {
            FXJ -> "com.webuy.common.base.CBaseActivity"
            HYK -> "cn.com.haoyiku.base.HYKBaseActivity"
            MC -> "com.income.common.base.CBaseActivity"
        }
    }

    fun simpleBaseActivity(): String {
        return when (this) {
            FXJ, MC -> "CBaseActivity"
            HYK -> "HYKBaseActivity"
        }
    }

    fun fullCBaseFragment(): String {
        return when (this) {
            FXJ -> "com.webuy.common.base.CBaseFragment"
            HYK -> "cn.com.haoyiku.base.HYKBaseFragment"
            MC -> "com.income.common.base.CBaseFragment"
        }
    }

    fun simpleCBaseFragment(): String {
        return when (this) {
            FXJ, MC -> "CBaseFragment"
            HYK -> "HYKBaseFragment"
        }
    }

    fun fullCBaseViewModel(): String {
        return when (this) {
            FXJ -> "com.webuy.common.base.CBaseViewModel"
            HYK -> "cn.com.haoyiku.base.HYKBaseViewModel"
            MC -> "com.income.common.base.CBaseViewModel"
        }
    }

    fun simpleCBaseViewModel(): String {
        return when (this) {
            FXJ, MC -> "CBaseViewModel"
            HYK -> "HYKBaseViewModel"
        }
    }

    fun fullCBaseDiffListAdapter(): String {
        return when (this) {
            FXJ -> "com.webuy.common.base.adapter.CBaseDiffListAdapter"
            HYK -> "com.webuy.jladapter.normal.BaseListAdapter"
            MC -> "com.income.common.base.adapter.CBaseDiffListAdapter"
        }
    }

    fun simpleCBaseDiffListAdapter(): String {
        return when (this) {
            FXJ, MC -> "CBaseDiffListAdapter"
            HYK -> "BaseListAdapter"
        }
    }


    fun fullIDiffVhModelType(): String {
        return when (this) {
            FXJ -> "com.webuy.common.base.adapter.IDiffVhModelType"
            HYK -> "com.webuy.jladapter.inter.IVhModelType"
            MC -> "com.income.common.base.adapter.IDiffVhModelType"
        }
    }

    fun simpleIDiffVhModelType(): String {
        return when (this) {
            FXJ, MC -> "IDiffVhModelType"
            HYK -> "IVhModelType"
        }
    }

    fun fullViewTypeDelegateManager(): String {
        return when (this) {
            FXJ -> "com.webuy.common.base.adapter.ViewTypeDelegateManager"
            HYK -> "com.webuy.jladapter.vtd.ViewTypeDelegateManager"
            MC -> "com.income.common.base.adapter.ViewTypeDelegateManager"
        }
    }

    fun fullHttpResponse(): String {
        return when (this) {
            FXJ -> "com.webuy.common.net.HttpResponse"
            HYK -> "cn.com.haoyiku.commmodel.api.HHttpResponse"
            MC -> "com.income.common.net.HttpResponse"
        }
    }

    fun simpleHttpResponse(): String {
        return when (this) {
            FXJ, MC -> "HttpResponse"
            HYK -> "HHttpResponse"
        }
    }


    fun fullToLoadUrl(): String {
        return when (this) {
            FXJ -> "com.webuy.common.utils.toLoadUrl"
            HYK -> "com.webuy.common.utils.toLoadUrl"
            MC -> "com.income.common.utils.toLoadUrl"
        }
    }


    fun fullRetrofitHelper(): String {
        return when (this) {
            FXJ -> "com.webuy.common.net.RetrofitHelper"
            HYK -> "cn.com.haoyiku.api.RetrofitHelper"
            MC -> "com.income.common.net.RetrofitHelper"
        }
    }

    fun retrofitHelperInstance(): String {
        return when (this) {
            FXJ, MC -> "RetrofitHelper.instance"
            HYK -> "RetrofitHelper.getInstance()"
        }
    }

    fun handleRequestException(): String {
        return when (this) {
            FXJ, MC -> "silentThrowable(e)"
            HYK -> "errorLogReport(e)"
        }
    }

    fun fullJLFitView(): String {
        return when (this) {
            FXJ, HYK -> "com.webuy.widget.JLFitView"
            MC -> "com.income.lib.widget.JLFitView"
        }


    }
}