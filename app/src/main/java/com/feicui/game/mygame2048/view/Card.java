package com.feicui.game.mygame2048.view;

import android.content.Context;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.feicui.game.mygame2048.R;

/**
 * Created by Administrator on 2016/9/27.
 */
public class Card extends FrameLayout {
    private int num;
    private TextView label;
    public static final int TOP_OUT = 1, BOTTOM_OUT = 2,
            LEFT_OUT = 3, RIGHT_OUT = 4;

    public Card(Context context) {
        super(context);

        label = new TextView(getContext());
        label.setTextSize(32);
        label.setGravity(Gravity.CENTER);
        label.setBackgroundColor(0x33CCC0B3);

        LayoutParams lp = new LayoutParams(-1, -1);//填充父布局
        lp.setMargins(10, 10, 0, 0);
        addView(label, lp);

        setNum(0);
    }

    private void setColors(int color) {
        label.setBackgroundColor(color);
        label.setTextColor(0xFFFFFFFF);
    }

    private int[] colors = new int[]{0xFFB2A79B,0xAA6B6C67,0xAA525453,0xFF6D7359,
                                    0xFF636031, 0xFF006658,0xFF6493AF,0xFF17507D,
                                    0xFF1D328A, 0xFFC2483D,0xFFC2483D,0xFFDACE56};

    public void launchBackColor() {
        switch (num) {
            case 0:
                setColors(colors[0]);
                break;
            case 2:
                setColors(colors[1]);
                break;
            case 4:
                setColors(colors[2]);
                break;
            case 8:
                setColors(colors[3]);
                break;
            case 16:
                setColors(colors[4]);
                break;
            case 32:
                setColors(colors[5]);
                break;
            case 64:
                setColors(colors[6]);
                break;
            case 128:
                setColors(colors[7]);
                break;
            case 256:
                setColors(colors[8]);
                break;
            case 512:
                setColors(colors[9]);
                break;
            case 1024:
                setColors(colors[10]);
                break;
            case 2048:
                setColors(colors[11]);
                break;
            default:
                label.setBackgroundResource(R.drawable.jinse);
                label.setTextColor(0xFFDE0404);
                break;
        }

    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;

        if (num <= 0) {
            label.setText("");
            launchBackColor();
        } else {
            scaleAnim();
        }

    }

    public void setNum(int num,int outAnim) {
        this.num = num;

        if (num <= 0) {
            cardOutAnim(outAnim);
        } else {
            scaleAnim();
        }
    }

    //设置不为零的数值时先稍变大再回复正常的动画
    private void scaleAnim() {
        Animation animation = AnimationUtils.loadAnimation(getContext(),
                R.anim.anim_card_scale);
        label.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                label.setText(num + "");
                launchBackColor();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 根据传入参数，设置卡片移出的动画
     * @param outAnim
     */
    private void cardOutAnim(int outAnim) {
        Animation animation = null;
        switch (outAnim) {
            case TOP_OUT:
                animation = AnimationUtils.loadAnimation(getContext(),
                        R.anim.anim_card_top_out);
                break;
            case BOTTOM_OUT:
                animation = AnimationUtils.loadAnimation(getContext(),
                        R.anim.anim_card_bottom_out);
                break;
            case LEFT_OUT:
                animation = AnimationUtils.loadAnimation(getContext(),
                        R.anim.anim_card_left_out);
                break;
            case RIGHT_OUT:
                animation = AnimationUtils.loadAnimation(getContext(),
                        R.anim.anim_card_right_out);
                break;
        }

        label.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                label.setText("");
                launchBackColor();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 判断两张卡片是否相同（只需要看数值是否相同）
     *
     * @param o
     * @return
     */
    public boolean equals(Card o) {
        return getNum() == o.getNum();
    }
}
