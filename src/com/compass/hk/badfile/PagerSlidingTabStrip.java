package com.compass.hk.badfile;

import java.util.Locale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.webdesign688.compass.R;

public class PagerSlidingTabStrip extends HorizontalScrollView {

	public interface IconTabProvider {
		public int getPageIconResId(int position);
	}

	// @formatter:off
	private static final int[] ATTRS = new int[] { android.R.attr.textSize,
			android.R.attr.textColor };
	// @formatter:on

	private LinearLayout.LayoutParams defaultTabLayoutParams;
	private LinearLayout.LayoutParams expandedTabLayoutParams;

	private final PageListener pageListener = new PageListener();
	public OnPageChangeListener delegatePageListener;

	private LinearLayout tabsContainer;
	private ViewPager pager;

	private int tabCount;

	private int currentPosition = 0;
	private float currentPositionOffset = 0f;

	private Paint rectPaint;
	private Paint dividerPaint;

	private int indicatorColor = 0xFF666666;
	private int underlineColor = 0x1A000000;
	private int dividerColor = 0x1A000000;

	private boolean shouldExpand = false;
	private boolean textAllCaps = true;

	private int scrollOffset = 52;
	private int indicatorHeight = 4;
	private int underlineHeight = 1;
	private int dividerPadding = 12;
	private int tabPadding = 0;
	private int dividerWidth = 1;

	private int tabTextSize = 16;
	private int tabTextColor = 0xFF666666;
	@SuppressWarnings("unused")
	private Typeface tabTypeface = null;
	// private int tabTypefaceStyle = Typeface.BOLD;

	private int lastScrollX = 0;

	private int tabBackgroundResId = R.drawable.background_tab;
	private Locale locale;

	// 第二个tab的消息数量提示
	TextView tvNum;
	// 第三个tab的未读消息提示
	TextView tvFriendPromt;

	public PagerSlidingTabStrip(Context context) {
		this(context, null);
	}

	public PagerSlidingTabStrip(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PagerSlidingTabStrip(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);

		setFillViewport(true);
		setWillNotDraw(false);

		tabsContainer = new LinearLayout(context);
		tabsContainer.setOrientation(LinearLayout.HORIZONTAL);
		tabsContainer.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		addView(tabsContainer);

		DisplayMetrics dm = getResources().getDisplayMetrics();

		scrollOffset = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, scrollOffset, dm);
		indicatorHeight = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, indicatorHeight, dm);
		underlineHeight = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, underlineHeight, dm);
		dividerPadding = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, dividerPadding, dm);
		tabPadding = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, tabPadding, dm);
		dividerWidth = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, dividerWidth, dm);
		tabTextSize = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_SP, tabTextSize, dm);

		// get system attrs (android:textSize and android:textColor)

		TypedArray a = context.obtainStyledAttributes(attrs, ATTRS);

		tabTextSize = a.getDimensionPixelSize(0, tabTextSize);
		tabTextColor = a.getColor(1, tabTextColor);

		a.recycle();

		// get custom attrs

		a = context.obtainStyledAttributes(attrs,
				R.styleable.PagerSlidingTabStrip);

		indicatorColor = a.getColor(
				R.styleable.PagerSlidingTabStrip_pstsIndicatorColor,
				indicatorColor);
		underlineColor = a.getColor(
				R.styleable.PagerSlidingTabStrip_pstsUnderlineColor,
				underlineColor);
		dividerColor = a
				.getColor(R.styleable.PagerSlidingTabStrip_pstsDividerColor,
						dividerColor);
		indicatorHeight = a.getDimensionPixelSize(
				R.styleable.PagerSlidingTabStrip_pstsIndicatorHeight,
				indicatorHeight);
		underlineHeight = a.getDimensionPixelSize(
				R.styleable.PagerSlidingTabStrip_pstsUnderlineHeight,
				underlineHeight);
		dividerPadding = a.getDimensionPixelSize(
				R.styleable.PagerSlidingTabStrip_pstsDividerPadding,
				dividerPadding);
		tabPadding = a.getDimensionPixelSize(
				R.styleable.PagerSlidingTabStrip_pstsTabPaddingLeftRight,
				tabPadding);
		tabBackgroundResId = a.getResourceId(
				R.styleable.PagerSlidingTabStrip_pstsTabBackground,
				tabBackgroundResId);
		shouldExpand = a
				.getBoolean(R.styleable.PagerSlidingTabStrip_pstsShouldExpand,
						shouldExpand);
		scrollOffset = a
				.getDimensionPixelSize(
						R.styleable.PagerSlidingTabStrip_pstsScrollOffset,
						scrollOffset);
		textAllCaps = a.getBoolean(
				R.styleable.PagerSlidingTabStrip_pstsTextAllCaps, textAllCaps);

		a.recycle();

		rectPaint = new Paint();
		rectPaint.setAntiAlias(true);
		rectPaint.setStyle(Style.FILL);

		dividerPaint = new Paint();
		dividerPaint.setAntiAlias(true);
		dividerPaint.setStrokeWidth(dividerWidth);

		defaultTabLayoutParams = new LinearLayout.LayoutParams(0,
				LayoutParams.MATCH_PARENT, 1);
		expandedTabLayoutParams = new LinearLayout.LayoutParams(0,
				LayoutParams.MATCH_PARENT, 1.0f);

		if (locale == null) {
			locale = getResources().getConfiguration().locale;
		}
	}

	public void setViewPager(ViewPager pager) {
		this.pager = pager;

		if (pager.getAdapter() == null) {
			throw new IllegalStateException(
					"ViewPager does not have adapter instance.");
		}

		pager.setOnPageChangeListener(pageListener);

		notifyDataSetChanged();
	}

	public void setOnPageChangeListener(OnPageChangeListener listener) {
		this.delegatePageListener = listener;
	}

	public void notifyDataSetChanged() {

		tabsContainer.removeAllViews();

		tabCount = pager.getAdapter().getCount();

		for (int i = 0; i < tabCount; i++) {

			if (pager.getAdapter() instanceof IconTabProvider) {
				addIconTab(i,
						((IconTabProvider) pager.getAdapter())
								.getPageIconResId(i));
			} else {
				addTextTab(i, pager.getAdapter().getPageTitle(i).toString());
			}
			// 初始化textView字体颜色
			View childAt = tabsContainer.getChildAt(i);
			if (childAt instanceof TextView) {
				TextView itemTextView = (TextView) childAt;
				if (i == pager.getCurrentItem())
					itemTextView.setTextColor(getResources().getColor(
							R.color.main_tab_text_selected));
				else
					itemTextView.setTextColor(getResources().getColor(
							R.color.main_tab_text_normal));
			}
		}

		TextView v = (TextView) tabsContainer
				.getChildAt(pager.getCurrentItem());
		v.setTextColor(getResources().getColor(R.color.main_tab_text_selected));
		updateTabStyles();

		getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@SuppressWarnings("deprecation")
					@SuppressLint("NewApi")
					@Override
					public void onGlobalLayout() {

						if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
							getViewTreeObserver().removeGlobalOnLayoutListener(
									this);
						} else {
							getViewTreeObserver().removeOnGlobalLayoutListener(
									this);
						}

						currentPosition = pager.getCurrentItem();
						scrollToChild(currentPosition, 0);
					}
				});

	}

	private void addTextTab(final int position, String title) {
		TextView tab = new TextView(getContext());
		tab.setText(title);
		tab.setGravity(Gravity.CENTER);
		tab.setSingleLine();
		if (position == 1) {
			// 聊天，显示未读数量
			// 第二个tab需要加入消息提示数量
			RelativeLayout relativeLayout = new RelativeLayout(getContext());
			android.widget.RelativeLayout.LayoutParams tabPara = new android.widget.RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			tabPara.addRule(RelativeLayout.CENTER_IN_PARENT);
			tabPara.setMargins(5, 0, 5, 0);
			tab.setLayoutParams(tabPara);
			tab.setId(R.id.tv_mes_tab);
			relativeLayout.addView(tab);
			tvNum = new TextView(getContext());
			tvNum.setLayoutParams(new LayoutParams(UIHelper.dip2px(
					getContext(), 5), UIHelper.dip2px(getContext(), 5)));
			tvNum.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
			tvNum.setVisibility(View.GONE);
			tvNum.setTextColor(getContext().getResources().getColor(
					R.color.white));
			tvNum.setGravity(Gravity.CENTER);

			int height = UIHelper.dip2px(getContext(), 18);
			android.widget.RelativeLayout.LayoutParams lp = new android.widget.RelativeLayout.LayoutParams(
					height, height);
			lp.addRule(RelativeLayout.RIGHT_OF, R.id.tv_mes_tab);
			lp.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.tv_mes_tab);
			lp.setMargins(UIHelper.dip2px(getContext(), 1), 0, 0,
					UIHelper.dip2px(getContext(), 3));
			tvNum.setLayoutParams(lp);

			relativeLayout.addView(tvNum);
			relativeLayout.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					pager.setCurrentItem(position);
				}
			});
			tabsContainer.addView(relativeLayout, position,
					shouldExpand ? expandedTabLayoutParams
							: defaultTabLayoutParams);
		} else if (position == 2) {
			// 好友，当有未读显示一个小红点
			RelativeLayout relativeLayout = new RelativeLayout(getContext());
			android.widget.RelativeLayout.LayoutParams tabPara = new android.widget.RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			tabPara.addRule(RelativeLayout.CENTER_IN_PARENT);
			tab.setLayoutParams(tabPara);
			tab.setId(R.id.tv_mes_tab2);
			relativeLayout.addView(tab);
			tvFriendPromt = new TextView(getContext());
			tvFriendPromt.setLayoutParams(new LayoutParams(UIHelper.dip2px(
					getContext(), 5), UIHelper.dip2px(getContext(), 5)));
			tvFriendPromt.setVisibility(View.GONE);
			tvFriendPromt.setGravity(Gravity.CENTER);

			int height = UIHelper.dip2px(getContext(), 8 );
			android.widget.RelativeLayout.LayoutParams lp = new android.widget.RelativeLayout.LayoutParams(
					height, height);
			lp.addRule(RelativeLayout.RIGHT_OF, R.id.tv_mes_tab2);
			lp.addRule(RelativeLayout.CENTER_VERTICAL);
			lp.setMargins(UIHelper.dip2px(getContext(), 5), 0, 0,
					UIHelper.dip2px(getContext(), 3));
			tvFriendPromt.setLayoutParams(lp);

			relativeLayout.addView(tvFriendPromt);
			relativeLayout.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					pager.setCurrentItem(position);
				}
			});
			tabsContainer.addView(relativeLayout, position,
					shouldExpand ? expandedTabLayoutParams
							: defaultTabLayoutParams);
		} else {
			addTab(position, tab);
		}

	}

	private void addIconTab(final int position, int resId) {

		ImageButton tab = new ImageButton(getContext());
		tab.setImageResource(resId);

		addTab(position, tab);

	}

	private void addTab(final int position, View tab) {
		tab.setFocusable(true);
		tab.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pager.setCurrentItem(position);
			}
		});

		tab.setPadding(tabPadding, 0, tabPadding, 0);
		tabsContainer
				.addView(tab, position, shouldExpand ? expandedTabLayoutParams
						: defaultTabLayoutParams);
	}

	private void updateTabStyles() {
		// tabsContainer.setBackgroundResource(tabBackgroundResId);
		for (int i = 0; i < tabCount; i++) {

			View v = tabsContainer.getChildAt(i);

			v.setBackgroundResource(tabBackgroundResId);
			// v.setBackgroundColor(getResources().getColor(tabBackgroundColorResId));
			TextView tab = null;
			if (v instanceof TextView) {
				tab = (TextView) v;
			} else if (v instanceof RelativeLayout) {
				tab = (TextView) v.findViewById(R.id.tv_mes_tab);
				if (tab == null) {
					tab = (TextView) v.findViewById(R.id.tv_mes_tab2);
				}
			}
			if (tab != null) {
				tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSize);
				// tab.setTypeface(tabTypeface, tabTypefaceStyle);
				// tab.setTextColor(tabTextColor);
				// tab.setTypeface(tabTypeface, tabTypefaceStyle);
				// tab.setTextColor(tabTextColor);

				// setAllCaps() is only available from API 14, so the upper case
				// is made manually if we are on a
				// pre-ICS-build
				if (textAllCaps) {
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
						tab.setAllCaps(true);
					} else {
						tab.setText(tab.getText().toString()
								.toUpperCase(locale));
					}
				}
			}
		}

	}

	private void scrollToChild(int position, int offset) {

		if (tabCount == 0) {
			return;
		}

		int newScrollX = tabsContainer.getChildAt(position).getLeft() + offset;

		if (position > 0 || offset > 0) {
			newScrollX -= scrollOffset;
		}

		if (newScrollX != lastScrollX) {
			lastScrollX = newScrollX;
			scrollTo(newScrollX, 0);
		}

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (isInEditMode() || tabCount == 0) {
			return;
		}

		final int height = getHeight();

		// draw indicator line

		rectPaint.setColor(indicatorColor);

		// default: line below current tab
		View currentTab = tabsContainer.getChildAt(currentPosition);
		float lineLeft = currentTab.getLeft();
		float lineRight = currentTab.getRight();

		// if there is an offset, start interpolating left and right coordinates
		// between current and next tab
		if (currentPositionOffset > 0f && currentPosition < tabCount - 1) {

			View nextTab = tabsContainer.getChildAt(currentPosition + 1);
			final float nextTabLeft = nextTab.getLeft();
			final float nextTabRight = nextTab.getRight();

			lineLeft = (currentPositionOffset * nextTabLeft + (1f - currentPositionOffset)
					* lineLeft);
			lineRight = (currentPositionOffset * nextTabRight + (1f - currentPositionOffset)
					* lineRight);
		}

		canvas.drawRect(lineLeft, height - indicatorHeight, lineRight, height,
				rectPaint);

		// draw underline

		rectPaint.setColor(underlineColor);
		canvas.drawRect(0, height - underlineHeight, tabsContainer.getWidth(),
				height, rectPaint);

		// draw divider

		// dividerPaint.setColor(dividerColor);
		// for (int i = 0; i < tabCount - 1; i++) {
		// View tab = tabsContainer.getChildAt(i);
		// canvas.drawLine(tab.getRight(), dividerPadding, tab.getRight(),
		// height - dividerPadding, dividerPaint);
		// }
	}

	private class PageListener implements OnPageChangeListener {

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {

			currentPosition = position;
			currentPositionOffset = positionOffset;

			scrollToChild(position, (int) (positionOffset * tabsContainer
					.getChildAt(position).getWidth()));

			invalidate();

			if (delegatePageListener != null) {
				delegatePageListener.onPageScrolled(position, positionOffset,
						positionOffsetPixels);
			}
		}

		@Override
		public void onPageScrollStateChanged(int state) {
			if (state == ViewPager.SCROLL_STATE_IDLE) {
				scrollToChild(pager.getCurrentItem(), 0);
			}

			if (delegatePageListener != null) {
				delegatePageListener.onPageScrollStateChanged(state);
			}
		}

		@Override
		public void onPageSelected(int position) {
			for (int i = 0; i < tabCount; i++) {
				View v = tabsContainer.getChildAt(i);

				v.setBackgroundResource(tabBackgroundResId);
				// v.setBackgroundColor(getResources().getColor(tabBackgroundColorResId));
				TextView tab = null;
				if (v instanceof TextView) {
					tab = (TextView) v;
				} else if (v instanceof RelativeLayout) {
					tab = (TextView) v.findViewById(R.id.tv_mes_tab);
					if (tab == null) {
						tab = (TextView) v.findViewById(R.id.tv_mes_tab2);
					}
				}
				if (tab != null) {
					if (i == position)
						tab.setTextColor(getResources().getColor(
								R.color.main_tab_text_selected));
					else
						tab.setTextColor(getResources().getColor(
								R.color.main_tab_text_normal));
				}

			}
			if (delegatePageListener != null) {
				delegatePageListener.onPageSelected(position);
			}
		}

	}

	public void setIndicatorColor(int indicatorColor) {
		this.indicatorColor = indicatorColor;
		invalidate();
	}

	public void setIndicatorColorResource(int resId) {
		this.indicatorColor = getResources().getColor(resId);
		invalidate();
	}

	public int getIndicatorColor() {
		return this.indicatorColor;
	}

	public void setIndicatorHeight(int indicatorLineHeightPx) {
		this.indicatorHeight = indicatorLineHeightPx;
		invalidate();
	}

	public int getIndicatorHeight() {
		return indicatorHeight;
	}

	public void setUnderlineColor(int underlineColor) {
		this.underlineColor = underlineColor;
		invalidate();
	}

	public void setUnderlineColorResource(int resId) {
		this.underlineColor = getResources().getColor(resId);
		invalidate();
	}

	public int getUnderlineColor() {
		return underlineColor;
	}

	public void setDividerColor(int dividerColor) {
		this.dividerColor = dividerColor;
		invalidate();
	}

	public void setDividerColorResource(int resId) {
		this.dividerColor = getResources().getColor(resId);
		invalidate();
	}

	public int getDividerColor() {
		return dividerColor;
	}

	public void setUnderlineHeight(int underlineHeightPx) {
		this.underlineHeight = underlineHeightPx;
		invalidate();
	}

	public int getUnderlineHeight() {
		return underlineHeight;
	}

	public void setDividerPadding(int dividerPaddingPx) {
		this.dividerPadding = dividerPaddingPx;
		invalidate();
	}

	public int getDividerPadding() {
		return dividerPadding;
	}

	public void setScrollOffset(int scrollOffsetPx) {
		this.scrollOffset = scrollOffsetPx;
		invalidate();
	}

	public int getScrollOffset() {
		return scrollOffset;
	}

	public void setShouldExpand(boolean shouldExpand) {
		this.shouldExpand = shouldExpand;
		requestLayout();
	}

	public boolean getShouldExpand() {
		return shouldExpand;
	}

	public boolean isTextAllCaps() {
		return textAllCaps;
	}

	public void setAllCaps(boolean textAllCaps) {
		this.textAllCaps = textAllCaps;
	}

	public void setTextSize(int textSizePx) {
		this.tabTextSize = textSizePx;
		updateTabStyles();
	}

	public int getTextSize() {
		return tabTextSize;
	}

	public void setTextColor(int textColor) {
		this.tabTextColor = textColor;
		updateTabStyles();
	}

	public void setTextColorResource(int resId) {
		this.tabTextColor = getResources().getColor(resId);
		updateTabStyles();
	}

	public int getTextColor() {
		return tabTextColor;
	}

	public void setTypeface(Typeface typeface, int style) {
		this.tabTypeface = typeface;
		// this.tabTypefaceStyle = style;
		updateTabStyles();
	}

	public void setTabBackground(int resId) {
		this.tabBackgroundResId = resId;
	}

	public int getTabBackground() {
		return tabBackgroundResId;
	}

	public void setTabPaddingLeftRight(int paddingPx) {
		this.tabPadding = paddingPx;
		updateTabStyles();
	}

	public int getTabPaddingLeftRight() {
		return tabPadding;
	}

	@Override
	public void onRestoreInstanceState(Parcelable state) {
		SavedState savedState = (SavedState) state;
		super.onRestoreInstanceState(savedState.getSuperState());
		currentPosition = savedState.currentPosition;
		requestLayout();
	}

	@Override
	public Parcelable onSaveInstanceState() {
		Parcelable superState = super.onSaveInstanceState();
		SavedState savedState = new SavedState(superState);
		savedState.currentPosition = currentPosition;
		return savedState;
	}

	static class SavedState extends BaseSavedState {
		int currentPosition;

		public SavedState(Parcelable superState) {
			super(superState);
		}

		private SavedState(Parcel in) {
			super(in);
			currentPosition = in.readInt();
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			super.writeToParcel(dest, flags);
			dest.writeInt(currentPosition);
		}

		public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
			@Override
			public SavedState createFromParcel(Parcel in) {
				return new SavedState(in);
			}

			@Override
			public SavedState[] newArray(int size) {
				return new SavedState[size];
			}
		};
	}

	/**
	 * 设置未读消息数,0为隐藏
	 * 
	 * @author yangsq
	 * @createDate 2014-9-11
	 * @param num
	 */
	public void setUnreadNum(int num) {
		if (tvNum != null) {
			if (num > 0) {
				tvNum.setVisibility(View.VISIBLE);
				if(num >= 100){
					tvNum.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
					tvNum.setText("···");
				}else{
					tvNum.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
					tvNum.setText(num + "");
				}
				
			} else {
				tvNum.setVisibility(View.GONE);
			}
		}
	}

	/**
	 * 设置新的朋友的提醒
	 * @author yangsq
	 * @createDate 2014-9-20
	 * @param isVisible
	 */
	public void setFriendMesVisible(boolean isVisible) {
		if (isVisible) {
			tvFriendPromt.setVisibility(View.VISIBLE);
		} else {
			tvFriendPromt.setVisibility(View.GONE);
		}
	}
}
