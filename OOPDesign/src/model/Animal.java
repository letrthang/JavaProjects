/**
 * 
 */
package model;

/**
 * it is better to make this class as abstract class so inherit classes are able
 * to override its methods.
 * 
 * @author Thang Le
 *
 */
public abstract class Animal {

	private boolean isFly = false;
	private boolean isWalk = false;
	private boolean isSing = false;
	private boolean isSay = false;
	private boolean iSwim = false;

	public enum Size {
		SMALL, LARGE
	}

	public enum Color {
		GREY, ORANGE
	}

	private Size size;
	private Color color;

	/**
	 * @return the size
	 */
	public Size getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(Size size) {
		this.size = size;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the isFly
	 */
	public boolean isFly() {
		return isFly;
	}

	/**
	 * @param isFly the isFly to set
	 */
	public void setFly(boolean isFly) {
		this.isFly = isFly;
	}

	/**
	 * @return the isWalk
	 */
	public boolean isWalk() {
		return isWalk;
	}

	/**
	 * @param isWalk the isWalk to set
	 */
	public void setWalk(boolean isWalk) {
		this.isWalk = isWalk;
	}

	/**
	 * @return the isSing
	 */
	public boolean isSing() {
		return isSing;
	}

	/**
	 * @param isSing the isSing to set
	 */
	public void setSing(boolean isSing) {
		this.isSing = isSing;
	}

	/**
	 * @return the iSwim
	 */
	public boolean isiSwim() {
		return iSwim;
	}

	/**
	 * @param iSwim the iSwim to set
	 */
	public void setiSwim(boolean iSwim) {
		this.iSwim = iSwim;
	}

	/**
	 * @return the isSay
	 */
	public boolean isSay() {
		return isSay;
	}

	/**
	 * @param isSay the isSay to set
	 */
	public void setSay(boolean isSay) {
		this.isSay = isSay;
	}
}
