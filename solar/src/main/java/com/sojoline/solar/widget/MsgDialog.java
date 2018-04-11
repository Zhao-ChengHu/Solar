package com.sojoline.solar.widget;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.flyco.dialog.widget.base.BaseDialog;
import com.sojoline.solar.R;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2018/03/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MsgDialog extends BaseDialog<MsgDialog> {
	private TextView tvTitle;
	private TextView tvPrompt;
	private TextView tvMsg;
	private Button btRight;

	private String msg;
	private String prompt;
	private String title;

	public MsgDialog(Context context, String title, String msg, String prompt) {
		super(context);
		this.msg = msg;
		this.prompt = prompt;
		this.title = title;
	}

	@Override
	public View onCreateView() {
		widthScale(0.85f);
		View view = View.inflate(mContext, R.layout.solar_dialog_msg, null);
		tvPrompt = (TextView) view.findViewById(R.id.tv_prompt);
		tvTitle = (TextView) view.findViewById(R.id.tv_title);
		tvMsg = (TextView) view.findViewById(R.id.tv_msg);
		btRight = (Button) view.findViewById(R.id.bt_right);
		return view;
	}

	@Override
	public void setUiBeforShow() {
		tvTitle.setText(title);
		tvMsg.setText(msg);
		tvPrompt.setText(prompt);
		btRight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
	}
}
