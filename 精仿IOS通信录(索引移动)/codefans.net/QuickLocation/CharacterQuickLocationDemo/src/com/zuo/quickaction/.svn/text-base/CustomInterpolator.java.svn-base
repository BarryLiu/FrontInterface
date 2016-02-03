package lab.sodino.list_quickaction;

import android.util.Log;
import android.view.animation.Interpolator;

/**
 * @author Sodino E-mail:sodinoopen@hotmail.com
 * @version Time：2011-5-3 下午08:02:01
 */
public class CustomInterpolator implements Interpolator {

	/**
	 * @param input
	 *            A value between 0 and 1.0 indicating our current point in the
	 *            animation where 0 represents the start and 1.0 represents the
	 *            end
	 * @return Returns The interpolation value. This value can be more than 1.0
	 *         for Interpolators which overshoot their targets, or less than 0
	 *         for Interpolators that undershoot their targets.
	 */
	public float getInterpolation(float input) {
		Log.d("ANDROID_LAB", "input=" + input);
		// 设定动画的加速度变化值。此例的效果是使用actionsLayout超过目标旋转区后再反弹回来。
		// 插值计算公式: 1.2-((x*1.55f)-1.1)^2
		// 画出函数图的话即可观察出动画执行过程中越过目标区再反弹的详细过程。
		// x :0 <= v <= 1.0
		// (x*1.55f) :0 <= v <= 1.55
		// ((x*1.55f)-1.1) :-1.1 <= v <= 0.45
		// ((x*1.55f)-1.1)^2 :0<= v <= 1.21
		// 1.2-((x*1.55f)-1.1)^2 :-0.1 <= v <= 1.2
		final float inner = (input * 1.55f) - 1.1f;
		// 如果返回值为常量1的话，则相当于没有动画效果。
		return 1.2f - inner * inner;
	}
}