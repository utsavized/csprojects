package recursion;

import java.util.ArrayList;

public class CoinDenominations {
	public void coinCombo(int [] coins, int n) {
		ArrayList<String> combo = new ArrayList<String>();
		coinCombo(coins, 0, combo, n);
	}
	
	private void coinCombo(int [] coins, int sum, ArrayList<String> combo, int n) {
		if(sum == n) {
			System.out.println(combo.toString());
			return;
		}
		if(sum > n)
			return;
		
		for(int i = 0; i < coins.length; i++) {
			combo.add(getCoinStr(coins[i]));
			coinCombo(coins, sum + coins[i], combo, n);
			combo.remove(combo.size() - 1);
			
		}
	}
	
	private String getCoinStr(int val) {
		StringBuffer coin = new StringBuffer();
		coin.append(Integer.toString(val));
		coin.append("c ");
		return coin.toString();
	}
}
