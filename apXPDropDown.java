 
package Menu;
import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Menu;
import java.awt.MenuComponent;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;
import netscape.javascript.JSObject;

public class apXPDropDown extends Applet implements Runnable {
	Thread III;
	int II1;
	int IIl;
	Image I1I;
	Image[] I11;
	Graphics IlI;
	int Il1;
	int Ill;
	int lII;
	int lI1;
	int lIl;
	int l1I;
	int I1;
	int lIl1I;
	int lIlI1;
	int lIlIl;
	String[][] l1l;
	String[] llI;
	String[] ll1;
	String[] lll;
	String l1II;
	String l1I1;
	String llllI;
	String lI1ll;
	URL[] l1Il;
	URL[] l11I;
	int[] l111;
	int[] l11l;
	int[] l1lI;
	int[] lll11;
	int[][] l1l1;
	int[] lll1I;
	int[] llll1;
	int l1ll;
	int lIII;
	int lllIl;
	int lll1l;
	String lI11l;
	URL lI1lI;
	int lI1l1;
	int[] lIIll;
	int[] lIlII;
	int llIIl;
	PopupMenu[] lI11;
	MenuItem[] lI1l;
	AudioClip lIlI;
	AudioClip lIl1;
	Frame lIll;
	boolean llII;
	boolean lIIlI;
	boolean llIl;
	boolean ll1I;
	boolean ll11;
	boolean ll1l;
	boolean lllI;
	boolean lll1;
	boolean llll;
	int I1II;
	int lIIl1;
	int I1Il;
	int I11I;
	int lllII;
	int lllI1;
	int I111;
	int I11l;
	int I1lI;
	int I1l1;
	int I1ll;
	String IIII;
	String III1;
	String IIIl;
	String II1I;
	int II11;
	int II1l;
	int IIlI;
	int[][] lIllI = { { 20, 17, 17, 17, 17, 17, 17 },
			{ 20, 30, 31, 31, 31, 30, 30 }, { 30, 34, 34, 34, 32, 31, 30 },
			{ 34, 33, 31, 31, 30, 29, 28 }, { 34, 31, 29, 29, -1, -1, -1 },
			{ 32, 29, 27, -1, -1, -1, -1 }, { 31, 28, 26, -1, -1, -1, -1 },
			{ 30, 27, -1, -1, -1, -1, -1 }, { 30, 26, -1, -1, -1, -1, -1 } };
	int[][] lIll1 = { { 17, 16, 15, 24, -1 }, { 29, 28, 27, 20, 16 },
			{ -1, -1, 28, 22, -1 }, { -1, 28, 27, -1, -1 } };
	int[][] lIl1l = { { -1, -1, 25, -1, -1 }, { -1, -1, 24, 21, -1 },
			{ 25, 24, 22, 20, -1 }, { -1, 21, 20, 11, 18 },
			{ -1, -1, -1, 18, -1 } };
	int[][] lIl11 = { { -1, 28, -1, -1 }, { -1, 27, 25, -1 },
			{ 18, 11, 24, 23 }, { -1, 18, -1, -1 } };
	int I;

	public apXPDropDown() {
		this.lIl1I = 3;
		this.lIlI1 = 2;
		this.lIlIl = 2;
		this.llllI = "Loading...";
		this.lI1ll = "";
		this.l1ll = -1;
		this.lllIl = -2;
		this.lll1l = -2;
		this.lI11l = "_";
		this.lI1l1 = -1;
		this.llIIl = 20;
		this.llII = true;
		this.lIIlI = true;
		this.llIl = false;
		this.ll1I = false;
		this.ll11 = true;
		this.ll1l = true;
		this.lllI = false;
		this.lll1 = true;
		this.llll = false;
		this.I1II = 2383580;
		this.lIIl1 = 2383580;
		this.I1Il = 16777215;
		this.I11I = 16777215;
		this.lllII = -1;
		this.lllI1 = -1;
		this.I1ll = 0;
		this.IIII = "";
		this.III1 = "left";
		this.IIIl = "link";
		this.II1I = ",";
		this.II11 = 11;
		this.II1l = 0;
		this.IIlI = 0;
		this.I = -1;
	}

	String I(String paramString) {
		char[] arrayOfChar = paramString.toCharArray();
		for (int i = 0; i < arrayOfChar.length; i++) {
			arrayOfChar[i] = ((char) (arrayOfChar[i] - i % 3 - 1));
		}
		return new String("");
	}

	public void II(String paramString) {
		if ("0123456789".indexOf(paramString.substring(11, 12)) != -1) {
			paramString = getParameter(paramString);
		}
		if (paramString != null) {
			if (paramString.startsWith("javascript:")) {
				paramString = paramString.substring(11);
			}
			try {
				JSObject.getWindow(this).eval(paramString);
			} catch (Throwable localThrowable) {
				try {
					JSObject localJSObject = JSObject.getWindow(this);
					String str = "";
					StringTokenizer localStringTokenizer = new StringTokenizer(
							paramString, "()");
					paramString = localStringTokenizer.nextToken().trim();
					if (localStringTokenizer.hasMoreTokens()) {
						str = localStringTokenizer.nextToken();
					}
					localStringTokenizer = new StringTokenizer(str.trim(), "'");
					String[] arrayOfString = new String[16];
					int i = 0;
					arrayOfString[0] = "";
					while (localStringTokenizer.hasMoreTokens()) {
						arrayOfString[i] = localStringTokenizer.nextToken()
								.trim();
						if (arrayOfString[i].equals(",")) {
							arrayOfString[i] = null;
						} else {
							i++;
						}
					}
					JSObject.getWindow(this).call(paramString, arrayOfString);
				} catch (Exception localException) {
				}
			}
		}
	}

	public void IIl1() {
		this.lII = 0;
		this.Ill = 0;
		this.Il1 = 0;
		this.l1II = getParameter("Copyright");

		this.llll = true;

		this.I1II = lII1l("backColor", 16, this.I1II);
		if (this.I1II == 64537) {
			this.I1II = SystemColor.control.getRGB();
		}
		this.lIIl1 = this.I1II;
		this.I1Il = lII1l("fontColor", 16, this.I1Il);
		if (this.I1Il == 64537) {
			this.I1Il = SystemColor.controlText.getRGB();
		}
		this.I11I = this.I1Il;
		this.l1II = getParameter("loadingString");
		if (this.l1II != null) {
			this.llllI = this.l1II;
		}
		this.II1 = size().width;
		this.IIl = size().height;
		this.II1 = lII1l("Width", 10, this.II1);
		this.IIl = lII1l("Height", 10, this.IIl);
		for (Object localObject1 = this; (localObject1 != null)
				&& (!(localObject1 instanceof Frame)); localObject1 = ((Component) localObject1)
				.getParent()) {
			this.lIll = ((Frame) localObject1);
			if (this.lIll != null) {
				this.lIll.setCursor(3);
			}
		}

		this.I1I = createImage(this.II1, this.IIl);
		this.IlI = this.I1I.getGraphics();
		this.lIIll = new int[this.llIIl * 2 + 1];
		this.lIIll = lII11(this.I1II);
		this.I11l = this.lIIll[0];
		this.I111 = this.lIIll[30];
		lIII1();
		this.IlI.setFont(new Font("Arial", 0, 11));
		this.IlI.setColor(new Color(this.I1Il));
		this.IlI.drawString(this.llllI, 10, 15);
		IlI1();
		this.I1ll = lII1l("buttonType", 10, this.I1ll);
		this.lllIl = (lII1l("pressedItem", 10, this.lllIl) - 1);
		this.lIIl1 = lII1l("buttonColor", 16, this.lIIl1);
		if (this.lIIl1 == 64537) {
			this.lIIl1 = SystemColor.textHighlight.getRGB();
		}
		this.lIlII = new int[this.llIIl * 2 + 1];
		this.lIlII = lII11(this.lIIl1);
		this.I1l1 = this.lIlII[0];
		this.I1lI = this.lIlII[30];
		this.I11I = lII1l("fontHighColor", 16, this.I11I);
		if (this.I11I == 64537) {
			this.I11I = SystemColor.textHighlightText.getRGB();
		}
		this.lllII = lII1l("shadowColor", 16, this.lllII);
		this.lllI1 = lII1l("shadowHighColor", 16, this.lllI1);
		this.l1II = getParameter("isHorizontal");
		if ((this.l1II != null) && (this.l1II.equalsIgnoreCase("false"))) {
			this.llII = false;
		}
		this.l1II = getParameter("3DBackground");
		if ((this.l1II != null) && (this.l1II.equalsIgnoreCase("false"))) {
			this.lIIlI = false;
			this.lIl1I = 0;
			this.lIlI1 = 0;
			this.lIlIl = 0;
		}
		this.l1II = getParameter("solidArrows");
		if ((this.l1II != null) && (this.l1II.equalsIgnoreCase("true"))) {
			this.ll1I = true;
		}
		this.l1II = getParameter("popupOver");
		if ((this.l1II != null) && (this.l1II.equalsIgnoreCase("true"))) {
			this.llIl = true;
		}
		this.l1II = getParameter("showArrows");
		if ((this.l1II != null) && (this.l1II.equalsIgnoreCase("false"))) {
			this.ll11 = false;
		}
		this.l1II = getParameter("systemSubFont");
		if ((this.l1II != null) && (this.l1II.equalsIgnoreCase("false"))) {
			this.ll1l = false;
		}
		this.l1II = getParameter("alignText");
		if (this.l1II != null) {
			this.III1 = this.l1II;
		}
		this.l1II = getParameter("status");
		if (this.l1II != null) {
			this.IIIl = this.l1II;
		}
		this.l1II = getParameter("statusString");
		if (this.l1II != null) {
			this.l1I1 = this.l1II;
		}
		try {
			this.l1II = getParameter("overSound");
			if (this.l1II != null) {
				this.lIlI = getAudioClip(getDocumentBase(), this.l1II);
			}
			this.l1II = getParameter("clickSound");
			if (this.l1II != null) {
				this.lIl1 = getAudioClip(getDocumentBase(), this.l1II);
			}
		} catch (Exception localException1) {
		}
		this.l1II = getParameter("font");
		Object localObject2;
		if (this.l1II != null) {
			localObject2 = new StringTokenizer(this.l1II, ",");
			this.IIII = ((StringTokenizer) localObject2).nextToken();
			this.II11 = Integer.parseInt(((StringTokenizer) localObject2)
					.nextToken());
			this.II1l = Integer.parseInt(((StringTokenizer) localObject2)
					.nextToken());
		}
		this.IlI.setFont(new Font(this.IIII, this.II1l, this.II11));
		this.l1II = getParameter("delimiter");
		if (this.l1II != null) {
			this.II1I = this.l1II;
		}
		this.l1II = getParameter("encoding");
		if (this.l1II != null) {
			this.lI1ll = this.l1II;
		}
		this.l1II = getParameter("menuItemsFile");
		Object localObject3;
		if (this.l1II != null) {
			try {
				localObject2 = new URL(getDocumentBase(), this.l1II);
				this.l1II = "";
				localObject3 = new BufferedReader(new InputStreamReader(
						((URL) localObject2).openStream()));
				String str1;
				while ((str1 = ((BufferedReader) localObject3).readLine()) != null) {
					this.l1II += str1;
				}
			} catch (Exception localException2) {
			}
		} else {
			this.l1II = getParameter("menuItems");
		}
		this.l1II = this.l1II.substring(0, this.l1II.lastIndexOf("}"));
		StringTokenizer localStringTokenizer1 = new StringTokenizer(this.l1II,
				"}");
		this.lII = localStringTokenizer1.countTokens();
		for (localStringTokenizer1 = new StringTokenizer(this.l1II, "}"); localStringTokenizer1
				.hasMoreTokens();) {
			localObject3 = localStringTokenizer1.nextToken();
			localObject3 = ((String) localObject3).substring(
					((String) localObject3).indexOf("{") + 1).trim();
			if (((String) localObject3).startsWith("|")) {
				this.Il1 += 1;
			}
		}
		localStringTokenizer1 = new StringTokenizer(this.l1II, "}");
		this.Ill = this.lII;
		this.lII = (this.Ill - this.Il1);
		this.l111 = new int[this.lII];
		this.l11l = new int[this.lII];
		this.l1lI = new int[this.lII];
		this.lll11 = new int[this.lII];
		this.l1l1 = new int[this.lII][10];
		this.lll1I = new int[this.lII];
		this.llll1 = new int[this.lII];
		this.l1l = new String[this.lII][10];
		this.l1Il = new URL[this.lII];
		this.llI = new String[this.lII];
		this.lI11 = new PopupMenu[this.lII];
		this.lI1l = new MenuItem[this.Il1];
		this.ll1 = new String[this.Il1];
		this.l11I = new URL[this.Il1];
		this.lll = new String[this.Il1];
		this.I11 = new Image[this.lII];
		this.lIl = 0;
		this.l1I = 0;
		int i = 0;
		int j = 0;
		String str2 = "";
		int i1;
		int i2;
		while (localStringTokenizer1.hasMoreTokens()) {
			this.l1II = localStringTokenizer1.nextToken();
			this.l1II = this.l1II.substring(this.l1II.indexOf("{") + 1);
			StringTokenizer localStringTokenizer2 = new StringTokenizer(
					this.l1II, this.II1I);
			String str3 = "";
			String str4 = "";
			i1 = this.l1II.indexOf("javascript:");
			i2 = this.l1II.lastIndexOf(")") + 1;
			if ((i1 != -1) && (i2 > i1)) {
				str3 = this.l1II.substring(i1, i2);
				i1 = this.l1II.lastIndexOf(this.II1I) + 1;
				i2 = this.l1II.lastIndexOf(".") + 4;
				if (i2 > i1) {
					str4 = this.l1II.substring(i1, i2);
				}
			}
			String str5 = localStringTokenizer2.nextToken();
			if (this.lI1ll != "") {
				try {
					str5 = new String(str5.getBytes(), this.lI1ll);
				} catch (Exception localException3) {
				}
			}
			if (!this.l1II.trim().startsWith("|")) {
				this.l1l[this.l1I][0] = str5;
				StringTokenizer localStringTokenizer3 = new StringTokenizer(
						this.l1l[this.l1I][0], "\\");
				for (int i5 = 0; localStringTokenizer3.hasMoreTokens(); i5++) {
					this.l1l[this.l1I][i5] = localStringTokenizer3.nextToken();
					this.llll1[this.l1I] = i5;
					if (i5 > 1) {
						j += i5 - 1;
					}
				}
			
				if (this.l1l[this.l1I][0].equals("_")) {
					this.l1l[this.l1I][0] = "";
				}
				if (this.l1l[this.l1I][0].trim().equals("-")) {
					i++;
					this.l1I += 1;
				} else if (!localStringTokenizer2.hasMoreTokens()) {
					this.l1I += 1;
				} else {
					if (localStringTokenizer2.countTokens() == 1) {
						this.I11[this.l1I] = IlII(localStringTokenizer2
								.nextToken());
					} else {
						this.l1II = localStringTokenizer2.nextToken().trim();
						if (!this.l1II.startsWith("javascript:")) {
							try {
								this.l1Il[this.l1I] = new URL(
										getDocumentBase(), this.l1II);
							} catch (Exception localException5) {
							}
							this.llI[this.l1I] = localStringTokenizer2
									.nextToken();
						} else if (str3 == "") {
							this.llI[this.l1I] = this.l1II;
							this.l1II = localStringTokenizer2.nextToken();
						} else {
							this.llI[this.l1I] = str3;
							if (str4 != "") {
								this.I11[this.l1I] = IlII(str4);
							}
							this.l1I += 1;
							continue;
						}
						if (localStringTokenizer2.hasMoreTokens()) {
							this.I11[this.l1I] = IlII(localStringTokenizer2
									.nextToken());
						}
					}
					this.l1I += 1;
				}
			} else {
				this.llI[(this.l1I - 1)] = ("__menu" + (this.l1I - 1) + "_");
				this.ll1[this.lIl] = str5;
				if (localStringTokenizer2.hasMoreTokens()) {
					this.l1II = localStringTokenizer2.nextToken().trim();
					if (!this.l1II.startsWith("javascript:")) {
						try {
							this.l11I[this.lIl] = new URL(getDocumentBase(),
									this.l1II);
						} catch (Exception localException4) {
						}
						this.lll[this.lIl] = localStringTokenizer2.nextToken();
					} else if (str3 == "") {
						this.lll[this.lIl] = this.l1II;
					} else {
						this.lll[this.lIl] = str3;
					}
				}
				this.lll[this.lIl] = (this.lll[this.lIl] + this.llI[(this.l1I - 1)]);
				this.lIl += 1;
			}
		}
		for (int k = 0; k < this.lII; k++) {
			if ((this.llI[k] != null) && (this.llI[k].startsWith("__menu"))) {
				this.lI11[k] = new PopupMenu();
				Illl(this.lI11[k], k, 1);
				add(this.lI11[k]);
			}
		}
		if (!this.llII) {
			this.lI1 = ((this.IIl - i * 8 - (this.lIl1I + this.lIlI1) - (this.II11 + 1)
					* j) / (this.lII - i));
		} else {
			this.lI1 = (this.IIl - (this.lIl1I + this.lIlI1));
		}
		this.lIl = 0;
		int m = 0;
		int n;
		if (this.llII) {
			for (n = 0; n < this.lII; n++) {
				if (!this.l1l[n][0].trim().equals("-")) {
					this.lll1I[n] = (this.lI1 / 2 - (this.II11 + 1)
							* (this.llll1[n] - 1) / 2 + this.II11 / 2 - 1);
					this.l1lI[n] = 0;
					for (i1 = 0; i1 < this.llll1[n]; i1++) {
						if (this.l1lI[n] < this.IlI.getFontMetrics()
								.stringWidth(this.l1l[n][i1])) {
							this.l1lI[n] = this.IlI.getFontMetrics()
									.stringWidth(this.l1l[n][i1]);
						}
					}
					if ((this.llI[n].startsWith("__menu")) && (this.ll11)) {
						m = 12;
						this.l1lI[n] += m;
					}
					if (this.I11[n] != null) {
						this.l1lI[n] += this.I11[n].getWidth(this) + 3;
					}
					this.lIl += this.l1lI[n];
				}
			}
			this.l111[0] = this.lIlIl;
			for (i1 = 0; i1 < this.lII; i1++) {
				m = 0;
				if ((this.llI[i1] != null)
						&& (this.llI[i1].startsWith("__menu")) && (this.ll11)) {
					m = this.II11;
				}
				this.lll11[i1] = this.lI1;
				i2 = (this.II1 - 10 * i - this.lIl) / (this.lII - i) / 2;
				if (this.l1l[i1][0].trim().equals("-")) {
					this.l1lI[i1] = 10;
				} else {
					this.l1lI[i1] += i2 * 2;
				}
				if (i1 > 0) {
					this.l111[i1] = (this.l111[(i1 - 1)] + this.l1lI[(i1 - 1)]);
				}
				this.l11l[i1] = this.lIl1I;
				int i3 = 0;
				if (this.I11[i1] != null) {
					i3 = this.I11[i1].getWidth(this);
				}
				for (int i4 = 0; i4 < this.llll1[i1]; i4++) {
					this.l1l1[i1][i4] = ((this.l1lI[i1]
							- this.IlI.getFontMetrics().stringWidth(
									this.l1l[i1][i4]) + i3 - m + 3) / 2);
					if (this.III1.equalsIgnoreCase("right")) {
						this.l1l1[i1][i4] = (this.l1l1[i1][i4] * 2 - i3
								- this.II11 + 3);
					}
					if (this.III1.equalsIgnoreCase("left")) {
						this.l1l1[i1][i4] = (this.II11 / 2 + i3 + 3);
					}
				}
			}
			this.l1lI[(this.lII - 1)] = (this.II1 - this.l111[(this.lII - 1)] - this.lIlIl);
			IlI1();
		} else {
			this.lIl = this.lIl1I;
			for (n = 0; n < this.lII; n++) {
				this.l111[n] = this.lIlIl;
				this.l1lI[n] = (this.II1 - this.lIlIl * 2);
				if (this.l1l[n][0].trim().equals("-")) {
					this.l11l[n] = this.lIl;
					this.lIl += 10;
				} else {
					if ((this.llI[n].startsWith("__menu")) && (this.ll11)) {
						m = this.II11;
					}
					this.lll1I[n] = (this.lI1 / 2 + this.II11 / 2 - 1);
					this.l11l[n] = this.lIl;
					this.lll11[n] = (this.lI1 + (this.llll1[n] - 1)
							* (this.II11 + 1));
					this.lIl += this.lll11[n];
					for (i1 = 0; i1 < this.llll1[n]; i1++) {
						this.l1l1[n][i1] = (this.II11 / 2 + this.IIlI + 3);
						if (this.III1.equalsIgnoreCase("right")) {
							this.l1l1[n][i1] = (this.II1
									- this.IlI.getFontMetrics().stringWidth(
											this.l1l[n][i1]) - this.II11 - m);
						}
						if (this.III1.equalsIgnoreCase("center")) {
							this.l1l1[n][i1] = ((this.II1 - this.IlI
									.getFontMetrics().stringWidth(
											this.l1l[n][i1])) / 2);
						}
					}
				}
			}
		}
		System.gc();
	}

	int IIll(int paramInt) {
		if (paramInt > 255) {
			return 255;
		}
		if (paramInt < 0) {
			return 0;
		}
		return paramInt;
	}

	public void Il1I(int paramInt1, int paramInt2, int paramInt3) {
		if ((!this.llI[paramInt1].startsWith("__menu")) || (!this.ll11)) {
			return;
		}
		int i = this.I111;
		int j = this.I11l;
		int k = this.I1Il;
		int n;
		if (paramInt3 != 0) {
			int m = this.I1lI;
			n = this.I1l1;
			k = this.I11I;
		}
		int m = 0;
		int i1;
		if (this.llII) {
			n = 0;
			i1 = 0;
			for (int i2 = 0; i2 < this.llll1[paramInt1]; i2++) {
				if (n < this.IlI.getFontMetrics().stringWidth(
						this.l1l[paramInt1][i2])) {
					n = this.IlI.getFontMetrics().stringWidth(
							this.l1l[paramInt1][i2]);
					i1 = i2;
				}
			}
			int i3 = this.l11l[paramInt1] + this.lll11[paramInt1] / 2 - 3
					+ paramInt2;
			int i4 = this.l111[paramInt1] + this.l1lI[paramInt1] - 12;
			if (this.III1.equalsIgnoreCase("center")) {
				i4 = this.l111[paramInt1] + this.l1lI[paramInt1]
						- this.l1l1[paramInt1][i1] - 6;
				if (this.I11[paramInt1] != null) {
					i4 += this.I11[paramInt1].getWidth(this);
				}
				if (this.l1l[paramInt1][0] == "") {
					i4 = this.l111[paramInt1] + this.l1l1[paramInt1][i1];
				}
			}
			int i5;
			if (this.ll1I) {
				this.IlI.setColor(new Color(k));
				for (i5 = 0; i5 < 4; i5++) {
					this.IlI.drawLine(i4 + i5, i3 + 2 + i5, i4 + 6 - i5, i3 + 2
							+ i5);
				}
			} else {
				this.IlI.setColor(new Color(k));
				for (i5 = 0; i5 < 2; i5++) {
					this.IlI.drawLine(i4 + 2, i3 + i5 * 4, i4 + 4, i3 + 2 + i5
							* 4);
					this.IlI.drawLine(i4 + 4, i3 + 2 + i5 * 4, i4 + 6, i3 + i5
							* 4);
					this.IlI.drawLine(i4 + 2, i3 + 1 + i5 * 4, i4 + 4, i3 + 3
							+ i5 * 4);
					this.IlI.drawLine(i4 + 4, i3 + 3 + i5 * 4, i4 + 6, i3 + 1
							+ i5 * 4);
				}
			}
		} else {
			n = this.l11l[paramInt1] + this.lll11[paramInt1] / 2 - 3
					+ paramInt2;
			if (this.ll1I) {
				this.IlI.setColor(new Color(k));
				for (i1 = 0; i1 < 4; i1++) {
					this.IlI.drawLine(this.l111[paramInt1]
							+ this.l1lI[paramInt1] - 12 + i1, n + i1,
							this.l111[paramInt1] + this.l1lI[paramInt1] - 12
									+ i1, n + 6 - i1);
				}
			} else {
				this.IlI.setColor(new Color(k));
				for (i1 = 0; i1 < 2; i1++) {
					this.IlI.drawLine(this.l111[paramInt1]
							+ this.l1lI[paramInt1] - 12 + i1 * 4, n,
							this.l111[paramInt1] + this.l1lI[paramInt1] - 10
									+ i1 * 4, n + 2);
					this.IlI.drawLine(this.l111[paramInt1]
							+ this.l1lI[paramInt1] - 12 + i1 * 4, n + 4,
							this.l111[paramInt1] + this.l1lI[paramInt1] - 10
									+ i1 * 4, n + 2);
					this.IlI.drawLine(this.l111[paramInt1]
							+ this.l1lI[paramInt1] - 13 + i1 * 4, n,
							this.l111[paramInt1] + this.l1lI[paramInt1] - 11
									+ i1 * 4, n + 2);
					this.IlI.drawLine(this.l111[paramInt1]
							+ this.l1lI[paramInt1] - 13 + i1 * 4, n + 4,
							this.l111[paramInt1] + this.l1lI[paramInt1] - 11
									+ i1 * 4, n + 2);
				}
			}
		}
	}

	public void Il1l(int paramInt1, int paramInt2) {
		if (this.I11[paramInt1] == null) {
			return;
		}
		if (this.llII) {
			int i = 0;
			int j = 0;
			for (int k = 0; k < this.llll1[paramInt1]; k++) {
				if (i < this.IlI.getFontMetrics().stringWidth(
						this.l1l[paramInt1][k])) {
					i = this.IlI.getFontMetrics().stringWidth(
							this.l1l[paramInt1][k]);
					j = k;
				}
			}
			this.IlI.drawImage(
					this.I11[paramInt1],
					this.l111[paramInt1] + this.l1l1[paramInt1][j]
							- this.I11[paramInt1].getWidth(this) - 3,
					this.l11l[paramInt1]
							+ (this.lll11[paramInt1] - this.I11[paramInt1]
									.getHeight(this)) / 2 + paramInt2, this);
		} else {
			this.IlI.drawImage(
					this.I11[paramInt1],
					this.l111[paramInt1] + 3,
					this.l11l[paramInt1]
							+ (this.lll11[paramInt1] - this.I11[paramInt1]
									.getHeight(this)) / 2 + paramInt2, this);
		}
	}

	public synchronized void IlI1() {
		if (this.I1I == null) {
			return;
		}
		if (this.lllI) {
			if (this.llll) {
				lIII1();
				for (int i = 0; i < this.lII; i++) {
					IllI(i);
				}
			} else {
				this.IlI.setColor(new Color(this.I1Il));
				this.IlI.drawString("Incorrect Copyright", 10, 15);
			}
		}
		getGraphics().drawImage(this.I1I, 0, 0, this);
	}

	public Image IlII(String paramString) {
		Image localImage = null;
		MediaTracker localMediaTracker = new MediaTracker(this);
		try {
			localImage = getImage(getDocumentBase(), paramString);
			localMediaTracker.addImage(localImage, 0);
			localMediaTracker.waitForID(0);
		} catch (Exception localException) {
			return null;
		}
		if (localMediaTracker.isErrorID(0)) {
			return null;
		}
		this.IIlI = (localImage.getWidth(this) <= this.IIlI ? this.IIlI
				: localImage.getWidth(this));
		return localImage;
	}

	public void IlIl(int paramInt) {
		int i = 2;
		int j = 6;
		int k = 6;
		int m;
		if (this.llII) {
			for (m = 0; m < (this.IIl - 5) / k; m++) {
				this.IlI.setColor(new Color(this.I111));
				this.IlI.drawRect(this.l111[paramInt] + i + 1, j + 1 + m * k,
						1, 1);
				this.IlI.setColor(new Color(this.I11l));
				this.IlI.drawRect(this.l111[paramInt] + i, j + m * k, 1, 1);
				if (this.IIl - (j + 4 + m * k) > 5) {
					this.IlI.setColor(new Color(this.I111));
					this.IlI.drawRect(this.l111[paramInt] + i + 4, j + 4 + m
							* k, 1, 1);
					this.IlI.setColor(new Color(this.I11l));
					this.IlI.drawRect(this.l111[paramInt] + i + 3, j + 3 + m
							* k, 1, 1);
				}
			}
		} else {
			for (m = 0; m < (this.l1lI[paramInt] - 3) / k; m++) {
				this.IlI.setColor(new Color(this.I111));
				this.IlI.drawRect(this.l111[paramInt] + i + 1 + m * k,
						this.l11l[paramInt] + i + 1, 1, 1);
				this.IlI.drawRect(this.l111[paramInt] + i + 4 + m * k,
						this.l11l[paramInt] + i + 4, 1, 1);
				this.IlI.setColor(new Color(this.I11l));
				this.IlI.drawRect(this.l111[paramInt] + i + m * k,
						this.l11l[paramInt] + i, 1, 1);
				this.IlI.drawRect(this.l111[paramInt] + i + m * k + 3,
						this.l11l[paramInt] + i + 3, 1, 1);
			}
		}
	}

	public int Ill1(int paramInt1, int paramInt2) {
		for (int i = 0; i < this.lII; i++) {
			if (!this.l1l[i][0].trim().equals("-")) {
				Rectangle localRectangle = new Rectangle(this.l111[i],
						this.l11l[i], this.l1lI[i], this.lll11[i]);
				if (localRectangle.inside(paramInt1, paramInt2)) {
					return i;
				}
			}
		}
		return -1;
	}

	public void IllI(int paramInt) {
		if (this.l1l[paramInt][0].trim().equals("-")) {
			IlIl(paramInt);
			return;
		}
		int i = 0;
		int j = 0;
		int k = this.I1II;
		int m = this.I1Il;
		int n = this.lllII;
		int i1 = 0;
		if (this.l1ll == paramInt) {
			i = 1;
			j = this.lIII;
			m = this.I11I;
			n = this.lllI1;
			i1 = 6;
		}
		if (this.lllIl == paramInt) {
			i = 1;
			j = 1;
			m = this.I11I;
			n = this.lllI1;
			if (this.lll1l != paramInt) {
				j = 0;
			}
		}
		int[] arrayOfInt1;
		int[] arrayOfInt2;
		if ((j == 0) && (this.lllIl != paramInt)) {
			if ((this.I1ll == 0) || (this.I1ll == 2) || (i1 != 0)) {
				if ((this.I1ll == 2) || (this.I1ll == 3)) {
					this.IlI.setColor(new Color(this.lIlII[17]));
					this.IlI.fillRect(this.l111[paramInt] + 1,
							this.l11l[paramInt] + 2, this.l1lI[paramInt] - 2,
							this.lll11[paramInt] - 3);
					if ((i1 == 0) || (this.I1ll == 3)) {
						arrayOfInt1 = new int[] { 10, 37, 39, 36, 34, 32, 30,
								28, 26, 23, 21, 19, 18 };

						lII1I(this.l111[paramInt] + 1, this.l11l[paramInt],
								this.l1lI[paramInt] - 3, arrayOfInt1,
								this.lIlII, 0);
					} else {
						arrayOfInt1 = new int[] { 18, 19, 21, 22, 24, 26, 28,
								30, 32, 34, 36, 38, 39, 39, 39 };

						lII1I(this.l111[paramInt] + 2, this.l11l[paramInt]
								+ this.lll11[paramInt] - 15,
								this.l1lI[paramInt] - 5, arrayOfInt1,
								this.lIlII, 0);
						arrayOfInt2 = new int[] { 10, 37 };

						lII1I(this.l111[paramInt] + 1, this.l11l[paramInt],
								this.l1lI[paramInt] - 3, arrayOfInt2,
								this.lIlII, 0);
					}
					this.IlI.setColor(new Color(this.lIlII[10]));
					this.IlI.drawLine(this.l111[paramInt],
							this.l11l[paramInt] + 1, this.l111[paramInt],
							this.l11l[paramInt] + this.lll11[paramInt] - 2);
					this.IlI.setColor(new Color(this.lIlII[32]));
					this.IlI.drawLine(this.l111[paramInt] + 1,
							this.l11l[paramInt] + 1, this.l111[paramInt] + 1,
							this.l11l[paramInt] + this.lll11[paramInt] - 3);
					this.IlI.setColor(new Color(this.lIlII[15]));
					this.IlI.drawLine(this.l111[paramInt] + 1,
							this.l11l[paramInt] + 1, this.l111[paramInt] + 1,
							this.l11l[paramInt] + 1);
					this.IlI.setColor(new Color(this.lIlII[15]));
					this.IlI.drawLine(this.l111[paramInt] + this.l1lI[paramInt]
							- 2, this.l11l[paramInt] + 1, this.l111[paramInt]
							+ this.l1lI[paramInt] - 2, this.l11l[paramInt] + 1);
					this.IlI.setColor(new Color(this.lIlII[32]));
					this.IlI.drawLine(this.l111[paramInt] + this.l1lI[paramInt]
							- 2, this.l11l[paramInt] + 2, this.l111[paramInt]
							+ this.l1lI[paramInt] - 2, this.l11l[paramInt]
							+ this.lll11[paramInt] - 3);
					this.IlI.setColor(new Color(this.lIlII[4]));
					this.IlI.drawLine(this.l111[paramInt] + this.l1lI[paramInt]
							- 1, this.l11l[paramInt] + 1, this.l111[paramInt]
							+ this.l1lI[paramInt] - 1, this.l11l[paramInt]
							+ this.lll11[paramInt] - 2);
					this.IlI.setColor(new Color(this.lIlII[2]));
					this.IlI.drawLine(this.l111[paramInt] + 1,
							this.l11l[paramInt] + this.lll11[paramInt] - 1,
							this.l111[paramInt] + this.l1lI[paramInt] - 2,
							this.l11l[paramInt] + this.lll11[paramInt] - 1);
				} else {
					this.IlI.setColor(new Color(this.lIlII[(26 + i1)]));
					this.IlI.fillRect(this.l111[paramInt] + 2,
							this.l11l[paramInt] + 2, this.l1lI[paramInt] - 4,
							this.lll11[paramInt] - 2);
					arrayOfInt1 = new int[] { 17, 30, 29, 28, 27, 26, 26 };

					lII1I(this.l111[paramInt] + 2, this.l11l[paramInt],
							this.l1lI[paramInt] - 5, arrayOfInt1, this.lIlII,
							i1);
					arrayOfInt2 = new int[] { 22, 6 };

					lII1I(this.l111[paramInt] + 2, this.l11l[paramInt]
							+ this.lll11[paramInt] - 2,
							this.l1lI[paramInt] - 5, arrayOfInt2, this.lIlII,
							i1);
					this.IlI.setColor(new Color(this.lIlII[(17 + i1)]));
					this.IlI.drawLine(this.l111[paramInt],
							this.l11l[paramInt] + 1, this.l111[paramInt],
							this.l11l[paramInt] + this.lll11[paramInt] - 3);
					this.IlI.setColor(new Color(this.lIlII[(29 + i1)]));
					this.IlI.drawLine(this.l111[paramInt] + 1,
							this.l11l[paramInt] + 2, this.l111[paramInt] + 1,
							this.l11l[paramInt] + this.lll11[paramInt] - 3);
					lIIIl(this.l111[paramInt] + 1, this.l11l[paramInt],
							this.lIllI, this.lIlII, i1);
					this.IlI.setColor(new Color(this.lIlII[(22 + i1)]));
					this.IlI.drawLine(this.l111[paramInt] + this.l1lI[paramInt]
							- 2, this.l11l[paramInt] + 1, this.l111[paramInt]
							+ this.l1lI[paramInt] - 2, this.l11l[paramInt]
							+ this.lll11[paramInt] - 2);
					this.IlI.setColor(new Color(this.lIlII[(6 + i1)]));
					this.IlI.drawLine(this.l111[paramInt] + this.l1lI[paramInt]
							- 1, this.l11l[paramInt] + 2, this.l111[paramInt]
							+ this.l1lI[paramInt] - 1, this.l11l[paramInt]
							+ this.lll11[paramInt] - 3);
					lIIIl(this.l111[paramInt] + this.l1lI[paramInt] - 5,
							this.l11l[paramInt], this.lIll1, this.lIlII, i1);
					lIIIl(this.l111[paramInt] + this.l1lI[paramInt] - 5,
							this.l11l[paramInt] + this.lll11[paramInt] - 5,
							this.lIl1l, this.lIlII, i1);
					lIIIl(this.l111[paramInt], this.l11l[paramInt]
							+ this.lll11[paramInt] - 4, this.lIl11, this.lIlII,
							i1);
				}
			}
		} else {
			if (i1 == 6) {
				i1 = 9;
			}
			this.IlI.setColor(new Color(this.lIlII[(14 + i1)]));
			this.IlI.fillRect(this.l111[paramInt] + 1, this.l11l[paramInt] + 2,
					this.l1lI[paramInt] - 3, this.lll11[paramInt] - 2);
			arrayOfInt1 = new int[] { 4, 9, 13 };

			lII1I(this.l111[paramInt] + 1, this.l11l[paramInt] + 1,
					this.l1lI[paramInt] - 3, arrayOfInt1, this.lIlII, i1);
			arrayOfInt2 = new int[] { 11, 6 };

			lII1I(this.l111[paramInt] + 1, this.l11l[paramInt]
					+ this.lll11[paramInt] - 2, this.l1lI[paramInt] - 4,
					arrayOfInt2, this.lIlII, i1);
			this.IlI.setColor(new Color(this.lIlII[(6 + i1)]));
			this.IlI.drawLine(this.l111[paramInt], this.l11l[paramInt] + 2,
					this.l111[paramInt], this.l11l[paramInt]
							+ this.lll11[paramInt] - 2);
			this.IlI.setColor(new Color(this.lIlII[(11 + i1)]));
			this.IlI.drawLine(this.l111[paramInt] + this.l1lI[paramInt] - 2,
					this.l11l[paramInt] + 3, this.l111[paramInt]
							+ this.l1lI[paramInt] - 2, this.l11l[paramInt]
							+ this.lll11[paramInt] - 2);
			this.IlI.setColor(new Color(this.lIlII[(4 + i1)]));
			this.IlI.drawLine(this.l111[paramInt] + this.l1lI[paramInt] - 1,
					this.l11l[paramInt] + 2, this.l111[paramInt]
							+ this.l1lI[paramInt] - 1, this.l11l[paramInt]
							+ this.lll11[paramInt] - 3);
		}
		int i2 = 0;
		int i3 = this.I1lI;
		int i4 = this.I1l1;
		if ((this.I1ll == 1) || (this.I1ll == 3)) {
			i3 = this.I111;
			i4 = this.I11l;
		}
		for (int i5 = 0; i5 < this.llll1[paramInt]; i5++) {
			if (n != -1) {
				this.IlI.setColor(new Color(n));
				this.IlI.drawString(this.l1l[paramInt][i5], this.l111[paramInt]
						+ this.l1l1[paramInt][i5] + 1, this.l11l[paramInt]
						+ this.lll1I[paramInt] + 1 + i2);
			}
			if (this.llI[paramInt].equals("_")) {
				this.IlI.setColor(new Color(i3));
				this.IlI.drawString(this.l1l[paramInt][i5], this.l111[paramInt]
						+ this.l1l1[paramInt][i5] + 1, this.l11l[paramInt]
						+ this.lll1I[paramInt] + 1 + i2);
			}
			this.IlI.setColor(new Color(m));
			if (this.llI[paramInt].equals("_")) {
				this.IlI.setColor(new Color(i4));
			}
			this.IlI.drawString(this.l1l[paramInt][i5], this.l111[paramInt]
					+ this.l1l1[paramInt][i5], this.l11l[paramInt]
					+ this.lll1I[paramInt] + j + i2);
			i2 += this.II11 + 1;
		}
		Il1l(paramInt, j);
		Il1I(paramInt, j, i);
		if ((this.I == -1) && (this.l1ll != -1)) {
			this.I = this.l1ll;
		}
		if (this.I > -1) {
			if (this.I != this.l1ll) {
				this.I = -2;
			}
		}
	}

	private void Illl(Menu paramMenu, int paramInt1, int paramInt2) {
		String str1 = "";
		String str2 = "__menu" + paramInt1 + "_";
		for (int i = 0; i < this.Il1; i++) {
			if (this.lll[i].indexOf(str2) != -1) {
				int j = 0;
				int k = 0;
				for (StringTokenizer localStringTokenizer = new StringTokenizer(
						this.ll1[i], "|", true); localStringTokenizer
						.nextToken().equals("|");) {
					j++;
				}
				if (paramInt2 > j) {
					return;
				}
				this.lll[i] = this.lll[i].substring(0,
						this.lll[i].indexOf(str2));
				String str3 = this.ll1[i].substring(j);
				Object localObject;
				if ((this.l11I[i] != null)
						|| (this.lll[i].startsWith("javascript:"))) {
					this.lI1l[i] = new MenuItem(str3);
					if (this.lll[i].equals("_")) {
						this.lI1l[i].enable(false);
					}
					paramMenu.add(this.lI1l[i]);
					if (!this.ll1l) {
						paramMenu.setFont(new Font(this.IIII, this.II1l,
								this.II11));
						this.lI1l[i].setFont(new Font(this.IIII, this.II1l,
								this.II11));
					}
				} else if (str3.startsWith("-")) {
					paramMenu.addSeparator();
				} else {
					localObject = new Menu(str3);
					if (!this.ll1l) {
						((MenuComponent) localObject).setFont(new Font(
								this.IIII, this.II1l, this.II11));
					}
					Illl((Menu) localObject, paramInt1, paramInt2 + 1);
					paramMenu.add((MenuItem) localObject);
					continue;
				}
				if ((i + 1 < this.Il1)
						&& (this.lll[(i + 1)].indexOf("__menu" + paramInt1
								+ "_") != -1)) {
					for (localObject = new StringTokenizer(this.ll1[(i + 1)],
							"|", true); ((StringTokenizer) localObject)
							.nextToken().equals("|");) {
						k++;
					}
				}
				if (k < j) {
					return;
				}
			}
		}
	}

	public void changeItem(String paramString1, String paramString2,
			String paramString3, String paramString4) {
		StringTokenizer localStringTokenizer1 = new StringTokenizer(
				paramString1, "_");
		int i = localStringTokenizer1.countTokens();
		int j = 0;
		int k = Integer.parseInt(localStringTokenizer1.nextToken()) - 1;
		if (!localStringTokenizer1.hasMoreTokens()) {
			if ((k >= this.lII) || (this.l1l[k][0].trim().equals("-"))) {
				return;
			}
			StringTokenizer localStringTokenizer2 = new StringTokenizer(
					paramString2, "\\");
			for (int n = 0; localStringTokenizer2.hasMoreTokens(); n++) {
				this.l1l[k][n] = localStringTokenizer2.nextToken();
				this.llll1[k] = n;
			}
			
			if (!this.llI[k].startsWith("__menu")) {
				if (!paramString4.equals("")) {
					this.llI[k] = paramString4;
				}
				if (!paramString3.equals("")) {
					if (!paramString3.startsWith("javascript:")) {
						try {
							this.l1Il[k] = new URL(getDocumentBase(),
									paramString3);
						} catch (Exception localException1) {
						}
					} else {
						this.llI[k] = paramString3;
					}
				}
			}
			return;
		}
		int m = Integer.parseInt(localStringTokenizer1.nextToken()) - 1;
		j = 2;
		MenuItem localMenuItem = null;
		Menu localMenu = null;
		if ((this.lI11[k].getItem(m) instanceof Menu)) {
			localMenu = (Menu) this.lI11[k].getItem(m);
			if (j == i) {
				localMenu.setLabel(paramString2);
			}
		} else {
			localMenuItem = this.lI11[k].getItem(m);
		}
		while (localStringTokenizer1.hasMoreTokens()) {
			int i1 = Integer.parseInt(localStringTokenizer1.nextToken()) - 1;
			j++;
			if ((localMenu.getItem(i1) instanceof Menu)) {
				localMenu = (Menu) localMenu.getItem(i1);
				if (j == i) {
					localMenu.setLabel(paramString2);
				}
			} else {
				if (j != i) {
					break;
				}
				localMenuItem = localMenu.getItem(i1);
				break;
			}
		}
		localMenuItem.setLabel(paramString2);
		for (int i1 = 0; i1 < this.Il1; i1++) {
			if ((this.lI1l[i1] != null)
					&& (this.lI1l[i1].equals(localMenuItem))) {
				if (!paramString4.equals("")) {
					this.lll[i1] = paramString4;
				}
				if (!paramString3.equals("")) {
					if (!paramString3.startsWith("javascript:")) {
						try {
							this.l11I[i1] = new URL(getDocumentBase(),
									paramString3);
						} catch (Exception localException2) {
						}
					} else {
						this.lll[i1] = paramString3;
					}
				}
				if (this.lll[i1].equals("_")) {
					this.lI1l[i1].enable(false);
				} else {
					this.lI1l[i1].enable(true);
				}
			}
		}
	}

	public boolean handleEvent(Event paramEvent) {
		if (!this.lllI) {
			return true;
		}
		if ((paramEvent.id == 1004) || (paramEvent.id == 1005)) {
			return true;
		}
		int i = Ill1(paramEvent.x, paramEvent.y);
		if ((i == -1) || (this.llI[i].equals("_"))) {
			i = -1;
		}
		try {
			if (paramEvent.id == 1001) {
				if (this.lll1l > -1) {
					this.lllIl = this.lll1l;
					this.lll1l = -2;
				}
				for (int j = 0; j < this.Il1; j++) {
					if ((this.lI1l[j] != null)
							&& (this.lI1l[j].equals(paramEvent.target))
							&& (!this.lll[j].equals("_"))) {
						this.lI1lI = this.l11I[j];
						this.lI11l = this.lll[j];
					}
				}
			}
			if ((i == -1) || (paramEvent.id == 505)) {
				this.l1ll = -1;
				if ((this.lll1l > -1)
						&& ((!this.llI[this.lll1l].startsWith("__menu")) || (this.lllIl == this.lll1l))) {
					this.lllIl = this.lll1l;
					this.lll1l = -2;
				}
				if (this.lIll != null) {
					this.lIll.setCursor(0);
				}
				IlI1();
				return true;
			}
			if (paramEvent.id == 503) {
				if (!this.lll1) {
					return true;
				}
				this.lll1 = false;
				if (i != this.l1ll) {
					if ((this.lll1l > -1)
							&& ((!this.llI[this.lll1l].startsWith("__menu")) || (this.lllIl == this.lll1l))) {
						this.lllIl = this.lll1l;
						this.lll1l = -2;
					}
					if (this.lIll != null) {
						this.lIll.setCursor(12);
					}
					this.lIII = 0;
					this.l1ll = i;
					IlI1();
					if (this.lIlI != null) {
						this.lIlI.play();
					}
					if (this.l1I1 == null) {
						if ((this.IIIl.equalsIgnoreCase("link"))
								&& (!this.llI[i].startsWith("__menu"))
								&& (this.l1Il[i] != null)) {
							showStatus(this.l1Il[i].toString());
						} else {
							showStatus(this.l1l[i][0].trim());
						}
					} else {
						showStatus(this.l1I1);
					}
					if ((this.llIl) && (this.llI[i].startsWith("__menu"))) {
						paramEvent.id = 501;
						this.lll1 = true;
					}
				}
			}
			if (paramEvent.id == 501) {
				if (!this.lll1) {
					return true;
				}
				this.lll1 = false;
				this.l1ll = i;
				this.lIII = 1;
				if (this.lllIl > -2) {
					this.lll1l = i;
					if (!this.llI[i].startsWith("__menu")) {
						this.lllIl = -1;
					}
				}
				IlI1();
				this.lI1lI = this.l1Il[i];
				this.lI11l = this.llI[i];
				this.lI1l1 = i;
				if (this.l1I1 == null) {
					showStatus(this.l1l[i][0].trim());
				}
			}
		} catch (Throwable localThrowable) {
		}
		this.lll1 = true;
		return true;
	}

	int[] lII11(int paramInt) {
		int[] arrayOfInt = new int[this.llIIl * 2 + 1];
		int i = (paramInt & 0xFF0000) >> 16;
		int j = (paramInt & 0xFF00) >> 8;
		int k = paramInt & 0xFF;
		int m = i / 3 / this.llIIl;
		int n = j / 3 / this.llIIl;
		int i1 = k / 3 / this.llIIl;
		int i2 = (255 - i) / 3 * 2 / this.llIIl;
		int i3 = (255 - j) / 3 * 2 / this.llIIl;
		int i4 = (255 - k) / 3 * 2 / this.llIIl;
		for (int i5 = 0; i5 < this.llIIl + 1; i5++) {
			arrayOfInt[(this.llIIl - i5)] = ((IIll(i - m * i5) << 16)
					+ (IIll(j - n * i5) << 8) + IIll(k - i1 * i5));
		}
		for (int i6 = 0; i6 < this.llIIl + 1; i6++) {
			arrayOfInt[(this.llIIl + i6)] = ((IIll(i + i2 * i6) << 16)
					+ (IIll(j + i3 * i6) << 8) + IIll(k + i4 * i6));
		}
		return arrayOfInt;
	}

	public void lII1I(int paramInt1, int paramInt2, int paramInt3,
			int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt4) {
		for (int i = 0; i < paramArrayOfInt1.length; i++) {
			int j = paramArrayOfInt1[i] + paramInt4;
			if (j >= paramArrayOfInt2.length) {
				j = paramArrayOfInt2.length - 1;
			}
			if (j > paramInt4 - 1) {
				this.IlI.setColor(new Color(paramArrayOfInt2[j]));
				this.IlI.drawLine(paramInt1, paramInt2 + i, paramInt1
						+ paramInt3, paramInt2 + i);
			}
		}
	}

	int lII1l(String paramString, int paramInt1, int paramInt2) {
		paramString = getParameter(paramString);
		if (paramString != null) {
			if (paramString.equalsIgnoreCase("system")) {
				paramInt2 = 64537;
			} else {
				if (paramString.startsWith("#")) {
					paramString = paramString.substring(1);
				}
				paramInt2 = Integer.parseInt(paramString, paramInt1);
			}
		}
		return paramInt2;
	}

	public void lIII1() {
		this.IlI.setColor(new Color(this.I1II));
		this.IlI.fillRect(0, 0, this.II1, this.IIl);
		if (this.lIIlI) {
			int[] arrayOfInt1 = { 17, 28, 30, 26, 22, 18, 18, 17, 17, 17, 17,
					18, 19, 19 };

			lII1I(0, 0, this.II1, arrayOfInt1, this.lIIll, 0);
			int[] arrayOfInt2 = { 19, 18, 17, 11, 4 };

			lII1I(0, this.IIl - 5, this.II1, arrayOfInt2, this.lIIll, 0);
			this.IlI.setColor(new Color(this.lIIll[17]));
			this.IlI.drawLine(0, 0, 0, this.IIl);
			this.IlI.setColor(new Color(this.lIIll[28]));
			this.IlI.drawLine(1, 3, 1, this.IIl - 2);
			this.IlI.setColor(new Color(this.lIIll[22]));
			this.IlI.drawLine(2, 5, 2, this.IIl - 3);
			this.IlI.setColor(new Color(this.lIIll[0]));
			this.IlI.drawLine(this.II1 - 1, 1, this.II1 - 1, this.IIl - 2);
		}
	}

	public void lIIIl(int paramInt1, int paramInt2, int[][] paramArrayOfInt,
			int[] paramArrayOfInt1, int paramInt3) {
		for (int i = 0; i < paramArrayOfInt.length; i++) {
			for (int j = 0; j < paramArrayOfInt[i].length; j++) {
				int k = paramArrayOfInt[i][j] + paramInt3;
				if (k >= paramArrayOfInt1.length) {
					k = paramArrayOfInt1.length - 1;
				}
				if (k > paramInt3 - 1) {
					this.IlI.setColor(new Color(paramArrayOfInt1[k]));
					this.IlI.drawLine(paramInt1 + j, paramInt2 + i, paramInt1
							+ j, paramInt2 + i);
				}
			}
		}
	}

	public void paint(Graphics paramGraphics) {
		IlI1();
	}

	public void run() {
		for (;;) {
			if (!this.lllI) {
				try {
					IIl1();
					this.lllI = true;
					if (this.lIll != null) {
						this.lIll.setCursor(0);
					}
					IlI1();
				} catch (Exception localException1) {
				}
			}
			try {
				if (!this.lI11l.equals("_")) {
					IlI1();
					if (this.lIl1 != null) {
						this.lIl1.play();
					}
					if (this.lI11l.startsWith("__menu")) {
						this.lI11l = "_";
						if (this.llII) {
							this.lI11[this.lI1l1].show(this,
									this.l111[this.lI1l1],
									this.l11l[this.lI1l1]
											+ this.lll11[this.lI1l1]);
						} else {
							this.lI11[this.lI1l1].show(this,
									this.l111[this.lI1l1]
											+ this.l1lI[this.lI1l1],
									this.l11l[this.lI1l1]);
						}
					} else {
						if (!this.lI11l.startsWith("javascript:")) {
							if (this.lI1lI != null) {
								getAppletContext().showDocument(this.lI1lI,
										this.lI11l);
							}
						} else {
							II(this.lI11l);
						}
						this.lI11l = "_";
					}
					IlI1();
				} else {
					Thread.sleep(200L);
					IlI1();
				}
			} catch (Exception localException2) {
			}
		}
	}

	public void setPressedItem(int paramInt) {
		this.lllIl = (paramInt - 1);
	}

	public void start() {
		if (this.III == null) {
			this.III = new Thread(this);
			this.III.start();
		}
	}

	public void stop() {
		if (this.III != null) {
			this.III.stop();
			this.III = null;
		}
	}
}