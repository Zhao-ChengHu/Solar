package com.sojoline.energy.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.sojoline.base.view.BaseCompatActivity;
import com.sojoline.energy.R;
import com.sojoline.presenter.common.suggest.SuggestContract;
import com.sojoline.presenter.common.suggest.SuggestPresenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/10/24
 *     desc   : 投诉建议界面
 *     version: 1.0
 * </pre>
 */
@Route(path = "/app/login/suggest")
public class SuggestActivity extends BaseCompatActivity implements SuggestContract.View{
	@BindView(R.id.et_suggest)
	EditText etSuggest;
	@BindView(R.id.tv_num)
	TextView tvNum;

	private SuggestPresenter presenter;

	public static void navigation() {
		ARouter.getInstance().build("/app/login/suggest").navigation();
	}

	@Override
	protected void setContentView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_suggest);
	}

	@Override
	protected void initPresenter() {
		super.initPresenter();
		presenter = new SuggestPresenter();
		presenter.attachView(this);
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		super.initView(savedInstanceState);
		initToolbarNav("意见反馈");
		etSuggest.addTextChangedListener(watcher);
	}

	@OnClick(R.id.bt_submit)
	public void onViewClicked() {
		String suggestion = etSuggest.getText().toString().trim();
		if (suggestion.length() < 6){
			showToast("建议至少6个字");
		}else {
			HashMap<String, Object> map = new HashMap<>();
			map.put("remark", suggestion);
			map.put("msgType", 1);
			map.put("subType", "意见反馈");
			presenter.suggest(map);
		}
	}

	TextWatcher watcher = new TextWatcher() {
		private CharSequence temp;
		private int editStart;
		private int editEnd;

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			temp = s;
			tvNum.setText("还可以输入" + (200 - s.length()) + "字");
		}

		@Override
		public void afterTextChanged(Editable s) {
			editStart = etSuggest.getSelectionStart();
			editEnd = etSuggest.getSelectionEnd();
			//防止粘贴过多文字
			if (temp.length() > 230){
				etSuggest.setText(temp.toString().substring(0,200));
			}else if (temp.length() > 200) {
				s.delete(editStart - 1, editEnd);
				int tempSelection = editEnd;
				etSuggest.setText(s);
				etSuggest.setSelection(tempSelection);
			}
		}
	};

	@Override
	public void showLoading(String msg) {

	}

	@Override
	public void showNormal() {

	}

	@Override
	public void requestFailed(String msg) {
		if (TextUtils.isEmpty(msg)){
			showToast(R.string.network_not_available);
		}else {
			showToast(msg);
		}
	}

	@Override
	public void success() {
		showToast("提交成功");
		finish();
	}

	@Override
	protected void destroyPresenter() {
		super.destroyPresenter();
		presenter.detachView();
	}
}
