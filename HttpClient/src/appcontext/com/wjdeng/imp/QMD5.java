/******************************************************************************** 
 * Create Author   : JoveDeng
 * Create Date     : Jun 10, 2010
 * File Name       : QMD5.java
 *
 ********************************************************************************/
package com.wjdeng.imp;

public class QMD5 {

	private static int hexcase = 1;
	private static String b64pad = "";
	private static int chrsz = 8;
	private static int mode = 32;

	/**
	 * function str2binl(D) { var C = Array(); var A = (1 << chrsz) - 1;
	 * for(var B = 0; B < D.length * chrsz; B += chrsz) { C[B >> 5] |=
	 * (D.charCodeAt(B / chrsz) & A) << (B % 32) } return C }
	 */
	public int[] str2binl(String D) {
		char[] d = String2CharA(D);
		int[] c = new int[d.length];
		int a = (1 << chrsz) - 1;
		for (int b = 0; b < d.length * chrsz; b += chrsz) {
			c[b >> 5] |= (d[b / chrsz] & a) << (b % 32);
		}
		this.printCharArr(c);
		return c;
	}

	/**
	 * function binl2str(C) { var D = ""; var A = (1 << chrsz) - 1; for(var B =
	 * 0; B < C.length * 32; B += chrsz) { D += String.fromCharCode((C[B >> 5]
	 * >>> (B % 32)) & A) } return D }
	 */

	public char[] binl2str(int[] c) {
		String D = "";
		// char[] d = new char[c.length*32/chrsz];
		int a = (1 << chrsz) - 1;
		for (int b = 0; b < c.length * 32; b += chrsz) {
			int t = (c[b >> 5] >>> (b % 32)) & a;
			// char s = (char) t;
			// d[b/chrsz] = s;
			// System.out.print(s + "_");
			if (t != 0)
				D += String.valueOf((char) t);
			// System.out.println(b>>5);
		}
		System.out.print(D);
		return String2CharA(D);
	}

	/***************************************************************************
	 * function binl2hex(C) { var B = hexcase ? "0123456789ABCDEF" :
	 * "0123456789abcdef"; var D = ""; for(var A = 0; A < C.length * 4; A ++ ) {
	 * D += B.charAt((C[A >> 2] >> ((A % 4) * 8 + 4)) & 15) + B.charAt((C[A >>
	 * 2] >> ((A % 4) * 8)) & 15) } return D }
	 * 
	 * @param arra
	 */

	public char[] binl2hex(int[] c) {
		// char[] c = String2CharA(C);
		String b = hexcase == 1 ? "0123456789ABCDEF" : "0123456789abcdef";
		// char[] d = new char[c.length*4];
		String D = "";
		for (int a = 0; a < c.length * 4; a++) {
			int s = b.charAt((c[a >> 2] >> ((a % 4) * 8 + 4)) & 15)
					+ b.charAt((c[a >> 2] >> ((a % 4) * 8)) & 15);
			// d[a] = (char) s;
			D += String.valueOf((char) s);
		}
		char[] d = String2CharA(D);
		return d;
	}

	/**
	 * function binl2b64(D) { var C =
	 * "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"; var F =
	 * ""; for(var B = 0; B < D.length * 4; B += 3) { var E = (((D[B >> 2] >> 8 *
	 * (B % 4)) & 255) << 16) | (((D[B + 1 >> 2] >> 8 * ((B + 1) % 4)) & 255) <<
	 * 8) | ((D[B + 2 >> 2] >> 8 * ((B + 2) % 4)) & 255); for(var A = 0; A < 4;
	 * A ++ ) { if(B * 8 + A * 6 > D.length * 32) { F += b64pad } else { F +=
	 * C.charAt((E >> 6 * (3 - A)) & 63) } } } return F }
	 */
	public char[] binl2b64(String d) {
		char[] D = String2CharA(d);
		String C = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
		String F = "";
		// char[] C = this.String2CharA(c);
		for (int B = 0; B < D.length * 4; B += 3) {
			int E = (((D[B >> 2] >> 8 * (B % 4)) & 255) << 16)
					| (((D[B + 1 >> 2] >> 8 * ((B + 1) % 4)) & 255) << 8)
					| ((D[B + 2 >> 2] >> 8 * ((B + 2) % 4)) & 255);
			for (int A = 0; A < 4; A++) {
				if (B * 8 + A * 6 > D.length * 32) {
					F += QMD5.b64pad;
				} else {
					F += C.charAt((E >> 6 * (3 - A)) & 63);
				}
			}
		}
		return this.String2CharA(F);
	}

	/***************************************************************************
	 * 
	 * function safe_add(A, D) { var C = (A & 65535) + (D & 65535); var B = (A >>
	 * 16) + (D >> 16) + (C >> 16); return(B << 16) | (C & 65535) }
	 */
	private int safe_add(int A, int D) {
		int C = (A & 65535) + (D & 65535);
		int B = (A >> 16) + (D >> 16) + (C >> 16);
		return (B << 16) | (C & 65535);
	}

	/***************************************************************************
	 * 
	 * function bit_rol(A, B) { return(A << B) | (A >>> (32 - B)) }
	 */
	private int bit_rol(int A, int B) {
		return (A << B) | (A >>> (32 - B));
	}

	/**
	 * 
	 * function md5_cmn(F, C, B, A, E, D) { return
	 * safe_add(bit_rol(safe_add(safe_add(C, F), safe_add(A, D)), E), B) }
	 */
	private int md5_cmn(int F, int C, int B, int A, int E, int D) {
		return safe_add(bit_rol(safe_add(safe_add(C, F), safe_add(A, D)), E), B);
	}

	/**
	 * function md5_ff(C, B, G, F, A, E, D) { return md5_cmn((B & G) | (( ~ B) &
	 * F), C, B, A, E, D) } function md5_gg(C, B, G, F, A, E, D) { return
	 * md5_cmn((B & F) | (G & ( ~ F)), C, B, A, E, D) } function md5_hh(C, B, G,
	 * F, A, E, D) { return md5_cmn(B ^ G ^ F, C, B, A, E, D) } function
	 * md5_ii(C, B, G, F, A, E, D) { return md5_cmn(G ^ (B | ( ~ F)), C, B, A,
	 * E, D) }
	 */
	private int md5_ff(int C, int B, int G, int F, int A, int E, int D) {
		return md5_cmn((B & G) | ((~B) & F), C, B, A, E, D);
	}

	private int md5_gg(int C, int B, int G, int F, int A, int E, int D) {
		return md5_cmn((B & G) | ((~B) & F), C, B, A, E, D);
	}

	private int md5_hh(int C, int B, int G, int F, int A, int E, int D) {
		return md5_cmn(B ^ G ^ F, C, B, A, E, D);
	}

	private int md5_ii(int C, int B, int G, int F, int A, int E, int D) {
		return md5_cmn(G ^ (B | (~F)), C, B, A, E, D);
	}

	/**
	 * function hex_md5(A) { return binl2hex(core_md5(str2binl(A), A.length *
	 * chrsz)) }
	 */

	private char[] hex_md5(String A) {
		// return binl2hex(core_md5(str2binl(A), A.length() * chrsz));
		int[] at = this.str2binl(A);
		int[] cm5 = this.core_md5(at, A.length() * chrsz);
		return this.binl2hex(cm5);
		// this.binl2hex(this.co)
	}

	private int[] core_md5(int[] K, int F) {
		K[F >> 5] |= 128 << ((F) % 32);
		K[(((F + 64) >>> 9) << 4) + 14] = F;
		int J = 1732584193;
		int I = -271733879;
		int H = -1732584194;
		int G = 271733878;
		for (int C = 0; C < K.length; C += 16) {
			int E = J;
			int D = I;
			int B = H;
			int A = G;
			J = md5_ff(J, I, H, G, K[C + 0], 7, -680876936);
			G = md5_ff(G, J, I, H, K[C + 1], 12, -389564586);
			H = md5_ff(H, G, J, I, K[C + 2], 17, 606105819);
			I = md5_ff(I, H, G, J, K[C + 3], 22, -1044525330);
			J = md5_ff(J, I, H, G, K[C + 4], 7, -176418897);
			G = md5_ff(G, J, I, H, K[C + 5], 12, 1200080426);
			H = md5_ff(H, G, J, I, K[C + 6], 17, -1473231341);
			I = md5_ff(I, H, G, J, K[C + 7], 22, -45705983);
			J = md5_ff(J, I, H, G, K[C + 8], 7, 1770035416);
			G = md5_ff(G, J, I, H, K[C + 9], 12, -1958414417);
			H = md5_ff(H, G, J, I, K[C + 10], 17, -42063);
			I = md5_ff(I, H, G, J, K[C + 11], 22, -1990404162);
			J = md5_ff(J, I, H, G, K[C + 12], 7, 1804603682);
			G = md5_ff(G, J, I, H, K[C + 13], 12, -40341101);
			H = md5_ff(H, G, J, I, K[C + 14], 17, -1502002290);
			I = md5_ff(I, H, G, J, K[C + 15], 22, 1236535329);
			J = md5_gg(J, I, H, G, K[C + 1], 5, -165796510);
			G = md5_gg(G, J, I, H, K[C + 6], 9, -1069501632);
			H = md5_gg(H, G, J, I, K[C + 11], 14, 643717713);
			I = md5_gg(I, H, G, J, K[C + 0], 20, -373897302);
			J = md5_gg(J, I, H, G, K[C + 5], 5, -701558691);
			G = md5_gg(G, J, I, H, K[C + 10], 9, 38016083);
			H = md5_gg(H, G, J, I, K[C + 15], 14, -660478335);
			I = md5_gg(I, H, G, J, K[C + 4], 20, -405537848);
			J = md5_gg(J, I, H, G, K[C + 9], 5, 568446438);
			G = md5_gg(G, J, I, H, K[C + 14], 9, -1019803690);
			H = md5_gg(H, G, J, I, K[C + 3], 14, -187363961);
			I = md5_gg(I, H, G, J, K[C + 8], 20, 1163531501);
			J = md5_gg(J, I, H, G, K[C + 13], 5, -1444681467);
			G = md5_gg(G, J, I, H, K[C + 2], 9, -51403784);
			H = md5_gg(H, G, J, I, K[C + 7], 14, 1735328473);
			I = md5_gg(I, H, G, J, K[C + 12], 20, -1926607734);
			J = md5_hh(J, I, H, G, K[C + 5], 4, -378558);
			G = md5_hh(G, J, I, H, K[C + 8], 11, -2022574463);
			H = md5_hh(H, G, J, I, K[C + 11], 16, 1839030562);
			I = md5_hh(I, H, G, J, K[C + 14], 23, -35309556);
			J = md5_hh(J, I, H, G, K[C + 1], 4, -1530992060);
			G = md5_hh(G, J, I, H, K[C + 4], 11, 1272893353);
			H = md5_hh(H, G, J, I, K[C + 7], 16, -155497632);
			I = md5_hh(I, H, G, J, K[C + 10], 23, -1094730640);
			J = md5_hh(J, I, H, G, K[C + 13], 4, 681279174);
			G = md5_hh(G, J, I, H, K[C + 0], 11, -358537222);
			H = md5_hh(H, G, J, I, K[C + 3], 16, -722521979);
			I = md5_hh(I, H, G, J, K[C + 6], 23, 76029189);
			J = md5_hh(J, I, H, G, K[C + 9], 4, -640364487);
			G = md5_hh(G, J, I, H, K[C + 12], 11, -421815835);
			H = md5_hh(H, G, J, I, K[C + 15], 16, 530742520);
			I = md5_hh(I, H, G, J, K[C + 2], 23, -995338651);
			J = md5_ii(J, I, H, G, K[C + 0], 6, -198630844);
			G = md5_ii(G, J, I, H, K[C + 7], 10, 1126891415);
			H = md5_ii(H, G, J, I, K[C + 14], 15, -1416354905);
			I = md5_ii(I, H, G, J, K[C + 5], 21, -57434055);
			J = md5_ii(J, I, H, G, K[C + 12], 6, 1700485571);
			G = md5_ii(G, J, I, H, K[C + 3], 10, -1894986606);
			H = md5_ii(H, G, J, I, K[C + 10], 15, -1051523);
			I = md5_ii(I, H, G, J, K[C + 1], 21, -2054922799);
			J = md5_ii(J, I, H, G, K[C + 8], 6, 1873313359);
			G = md5_ii(G, J, I, H, K[C + 15], 10, -30611744);
			H = md5_ii(H, G, J, I, K[C + 6], 15, -1560198380);
			I = md5_ii(I, H, G, J, K[C + 13], 21, 1309151649);
			J = md5_ii(J, I, H, G, K[C + 4], 6, -145523070);
			G = md5_ii(G, J, I, H, K[C + 11], 10, -1120210379);
			H = md5_ii(H, G, J, I, K[C + 2], 15, 718787259);
			I = md5_ii(I, H, G, J, K[C + 9], 21, -343485551);
			J = safe_add(J, E);
			I = safe_add(I, D);
			H = safe_add(H, B);
			G = safe_add(G, A);
		}
		if (mode == 16) {
			return new int[] { I, H };
		} else {
			return new int[] { J, I, H, G };
		}
	}

	/**
	 * function core_hmac_md5(C, F) { var E = str2binl(C); if(E.length > 16) { E =
	 * core_md5(E, C.length * chrsz) } var A = Array(16), D = Array(16); for(var
	 * B = 0; B < 16; B ++ ) { A[B] = E[B] ^ 909522486; D[B] = E[B] ^ 1549556828 }
	 * var G = core_md5(A.concat(str2binl(F)), 512 + F.length * chrsz); return
	 * core_md5(D.concat(G), 512 + 128) } private int core_hmac_md5(int C, int
	 * F){ int E = str2binl(C); if(E.length > 16) { E = core_md5(E, C.length *
	 * chrsz) } var A = Array(16), D = Array(16); for(var B = 0; B < 16; B ++ ) {
	 * A[B] = E[B] ^ 909522486; D[B] = E[B] ^ 1549556828 } var G =
	 * core_md5(A.concat(str2binl(F)), 512 + F.length * chrsz); return
	 * core_md5(D.concat(G), 512 + 128); }
	 */

	private char[] String2CharA(String D) {
		char[] d = new char[D.length()];
		for (int i = 0; i < D.length(); i++) {
			d[i] = D.charAt(i);
		}
		return d;

	}

	public void printCharArr(int[] arra) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arra.length; i++) {
			sb.append(arra[i]).append(",");
		}
		System.out.println(sb.toString());
	}

	public QMD5() {

	};

	public static void main(String[] arg) {

		int t = -256;
		char s = (char) t;
		System.out.println(String.valueOf(s));

		char ts = '';
		int is = ts;
		char[] c = new char[2];
		int i = c[0];
		QMD5 ut = new QMD5();
		char[] d = { '1', 'w' };
		int[] tem = ut.str2binl("1w");
		ut.binl2str(tem);
		ut.hex_md5("abc");
		// return hex_md5("abc") == "900150983cd24fb0d6963f7d28e17f72"

	}
}
