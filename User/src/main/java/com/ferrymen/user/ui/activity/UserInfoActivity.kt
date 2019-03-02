package com.ferrymen.user.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.ferrymen.core.ext.onClick
import com.ferrymen.core.ui.activity.BaseMVPActivity
import com.ferrymen.core.widgets.DateUtils
import com.ferrymen.user.R
import com.ferrymen.user.injection.component.DaggerUserComponent
import com.ferrymen.user.injection.module.UserModule
import com.ferrymen.user.presenter.UserInfoPresenter
import com.ferrymen.user.presenter.view.UserInfoView
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast
import java.io.File

class UserInfoActivity : BaseMVPActivity<UserInfoPresenter>(), UserInfoView, TakePhoto.TakeResultListener {

    private lateinit var mTakePhoto: TakePhoto
    private lateinit var mTempFile: File
    override fun injectComponent() {
        // inject done
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        mTakePhoto = TakePhotoImpl(this, this)
        mTakePhoto.onCreate(savedInstanceState)

        initView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
    }

    private fun initView() {
        mUserIconView.onClick {
            showAlertView()
        }
    }

    private fun showAlertView() {
        AlertView("选择图片", "", "取消", null, arrayOf("拍照", "相册"), this,
                AlertView.Style.ActionSheet, object : OnItemClickListener {
            override fun onItemClick(o: Any?, position: Int) {
                mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
                when(position) {
                    0 -> {
                        createTempFile()
                        mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
                    }
                    1 -> mTakePhoto.onPickFromGallery()
                }
            }

        }).show()
    }

    override fun takeSuccess(result: TResult?) {
        Log.d("TakePhoto", result!!.image.originalPath)
        Log.d("TakePhoto", result!!.image.compressPath)
    }

    override fun takeCancel() {
    }

    override fun takeFail(result: TResult?, msg: String?) {
        Log.e("TakePhoto", msg)
    }

    fun createTempFile() {
        val tempFileName = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            this.mTempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }
        this.mTempFile = File(filesDir, tempFileName)
    }
}

