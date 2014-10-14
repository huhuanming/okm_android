// Generated code from Butter Knife. Do not modify!
package com.okm_android.main.Activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class PlaceOrderActivity$$ViewInjector {
  public static void inject(Finder finder, final com.okm_android.main.Activity.PlaceOrderActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230889, "field 'orderChoose'");
    target.orderChoose = (android.widget.RadioButton) view;
    view = finder.findRequiredView(source, 2131230888, "field 'segmentedGroup'");
    target.segmentedGroup = (info.hoang8f.android.segmented.SegmentedGroup) view;
    view = finder.findRequiredView(source, 2131230890, "field 'orderDetail'");
    target.orderDetail = (android.widget.RadioButton) view;
  }

  public static void reset(com.okm_android.main.Activity.PlaceOrderActivity target) {
    target.orderChoose = null;
    target.segmentedGroup = null;
    target.orderDetail = null;
  }
}
