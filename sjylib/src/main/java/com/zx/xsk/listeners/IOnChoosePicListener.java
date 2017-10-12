package com.zx.xsk.listeners;

import com.jph.takephoto.model.TResult;

/**
 * 图片选择回调
 * Created by sjy on 2017/10/11.
 */

public interface IOnChoosePicListener {
    void onSuccess(TResult result);
    void onFailure(TResult result, String msg);
    void onCancel();
}
