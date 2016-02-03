package lab.sodino.list_quickaction;

import java.util.Vector;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * @author Sodino E-mail:sodinoopen@hotmail.com
 * @version Time：2011-5-2 下午09:57:23
 */
public class QuickActionBar {
	// 设置了四种动画方式。
	public static final int ANIM_GROW_FROM_LEFT = 1;
	public static final int ANIM_GROW_FROM_RIGHT = 2;
	public static final int ANIM_GROW_FROM_CENTER = 3;
	/** 根据位置自动匹配动画的出现位置。 */
	public static final int ANIM_AUTO = 4;
	public static final Interpolator interpolator;
	/** 即响应点击后显示操作框的组件，本例中为“咧牙”图标。 */
	private View anchor;
	private Vector<ActionItem> vecActions;
	/** 快捷操作框附加在PopupWindow上。 */
	private PopupWindow popupWindow;
	/** 整个快捷操作框布局。 */
	private View qaBarRoot;
	/** 手机屏幕的宽度。 */
	private int phoneScreenWidth;
	/** 手机屏幕的高度。 */
	private int phoneScreenHeight;
	private int animType;
	/** 各种组件的填充器。 */
	private LayoutInflater inflater;
	/** 默认PopupWindow有自己不透明的灰色的背景，可能会与整体风格不符，所以需额外设置背景。 */
	private Drawable popupWindowBackground;
	/** 快捷操作框的向上箭头，可能不显示。 */
	private ImageView arrowUp;
	/** 快捷操作框的向下箭头，可能不显示。 */
	private ImageView arrowDown;
	/** 是否允许ActionsLayout以动画的形式出现。 */
	private boolean enableActionsLayout;
	private Animation actionsLayoutAnim;
	/** 引发此QuickActionBar的ListItem的序号。 */
	private int listItemIndex;

	static {
		interpolator = new CustomInterpolator();
	}

	/**
	 * @param anchor
	 *            快捷操作框将要指示的视图组件。
	 * @param idx
	 *            引发此QuickActionBar的ListItem的序号。
	 */
	public QuickActionBar(View anchor, int idx) {
		if (vecActions == null) {
			vecActions = new Vector<ActionItem>();
		}
		this.anchor = anchor;
		this.listItemIndex = idx;
		Context context = this.anchor.getContext();
		this.popupWindow = new PopupWindow(context);
		// 当显示快捷操作框时，如果用户点击了框外的地方，则框要消失
		popupWindow.setTouchInterceptor(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
					QuickActionBar.this.popupWindow.dismiss();
					// 该事件已处理完返回true。
					return true;
				}
				return false;
			}
		});
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		phoneScreenWidth = windowManager.getDefaultDisplay().getWidth();
		phoneScreenHeight = windowManager.getDefaultDisplay().getHeight();
		// 填充QuickActionBar界面
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		qaBarRoot = (ViewGroup) inflater.inflate(R.layout.qa_bar, null);
		arrowUp = (ImageView) qaBarRoot.findViewById(R.id.qa_arrow_up);
		arrowDown = (ImageView) qaBarRoot.findViewById(R.id.qa_arrow_down);
		animType = ANIM_AUTO;

		actionsLayoutAnim = AnimationUtils.loadAnimation(anchor.getContext(),
				R.anim.anim_actionslayout);
		actionsLayoutAnim.setInterpolator(interpolator);
	}

	/** 设置整个QuickActionBar的出现动画效果。 */
	public void setAnimType(int type) {
		this.animType = type;
	}

	public void setEnableActionsLayoutAnim(boolean bool) {
		enableActionsLayout = bool;
	}

	public void addActionItem(ActionItem actionWeb) {
		vecActions.add(actionWeb);
	}

	public void show() {
		// 确定popupWindow的显示位置
		int[] location = new int[2];
		anchor.getLocationOnScreen(location);
		Rect anchorRect = new Rect(location[0], location[1], location[0] + anchor.getWidth(),
				location[1] + anchor.getHeight());
		// Log.d("ANDROID_LAB", "anchorRect:" + anchorRect.toString());
		qaBarRoot.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		qaBarRoot.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		int rootWidth = qaBarRoot.getMeasuredWidth();
		int rootHeight = qaBarRoot.getMeasuredHeight();
		// 标识快捷操作框箭头尖角的X坐标
		int xPos = (phoneScreenWidth - rootWidth) >> 1;
		// 标识快捷操作框箭头尖角的Y坐标
		int yPos = anchorRect.bottom;
		// 判断指示箭头的位置，并设定快捷操作框的整体位移值
		int diff = -10;
		boolean arrowOnTop = true;
		if (anchorRect.bottom + diff + rootHeight > phoneScreenHeight) {
			arrowOnTop = false;
			yPos = anchorRect.top - rootHeight;
			diff = -diff;
		}
		// 显示/隐藏指定的箭头
		int arrowId = arrowOnTop ? R.id.qa_arrow_up : R.id.qa_arrow_down;
		int marginLeft = anchorRect.centerX() - (arrowUp.getMeasuredWidth() >> 1);
		handleArrow(arrowId, marginLeft);
		// 填充ActionItem组件并添加到actionsLayout中
		// 包括ActionItem头尾的边框及中间的各个ActionItem。
		LinearLayout actionsLayout = (LinearLayout) qaBarRoot.findViewById(R.id.actionsLayout);
		appendActionsItemUI(actionsLayout, vecActions);

		// 设置PopupWindow
		// 设置PopupWindow的出现动画与消失动画
		setAnimationStyle(phoneScreenWidth, anchorRect.centerX(), arrowOnTop);
		// 如果对操作框设置默认背景的话，就显示上去。如果没有的话，则生成一个全空透明的BitmapDrawable替代原有的不符合风格的默认背景。
		if (popupWindowBackground == null) {
			popupWindow.setBackgroundDrawable(new BitmapDrawable());
		} else {
			popupWindow.setBackgroundDrawable(popupWindowBackground);
		}
		popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
		popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
		popupWindow.setTouchable(true);
		popupWindow.setFocusable(true);
		// 对框外的区域可响应，用于点击框外区域时让框消失
		popupWindow.setOutsideTouchable(true);
		// 将显示界面与PopupWindow关联
		popupWindow.setContentView(qaBarRoot);
		popupWindow.showAtLocation(this.anchor, Gravity.NO_GRAVITY, xPos, yPos + diff);

		if (enableActionsLayout) {
			actionsLayout.startAnimation(actionsLayoutAnim);
		}
	}

	/** 填充ActionItem组件并添加到actionsLayout中 */
	private void appendActionsItemUI(ViewGroup actionsLayout, Vector<ActionItem> vec) {
		if (vec == null || vec.size() == 0) {
			return;
		}

		View view = null;
		int idx = 1;
		int size = vec.size();
		for (int i = 0; i < size; i++, idx++) {
			view = getActionItemUI(vecActions.get(i));
			// 将本QuickActionBar设为Tag供处理点击事件时隐藏QuickActionBar
			view.setTag(this);
			actionsLayout.addView(view, idx);
		}
	}

	private View getActionItemUI(ActionItem item) {
		LinearLayout actionItemLayout = (LinearLayout) inflater.inflate(R.layout.action_item, null);
		ImageView icon = (ImageView) actionItemLayout.findViewById(R.id.qa_actionItem_icon);
		TextView txtName = (TextView) actionItemLayout.findViewById(R.id.qa_actionItem_name);
		Drawable drawable = item.getIcon();
		if (drawable == null) {
			icon.setVisibility(View.GONE);
		} else {
			icon.setImageDrawable(drawable);
		}
		String name = item.getActionName();
		if (name == null) {
			txtName.setVisibility(View.GONE);
		} else {
			txtName.setText(name);
		}
		actionItemLayout.setOnClickListener(item.getOnClickListener());
		return actionItemLayout;
	}

	/**
	 * 显示/隐藏相应的箭头，并将箭头尖角的位移至相应的X坐标。
	 * 
	 * @param arrowShowId
	 *            最终显示在屏幕上的箭头ID。
	 * @param marginLeft
	 *            为将箭头尖角设置在anchor中心点的X值处所需的位置值。
	 */
	private void handleArrow(int arrowShowId, int marginLeft) {
		View showArrow = (arrowShowId == R.id.qa_arrow_up) ? arrowUp : arrowDown;
		View hideArrow = (arrowShowId == R.id.qa_arrow_up) ? arrowDown : arrowUp;
		showArrow.setVisibility(View.VISIBLE);
		hideArrow.setVisibility(View.INVISIBLE);
		// 设置箭头的X最终显示的位置，通过margin调整位移量
		ViewGroup.MarginLayoutParams param = (ViewGroup.MarginLayoutParams) showArrow
				.getLayoutParams();
		param.leftMargin = marginLeft;
	}

	/**
	 * 设置PopupWindow的Style选择PopupWindow的显示/隐藏动画。
	 * 
	 * @param screenWidth
	 *            手机屏幕的宽度。
	 * @param coordinateX
	 *            响应图标中心点的X坐标值。
	 * @param arrowOnTop
	 *            标识指示箭头是否在上。
	 */
	private void setAnimationStyle(int screenWidth, int coordinateX, boolean arrowOnTop) {
		int arrowPos = coordinateX - (arrowUp.getMeasuredWidth() >> 1);
		switch (animType) {
		case ANIM_GROW_FROM_LEFT:
			popupWindow.setAnimationStyle((arrowOnTop) ? R.style.QuickActionBar_PopDown_Left
					: R.style.QuickActionBar_PopUp_Left);
			break;
		case ANIM_GROW_FROM_RIGHT:
			popupWindow.setAnimationStyle((arrowOnTop) ? R.style.QuickActionBar_PopDown_Right
					: R.style.QuickActionBar_PopUp_Right);
			break;
		case ANIM_GROW_FROM_CENTER:
			popupWindow.setAnimationStyle((arrowOnTop) ? R.style.QuickActionBar_PopDown_Center
					: R.style.QuickActionBar_PopUp_Center);
			break;
		case ANIM_AUTO:
			if (arrowPos <= screenWidth / 4) {
				popupWindow.setAnimationStyle((arrowOnTop) ? R.style.QuickActionBar_PopDown_Left
						: R.style.QuickActionBar_PopUp_Left);
			} else if (arrowPos > screenWidth / 4 && arrowPos < 3 * (screenWidth / 4)) {
				popupWindow.setAnimationStyle((arrowOnTop) ? R.style.QuickActionBar_PopDown_Center
						: R.style.QuickActionBar_PopUp_Center);
			} else {
				popupWindow.setAnimationStyle((arrowOnTop) ? R.style.QuickActionBar_PopDown_Right
						: R.style.QuickActionBar_PopUp_Right);
			}
			break;
		}
	}

	public void dismissQuickActionBar() {
		popupWindow.dismiss();
	}

	public int getListItemIndex() {
		return listItemIndex;
	}
}