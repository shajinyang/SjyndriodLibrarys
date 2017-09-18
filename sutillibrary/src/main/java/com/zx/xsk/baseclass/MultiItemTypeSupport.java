package com.zx.xsk.baseclass;

public interface MultiItemTypeSupport<T>
{
    int getLayoutId(int itemType);

    int getItemViewType(int position, T t);
}