package com.sojoline.base.widget;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.flyco.animation.Attention.Swing;
import com.flyco.dialog.utils.CornerUtils;
import com.flyco.dialog.widget.base.BaseDialog;
import com.sojoline.base.R;
import com.sojoline.base.util.ScreenUtils;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/11/15
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class InputDialog extends BaseDialog<InputDialog> {
	private EditText etContent;
	private TextView tvTitle;
	private TextView tvPrompt;
	private Button btLeft, btRight;
	private OnContentChangedListener listener;
	private String content = "";
	private String title = "";
	private InputFilter filter;
	private String prompt;
	public InputDialog(Context context) {
		super(context);
	}

	@Override
	public View onCreateView() {
		widthScale(0.85f);
		showAnim(new Swing());
		View view = View.inflate(mContext, R.layout.view_dialog_input, null);
		etContent = (EditText) view.findViewById(R.id.et_content);
		tvPrompt = (TextView) view.findViewById(R.id.tv_prompt);
		tvPrompt.setVisibility(View.GONE);
		tvTitle = (TextView) view.findViewById(R.id.tv_title);
		btLeft = (Button) view.findViewById(R.id.bt_left);
		btRight = (Button) view.findViewById(R.id.bt_right);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			view.setBackground(CornerUtils.cornerDrawable(Color.parseColor("#ffffff"),
					ScreenUtils.dp2px(5)));
		}
		return view;
	}

	@Override
	public void setUiBeforShow() {
		etContent.setText(content);
		//设置光标位置
		etContent.setSelection(content.length());
		if (filter != null) {
			etContent.setFilters(new InputFilter[]{filter});
		}

		if (!TextUtils.isEmpty(prompt)){
			tvPrompt.setVisibility(View.VISIBLE);
			tvPrompt.setText(prompt);
		}
		tvTitle.setText(title);
		btLeft.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});

		btRight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (content != null && !content.equals(etContent.getText().toString())){
					if (listener != null) {
						listener.contentChanged(etContent.getText().toString());
					}
				}
				dismiss();
			}
		});
	}

	/**
	 * 设置输入内容
	 * @param content
	 */
	public void setContent(String content){
		if (content != null) {
			this.content = content;
		}
	}

	/**
	 * 设置标题
	 * @param title
	 */
	public void setTitle(String title){
		if (title != null) {
			this.title = title;
		}
	}

	/**
	 * 设置过滤器
	 * @param filter
	 */
	public void setFilter(InputFilter filter){
		this.filter = filter;
	}

	/**
	 * 设置提示
	 * @param prompt
	 */
	public void setPrompt(String prompt){
		this.prompt = prompt;
	}

	public void setOnContentChangedListener(OnContentChangedListener listener){
		this.listener = listener;
	}

	public interface OnContentChangedListener{
		/**
		 * 当内容发生改变时回调
		 * @param content
		 */
		void contentChanged(String content);
	}
}
