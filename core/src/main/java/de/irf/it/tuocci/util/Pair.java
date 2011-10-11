/*
 * // $Id: Pair.java 1537 2010-07-17 14:42:25Z alexp $ //
 *
 * tGSF -- teikoku Grid Scheduling Framework
 *
 * Copyright (c) 2006-2009 by the
 *   Robotics Research Institute (Section Information Technology)
 *   at TU Dortmund University, Germany
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the
 *
 *   Free Software Foundation, Inc.,
 *   51 Franklin St, Fifth Floor,
 *   Boston, MA 02110, USA
 */
package de.irf.it.tuocci.util;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * A container type for keeping pairs of arbitrary objects together.
 * 
 * This class provides a simple bin for pairs of objects. Equality of a pair is
 * determined by the equality of its content, that is, both elements must pass
 * the equality test compared to the other pair.
 * 
 * @author <a href="mailto:alexander.papaspyrou@udo.edu">Alexander
 *         Papaspyrou</a>
 * @since 0.1 (mini)
 * @version $Revision: 1537 $ (as of $Date: 2010-07-17 16:42:25 +0200 (Sat, 17 Jul 2010) $ by $Author: alexp $)
 * 
 * @param <L>
 *            The desired type of the pair's "left" part.
 * @param <R>
 *            The desired type of the pair's "right" part.
 * 
 */
public class Pair<L, R> {

	/**
	 * Holds the "left" part of the pair.
	 * 
	 */
	private L left;

	/**
	 * Holds the "right" part of the pair.
	 * 
	 */
	private R right;

	/**
	 * Creates a new instance of this class, using the given parameters.
	 * 
	 */
	public Pair() {
		this.left = null;
		this.right = null;
	}

	/**
	 * Creates a new instance of this class, using the given parameters.
	 * 
	 * @param left
	 *            The initial "left" part of the new pair.
	 * @param right
	 *            The initial "right" part of the new pair.
	 */
	public Pair(final L left, final R right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * Returns the "left" part of this pair.
	 * 
	 * @return The "left" part of this pair.
	 */
	public L getLeft() {
		return this.left;
	}

	/**
	 * Sets the "left" part of this pair.
	 * 
	 * @param left
	 *            The new value for the "left" part of this pair.
	 */
	public void setLeft(final L left) {
		this.left = left;
	}

	/**
	 * Returns the "right" part of this pair.
	 * 
	 * @return The "right" part of this pair.
	 */
	public R getRight() {
		return this.right;
	}

	/**
	 * Sets the "right" part of this pair.
	 * 
	 * @param left
	 *            The new value for the "right" part of this pair.
	 */
	public void setRight(final R right) {
		this.right = right;
	}

	// -------------------------------------------------------------------------
	// Implementation/Overrides for java.lang.Object
	// -------------------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (other == null) {
			return false;
		} // if
		if (this == other) {
			return true;
		} // if
		if (other instanceof Pair<?, ?>) {
			final Pair<?, ?> candidate = ( Pair<?, ?> )other;
			return new EqualsBuilder().append(this.left, candidate.left)
					.append(this.right, candidate.right).isEquals();
		} // if
		else {
			return false;
		} // else
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.left).append(this.right)
				.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append(this.left).append(this.right)
				.toString();
	}
}
