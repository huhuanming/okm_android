// Generated code from Butter Knife. Do not modify!
package com.okm_android.main.Activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class LoginRegisterActivity$$ViewInjector {
  public static void inject(Finder finder, final com.okm_android.main.Activity.LoginRegisterActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296338, "field 'segmentedGroup'");
    target.segmentedGroup = (info.hoang8f.android.segmented.SegmentedGroup) view;
    view = finder.findRequiredView(source, 2131296339, "field 'login_segmentbutton'");
    target.login_segmentbutton = (android.widget.RadioButton) view;
    view = finder.findRequiredView(source, 2131296340, "field 'register_segmentbutton'");
    target.register_segmentbutton = (android.widget.RadioButton) view;
  }

  public static void reset(com.okm_android.main.Activity.LoginRegisterActivity target) {
    target.segmentedGroup = null;
    target.login_segmentbutton = null;
    target.register_segmentbutton = null;
  }
}
