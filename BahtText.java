// BahtText.java
// Objective: converting  number to Thai-Baht-text
// Author : "Wachara R." ( wachr0@gmail.com)
// Date released : 10 Dec 2020
// Last updated : 18 Dec 2020
// Java Version :  8+
//-------------------------------------------------
import java.math.BigDecimal;
import java.util.ArrayList;

public final class BahtText {
public static String convert( String strNum) {
String[]parts;  // parts[0]-> beforeDecimal, parts[1]-> after decimal
	
	if (strNum.length() != 0){
		// delete  commas,semicolon and white spaces .
		strNum = strNum.replaceAll("[,\\s]","");//	 strNum.replaceAll("[+\\-,\\s]","");
		//delete leading plus and minus sign
		char firstChar = strNum.charAt(0);
		char lastChar = strNum.charAt(strNum.length()-1);
		if (firstChar =='+' || firstChar=='-'|| firstChar=='฿') strNum = strNum.substring(1);
		if(lastChar=='฿') strNum = strNum.substring(0,strNum.length()-1);
	}else	return "ศูนย์บาทถ้วน";
	if (isConvertible(strNum) ){
		// round number to 2 decimal point using BigDecimal for precision and accuracy
		BigDecimal bdNum = new BigDecimal(strNum);
		bdNum = bdNum.setScale(2,BigDecimal.ROUND_HALF_EVEN); // setScale = set number of decimal point to 2
		// 0.004 -> 0.00 After rounding,  a number may  becomes to zero.
		if (bdNum.compareTo(BigDecimal.ZERO) == 0) return "ศูนย์บาทถ้วน";
		// 123.998 -> 124.00 Number becomes only integer.
		boolean isOnlyInteger = false;
		String suffix = "บาท";
		if (bdNum.stripTrailingZeros().scale() <= 0) { //  equivalent to  (dblNum % 1 == 0)
			isOnlyInteger = true;
			suffix = "บาทถ้วน";
		}
		strNum = bdNum.toPlainString();
	//	System.out.println("before conversion: "+ strNum);
		parts=strNum.split("[.]",0);
		BigDecimal bdPart0 = new BigDecimal(parts[0]);
		StringBuilder strBahtText = new StringBuilder();
//		String strBahtText="";
		if (bdPart0.compareTo(BigDecimal.ZERO) != 0) {
				int numberLength = parts[0].length();
				ArrayList<String> groupedNumber = new ArrayList<>();
				int numGroup;
				int remainder;
				int m = 0;
				if (numberLength > 7) {
					numGroup = numberLength / 6;
					remainder = numberLength %6;
					// divide  'parts[0]' equally into 'numGroup' group
					if (remainder==0)
						for (int i = 0; i < numberLength; i = i + 6) {
							groupedNumber.add(m, parts[0].substring(i, i + 6));
							m = m + 1;
						}
					else { //divide 'parts[0] in different size
						numGroup +=1;  // numGroup from integer division plus the rest.
						groupedNumber.add(m, parts[0].substring(0, remainder));
						m=m+1;
						for (int i = remainder; i < numberLength; i=i+6){
							groupedNumber.add(m, parts[0].substring(i, i + 6));
							m=m+1;
						}
					}
					for(int i = numGroup-1; i >=0; i--) {
		//				strBahtText =  (changeNumber2Word(groupedNumber.get(i), suffix)).concat(strBahtText);
						strBahtText.insert(0, changeNumber2Word(groupedNumber.get(i), suffix));
						suffix = "ล้าน";
					}
				} else 	strBahtText.append(changeNumber2Word(parts[0], suffix));

			}
			if (!isOnlyInteger) {
				suffix="สตางค์";
			//	strBahtText = strBahtText + " " + changeNumber2Word(parts[1],suffix);
				strBahtText.append(" ").append(changeNumber2Word(parts[1], suffix));
			}
		return strBahtText.toString();
	}
	else return  "ไม่ใช่จำนวนเลข"; //"NaN - Not a Number";
}
private static boolean isConvertible( String strNum){
	int dotCount=0;
	// check if there are characters or unwanted signs in strNum
	for(int i =0; i < strNum.length(); i++){
			char c = strNum.charAt(i);
			if ((c !='.') && (c < '0' || c > '9'))return false;
		// check if there are many dots. eg. "127.0.0.1" or only one dot, eg "."
			if (c == '.') dotCount = dotCount+1;
	}
	//	if (dotCount > 1 ||(dotCount==1 && strNum.length()==1)) return false;
	return dotCount <= 1 && (dotCount != 1 || strNum.length() != 1);
}
private static String changeNumber2Word(String strValue,String lastWord) {
	String[] weight = {"", "สิบ", "ร้อย", "พัน", "หมื่น", "แสน", "ล้าน"}; // Digit weight
	String[] pn = {"", "หนึ่ง", "สอง", "สาม", "สี่", "ห้า", "หก", "เจ็ด", "แปด", "เก้า"}; // Digit pronounce
	StringBuilder result = new StringBuilder();
	int length = strValue.length();
	for (int i = 0; i < length; i++) {
		char c =  strValue.charAt(i);
		int n = Integer.parseInt(String.valueOf(c));
		if (n > 0) {
			switch (n) {
				case 1:
					if (i == length - 1 && length > 1  ) {
						if (lastWord.equals("สตางค์") && strValue.charAt(length-2)=='0')  // case -> 0.01
							result.append(pn[n]).append(weight[length - i - 1]);
						else	result.append("เอ็ด").append(weight[length - i - 1]);
					} else if (i== length-2){
						result.append(weight[length - i - 1]);
					}
					else result.append(pn[n]).append(weight[length - i - 1]);
					break;
				case 2:
					if (i == length - 2) {
						result.append("ยี่").append(weight[length - i - 1]);
					} else result.append(pn[n]).append(weight[length - i - 1]);
					break;

				default: result.append(pn[n]).append(weight[length - i - 1]);
			}
		}
	}
	return result + lastWord;
}
}
