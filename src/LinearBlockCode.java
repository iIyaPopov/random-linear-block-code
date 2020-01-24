public class LinearBlockCode {
	private int k;
	private int n;
	private int wordCount;
	private Matrix G;
	private Matrix H;
	
	public LinearBlockCode(int k, int n) {
		this.n = n;
		this.k = k;
		this.wordCount = (int) Math.pow(2, k);
		this.G = new Matrix(k, n);
		this.G.generate();
		/*G.set(0, 4, 1);
		G.set(0, 5, 0);
		G.set(0, 6, 1);
		
		G.set(1, 4, 1);
		G.set(1, 5, 1);
		G.set(1, 6, 0);
		
		G.set(2, 4, 0);
		G.set(2, 5, 1);
		G.set(2, 6, 1);
		
		G.set(3, 4, 1);
		G.set(3, 5, 1);
		G.set(3, 6, 1);*/
		
		this.HMatrixGenerate();
	}
	
	private void HMatrixGenerate() {
		int h = this.n - this.k;
		int w = this.n;
		this.H = new Matrix(h, w);
		for (int i = 0; i < h; i++) {
			this.H.set(i, w - h + i, 1);
		}
		Matrix tmp = this.G.T();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w - h; j++) {
				this.H.set(i, j, tmp.get(i + this.k, j));
			}
		}
	}
	
	private String zeroAdder(String s, int size) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < size - s.length(); i++) {
			res.append("0");
		}
		res.append(s);
		return res.toString();
	}
	
	public String getCodeBook() {
		StringBuilder res = new StringBuilder();
		res.append("----------CODE BOOK----------\n");
		for (int i = 0; i < wordCount; i++) {
			String s = Integer.toBinaryString(i);
			s = zeroAdder(s, k);
			Matrix vector = new Matrix(s);
			Matrix m = vector.multiply(this.G);
			res.append(s + " ---> " + m + "\n");
		}
		res.append("-----------------------------\n");
		return res.toString();
	}
	
	public boolean isContains(String vector) {
		Matrix m = new Matrix(vector);
		Matrix res = m.multiply(this.H.T());
		if (res.isZeros()) {
			return true;
		}
		return false;
	}
	
	public void minDistance() {
		int[] codeBook = new int[this.wordCount];
		for (int i = 0; i < this.wordCount; i++) {
			String s = Integer.toBinaryString(i);
			s = zeroAdder(s, k);
			Matrix vector = new Matrix(s);
			Matrix m = vector.multiply(this.G);
			codeBook[i] = Integer.parseInt(m.toString(), 2);
		}
		int min = n + 1;
		for (int i = 0; i < this.wordCount; i++) {
			for (int j = i + 1; j < this.wordCount; j++) {
				int d = Integer.bitCount(codeBook[i] ^ codeBook[j]);
				if (d < min) {
					min = d;
				}
			}
		}
		System.out.println("Minimun distance is " + min);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Word count = " + this.wordCount + "\n\n");
		str.append("G:\n" + this.G + "\n");
		str.append("H:\n" + this.H + "\n");
		str.append(this.getCodeBook());
		return str.toString();
	}
}
