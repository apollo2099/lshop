<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>TVpad商城</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp" %>
<script type="text/javascript" src="${resDomain}/www/res/js/developer.js"></script>
	</head>

	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/www/common/header.jsp" %>
	
<script type="text/javascript">

$(function(){
	if(!$("#checkbox").attr("checked")){
	  show("xieyi");
	}
	  $("#idUrl").change(function(){
		  var file_val=$(this).val();
		  if(file_val==null||file_val==""){
	        $("#upload_ps").html("上传小于4MB的图片");
		  }else if(file_val.length>104){
			  $("#upload_ps").html("上傳的文件名稱過長,請控制于100長度以內。");  
		  }else{
		    file_val=file_val.substring(file_val.lastIndexOf("\\")+1);
		    if(file_val.length>30){
		         $("#upload_ps").attr("title",file_val);
		    	file_val=file_val.substring(0,30);
		    	file_val+="...";
		    }
	         $("#upload_ps").html(file_val);
		  }
	  });
});



function checkDate(form){
	$(".errormsg").html("");
	var name=$("#contactName").val();
	var idNum=$("#idNum").val();
	var tel=$("#tel").val();
	var isCheck=$("#checkbox").attr("checked");
	var issubmit=true;
	if(!checkInput(name,32)){
		showMsg("contactName","請輸入32長度以內的真實姓名");
		issubmit= false;
	}
	if(!checkInput(idNum,18)){
		showMsg("idNum","請輸入18長度以內的身份證號碼");
		issubmit= false;
	}
	
    var filetest = /.+\.(jpg|JPG|png|PNG)/;
    var file_val=$("#idUrl").val();
    if(file_val==null||file_val==""){
           $("#errormsg").html("請選擇jpg|png格式圖片");
           issubmit= false;
    }
    if(file_val!=""&&!filetest.test(file_val)){
    	$("#errormsg").html("只能上傳jpg|png格式圖片");
    	issubmit= false;
    }else {
        file_val=file_val.substring(file_val.lastIndexOf("\\")+1);
       $("#file_name").val(file_val);
    }
	if(!checkInput(tel,32)){
		showMsg("tel","請輸入32長度以內的聯繫電話");
		issubmit= false;
	}
	
	 if(!isCheck){
		 issubmit= false;
	 }
	return issubmit;
}
</script>
	
	
<div class="application1" style="height:360px">
	   <div class="posit">
	      <h2 class="bt3" id="nbt">
		    <p class="home"><img src="${resDomain}/www/res/images/icon02.gif" /></p><p><a href="${storeDomain}/web/index!toIndex.action">首頁 > <a href="${storeDomain}/web/developer!developerIndex.action">>註冊開發者</a>  > 個人開發者 </p>
		  </h2>
	   </div> 
	   <div class="clear"></div> 
	  <div class="app_submit">
	   <form action="${storeDomain}/web/developer!apply.action" method="post" enctype="multipart/form-data" onsubmit="return checkDate(this)">
	      <input type="hidden" name="developer.dtype" id="dtype" value="0" />
	      <input type="hidden" name="developer.isAgree" id="isAgree" value="${developer.isAgree}" />
	     <table width="100%" border="0">
	  <tr>
	    <td width="35%" height="40" align="right"><p><span class="star">*</span>真實姓名：</p></td>
	    <td width="28%" height="40">
	      <input type="text" name="developer.contactName" id="contactName" value="${developer.contactName}" class="inttext"/><p class="star"></p></td>
	    <td width="37%" height="40"><span class="star errormsg"></span></td>
	  </tr>
	  <tr>
	    <td height="40" align="right"><p><span class="star">*</span>身份證號碼：</p></td>
	    <td height="40"><input type="text" name="developer.idNum" id="idNum"  value="${developer.idNum}"  class="inttext"/></td>
	    <td height="40"><span class="star errormsg"><c:choose><c:when test="${status eq '3'}">身份證號碼已被註冊</c:when></c:choose></span></td>
	  </tr>
	  <tr>
	    <td height="40" align="right"><p><span class="star">*</span>身份證掃描件：</p></td>
	    <td height="40">
	     <div class="upload">
	     <input name="fileName" id="file_name" class="file_name" type="hidden" value="0" />
		 <input name="file"  type="file" id="idUrl" size="30"  />
	     </div>
	      <div class="upload_detial"><span class="bt4" id="upload_ps">上傳小於4MB的圖片</span></div>
	     </td>
	    <td height="40"><span class="star errormsg" id="errormsg">${msg}</span></td>
	  </tr>
	  <tr>
	    <td height="40" align="right"><p><span class="star">*</span>聯繫電話：</p></td>
	    <td height="40"><input type="text"  name="developer.tel" id="tel"  value="${developer.tel}"  class="inttext"/></td>
	    <td height="40"><span class="star errormsg"></span></td>
	  </tr>
	  <tr>
	    <td height="40" align="right">&nbsp;</td>
	    <td height="40" colspan="2"><input type="submit" name="button" id="button" value="提 交"  class="subminbt"/>
	      <input type="checkbox" name="checkbox" id="checkbox" onchange="changeAgree()" <c:if test="${developer.isAgree==1}">checked='checked'</c:if> />  
	      同意 <a href="#" id="shanfan" onclick="show('xieyi')">《第三方應用入駐協議提交》</a></td>
	    </tr>
	  <tr>
	    <td height="40" align="right">&nbsp;</td>
	    <td height="40"><p class="star" id="agreemsg" > <c:if test="${developer.isAgree!=1}">請查看協議，同意後方可提交！</c:if></p></td>
	    <td height="40">&nbsp;</td>
	  </tr>
	</table>
	</form>
	  </div>
 </div>
	
		<!-- footer -->
		<%@include file="/web/www/common/footer.jsp" %>
		
<div id="mask" style="display:none;"></div>
<div class="xieyi" id="xieyi" style="top:150px;position: absolute;display: none;z-index:9999;" >
  <div class="context_xieyi_title"><strong>第三方應用入駐協議</strong></div>
  <div class="context_xieyi">
   <p><strong>授權方：入駐者</strong>(以下簡稱為甲方)</p>                                   
   <p><strong>被授權方：</strong>TVpad公司 (以下簡稱為乙方)</p>

   <p>甲乙雙方經友好協商，本著互惠互利的原則，就協力廠商開發者應用入駐TVpad應用商店事宜達成如下合同條款，以資遵守：</p>
   <p><strong>一、前言</strong><br />

1.1 本協議適用於所有在啟創TVpad應用商店（以下簡稱“TVpad應用商店”）上發佈應用程式的協力廠商應用開發者/所有者（以下簡稱”甲方“），並在開發者/所有者與啟創科技（香港）有限公司 (以下簡稱“乙方”)之間簽訂生效。<br />
1.2 您通過点击”接受“按钮接受本協議，這意味著您已經仔細閱讀本協議的全部條款並充分瞭解可能存在的一切風險和責任，並且承諾承擔本協議下的全部責任和義務。
</p>
   <p><strong>二、甲方的權利與義務</strong><br />
2.1 任何擁有自然能力的甲方均有權向TVpad應用商店提交應用上架申請，符合條件的申請者通過乙方審核後即可獲得TVpad應用商店的收錄、上架及其他推廣支持服務。
<br />
2.2 甲方應保證其在申請上架資訊真實、準確、完整。如上述信息發生變化，甲方應及時變更個人信息。甲方對於申請入駐信息的不真實、不完整、不準確、未及時更新所導致的一切不利後果及損失，應承擔一切責任。
<br />
2.3 甲方應保證對其發佈的內容擁有合法的權利，不侵犯任何協力廠商的合法權利。同時，甲方應保證其發佈的作品不違反法律法規，不包含任何色情等非法信息，不存在盜取、破壞使用者資料及系統的隱藏內容。涉及應用程式本身的侵權與違法行為，一切責任由甲方承擔。 
<br />
2.4 如果協力廠商機構或個人對甲方所發佈內容的智慧財產權歸屬提出質疑或投訴，甲方有責任出具相關智慧財產權證明材料，並負責解決該類質疑或投訴。因甲方經由 TVpad應用商店上傳或發佈內容違反本協議或侵害其他人的任何權利導致任何第三人提出權利主張，甲方應單獨對該類第三人承擔全部責任，並同意賠償乙方及 其關聯公司、代理人或其他合作夥伴及員工，並使其免受損害。<br />
2.5 禁止甲方從事以下行為<br />
（1）上傳、張貼、發送電子郵件或傳送包含任何反對憲法所確定的基本原則、危害國家安全、洩露國家秘密、顛覆國家政權、破壞國家統一、破壞民族團結、損害 國家榮譽和利益、煽動民族仇恨、民族歧視、破壞民族團結、破壞國家宗教政策、宣揚邪教和封建迷信、淫穢、色情、賭博、暴力、兇殺、恐怖或者教唆犯罪、侮辱 或者誹謗他人，侵害他人合法權益的等法律、行政法規禁止的內容或其他侵犯公民人身權利的包括但不限於信息、資料、文字、視頻、音樂、照片、圖形或其他資料 （以下簡稱“內容”）。<br />
（2）以任何方式危害未成年人。<br />
（3）冒充他人或其他機構，或使用欺詐等非法手段謊稱或使人誤認為與他人或其他機構有關。<br />
（4）偽造標題或以其他方式操控識別資料，使人誤認為該內容為TVpad應用商店所傳送。<br />
（5）將無權傳送或者未經許可公開、發佈的內容（例如內部資料、機密資料、文檔）進行上傳、張貼、發送電子郵件或以其他方式公開傳送。<br />
（6）將侵犯他人智慧財產權之內容加以上傳、張貼、發送電子郵件或以其他方式公開傳送。<br />
（7）將廣告函件、促銷資料、“垃圾郵件”等，加以上傳、張貼、發送電子郵件或以其他方式公開傳送。供前述目的使用的專用區域除外。<br />
（8）將有關干擾、破壞或限制任何電腦軟件、硬件或通訊設備功能的軟件病毒或其他電腦代碼、檔案和程式之資料，加以上傳、張貼、發送電子郵件或以其他方式公開傳送。<br />
（9）干擾或破壞TVpad應用商店服務或與TVpad應用商店服務相連的伺服器和網絡，或不遵守TVpad應用商店使用之規定。<br />
（10）故意或非故意違反任何相關的中國法律、法規、規章、條例等其他具有法律效力的規範。
</p>
   <p><strong>三、乙方的權利與義務</strong><br />
3.1 甲方為乙方提供免費的應用程式收錄及上架服務，但不排除今後就所提供的服務及其他新增服務收取費用。費用的數額、比例以及其他收益分享模式會在本協議的更新版本中另行規定。<br />
3.2 乙方負責TVpad應用商店平臺建設與維護，並有權免費利用甲方上傳的作品（應用程式及應用內容）進行必要的市場宣傳和推廣。<br />
3.3 如遇任何第三方對甲方所發佈的應用程式提出智慧財產權質疑或投訴，乙方有權做出對涉及智慧財產權質疑或投訴的內容進行下架處理，直至該等質疑或投訴甲方已全部排除。 <br />
3.4 乙方有權在不洩露用戶隱私的前提下，收集、分析包括但不限於應用下載量、安裝量、打開量等統計資料。 
</p>
   <p>
<strong>四、智慧財產權</strong><br />
 甲方在TVpad應用商店發佈的作品（應用程式及相關內容）的所有權和智慧財產權歸屬於甲方或其合法權利人。作品如涉及到協力廠商的合法權益，甲方應在作 品發佈之前獲得相關權益人的授權。TVpad應用商店在審查批准上傳作品時，會預設甲方擁有其發佈作品的合法權益。如有任何第三人對甲方所發佈的作品提出 投訴，TVpad應用商店有權在收到該類投訴後，立即刪除該作品而無需事先取得甲方的同意。 </p>
   <p>
<strong>五、隱私保護</strong><br />
 尊重和保障入駐者個人隱私是TVpad應用商店的一項基本政策，TVpad應用商店不會公開、編輯或透露入駐者的註冊資料，除非以下任何一種情況：<br />
（1）事先獲得甲方的明確授權；<br />
（2）根據有關的法律法規要求；<br />
（3）按照相關政府主管部門的要求；<br />
（4）為維護社會公眾的利益；<br />
（5）為維護TVpad應用商店的合法權益；<br />
（6）其他需要公開、編輯或透露個人信息的情況。
</p>
   <p>
<strong>六、免責</strong> <br />
6.1 乙方TVpad應用商店根據甲方的需要提供相關平臺服務，TVpad應用商店本身不直接開發任何應用程式，所有應用均屬於協力廠商開發者/所有者。乙方對 於任何自本平臺而獲得的信息不保證其真實、準確、完整性並且不保證未侵犯協力廠商的合法權益。甲方在使用此類信息或資料時，應自行判斷其可能存在的風險， 乙方就一切可能造成的損失和不利後果不承擔任何責任。<br />
6.2 因不可抗力（包括但不限於火災、水災、地震、颱風、自然災害，以及政府法律、法規、命令的更改，運營商技術和通訊原因）導致本協議無法履行或未能達到本協 議約定的經營目標，不承擔責任。如發生不可抗力事件後，遭受該事件影響的一方應立即以可能的最快捷的方式通知對方，由雙方協商相應解決方案。 <br />
6.3 乙方會根據自身策略的調整，對甲方已經上架的應用程式進行刪除、移動位置或者其他的調整。對於此類情形下可能造成的風險，甲方應充分瞭解並同意自行承擔由此可能造成的一切不利後果和損失。</p>
   <p>
<strong>七、協議的解釋、變更與終止</strong> 
7.1 乙方有權在必要時修改入駐協議，協議一旦發生變動，將會在重要頁面上提示修改內容。如果不同意所改動的內容，入駐者可以投訴或者申請取消網絡服務，但是在 修正前必須執行現行的服務條款。如果入駐者在服務條款修改後繼續使用TVpad應用商店，則視為開發者接受乙方對本協議所做的一切修改。乙方保留隨時修改 或中斷服務而不需知會開發者的權利。乙方行使修改或中斷服務的權利，不需對開發者或協力廠商負責。<br />
7.2 本協議在根據下述規定終止前，將會一直有效。當下列情況出現時，乙方可以隨時中止與發佈者的協議：<br />
(1) 發佈者違反了本協議中的任何規定；<br />
(2) 法律法規要求終止本協議；<br />
(3) 乙方認為實際情形不再適宜繼續執行本協議。 </p>
   <p>
<strong>八、法律管轄</strong> <br />
本協議的訂立、執行和解釋及爭議的解決均應適用香港法律並受香港法院管轄。如雙方就本協議內容或其執行發生任何爭議，雙方應盡量友好協商解決；協商不成時，任何一方均可向香港法院提起訴訟。</p>
   <p><strong>九、其他</strong><br />
    9.1 本協議構成雙方對本協議之約定事項及其他相關事宜的完整協議，除本協議規定的之外，未賦予本協議各方其他權利。<br />
    9.2 本協議中的任何條款無論因何種原因完全或部分無效或不具有執行力，本協議的其餘條款仍應有效並且有約束力。 
   </p>
   
   <br /><br /><br />
   <!-- 英文协议部分 -->
   <p><strong>Third Party Application Publish Agreement</strong></p><br />
   <p><strong>License：</strong>Application Software Provider(s) (hereinafter referred to as "Party A"</p>                                   
   <p><strong>Licensee：</strong>Create New Technology (HK) Limited (hereinafter referred to as "Party B")</p>
   <p>After friendly negotiation with each other and based on mutual benefit principal, Party A and Party B has agreed the terms and conditions as follows:</p>
   <br />
   <p><strong>1.	Purpose</strong><br />
1.1	This Agreement is applicable to Party A which has published application softwares for users 	to download in TVpad Application Store and this Agreement is binding upon between Party A and Party B.<br />
1.2	By clicking the button “Accept”, Party A has agreed the terms and conditions of this 		Agreement , which means that Party A has clearly read all the clauses of this Agreement and has sufficiently understood all the potential risks and obligations and Party A has agreed to be 	responsible for all the obligations arising up in relation to the application softwares provided by Party A.
</p>

<br />
   <p><strong>2.	Rights and Obligations of Party A</strong><br />
2.1	Any application software provider is entitled to submit application software publish application with Party B.  Upon successfully reviewed by Party B, Party A is entitle to obtain from Party B the supporting services relating to the collection, publishing and promotion of the relative applications.
<br />
2.2	Party A shall ensure that all the information shown on the relative applications is true, accurate and complete.  In case any of the said information has been changed, Party A shall immediately inform Party B of those changes.  Party A shall be responsible for all the loss and damages caused by the untrue, inaccurate and incomplete of the information provided by Party A.
<br />
2.3	Party A shall ensure that Party A has all the rights to the contents shown on the application software and it will not infringe any legal rights of any third party.  Party A further guarantee that all the contents shown on its application software will not violate laws and regulations, include any pornographic contents and will not steal and damage any 	information of users and the contents hidden in the system.  Party A shall be solely responsible for all the infringement and other illegal conduct. 
<br />
2.4	In case that any third party has filed complaint to Party B that the contents shown on the relative application software have infringed their intellectual property rights, Party A shall immediately supply all the evidence proving that it has the relative intellectual property rights and shall be solely responsible for settling such complaints.  Party A shall solely bear all the responsibilities claimed by any third party due to the infringement of that party’s rights in 	relation to the contents shown on the relative applications.  Party A shall compensate all the 	loss and damages of Party B, Party B’s associate companies, agents, other partners and 	employees arising up to such claims made by such third party.<br />
2.5	Party A shall not conduct the followings:<br />
2.5.1	Uploading, posting, sending emails or transmitting any contents which violate the Constitution principles, endangering national security, disclosing national secrets, subversion of state power, undermine national unification, undermine national solidarity, harm national honor and interest, incite the hatred of nationalities and national 	discrimination, undermine national religious policy, disseminating obscenity , gambling , violence , or instigating others to commit crimes, insulting or slandering others, infringing other people’s legal rights. (hereinafter referred to as “Contents”).<br />
2.5.2	endangering minors in any ways.<br />
2.5.3	impost to be others or other organizations, or deceive the public in any ways to make them believe that Party A has connected with such persons or organizations.<br />
2.5.4	causing the public confusion of believing that the contents shown on the relative applications are transmitted by Party B.<br />
2.5.5	uploading, posting, sending emails or transmitting in any ways the contents without authorization.<br />
2.5.6	uploading, posting, sending emails or transmitting in any ways the contents which have 	infringed other intellectual property rights.<br />
2.5.7	uploading, posting, sending emails or transmitting in any ways any advertisements, promotion materials or other junk mails.<br />
2.5.8	uploading, posting, sending emails or transmitting in any ways computer virus, computer codes, files or documentation which will disturb, damage or limit the use of any computer software, hardware or other functions of the apparatus.<br />
2.5.9	disturbing or damaging the service of TVpad Application Store or the server or internet connecting with TVpad Application Store.<br />
2.5.10	violating China laws and regulations deliberately or accidently.
</p>
<br />
   <p><strong>3.	Rights and Obligations of Party B</strong><br />
3.1	Party B agrees to offer the collection and uploading of the application software into TVpad Application Store free of charge, nevertheless, Party B reserve the rights to charge Party A for the related services and other added value services.  The details of the service fees will be reflected in Party B’s upgrading version of this Agreement.<br />
3.2	Party B shall be responsible for the construction and maintenance of the platform of TVpad 	Application Store and Party B is entitled to use the contents provided by Party A to promote Party B’s products.<br />
3.3	Party B is entitled to remove the application provided Party A if Party B has received any 	complaints against Party A that the contents shown on the relative application has infringed any third party’s intellectual property rights until Party A has settled such complaints completely.<br />
3.4	Party B is entitled to collect and analyze the data of downloading, installing and using of such application software provided that such collection and analysis will not disclose users’ privacy.。 
</p>
<br />
   <p>
<strong>4.	Intellectual Property Rights</strong><br />
 Party A guarantees that Party A is the owner or rights holder of the intellectual property rights for all the contents (including the application software and the contents) shown on the application software.  If any part of the application software including the contents shown on the application software is related to any third party, Party A shall obtain the required authorization from such third party before publish on the relative application software.  If Party B receives any complaints from third party, Party B is entitled to delete such contents without prior contents form Party A.</p>
 <br />
   <p>
<strong>5.	Protection of Privacy</strong><br />
 Respecting and protecting the users’ privacy is the base of TVpad Application Store.  TVpad Application Store will not publish, collect or disclose the basic information of Party A unless under any of the following circumstances:<br />
5.1	Obtaining the prior authorization from Party A; <br />
5.2	Disclosing according to the requirement of the relative laws and regulations; <br />
5.3	Disclosing under the request of the relative government authorities; <br />
5.4	Protecting the public interest; <br />
5.5	Protecting the legal rights of TVpad Application Store; <br />
5.6	Other circumstances.
</p>
<br />
   <p>
<strong>6.	Exemption</strong> <br />
6.1	Party B provides relative platform services under the request of Party A.  Party B does not 	develop any application software.  All the application software belong to the relative 	developers and owners.  Party B does not ensure that the information obtained from Party 	B’s platform is true, accurate and complete and Party B does not guarantee that such 	information will not infringe third party’s legal rights.  Party A shall estimate the potential 	risks when using such information or documentation and Party B shall not be responsible for 	any loss or damages arising from the use of such information or documentation.<br />
6.2 Both parties shall not be responsible for any loss or damages caused by force majeour.  Any 	Party influenced by such events shall inform the other Party immediately and negotiate the 	solutions.<br />
6.3	Party B may delete, move or make other adjustment to Party A’s application software at Party 	B’s will without prior contents from Party A.  Party A shall understand and agree to solely 	bear all the related loss and damages.</p>

<br />
   <p>
<strong>7.	Interpretation, modification and termination of this Agreement</strong> <br />
7.1	Party B is entitled to amend this Agreement from time to time.  Once this Agreement has 	been amended, Party B will publish the amendment on its official website.  If Party A does 	not agree any part of the amendment, Party A is entitled to object such amendments or 	terminate cooperation prior that Party A shall be bonded by this Agreement before 	termination.  If Party A continues provide application software in the TVpad Application 	Store, it shall be deemed that Party A has accepted all the amendment of this Agreement.  	Party B reserves the rights to amend or terminate of this Agreement without prior informing 	Party A .  Party B shall not be responsible for all the loss or damages of Party A .<br />
7.2	This Agreement will bind upon both Parties before termination.  Party B is entitled to 	terminate this Agreement under any of the following circumstances:<br />
7.2.1	Party A breaches any of the clauses of this Agreement;<br />
7.2.2	Under the relative laws and regulations;<br />
7.2.3	Part B believes that the circumstances are not benefit for implement of this Agreement. </p>
   
   <br />
   <p>
<strong>8	Jurisdiction</strong> <br />
The execution, implementation and interpretation of this Agreement and the resolution of any dispute arising from this Agreement shall be governed by the laws of Hong Kong and the dispute shall be submitted to Hong Kong court for sole jurisdiction.  If there is any dispute regarding the contents or the implementation of this Agreement between the Parties, both Parties shall negotiate friendly, if no settlement achieved, the dispute shall be submitted to Chinese courts for rsole jurisdiction.</p>

 <br />
   <p><strong>9	Miscellaneous</strong><br />
    9.1	This Agreement constitutes entire agreement between the Parties.  Unless otherwise agreed, Any Party does not grant any rights to the other Party.<br />
    9.2	If any part of this Agreement has been declared to be void or unenforceable, the remaining part of this Agreement shall remain void and binding. 
   </p>   
</div>
<div class="context_bottom">
  <div class="bt_bottom">
    <a href="#" class="Agree" onclick="agreement(1)">同意</a>
    <a href="${storeDomain}/web/developer!developerIndex.action" >不同意</a>
  </div>
</div>
</div>
	</body>
</html>