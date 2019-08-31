import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		String arrangement = "()(((()())(())()))(())";
		System.out.println(new Solution().solution(arrangement));
	}

}
class Solution {
    public int solution(String arrangement) {
    	int answer = 0;
        Stack<Token> s = new Stack<>();
        for (int i = 0; i < arrangement.length(); i++) {
        	char tok = arrangement.charAt(i);
        	if (tok == '(') {
        		s.push(new Token(tok, i));
        	} else { // tok == ')'
        		Token openTok = s.pop();
        		TokenPair tokenPair = new TokenPair(openTok, new Token(tok, i));
        		if (tokenPair.isLaser()) {
        			answer += s.size();
        		} else if (tokenPair.isIronRod()) {
        			answer++;
        		}
        	}
        }
        return answer;
    }
    
}
class Token {
	private char tok;
	private int pos;
	public Token(char tok, int pos) {
		this.tok = tok;
		this.pos = pos;
	}
	public char getTok() {
		return this.tok;
	}
	public int getPos() {
		return this.pos;
	}
	@Override
	public String toString() {
		return this.tok + " : " + this.pos;
	}
}

class TokenPair {
	private Token openTok;	//	(
	private Token closeTok;	//	)
	public TokenPair(Token openTok, Token closeTok) {
		this.openTok = openTok;
		this.closeTok = closeTok;
	}

	public Token getOpenTok() {
		return this.openTok;
	}
	public Token getCloseTok() {
		return this.closeTok;
	}
	public boolean isLaser() {
		if (openTok.getPos()+1 == closeTok.getPos())
			return true;
		else
			return false;
	}
	public boolean isIronRod() {
		return !isLaser();
	}
	
	@Override
	public String toString() {
		if (isLaser())
			return "Laser (" + this.openTok.getPos() + ", " + this.closeTok.getPos() + ")";
		else if (isIronRod())
			return "IronRod (" + this.openTok.getPos() + " " + this.closeTok.getPos() + ")";
		return null;
	}
}