/*
 * This file is part of HE_Mesh, a library for creating and manipulating meshes.
 * It is dedicated to the public domain. To the extent possible under law,
 * I , Frederik Vanhoutte, have waived all copyright and related or neighboring
 * rights.
 *
 * This work is published from Belgium. (http://creativecommons.org/publicdomain/zero/1.0/)
 *
 */
package wblut.math;

/**
 *
 *
 * @author Frederik Vanhoutte, W:Blut
 *
 *         A parameter which is linear in a parameter, clamped or unclamped on a
 *         range
 */
public class WB_LinearScalarParameter implements WB_ScalarParameter {

	double lowT, highT;
	double lowValue, highValue;
	double rangeT, rangeValue;
	boolean clamp;

	public WB_LinearScalarParameter(final double lowT, final double highT, final double lowValue,
			final double highValue) {
		if (lowT <= highT) {
			this.lowT = lowT;
			this.highT = highT;
			this.lowValue = lowValue;
			this.highValue = highValue;
		} else {

			this.lowT = highT;
			this.highT = lowT;
			this.lowValue = highValue;
			this.highValue = lowValue;
		}
		rangeT = this.highT - this.lowT;
		rangeValue = this.highValue - this.lowValue;
		clamp = false;
	}

	public WB_LinearScalarParameter(final double lowT, final double highT, final double lowValue,
			final double highValue, final boolean clamp) {
		if (lowT <= highT) {
			this.lowT = lowT;
			this.highT = highT;
			this.lowValue = lowValue;
			this.highValue = highValue;
		} else {

			this.lowT = highT;
			this.highT = lowT;
			this.lowValue = highValue;
			this.highValue = lowValue;
		}
		rangeT = this.highT - this.lowT;
		rangeValue = this.highValue - this.lowValue;
		this.clamp = clamp;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wblut.math.WB_ScalarParameter#evaluate(double[])
	 */
	@Override
	public double evaluate(final double... x) {
		if (rangeT == 0) {
			return lowValue;
		}
		if (clamp) {
			if (x[0] <= lowT) {
				return lowValue;
			}
			if (x[0] >= highT) {
				return highValue;
			}
		}

		return (x[0] - lowT) / rangeT * rangeValue + lowValue;
	}
}
