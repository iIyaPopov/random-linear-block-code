import java.util.Random;

public class Matrix {
	private int height;
	private int width;
	private int[][] array;
	
	public Matrix(int height, int width) {
		this.height = height;
		this.width = width;
		this.array = new int[height][width];
	}
	
	public Matrix(String vector) {
		this.height = 1;
		this.width = vector.length();
		this.array = new int[height][width];
		for (int i = 0; i < this.width; i++) {
			if (vector.charAt(i) == '1') {
				this.array[0][i] = 1;
			} else {
				this.array[0][i] = 0;
			}
		}
	}
	
	public void set(int x, int y, int value) {
		this.array[x][y] = value;
	}
	
	public int get(int x, int y) {
		return this.array[x][y];
	}
	
	public void generate() {
		for (int i = 0; i < height; i++) {
			this.array[i][i] = 1;
		}
		Random rand = new Random();
		for (int i = 0; i < height; i++) {
			for (int j = height; j < width; j++) {
				this.array[i][j] = rand.nextInt(2);
			}
		}
	}
	
	public Matrix multiply(Matrix obj) {
		if (this.width != obj.height) {
			return null;
		}
		int h = this.height;
		int w = obj.width;
		Matrix result = new Matrix(h, w);
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int tmp = 0;
				for (int k = 0; k < obj.height; k++) {
					tmp += this.array[i][k] * obj.array[k][j];
				}
				result.array[i][j] = tmp % 2;
			}
		}
		return result;
	}
	
	public Matrix T() {
		int h = this.width;
		int w = this.height;
		Matrix result = new Matrix(h, w);
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				result.array[i][j] = this.array[j][i];
			}
		}
		return result;
	}
	
	public boolean isZeros() {
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				if (this.array[i][j] == 1) {
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				str.append(this.array[i][j]);
			}
			if (height > 1) {
				str.append("\n");
			}
		}
		return str.toString();
	}
}
