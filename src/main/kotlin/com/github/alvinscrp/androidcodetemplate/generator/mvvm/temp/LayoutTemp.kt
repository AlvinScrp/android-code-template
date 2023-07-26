package com.github.alvinscrp.androidcodetemplate.generator.mvvm.temp

import com.github.alvinscrp.androidcodetemplate.generator.util.AppType
import com.github.alvinscrp.androidcodetemplate.generator.util.firstUppercase


fun activityLayoutTemp(): String {
    return """
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/fragment_container"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:ignore="MissingDefaultResource,ResourceName" />
"""
}

fun fragmentLayoutTemp(appType: AppType,classPackageName: String, bizName: String): String {
    return """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    tools:ignore="MissingDefaultResource">
    <data>

        <variable
            name="vm"
            type="${classPackageName}.${bizName}.viewmodel.${firstUppercase(bizName)}ViewModel" />

        <variable
            name="listener"
            type="${classPackageName}.${bizName}.ui.${firstUppercase(bizName)}Fragment.OnEventListener" />
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:id="@+id/cl_board"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <${appType.fullJLFitView()}
            android:id="@+id/fit_view"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="#ffffffff"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

        <FrameLayout
            android:id="@+id/fl_container"
            android:layout_width="match_parent"
            android:layout_height="@dimen/pt_44"
            android:layout_below="@+id/fit_view"
            android:background="#ffffffff"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@id/fit_view">

            <TextView
                android:id="@+id/tv_title"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:fontFamily="sans-serif-medium"
                android:text="template 标题"
                android:textColor="@color/color_333333"
                android:textSize="@dimen/pt_18" />

            <ImageView
                android:layout_width="@dimen/pt_44"
                android:layout_height="@dimen/pt_44"
                android:onClick="@{()->listener.onBackClick()}"
                android:padding="@dimen/pt_14"
                android:src="@drawable/jlwv_back"
                tools:ignore="ContentDescription" />

        </FrameLayout>

        <com.scwang.smartrefresh.layout.SmartRefreshLayout
            android:id="@+id/srl"
            binding_srl_isRefreshFinish="@{vm.refreshFinish}"
            binding_srl_onRefreshLoadMoreListener="@{listener}"
            app:srlEnableLoadMore="false"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:focusable="true"
            android:focusableInTouchMode="true"
            app:layout_constrainedHeight="true"
            app:layout_constraintTop_toBottomOf="@+id/fl_container"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent">


            <androidx.recyclerview.widget.RecyclerView
                android:id="@+id/rv"
                binding_rv_data="@{vm.listLiveData}"
                binding_rv_noAnim="@{true}"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:orientation="vertical"
                app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager" />
        </com.scwang.smartrefresh.layout.SmartRefreshLayout>

    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
"""
}

fun itemFirstLayoutTemp(classPackageName: String, bizName: String): String {
    return """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    tools:ignore="MissingDefaultResource">

    <data>

        <variable
            name="item"
            type="${classPackageName}.${bizName}.model.${firstUppercase(bizName)}FirstModel" />

        <variable
            name="listener"
            type="${classPackageName}.${bizName}.model.${firstUppercase(bizName)}FirstModel.OnItemEventListener" />
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout

        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="@{()->listener.on${firstUppercase(bizName)}FirstClick(item)}"
        android:orientation="vertical"
        android:padding="@dimen/pt_11">

        <TextView
            android:id="@+id/tvTitle"
            android:text="@{item.title}"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textColor="@color/black"
            android:textSize="@dimen/pt_18"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            tools:text="这是标题" />

        <TextView
            android:text="@{item.summary}"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="@dimen/pt_3"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/tvTitle"
            tools:text="这是第二行内容" />
    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>

"""
}