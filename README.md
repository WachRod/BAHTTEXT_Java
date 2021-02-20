## BAHTTEXT_Java
### Converting currency number to Thai Baht Text with java programming

> **โปรดทราบก่อนนำไปใช้งาน :** โปรแกรมแปลงตัวเลขค่าเงินบาทเป็นคำอ่านภาษาไทยนี้ ผู้เขียนโปรแกรมเขียนไว้ใช้ในงานของตนเองโดยเฉพาะ ได้พยายามอย่างเต็มที่แล้วที่จะกำจัดข้อผิดพลาดที่จะพึงมี ผู้ที่ต้องการนำโปรแกรมนี้
> ไปใช้ในงานอื่น ๆ สามารถนำไปใช้ได้ทันที แต่ผู้เขียนโปรแกรมไม่รับประกันความผิดพลาดอันใดที่อาจจะเกิดขึ้นได้จากการใช้โปรแกรมนี้  และขอปฏิเสธความรับผิดชอบความเสียหายทั้งโดยตรงและโดยอ้อมที่เกิดขึ้นทั้งหมดจากการนำไปใช้งาน
> ผู้นำไปใช้งานต้องยอมรับความเสี่ยงด้วยตนเอง การแก้ไข ดัดแปลง เพิ่มเติม ตัดทอนคำสั่งในโปรแกรม สามารถทำได้เต็มที่ โดยไม่ต้องขออนุญาต

โปรแกรมการแปลงตัวเลขของค่าเงินบาทให้เป็นคำอ่านภาษาไทยที่กล่าวถึงนี้ เป็นการเลียนแบบฟังก์ชัน BATHTEXT ( “จำนวนเลข”)  ที่ฝังติดมาใน MS Excel  โดยเขียนขึ้นใหม่เป็นภาษาจาวา (เวอร์ชั่น 8+)  และจะใช้ผลลัพธ์ที่ได้จากการคำนวณของ BATHTEXT ของ Excel  เปรียบเทียบกับผลลัพธ์ที่ได้จากการทำงานของโปรแกรม

**วิธีใช้งานโปรแกรม**
	ฟังก์ชันที่เกี่ยวข้องกับการแปลงค่าเงินบาททั้งหมด ถูกเขียนรวมกันไว้ในคลาส BAHTTEXT  เพียงคลาสเดียว การเรียกใช้งานในคลาสอื่น สามารถทำได้โดยเรียกการเรียกใช้ฟังก์ชัน convert ในคลาส BAHTTEXT ดังนี้
	
	String yourString = BAHTTEXT.convert (“จำนวนเงิน”); 
	
	ถ้า “จำนวนเงิน” เป็นข้อมูลชนิด double หรือ float  หรือ integer จะต้องแปลงข้อมูลให้เป็นชนิดสตริงเสียก่อน

**ตัวอย่างการใช้งาน**   
  1. จำนวนเงินสามารถมีเครื่องหมายจุลภาคและช่องว่าง (white space)  หรือมี/ไม่มีเลขศูนย์นำหน้าจำนวนเลขที่อยู่ก่อนหน้าทศนิยม หรือหลังทศนิยมก็ได้
  
  	BAHTTEXT.convert(“0005,2 30,90.00987”);		//ห้าแสนสองหมื่นสามพันเก้าสิบบาท หนึ่งสตางค์
	BAHTTEXT.convert(“432,121.400 000”);		// สี่แสนสามหมื่นสองพันหนึ่งร้อยยี่สิบเอ็ดบาท สี่สิบสตางค์
	
  2. จำนวนเงินสามารถมีเครื่องหมายบวกและลบนำหน้า แต่จะอยู่ตำแหน่งสุดท้ายของตัวเลขไม่ได้ เครื่องหมาย ฿ จะอยู่นำหน้าตัวเลขหรืออยู่ท้ายตัวเลขก็ได้ โปรแกรมจะตัดทิ้งเครื่องหมายเหล่านี้ ไม่แสดงคำอ่านเครื่องหมายเหล่านี้ในข้อความ
  
	BAHTTEXT.convert(“+987,654.321”); 		//เก้าแสนแปดหมื่นเจ็ดพันหกร้อยห้าสิบสี่บาท สามสิบสองสตางค์
	BAHTTEXT.convert(“-1,234,567.890”);		//หนึ่งล้านสองแสนสามหมื่นสี่พันห้าร้อยหกสิบเจ็ดบาท แปดสิบเก้าสตางค์
	BAHTTEXT.convert(“฿789.354”); 			//เจ็ดร้อยแปดสิบเก้าบาท สามสิบห้าสตางค์
	BAHTTEXT.convert(“45,234.75฿”);			//สี่หมื่นห้าพันสองร้อยสามสิบสี่บาท เจ็ดสิบห้าสตางค์
	BAHTTEXT.convert(“45,234฿”);  			//สี่หมื่นห้าพันสองร้อยสามสิบสี่บาทถ้วน

  3. จำนวนเงินที่มีตัวอักษรอื่น ๆ ที่นอกเหนือจากที่กล่าวไว้ในข้อ 1-2  โปรแกรมจะแจ้งข้อความว่า “ไม่ใช่จำนวนเลข”  เครื่องหมายบวก ลบ และ ฿ เมื่อแทรกอยู่ระหว่างตัวเลข ก็จะถือว่าไม่ใช่จำนวนเลข วงเล็บ ไม่ว่าจะเป็นวงเล็บธรรมดา วงเล็บปีกกา หรือวงเล็บก้ามปู โปรแกรมมองเห็นว่า “ไม่ใช่จำนวนเลข” เช่นกัน
 
 	BAHTTEXT.convert(“42abc3.3985”);	//ไม่ใช่จำนวนเลข
	BAHTTEXT.convert(“000,435;769”);	// ไม่ใช่จำนวนเลข  เพราะมีเครื่องหมาย semi-colon
	BAHTTEXT.convert(“5467+2340”);		//ไม่ใช่จำนวนเลข
	BAHTTEXT.convert(“765-123.446”);	//ไม่ใช่จำนวนเลข
	BAHTTEXT.convert(“45,234฿.75”); 	// ไม่ใช่จำนวนเลข  เพราะมีเครื่องหมาย ฿ ตรงจุดทศนิยม
	BAHTTEXT.convert(“.”);			// ไม่ใช่จำนวนเลข  เพราะมีจุดเพียงจุดเดียว

  4. ตัวเลขหน้าทศนิยมและหลังทศนิยมจะมีกี่หลักก็ได้  แต่ตัวเลขหลังทศนิยมจะถูกปัดเศษให้เหลือเพียง 2  ตำแหน่ง
  
  	BAHTTEXT.convert(“0.00”);				//ศูนย์บาทถ้วน
	BAHTTEXT.convert (“0.245000000000000009436”);  		// ยี่สิบห้าสตางค์
	BAHTTEXT.convert(“0.05”); 				//  ห้าสตางค์
	BAHTTEXT.convert(“0.5”); 				//  ห้าสิบสตางค์
	BAHTTEXT.convert(“0.555”); 				//  ห้าสิบหกสตางค์
	BAHTTEXT.convert(“1.004”);  				// หนึ่งบาทถ้วน
	BAHTTEXT.convert(“0.995”);				//หนึ่งบาทถ้วน
	BAHTTEXT.convert(“1.321”);				// หนึ่งบาท สามสิบสองสตางค์
	BAHTTEXT.convert(“1.9944”);				// หนึ่งบาท เก้าสิบเก้าสตางค์
	BAHTTEXT.convert(“0.10”);				//สิบสตางค์
	
	จำนวนทศนิยม ที่ไม่มีเลขศูนย์นำหน้าจุดทศนิยม สามารถแปลงได้เช่นกัน
	BAHTTEXT.convert(“.11”);				// สิบเอ็ดสตางค์
	BAHTTEXT.convert(“.123”);				//สิบสองสตางค์
	
	จำนวนเต็ม (ที่ไม่มีทศนิยม)
	BAHTTEXT.convert(“0”);					//ศูนย์บาทถ้วน
	BAHTTEXT.convert(“1”);					// หนึ่งบาทถ้วน
	BAHTTEXT.convert(“10”);					//สิบบาทถ้วน
	BAHTTEXT.convert(“11”);					//สิบเอ็ดบาทถ้วน
	BAHTTEXT.convert(“111”);				//หนึ่งร้อยสิบเอ็ดบาทถ้วน
	BAHTTEXT.convert(“123.”);				//หนึ่งร้อยยี่สิบสามบาทถ้วน
	BAHTTEXT.convert(“222,221”);				// สองแสนสองหมื่นสองพันสองร้อยยี่สิบเอ็ดบาทถ้วน
	BAHTTEXT.convert(“1,000,001”);				//หนึ่งล้านเอ็ดบาทถ้วน
	
	
	จำนวนจริงที่มีเลขหน้าทศนิยมไม่เกิน 7 หลัก 
	BAHTTEXT.convert(“54,321.3333”);	//ห้าหมื่นสี่พันสามร้อยยี่สิบเอ็ดบาท สามสิบสามสตางค์
	BAHTTEXT.convert(“100,001.0095”);	//หนึ่งแสนเอ็ดบาท หนึ่งสตางค์
	
	จำนวนเต็มหรือจำนวนจริงที่มีจำนวนตัวเลขหน้าจุดทศนิยมเกิน 7 หลัก
	BAHTTEXT.convert(“2,147,483,647”);	//สองพันหนึ่งร้อยสี่สิบเจ็ดล้านสี่แสนแปดหมื่นสามพันหกร้อยสี่สิบเจ็ดบาทถ้วน
	BAHTTEXT.convert(“1,001,001,000,001”);	//หนึ่งล้านหนึ่งพันเอ็ดล้านเอ็ดบาทถ้วน
	BAHTTEXT.convert(“123,001,998,830,750,501”);
	// หนึ่งแสนสองหมื่นสามพันเอ็ดล้านเก้าแสนเก้าหมื่นแปดพันแปดร้อยสามสิบล้านเจ็ดแสนห้าหมื่นห้าร้อยเอ็ดบาทถ้วน
	BAHTTEXT.convert(“4,123,001,998,830,750,501”);
	//สี่ล้านหนึ่งแสนสองหมื่นสามพันเอ็ดล้านเก้าแสนเก้าหมื่นแปดพันแปดร้อยสามสิบล้านเจ็ดแสนห้าหมื่นห้าร้อยเอ็ดบาทถ้วน
	BAHTTEXT.convert(“999 999.99”);		//เก้าแสนเก้าหมื่นเก้าพันเก้าร้อยเก้าสิบเก้าบาท เก้าสิบเก้าสตางค์
	BAHTTEXT.convert(“9,009,009,000,000”);		// เก้าล้านเก้าพันเก้าล้านบาทถ้วน
	BAHTTEXT.convert(“999,999,999.999”);		//หนึ่งพันล้านบาทถ้วน
	BAHTTEXT.convert(“999,999,999,999,999,999,999,999,999,999,999,999.9999”);	//หนึ่งล้านล้านล้านล้านล้านล้านบาทถ้วน
	BAHTTEXT.convert(“9,223,372,036,854,775,807”);
	//เก้าล้านสองแสนสองหมื่นสามพันสามร้อยเจ็ดสิบสองล้านสามหมื่นหกพันแปดร้อยห้าสิบสี่ล้านเจ็ดแสนเจ็ดหมื่นห้าพันแปดร้อยเจ็ดบาทถ้วน


  5. ไม่สามารถรับนิพจน์ทางคณิตศาสตร์ เช่น  “123 + 345” หรือ “25 * 3 + 27/9“ จะแสดงข้อความว่า “ไม่ใช่จำนวนเลข” ผู้ใช้งานควรคำนวณหาผลลัพธ์ของนิพจน์เหล่านี้ก่อนที่จะนำไปแปลงเป็นคำอ่านภาษาไทย
  
  
