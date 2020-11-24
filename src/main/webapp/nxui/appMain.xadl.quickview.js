(function()
{
    return function()
    {
        this.on_loadAppVariables = function()
        {		
            var obj = null;
            
			// global dataobject
		
            // global dataset
            obj = new Dataset("gdsUser", this);
            obj._setContents("");
            this._addDataset(obj.name, obj);


            obj = new Dataset("gdsMenu", this);
            obj._setContents("");
            this._addDataset(obj.name, obj);
            
            // global variable

            
            obj = null;
        };
        
        // property, event, createMainFrame
        this.on_initApplication = function()
        {
            // properties
            this.set_id("appMain");
            this.set_screenid("screenMain");

            if (this._is_attach_childframe)
            	return;
            
            // frame
            var mainframe = this.createMainFrame("mainframe","0","0","1280","720",null,null,this);
            mainframe.set_showtitlebar("true");
            mainframe.set_showstatusbar("true");
            mainframe.set_titletext("NexacroFramework");
            mainframe.on_createBodyFrame = this.mainframe_createBodyFrame;
            // tray

        };
        
        this.loadPreloadList = function()
        {

        };
        
        this.mainframe_createBodyFrame = function()
        {
            var obj = new ChildFrame("QuickViewFrame", null, null, null, null, null, null, "", this);
            
            obj.set_showtitlebar("false");
            obj.set_showstatusbar("false");
            obj.set_border("0px none");
			
            this.addChild(obj.name, obj);
            obj.set_formurl(nexacro._quickview_formurl);
            this.frame = obj;
            
            obj = null;
        };
        
        this.on_initEvent = function()
        {
        };
		// script Compiler
        this.registerScript("appMain.xadl", function() {
        /**
        *  Application Main
        *
        *  @Creator 	공통팀
        *  @CreateDate 	2020.11.20
        *  @Desction    스크립트 표준 및 주석 표준 정의
        ************** 소스 수정 이력 ***********************************************
        *  date          		Modifier                Description
        *******************************************************************************
        *  2020.11.20     	    공통팀 	                 최초 생성
        *******************************************************************************

        /************************************************************************************************
         * Application 변수 선언 영역
         ************************************************************************************************/
        /* frame 정의 */
        this.gvVFrameSet  = "";
        this.gvTopFrame   = "";
        this.gvLoginFrame = "";
        this.gvHFrameSet  = "";
        this.gvLeftFrame  = "";
        this.gvVFrameSet  = "";
        this.gvWorkFrame  = "";
        this.gvMainFrame  = "";
        this.gvTabFrame   = "";

        /***********************************************************************************************
         * Application 사용자 정의 함수선언 영역
        /***********************************************************************************************/
        /**
         * 운영에서는 TRACE를 하지 않도록 설정
         **/
        this.afnSetTraceMode = function(useTrace)
        {
            if (!useTrace)
            {
                trace = function trace(){};
            }
        }

        /***********************************************************************************************
         * Application EVENT 영역
        /***********************************************************************************************/
        /**
         * Application load 시점이벤트 처리
         * 1. frameset 접근 변수 정의
         * 2. runmode  설정 및 관련 처리
         **/
        this.Application_onload = function(obj,e)
        {
        	var objApp = nexacro.getApplication();

        	// Frame 변수 저장
        	this.gvVFrameSet	= objApp.mainframe.VFrameSet;                                        // VFrameSet
        	this.gvLoginFrame	= objApp.mainframe.VFrameSet.frameLogin;                             // LoginFrame
        	this.gvHFrameSet    = objApp.mainframe.VFrameSet.HFrameSet;                              // HFrame
        	this.gvLeftFrame    = objApp.mainframe.VFrameSet.HFrameSet.frameLeft;                    // leftFrame
        	this.gvVFrameSet1   = objApp.mainframe.VFrameSet.HFrameSet.VFrameSet1;                   // VFrameSet1
        	this.gvTopFrame     = objApp.mainframe.VFrameSet.frameTop;                               // TopFrame
        	this.gvWorkFrame    = objApp.mainframe.VFrameSet.HFrameSet.VFrameSet1.framesetWork;      // workForm
        	this.gvMainFrame    = objApp.mainframe.VFrameSet.HFrameSet.VFrameSet1.frameMain;         // mainForm
        	this.gvTabFrame     = objApp.mainframe.VFrameSet.HFrameSet.VFrameSet1.frameTab;          // TabFrame

        	// runmode 설정 관련 처리
        	var runMode = this.gfnIsEmpty(nexacro.getEnvironmentVariable("gvRunMode")) ? "local" : nexacro.getEnvironmentVariable("gvRunMode");

        	var title = "";

        	if(runMode == "local") {
        		title = "로컬 - " + objApp.mainframe.titletext;
        	} else if(runMode == "dev") {
        		title = "개발 - " + objApp.mainframe.titletext;
        	} else if(runMode == "real") {
        		this.afnSetTraceMode(false);
        	}
        	objApp.mainframe.set_titletext(title);

        	// 로그인 화면 이동
        	this.gfnGoLoginPage();
        };

        /**
         * Application error 시점이벤트 처리
         **/
        this.Application_onerror = function(obj,e)
        {

        };
        });
        
        this.loadPreloadList();

    };
}
)();
