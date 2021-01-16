/**
*  컨설팅 표준화 작업
*  @FileName 	Popup.js 
*  @Creator 	soojeong
*  @CreateDate 	2017.03.08
*  @Desction   		
************** 소스 수정 이력 ***************************************************
*  date          		Modifier                Description
*******************************************************************************
*  2017.03.08     	soojeong 	           최초 생성 
*  2017.10.17     	soojeong  	           주석 정비
*  2020.01.16       ywkim                  팝업닫기/팝업 콜백함수 추가
*******************************************************************************
*/

var pForm = nexacro.Form.prototype;

/**
 * @class 팝업오픈
 * @param {String} sPopupId	- 팝업ID
 * @param {String} sUrl	 - 팝업URL
 * @param {String} [oArg] - 전달값
 * @param {String} [sPopupCallback] - 팝업콜백
 * @param {Object} [oOption] - 팝업옵션 <br>
 *	oOption.top : 상단 좌표 <br>
 *	oOption.left : 좌측 좌표 <br>
 *	oOption.width : 넓이 <br>
 *	oOption.height : 높이 <br>
 *	oOption.popuptype : 팝업종류(modal:showModal, modeless:application.open) <br>
 *	oOption.layered : 투명 윈도우 <br>
 *	oOption.opacity : 투명도 <br>
 *	oOption.autosize : autosize <br>
 * @return N/A
 * @example
 * this.gfnOpenPopup(this);
 */
pForm.gfnOpenPopup = function ( sPopupId, sUrl, oArg, sPopupCallback, oOption)
{
    var objApp = nexacro.getApplication();
	var nLeft = -1;
	var nTop = -1;
	var nWidth = -1;
	var nHeight = -1;
	var bShowTitle = false;	
	var bShowStatus = false;	
	var sPopupType = "modal";
	var bLayered = false;
	var nOpacity = 100;
	var bAutoSize = false;
	var bResizable = false;
	var sTitleText = "";

	oArg = oArg || {}; // popup id 설정
	oArg[popupId] = sPopupId;
	
	for (var key in oOption) {
       if (oOption.hasOwnProperty(key)) {
            switch (key) 
			{
				case "top":				
					nTop = parseInt(oOption[key]);
					break;
				case "left":
					nLeft = parseInt(oOption[key]);
					break;
				case "width":
					nWidth = parseInt(oOption[key]);
					break;
				case "height":
					nHeight = parseInt(oOption[key]);
					break;
				case "popuptype":
					sPopupType = oOption[key];
					break;
				case "layered":
					bLayered = oOption[key];
					break;
				case "opacity":
					nOpacity =oOption[key];
					break;
				case "autosize":
					bAutoSize = oOption[key];
					break;
			}	
        }
    }

	var sOpenalign = "";
	if(nLeft == -1 && nTop == -1) 
	{		
		sOpenalign = "center middle";
        nLeft   =  (objApp.mainframe.width / 2) - Math.round(nWidth / 2);
	    nTop    = (objApp.mainframe.height / 2) - Math.round(nHeight / 2) ;		
	}else{
		nLeft  =  this.getOffsetLeft() + nLeft;
		nTop   =  this.getOffsetTop() + nTop;
	}
		
	if(nWidth == -1 || nHeight == -1)
	{
	    bAutoSize = true;
	}
	
	var oParentFrame = this.getOwnerFrame();
	oParentFrame.popup[sPopupId] = {"popupType" : sPopupType, "callback" : sPopupCallback};

    if(sPopupType == "modeless")
    {
         var sOpenStyle= "showtitlebar=true showstatusbar=false resizable=true autosize=true";
		
         nexacro.open(sPopupId, sUrl, oParentFrame, oArg, sOpenStyle, nLeft, nTop, nWidth, nHeight, this);
    }
    else
    {
		newChild = new nexacro.ChildFrame;
		newChild.init(sPopupId, nLeft, nTop, nWidth, nHeight, null, null, sUrl);
		
		newChild.set_dragmovetype("all");
		newChild.set_showtitlebar(bShowTitle);    //titlebar는 안보임
		newChild.set_autosize(bAutoSize);	
		newChild.set_resizable(bResizable);    //resizable 안됨
		if(!this.gfnIsNull(sTitleText)) newChild.set_titletext(sTitleText);
		newChild.set_showstatusbar(bShowStatus);    //statusbar는 안보임
		newChild.set_openalign(sOpenalign);
		newChild.set_layered(bLayered);
		
		newChild.showModal(oParentFrame, oArg, this, this.gfnPopupCallback);
    }
};

/**
 * @class 팝업닫기
 * @param rtnVal - 팝업반환값
 * @example
 * this.gfnOpenPopup(this);
 */
pForm.gfnClosePopup = function(sPopupId, oRtnVal) {

	// modeless 팝업일때 부모창의 callBack 함수 실행
	if (this.opener) {

		var oOpener = this.opener.getOwnerFrame();
		var sPopupType = oOpener[sPopupId].popupType;
		
		// 팝업이 modeless 일때
		if (sPopupType == "modeless") {

			var sCallBack = oOpener[sPopupId].callback;

			// callBack 함수가 있을 때
			if (this.gfnIsNull(sCallBack) == false) {	
			
				// callback 함수object로 파라미터 전달시 바로 호출
				if (typeof(sCallBack) == "function") {
					sCallBack.call(this.opener, sPopupId, oRtnVal);
				}
				else {
					this.opener.lookupFunc(sCallBack).call(sPopupId, oRtnVal);
				}
			}
		} else {

			oOpener[sPopupId].rtnVal = oRtnVal;
		}
	}
	
	// 팝업창 닫기
	this.close(sPopupId);
};

/**
 * @class  팝업콜백
 * @param rtnVal - 팝업반환값
 * @example
 * this.gfnPopupCallback(sPopupId);
 */
 pForm.gfnPopupCallback = function(sPopupId) {
 
	var oOwnerFrame = this.getOwnerFrame();
	var oCallback = ownerFrame[sPopupId].callback;
	var oRtnVal   = ownerFrame[sPopupId].rtnVal;

	if (!this.gfnIsNull(oCallback)) {
		if (typeof(oCallback) == "function") {
			sCallBack.call(this.opener, sPopupId, oRtnVal);
		}
		else {
			this.opener.lookupFunc(oCallback).call(sPopupId, oRtnVal);
		}
	}
 };