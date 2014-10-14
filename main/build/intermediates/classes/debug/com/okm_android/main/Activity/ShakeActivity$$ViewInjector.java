// Generated code from Butter Knife. Do not modify!
package com.okm_android.main.Activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ShakeActivity$$ViewInjector {
  public static void inject(Finder finder, final com.okm_android.main.Activity.ShakeActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230820, "field 'shake_img'");
    target.shake_img = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131230821, "field 'shake_text'");
    target.shake_text = (android.widget.TextView) view;
  }

  public static void reset(com.okm_android.main.Activity.ShakeActivity target) {
    target.shake_img = null;
    target.shake_text = null;
  }
}
