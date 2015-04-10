// Generated code from Butter Knife. Do not modify!
package com.okm_android.main.Activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class SearchActivity$$ViewInjector {
  public static void inject(Finder finder, final com.okm_android.main.Activity.SearchActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296354, "field 'listView'");
    target.listView = (android.widget.ListView) view;
  }

  public static void reset(com.okm_android.main.Activity.SearchActivity target) {
    target.listView = null;
  }
}
